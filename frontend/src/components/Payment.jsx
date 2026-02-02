import { useState } from "react";

const Payment = () => {
  const [total, setTotal] = useState("$0.00");

  const [pagado, setPagado] = useState("$0.00");
  const [piezas, setPiezas] = useState(0);
  const [cambio, setCambio] = useState("$0.00");

  const [devolver, setDevolver] = useState("$0.00");

  const pagoBadge = [
    "$0.01",
    "$0.05",
    "$0.10",
    "$0.25",
    "$1.00",
    "$5.00",
    "$10.00",
    "$20.00",
    "$50.00",
  ];
  return (
    <div>
      <div class="card card-border bg-base-100 w-96">
        <div class="card-body flex flex-col gap-6">
          <div>
            <span class="text-xs">Total: </span>
            <h2 class="card-title">{total}</h2>
          </div>

          <div class="bg-info text-info-content p-2 rounded-lg">
            <p>Insertar pago: </p>
            {pagoBadge.map((badge) => (
              <button class="btn btn-sm btn-outline m-1">{badge}</button>
            ))}
          </div>

          <div class="flex flex-col gap-2 bg-secondary text-secondary-content p-2 rounded-lg">
            <label>Pagado: {pagado}</label>
            <label>Piezas: {piezas}</label>
            <label>Cambio: {cambio}</label>
          </div>

          <div class="flex flex-col gap-2 bg-accent text-accent-content p-2 rounded-lg">
            <label>A devolver: {devolver}</label>
            <label>Piezas: {piezas}</label>
          </div>

          <div class="card-actions justify-between">
            <button class="btn btn-error">Limpiar</button>
            <button class="btn btn-primary">Pagar</button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Payment;
