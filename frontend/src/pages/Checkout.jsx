import React, { useState } from "react";
import { useCarrito } from "../hooks/useCarrito";
import Payment from "../components/Payment";
import { useNavigate } from "react-router-dom";

export default function Checkout() {
  const { carrito, removerProducto, limpiarCarrito } = useCarrito();
  const navigate = useNavigate();
  const [mostrarPago, setMostrarPago] = useState(false);

  function handlePagoExitoso(resultado) {
    alert(`Pago exitoso! Cambio: $${resultado.cambio.toFixed(2)}`);
    navigate('/productos');
  }

  if (carrito.items.length === 0) {
    return (
      <div className="container mx-auto p-4">
        <h1 className="text-2xl font-semibold mb-4">Carrito de Compras</h1>
        <p className="text-center text-gray-500">El carrito está vacío</p>
        <button
          onClick={() => navigate('/productos')}
          className="mt-4 mx-auto block px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600"
        >
          Ir a Productos
        </button>
      </div>
    );
  }

  return (
    <div className="container mx-auto p-4">
      <h1 className="text-2xl font-semibold mb-4">Carrito de Compras</h1>

      <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
        {/* Lista de productos */}
        <div className="space-y-4">
          {carrito.items.map((item) => (
            <div key={item.productoId} className="card bg-base-100 shadow-sm">
              <div className="card-body">
                <div className="flex justify-between items-center">
                  <div>
                    <h3 className="font-semibold">{item.nombreProducto}</h3>
                    <p className="text-sm text-gray-600">
                      ${item.precioUnitario.toFixed(2)} x {item.cantidad}
                    </p>
                  </div>
                  <div className="text-right">
                    <p className="font-bold">${item.subtotal.toFixed(2)}</p>
                    <button
                      onClick={() => removerProducto(item.productoId)}
                      className="btn btn-sm btn-error mt-2"
                    >
                      Eliminar
                    </button>
                  </div>
                </div>
              </div>
            </div>
          ))}

          <div className="card bg-primary text-primary-content">
            <div className="card-body">
              <div className="flex justify-between items-center">
                <span className="text-xl font-bold">Total:</span>
                <span className="text-2xl font-bold">${carrito.total.toFixed(2)}</span>
              </div>
            </div>
          </div>

          <div className="flex gap-4">
            <button
              onClick={() => limpiarCarrito()}
              className="btn btn-error flex-1"
            >
              Vaciar Carrito
            </button>
            <button
              onClick={() => setMostrarPago(true)}
              className="btn btn-success flex-1"
            >
              Proceder al Pago
            </button>
          </div>
        </div>

        {/* Componente de pago */}
        <div className="flex justify-center">
          {mostrarPago ? (
            <Payment
              total={carrito.total}
              onPagoExitoso={handlePagoExitoso}
            />
          ) : (
            <div className="card bg-base-200 w-96 h-fit">
              <div className="card-body items-center text-center">
                <h2 className="card-title">Resumen</h2>
                <p>Items: {carrito.totalItems}</p>
                <p className="text-2xl font-bold">Total: ${carrito.total.toFixed(2)}</p>
                <p className="text-sm text-gray-600 mt-4">
                  Haz clic en "Proceder al Pago" para continuar
                </p>
              </div>
            </div>
          )}
        </div>
      </div>
    </div>
  );
}
