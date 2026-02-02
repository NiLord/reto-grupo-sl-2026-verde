import { useState, useEffect } from "react";

const API_URL = 'http://localhost:8081/pago';

const Piezas = () => {
  const [denominaciones, setDenominaciones] = useState([]);

  useEffect(() => {
    fetchDenominaciones();
  }, []);

  async function fetchDenominaciones() {
    try {
      const response = await fetch(`${API_URL}/denominaciones`);
      if (!response.ok) throw new Error('Error al cargar denominaciones');
      const data = await response.json();
      setDenominaciones(data);
    } catch (error) {
      console.error('Error:', error);
    }
  }

  const isCoin = (value) => value < 1;

  const CoinSvg = ({ value }) => (
    <svg
      width="64"
      height="64"
      viewBox="0 0 64 64"
      fill="none"
      xmlns="http://www.w3.org/2000/svg"
      className="hover:drop-shadow-lg"
    >
      <circle
        cx="32"
        cy="32"
        r="30"
        fill="#F59E0B"
        stroke="#D97706"
        strokeWidth="2"
      />
      <circle cx="32" cy="32" r="28" fill="#FBBF24" />
      <circle cx="32" cy="32" r="26" fill="#FCD34D" opacity="0.7" />
      <text
        x="32"
        y="38"
        textAnchor="middle"
        fontSize="16"
        fontWeight="bold"
        fill="#92400E"
      >
        {value}¢
      </text>
    </svg>
  );

  const BillSvg = ({ value }) => (
    <svg
      width="64"
      height="64"
      viewBox="0 0 64 64"
      fill="none"
      xmlns="http://www.w3.org/2000/svg"
      className="hover:drop-shadow-lg"
    >
      <rect
        x="8"
        y="16"
        width="48"
        height="32"
        rx="2"
        fill="#10B981"
        stroke="#059669"
        strokeWidth="2"
      />
      <rect x="10" y="18" width="44" height="28" rx="1" fill="#34D399" />
      <circle cx="20" cy="32" r="6" fill="#10B981" opacity="0.5" />
      <circle cx="44" cy="32" r="6" fill="#10B981" opacity="0.5" />
      <text
        x="32"
        y="37"
        textAnchor="middle"
        fontSize="16"
        fontWeight="bold"
        fill="#065F46"
      >
        ${value}
      </text>
    </svg>
  );

  return (
    <>
      <div className="flex flex-wrap justify-between gap-4 p-4 m-4 bg-base-300">
        {denominaciones.map((denom) => {
          const numValue = parseFloat(denom.valor);
          const denomInCents = Math.round(numValue * 100);
          return (
            <div
              key={denom.id}
              className="flex flex-col items-center gap-2"
            >
              <div className="flex items-center justify-center">
                {isCoin(numValue) ? (
                  <CoinSvg value={denomInCents} />
                ) : (
                  <BillSvg value={numValue.toFixed(2)} />
                )}
              </div>
              <span className="text-sm font-semibold text-gray-700">
                Stock: {denom.cantidadDisponible}
              </span>
            </div>
          );
        })}
      </div>
    </>
  );
};

export default Piezas;
