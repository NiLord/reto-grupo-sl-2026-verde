import { useState } from "react";
import "./App.css";
import Payment from "./components/Payment";

function App() {
  const [count, setCount] = useState(0);

  return (
    <>
      <div class="navbar bg-base-300 shadow-sm">
        <a class="btn btn-ghost text-xl">Reto Grupo Verde</a>
      </div>
      <Payment />
    </>
  );
}

export default App;
