import { Link, Route, Routes, useLocation } from "react-router-dom";
import "./App.css";
import Piezas from "./components/Piezas";
import Checkout from "./pages/Checkout";
import Products from "./pages/Products";

function App() {
  const location = useLocation();

  return (
    <>
      <div className="navbar bg-base-300 shadow-sm mb-5">
        <div className="flex-1">
          <Link to="/" className="btn btn-ghost text-xl">
            Reto Grupo Verde
          </Link>
        </div>
        <div className="flex-none">
          <ul className="menu menu-horizontal px-1">
            <li>
              <Link
                to="/"
                className={location.pathname === "/" ? "active" : ""}
              >
                Productos
              </Link>
            </li>
            <li>
              <Link
                to="/checkout"
                className={location.pathname === "/checkout" ? "active" : ""}
              >
                🛒 Carrito
              </Link>
            </li>
          </ul>
        </div>
      </div>

      <Routes>
        <Route
          path="/"
          element={
            <>
              <Products />
              <Piezas />
            </>
          }
        />
        <Route
          path="/productos"
          element={
            <>
              <Products />
              <Piezas />
            </>
          }
        />
        <Route path="/checkout" element={<Checkout />} />
      </Routes>
    </>
  );
}

export default App;
