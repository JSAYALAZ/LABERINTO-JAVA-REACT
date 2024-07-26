import { LabyrinthT, summaryT } from "@/src/types";
import React, { useEffect, useState } from "react";

type PropsT = {
  data: LabyrinthT
  summary?: summaryT
};

export default function Laberinto({data,summary}:PropsT) {
  const [resp, setResp] = useState<string[]>();
  const [pasos, setPasos] = useState<string[]>();

  useEffect(() => {
    if (summary) {
      if (summary.recorrido.length === 0) {
        setPasos([]);
      } else {
        const timeouts = summary.recorrido.map((pathC, index) =>
          setTimeout(() => {
            setPasos((prevSteps) => [...(prevSteps || []), pathC]);
          }, index * 50)
        );

        return () => timeouts.forEach(clearTimeout);
      }
    }
  }, [summary?.recorrido]);

  useEffect(() => {
    if (summary) {
      if (summary.respuesta.length === 0) {
        setResp([]);
      } else {
        const timeouts = summary.respuesta.map((pathC, index) =>
          setTimeout(() => {
            setResp((prevSteppeds) => [...(prevSteppeds || []), pathC]);
          }, index * 100)
        );

        return () => timeouts.forEach(clearTimeout);
      }
    }
  }, [summary?.respuesta]);

  const getMaxCoordinate = (matriz: { [key: string]: string[] }) => {
    let maxX = 0;
    let maxY = 0;
    Object.keys(matriz).forEach((key) => {
      const [x, y] = key.split(",").map(Number);
      if (x + 1 > maxX) maxX = x + 1;
      if (y + 1 > maxY) maxY = y + 1;
    });

    return { maxX, maxY };
  };

  const { maxX, maxY } = getMaxCoordinate(data.matriz);

  const hasNeighbor = (x: number, y: number, direction: string) => {
    const neighborKey = {
      up: `${x},${y - 1}`,
      down: `${x},${y + 1}`,
      left: `${x - 1},${y}`,
      right: `${x + 1},${y}`,
    }[direction];

    return data.matriz[`${x},${y}`]?.includes(neighborKey!);
  };

  return (
    <div
      className="w-full h-full grid"
      style={{
        gridTemplateColumns: `repeat(${maxX}, 1fr)`,
        gridTemplateRows: `repeat(${maxY}, 1fr)`,
      }}
    >
      {Array.from({ length: maxY }).map((_, rowIndex) =>
        Array.from({ length: maxX }).map((_, colIndex) => {
          const key = `${colIndex},${rowIndex}`;
          const borders = {
            borderTop: hasNeighbor(colIndex, rowIndex, "up")
              ? "1px solid transparent"
              : "1px solid black",
            borderBottom: hasNeighbor(colIndex, rowIndex, "down")
              ? "1px solid transparent"
              : "1px solid black",
            borderLeft: hasNeighbor(colIndex, rowIndex, "left")
              ? "1px solid transparent"
              : "1px solid black",
            borderRight: hasNeighbor(colIndex, rowIndex, "right")
              ? "1px solid transparent"
              : "1px solid black",
          };

          return (
            <div
              key={key}
              className={`flex items-center justify-center 
                ${key == data.start && "bg-green-300"}
                ${key == data.end && "bg-amber-500"}
                ${pasos?.includes(key) && "bg-indigo-400"}
                ${(resp?.includes(key)&&pasos?.includes(key)) && "bg-cyan-400"}
                ${(resp?.includes(key)&&pasos?.length==0) && "bg-red-400"}
                `}
              style={borders}
            >
              {/* aqui aguegar lo de cada  */}
              {key}
            </div>
          );
        })
      )}
    </div>
  );
}
