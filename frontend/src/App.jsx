import "./App.css";
import Payment from "./components/Payment";
import Piezas from "./components/Piezas";
import Products from "./pages/Products";

function App() {
  const denominacion = [
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
    <>
      <div class="navbar bg-base-300 shadow-sm mb-5">
        <a class="btn btn-ghost text-xl">Reto Grupo Verde</a>
      </div>

      <div class="container mx-auto flex gap-8">
        <Products />
        <Payment denominacion={denominacion} />
      </div>
      <Piezas denominacion={denominacion} />
    </>
  );
}

export default App;
