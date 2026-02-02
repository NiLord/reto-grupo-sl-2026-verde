import "./App.css";
import Products from "./pages/Products";

function App() {
  return (
    <>
      <div className="navbar bg-base-100 shadow-sm mb-5">
        <a className="btn btn-ghost text-xl">Reto Grupo Verde</a>
      </div>

      <Products />
    </>
  );
}

export default App;
