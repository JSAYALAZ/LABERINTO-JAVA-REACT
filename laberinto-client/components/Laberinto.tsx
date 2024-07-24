import React, { useEffect, useState } from "react";

type PropsT = {
  matriz: {
    [key: string]: string[];
  };
  respuesta: string[];
  recorrido: string[];
};

export default function Laberinto({ recorrido, respuesta, matriz }: PropsT) {
  const [resp, setResp] = useState<string[]>();
  const [pasos, setPasos] = useState<string[]>();

  useEffect(() => {
    console.log(recorrido);
    
    if (recorrido.length === 0) {
      setPasos([]);
    } else {
      const timeouts = recorrido.map((pathC, index) =>
        setTimeout(() => {
          setPasos((prevSteps) => [...(prevSteps || []), pathC]);
        }, index * 100)
      );

      return () => timeouts.forEach(clearTimeout);
    }
  }, [recorrido]);

  useEffect(() => {
    console.log(respuesta);
    
    if (respuesta.length === 0) {
      setResp([]);
    } else {
      const timeouts = respuesta.map((pathC, index) =>
        setTimeout(() => {
          setResp((prevSteppeds) => [...(prevSteppeds || []), pathC]);
        }, index * 100)
      );

      return () => timeouts.forEach(clearTimeout);
    }
  }, [respuesta]);

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

  const { maxX, maxY } = getMaxCoordinate(matriz);

  const hasNeighbor = (x: number, y: number, direction: string) => {
    const neighborKey = {
      up: `${x},${y - 1}`,
      down: `${x},${y + 1}`,
      left: `${x - 1},${y}`,
      right: `${x + 1},${y}`,
    }[direction];

    return matriz[`${x},${y}`]?.includes(neighborKey!);
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
                ${key == "0,0" && "bg-green-300"}
                ${key == `${maxX - 1},${maxY - 1}` && "bg-amber-500"}
                ${pasos?.includes(key) && "bg-indigo-400"}
                ${resp?.includes(key) && "bg-amber-500"}
                `}
              style={borders}
            >
              {`${key}`}
            </div>
          );
        })
      )}
    </div>
  );
}
