
import React from "react";

export default function Card({
  image = "",
  title = "Card Title",
  price = null,
  qty = null,
  children,
  onClick,
  selected = false,
}) {
  const baseClasses =
    "card bg-base-100 shadow-sm transition-transform duration-150";
  const interactiveClasses =
    "cursor-pointer hover:shadow-lg hover:-translate-y-0.5 focus:outline-none";
  const selectedClasses = selected ? "ring ring-primary" : "";

  return (
    <div
      role="button"
      tabIndex={0}
      onClick={onClick}
      onKeyDown={(e) => {
        if (e.key === "Enter" || e.key === " ") {
          e.preventDefault();
          onClick && onClick(e);
        }
      }}
      className={`${baseClasses} ${interactiveClasses} ${selectedClasses}`}
    >
      <figure>
        <img src={image} alt={title} />
      </figure>
      <div className="card-body">
        <h2 className="card-title">{title}</h2>
        {price !== null && (
          <p className="font-medium">$ {Number(price).toFixed(2)}</p>
        )}
        {qty !== null && <p className="text-xs">Disponible: {qty}</p>}
        {children}
      </div>
    </div>
  );
}
