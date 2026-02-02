import { useState, useEffect } from "react";

const API_URL = 'http://localhost:8081/pago';

const Payment = ({ total = 0, onPagoExitoso }) => {
  const [denominaciones, setDenominaciones] = useState([]);
  const [denominacionesInsertadas, setDenominacionesInsertadas] = useState({});

  const [pagado, setPagado] = useState(0);
  const [piezasInsertadas, setPiezasInsertadas] = useState(0);
  const [cambio, setCambio] = useState(0);
  const [cambioDetallado, setCambioDetallado] = useState([]);
  const [piezasCambio, setPiezasCambio] = useState(0);
  const [mensaje, setMensaje] = useState('');
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    fetchDenominaciones();
  }, []);

  useEffect(() => {
    // Calcular pagado y piezas
    const totalPagado = Object.entries(denominacionesInsertadas).reduce((sum, [id, cant]) => {
      const denom = denominaciones.find(d => d.id === parseInt(id));
      return sum + (denom ? denom.valor * cant : 0);
    }, 0);

    const totalPiezas = Object.values(denominacionesInsertadas).reduce((sum, cant) => sum + cant, 0);

    setPagado(totalPagado);
    setPiezasInsertadas(totalPiezas);
    setCambio(totalPagado - total);
  }, [denominacionesInsertadas, denominaciones, total]);

  async function fetchDenominaciones() {
    try {
      const response = await fetch(`${API_URL}/denominaciones`);
      if (!response.ok) throw new Error('Error al cargar denominaciones');
      const data = await response.json();
      setDenominaciones(data);
    } catch (error) {
      console.error('Error:', error);
      setMensaje('Error al cargar denominaciones');
    }
  }

  function insertarDenominacion(denominacionId) {
    setDenominacionesInsertadas(prev => ({
      ...prev,
      [denominacionId]: (prev[denominacionId] || 0) + 1
    }));
  }

  function limpiar() {
    setDenominacionesInsertadas({});
    setPagado(0);
    setPiezasInsertadas(0);
    setCambio(0);
    setCambioDetallado([]);
    setPiezasCambio(0);
    setMensaje('');
  }

  async function procesarPago() {
    if (pagado < total) {
      setMensaje('Monto insuficiente');
      return;
    }

    try {
      setLoading(true);
      setMensaje('');

      const response = await fetch(`${API_URL}/procesar`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ denominacionesInsertadas })
      });

      if (!response.ok) throw new Error('Error al procesar pago');

      const resultado = await response.json();

      if (resultado.exitoso) {
        setCambioDetallado(resultado.cambioDetallado);
        const totalPiezasCambio = resultado.cambioDetallado.reduce((sum, d) => sum + d.cantidad, 0);
        setPiezasCambio(totalPiezasCambio);
        setMensaje('Pago procesado exitosamente');

        // Notificar al componente padre
        if (onPagoExitoso) {
          setTimeout(() => {
            onPagoExitoso(resultado);
            limpiar();
          }, 3000);
        }
      } else {
        setMensaje(resultado.mensaje);
      }
    } catch (error) {
      console.error('Error:', error);
      setMensaje('Error al procesar el pago');
    } finally {
      setLoading(false);
    }
  }

  function handleCleanSelectedProducts() {
    console.log("Productos seleccionados limpiados");
  }

  function handlePay() {
    console.log("Pago realizado");
  }

  return (
    <div className="card card-border bg-base-300 w-96">
      <div className="card-body flex flex-col gap-6">
        <div>
          <span className="text-xs">Total: </span>
          <h2 className="card-title">${total.toFixed(2)}</h2>
        </div>

        <div className="bg-info text-info-content p-2 rounded-lg">
          <p className="mb-2">Insertar pago: </p>
          <div className="flex flex-wrap gap-1">
            {denominaciones.map((denom) => (
              <button
                key={denom.id}
                onClick={() => insertarDenominacion(denom.id)}
                className="btn btn-sm btn-outline"
                disabled={loading}
              >
                ${denom.valor.toFixed(2)}
              </button>
            ))}
          </div>
        </div>

        <div className="flex flex-col gap-2 bg-secondary text-secondary-content p-2 rounded-lg">
          <label>Pagado: ${pagado.toFixed(2)}</label>
          <label>Piezas insertadas: {piezasInsertadas}</label>
          <label>Cambio: ${cambio.toFixed(2)}</label>
        </div>

        {cambioDetallado.length > 0 && (
          <div className="flex flex-col gap-2 bg-accent text-accent-content p-2 rounded-lg">
            <label className="font-bold">A devolver:</label>
            {cambioDetallado.map((dc, idx) => (
              <label key={idx}>
                {dc.cantidad}x ${dc.valor.toFixed(2)} = ${(dc.valor * dc.cantidad).toFixed(2)}
              </label>
            ))}
            <label className="font-bold">Total piezas: {piezasCambio}</label>
          </div>
        )}

        {mensaje && (
          <div className={`alert ${mensaje.includes('Error') || mensaje.includes('insuficiente') ? 'alert-error' : 'alert-success'}`}>
            {mensaje}
          </div>
        )}

        <div className="card-actions justify-between">
          <button
            className="btn btn-error"
            onClick={limpiar}
            disabled={loading}
          >
            Limpiar
          </button>
          <button
            className="btn btn-primary"
            onClick={procesarPago}
            disabled={loading || pagado < total}
          >
            {loading ? 'Procesando...' : 'Pagar'}
          </button>
        </div>
      </div>
    </div>
  );
};

export default Payment;
