import { useState, useEffect } from 'react';

const API_URL = 'http://localhost:8081/carrito';

export function useCarrito() {
  const [carrito, setCarrito] = useState({
    items: [],
    total: 0,
    totalItems: 0
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  // Obtener carrito
  const fetchCarrito = async () => {
    try {
      setLoading(true);
      const response = await fetch(API_URL);
      if (!response.ok) throw new Error(`Error: ${response.status}`);
      const data = await response.json();
      setCarrito(data);
      setError(null);
    } catch (err) {
      console.error('Error fetching carrito:', err);
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  // Agregar producto al carrito
  const agregarProducto = async (productoId, cantidad = 1) => {
    try {
      setLoading(true);
      const response = await fetch(`${API_URL}/agregar`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ productoId, cantidad })
      });
      if (!response.ok) throw new Error(`Error: ${response.status}`);
      const data = await response.json();
      setCarrito(data);
      setError(null);
      return data;
    } catch (err) {
      console.error('Error agregando producto:', err);
      setError(err.message);
      throw err;
    } finally {
      setLoading(false);
    }
  };

  // Actualizar cantidad de un producto
  const actualizarCantidad = async (productoId, cantidad) => {
    try {
      setLoading(true);
      const response = await fetch(`${API_URL}/actualizar`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ productoId, cantidad })
      });
      if (!response.ok) throw new Error(`Error: ${response.status}`);
      const data = await response.json();
      setCarrito(data);
      setError(null);
      return data;
    } catch (err) {
      console.error('Error actualizando cantidad:', err);
      setError(err.message);
      throw err;
    } finally {
      setLoading(false);
    }
  };

  // Remover producto del carrito
  const removerProducto = async (productoId) => {
    try {
      setLoading(true);
      const response = await fetch(`${API_URL}/remover/${productoId}`, {
        method: 'DELETE'
      });
      if (!response.ok) throw new Error(`Error: ${response.status}`);
      const data = await response.json();
      setCarrito(data);
      setError(null);
      return data;
    } catch (err) {
      console.error('Error removiendo producto:', err);
      setError(err.message);
      throw err;
    } finally {
      setLoading(false);
    }
  };

  // Limpiar carrito
  const limpiarCarrito = async () => {
    try {
      setLoading(true);
      const response = await fetch(`${API_URL}/limpiar`, {
        method: 'DELETE'
      });
      if (!response.ok) throw new Error(`Error: ${response.status}`);
      setCarrito({ items: [], total: 0, totalItems: 0 });
      setError(null);
    } catch (err) {
      console.error('Error limpiando carrito:', err);
      setError(err.message);
      throw err;
    } finally {
      setLoading(false);
    }
  };

  // Cargar carrito al montar el componente
  useEffect(() => {
    fetchCarrito();
  }, []);

  return {
    carrito,
    loading,
    error,
    agregarProducto,
    actualizarCantidad,
    removerProducto,
    limpiarCarrito,
    refrescarCarrito: fetchCarrito
  };
}
