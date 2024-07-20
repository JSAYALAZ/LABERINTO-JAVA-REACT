import React from "react";

type PropsT = {
  matriz: {
    [key: string]: string[];
  };
};

export default function Laberinto({ matriz }: PropsT) {
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
              className={`flex items-center justify-center ${
                matriz[key]?.length > 0 ? "bg-green-300" : "bg-red-400"
              }`}
              style={borders}
            >
              {key}
            </div>
          );
        })
      )}
    </div>
  );
}
