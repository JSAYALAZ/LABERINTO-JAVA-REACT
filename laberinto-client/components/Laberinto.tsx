import { LabyrinthT, summaryT } from "@/src/types";
import Image from "next/image";
import React, { useEffect, useState } from "react";

type PropsT = {
  data: LabyrinthT;
  summary?: summaryT;
  speed: number
};

export default function Laberinto({ data, summary,speed }: PropsT) {
  const [resp, setResp] = useState<string[]>();
  const [pasos, setPasos] = useState<string[]>();
  const [deadEnds, setDeadEnds] = useState<string[]>();
  useEffect(()=>{
    setResp([])
    setPasos([])
  },[speed])

  useEffect(() => {
    if (summary) {
      if (summary.recorrido.length === 0) {
        setPasos([]);
      } else {
        const timeouts = summary.recorrido.map((pathC, index) =>
          setTimeout(() => {
            setPasos((prevSteps) => [...(prevSteps || []), pathC]);
          }, index * (300 / speed))
        );
        return () => timeouts.forEach(clearTimeout);
      }
    }
  }, [summary?.recorrido, speed]);
  
  useEffect(() => {
    if (summary) {
      if (summary.respuesta.length === 0) {
        setResp([]);
      } else {
        const timeouts = summary.respuesta.map((pathC, index) =>
          setTimeout(() => {
            setResp((prevSteppeds) => [...(prevSteppeds || []), pathC]);
          }, index * (300 / speed))
        );
        return () => timeouts.forEach(clearTimeout);
      }
    }
  }, [summary?.respuesta, speed]);

  useEffect(() => {
    if (summary?.recorrido) {
      const findFinalSteps = (laberinto: LabyrinthT, recorrido: string[]) => {
        const deadEnds = [];
        for (let i = 0; i < recorrido.length - 1; i++) {
          if (laberinto.matriz[recorrido[i]]?.length === 1) {
            deadEnds.push(recorrido[i]);
          }
        }
        const lastNode = recorrido[recorrido.length - 1];
        if (laberinto.matriz[lastNode]?.length === 1) {
          deadEnds.push(lastNode);
        }
        return deadEnds;
      };

      const deadEnds = findFinalSteps(data, summary.recorrido);
      setDeadEnds(deadEnds);
    }
  }, [summary?.recorrido, data.matriz]);

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
      className="w-full lg:h-full min-h-96 grid rounded-lg"
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

          const isPaso = pasos?.includes(key);
          const isResp = resp?.includes(key);
          const isStart = key === data.start;
          const isEnd = key === data.end;
          const isEndStep = deadEnds?.includes(key) && pasos?.includes(key);

          let bgColor = "";
          if (isStart) {
            bgColor = "bg-green-400";
          } else if (isEnd) {
            bgColor = "bg-amber-500";
          } else if (isPaso && !isEndStep && !isResp) {
            bgColor = "bg-indigo-400";
          } else if (isResp&&summary?.recorrido.length==0) {
            bgColor = "bg-cyan-300";
          }else if (isResp&&pasos?.includes(key)) {
            bgColor = "bg-cyan-300";
          } else if (isEndStep) {
            bgColor = "bg-red-300";
          }

          return (
            <div
              key={key}
              className={`flex items-center justify-center ${bgColor} 
                bg-opacity-60 border-neutral-500`}
              style={borders}
            >
              <div className="relative w-full h-full">
                {pasos?.at(pasos.length - 1) === key && (
                  <Image
                    alt="liebre"
                    fill
                    src={"/img/liebre.png"}
                    className="object-contain"
                  />
                )}
                {(resp?.at(resp.length - 1) === key)&&summary?.recorrido.length==0 && 
                  (
                  <Image
                    alt="tortuga "
                    fill
                    src={"/img/tortuga.png"}
                    className="object-contain"
                  />
                )}
                {isEndStep && !isStart && !isEnd && (
                  <Image
                    alt="liebre"
                    fill
                    src={"/img/x.png"}
                    className="object-contain scale-50"
                  />
                )}
              </div>
            </div>
          );
        })
      )}
    </div>
  );
}
