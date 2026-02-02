import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Card from "../components/Card";
import { CartIcon, CookieIcon, CoffeeIcon, JuiceIcon, CandyIcon } from "../icons";
import { useCarrito } from "../hooks/useCarrito";

const API_URL = "http://localhost:8081/producto";

export default function Products() {
  const navigate = useNavigate();
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [selectedProducts, setSelectedProducts] = useState(new Map()); // Map<productoId, cantidad>

  const { carrito, agregarProducto, refrescarCarrito } = useCarrito();

  useEffect(() => {
    fetchProducts();
  }, []);

  async function fetchProducts() {
    try {
      setLoading(true);
      const response = await fetch(API_URL);

      if (!response.ok) {
        throw new Error(`Error: ${response.status}`);
      }

      const data = await response.json();

      // Mapear los productos de la BD al formato del frontend
      const mappedProducts = data.map(product => ({
        id: product.id,
        title: product.nombre,
        price: product.precio,
        qty: product.cantidadDisponible,
        icon: CartIcon, // Puedes agregar lógica para asignar iconos según el tipo
      }));

      setProducts(mappedProducts);
      setError(null);
    } catch (err) {
      console.error("Error fetching products:", err);
      setError(err.message);
    } finally {
      setLoading(false);
    }
  }

  function incrementarCantidad(productId) {
    setSelectedProducts((prev) => {
      const next = new Map(prev);
      const currentQty = next.get(productId) || 0;
      const product = products.find(p => p.id === productId);

      if (currentQty < product.qty) {
        next.set(productId, currentQty + 1);
      }
      return next;
    });
  }

  function decrementarCantidad(productId) {
    setSelectedProducts((prev) => {
      const next = new Map(prev);
      const currentQty = next.get(productId) || 0;

      if (currentQty > 0) {
        next.set(productId, currentQty - 1);
      }
      if (next.get(productId) === 0) {
        next.delete(productId);
      }
      return next;
    });
  }

  async function handleAgregarAlCarrito() {
    try {
      for (const [productoId, cantidad] of selectedProducts.entries()) {
        if (cantidad > 0) {
          await agregarProducto(productoId, cantidad);
        }
      }
      // Limpiar selección después de agregar
      setSelectedProducts(new Map());
      // Refrescar el carrito
      await refrescarCarrito();
    } catch (err) {
      console.error("Error al agregar productos al carrito:", err);
    }
  }

  function irAlCarrito() {
    navigate('/checkout');
  }

  // Al seleccionar cards
  function handleCardClick(product) {
    incrementarCantidad(product.id);
  }

  function handleRemoveSelected() {
    setSelectedProducts(new Map());
  }

  const totalSeleccionado = Array.from(selectedProducts.entries()).reduce((sum, [productId, cantidad]) => {
    const product = products.find(p => p.id === productId);
    return sum + (product ? product.price * cantidad : 0);
  }, 0);

  if (loading) {
    return (
      <div className="container mx-auto p-4">
        <p className="text-center text-gray-500">Cargando productos...</p>
      </div>
    );
  }

  if (error) {
    return (
      <div className="container mx-auto p-4">
        <p className="text-center text-red-500">Error al cargar productos: {error}</p>
        <button
          onClick={fetchProducts}
          className="mt-4 mx-auto block px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600"
        >
          Reintentar
        </button>
      </div>
    );
  }

  return (
    <div className="container mx-auto p-4">
      <div className="flex items-center justify-between mb-4">
        <h1 className="text-2xl font-semibold">Productos</h1>

        <div className="flex items-center gap-4">
          {selectedProducts.size > 0 && (
            <div className="flex items-center gap-2 bg-blue-100 px-4 py-2 rounded">
              <span className="font-semibold">Total: ${totalSeleccionado.toFixed(2)}</span>
              <button
                onClick={handleAgregarAlCarrito}
                className="ml-2 px-3 py-1 bg-blue-500 text-white rounded hover:bg-blue-600"
              >
                Agregar al Carrito
              </button>
                      <button
                        onClick={handleRemoveSelected}
                        className="px-3 py-1 bg-red-500 text-white rounded hover:bg-red-600"
                      >
                        Limpiar
                      </button>
                    </div>
                  )}
        
                  <div
                    className="bg-gray-100 px-4 py-2 rounded cursor-pointer hover:bg-gray-200"
                    onClick={irAlCarrito}
                  >
                    <span className="font-semibold">🛒 Ver Carrito</span>
                  </div>
        
                  <div className="bg-gray-100 px-4 py-2 rounded">
                    <span className="font-semibold">Carrito: {carrito.totalItems} items - ${carrito.total.toFixed(2)}</span>
                  </div>
                </div>
              </div>
        
        {products.length === 0 ? (
          <p className="text-center text-gray-500">No hay productos disponibles</p>
        ) : (
          <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
            {products.map((p) => {
              const cantidadSeleccionada = selectedProducts.get(p.id) || 0;
              return (
                <div key={p.id} className="relative">
                  <Card
                    icon={p.icon}
                    title={p.title}
                    price={p.price}
                    qty={p.qty}
                    selected={cantidadSeleccionada > 0}
                    onClick={() => handleCardClick(p)}
                  >
                  </Card>
  
                  {cantidadSeleccionada > 0 && (
                    <div className="absolute top-2 right-2 flex items-center gap-2 bg-white px-3 py-1 rounded shadow-lg">
                      <button
                        onClick={(e) => {
                          e.stopPropagation();
                          decrementarCantidad(p.id);
                        }}
                        className="text-xl font-bold text-red-500 hover:text-red-700"
                      >
                        -
                      </button>
                      <span className="font-bold text-lg">{cantidadSeleccionada}</span>
                      <button
                        onClick={(e) => {
                          e.stopPropagation();
                          incrementarCantidad(p.id);
                        }}
                        className="text-xl font-bold text-green-500 hover:text-green-700"
                      >
                        +
                      </button>
                    </div>
                  )}
                </div>
              );
            })}
          </div>
        )}
      </div>
    );
  }

