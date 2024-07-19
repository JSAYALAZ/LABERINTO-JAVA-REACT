import React from "react";

type propsT = {
  matriz: boolean[][];
};

export default function Laberinto({ matriz }: propsT) {
  return (
    <div className="w-full h-full">
      {matriz.map((row, rowIndex) => {
        const numColumns = row.length; // Obtiene el número de columnas en la fila actual
        const rowHeight = 100 / matriz.length; // Calcula la altura de la fila como porcentaje
        return (
          <div
            key={rowIndex}
            className="grid w-full"
            style={{
              height: `${rowHeight}%`,
              gridTemplateColumns: `repeat(${numColumns}, 1fr)`,
            }}
          >
            {row.map((cell, cellIndex) => (
              <div
                key={`${rowIndex}-${cellIndex}`}
                className={`flex items-center justify-center border border-black ${
                  cell ? "bg-green-300" : "bg-red-400"
                }`}
              ></div>
            ))}
          </div>
        );
      })}
    </div>
  );
}
