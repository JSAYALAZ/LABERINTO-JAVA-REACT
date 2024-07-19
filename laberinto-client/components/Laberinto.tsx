import React from "react";

type propsT = {
  matris: boolean[][];
};
export default function Laberinto({ matris }: propsT) {
  return (
    <div className="border border-black">
      {matris.map((row, rowIndex) => (
        <div key={rowIndex} className="flex">
          {row.map((cell, cellIndex) => (
            <div
              key={`${rowIndex}-${cellIndex}`}
              className={`w-5 h-5 flex flex-col items-center justify-center ${
                cell ? "bg-white" : "bg-black"
              }`}
            ></div>
          ))}
        </div>
      ))}
    </div>
  );
}
