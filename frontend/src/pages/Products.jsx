import React, { useState } from "react";
import Card from "../components/Card";
import { CartIcon, CookieIcon } from "../icons";
import CoffeeIcon from "../icons/CoffeeIcon";
import JuiceIcon from "../icons/JuiceIcon";
import CandyIcon from "../icons/CandyIcon";

// TEMP 
const sampleProducts = [
  {
    id: 1,
    title: "Agua 500 mL",
    description: "Botella de agua mineral",
    price: 2.5,
    qty: 12,
    icon: CartIcon,
  },
  {
    id: 2,
    title: "Galletas",
    price: 3.75,
    qty: 8,
    icon: CookieIcon,
  },
  {
    id: 3,
    title: "Café",
    price: 1.2,
    qty: 20,
    icon: CoffeeIcon,
  },
  {
    id: 4,
    title: "Jugo Natural",
    price: 4.0,
    qty: 6,
    icon: JuiceIcon,
  },
  {
    id: 5,
    title: "Bébida energética",
    price: 5.5,
    qty: 10,
    icon: JuiceIcon,
  },
  {
    id: 6,
    title: "Dulce",
    price: 6.25,
    qty: 4,
    icon: CandyIcon,
  },
];

export default function Products() {
  const [selectedIds, setSelectedIds] = useState(new Set());

  function toggleSelect(id) {
    setSelectedIds((prev) => {
      const next = new Set(prev);
      if (next.has(id)) next.delete(id);
      else next.add(id);
      return next;
    });
  }

  // Al seleccionar cards
  function handleCardClick(product) {
    console.log("Card clicked:", product.id);
    // falta logica
    toggleSelect(product.id);
  }

  function handleRemoveSelected() {
    // falta logica
  }

  return (
    <div className="container mx-auto p-4">
      <div className="flex items-center justify-between mb-4">
        <h1 className="text-2xl font-semibold">Productos</h1>
        <div />
      </div>

      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
        {sampleProducts.map((p) => (
          <Card
            key={p.id}
            icon={p.icon}
            title={p.title}
            price={p.price}
            qty={p.qty}
            selected={selectedIds.has(p.id)}
            onClick={() => handleCardClick(p)}
          >
          </Card>
        ))}
      </div>
    </div>
  );
}
