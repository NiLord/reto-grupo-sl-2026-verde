import "./App.css";
import Products from "./pages/Products";
import Payment from "./components/Payment";

function App() {
  return (
    <>
      <div class="navbar bg-base-300 shadow-sm mb-5">
        <a class="btn btn-ghost text-xl">Reto Grupo Verde</a>
      </div>
  
      <Products />
      <Payment />
    </>
  );
}

export default App;
