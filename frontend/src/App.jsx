import { useState } from "react";
import "./App.css";

function App() {
  const [count, setCount] = useState(0);

  return (
    <>
      <div class="navbar bg-base-100 shadow-sm">
        <a class="btn btn-ghost text-xl">Reto Grupo Verde</a>
      </div>
    </>
  );
}

export default App;
