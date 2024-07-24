"use client";
import CreateLaberinto from "@/components/CreateLaberinto";
import DFS from "@/components/Search/DFS";
import Laberinto from "@/components/Laberinto";
import Loading from "@/components/Loading";
import BFS from "@/components/Search/BFS";
import Clean from "@/components/Search/Clean";
import Dinamic from "@/components/Search/Dinamic";
import Recursive from "@/components/Search/Recursive";
import Best from "@/components/Search/Best";
import Summary from "@/components/Summary";
import { useEffect, useState } from "react";

export default function Page() {
  const [data, setData] = useState();
  const [recorrido, setRecorrido] = useState<string[]>([]);
  const [respuesta, setRespuesta] = useState<string[]>([]);
  const [pasos, setPasos] = useState<number[]>([]);
  const [names, setNames] = useState<string[]>([]);

  useEffect(() => {
    console.log(recorrido);
  }, [recorrido]);

  return (
    <div className="bg-neutral-200 min-h-screen w-full text-center">
      <h1 className="text-3xl uppercase font-bold py-5">Laberinto</h1>

      <div className="flex flex-col lg:flex-row gap-4 bg-neutral-100 p-3 lg:w-4/5 lg:mx-auto shadow-md">
        <div className="lg:w-1/2 w-full flex flex-col gap-4">
          <Section title="Creación">
            <CreateLaberinto
              setData={setData}
              setResp={setRespuesta}
              setSteps={setRecorrido}
              setPasos={setPasos}
              setNames={setNames}
            />
          </Section>
          <Section title="Recorrido">
            <DFS setResp={setRespuesta} setRecorrido={setRecorrido} />
            <BFS setResp={setRespuesta} setRecorrido={setRecorrido} />
          </Section>
          <Section title="Respuestas">
            <Dinamic setResp={setRespuesta} setRecorrido={setRecorrido} />
            <Recursive setResp={setRespuesta} setRecorrido={setRecorrido} />
          </Section>
          <Section title="Análisis">
            <Best
              setResponse={setRespuesta}
              setSteps={setRecorrido}
              setNames={setNames}
              setPasos={setPasos}
            />
          </Section>
          <Section title="Limpiar">
            <Clean setResp={setRespuesta} setSteps={setRecorrido} />
          </Section>
          {pasos.length > 0 && names.length > 0 && (
            <div className="mt-4">
              <Summary
                method1={names[0]}
                method2={names[1]}
                pasos1={pasos[0]}
                pasos2={pasos[1]}
              />
            </div>
          )}
        </div>
        <div className="lg:w-1/2 w-full flex flex-col gap-4">
          {pasos.length > 0 && names.length > 0 && data !== undefined && (
            <div className="flex flex-col gap-4 h-full">
              <Laberinto matriz={data} recorrido={[]} respuesta={respuesta} />
              <Laberinto matriz={data} recorrido={recorrido} respuesta={[]} />
            </div>
          )}
          {data !== undefined && pasos.length === 0 && names.length === 0 && (
            <Laberinto matriz={data} recorrido={recorrido} respuesta={respuesta} />
          )}
        </div>
      </div>
    </div>
  );
}

type SectionProps = {
  title: string;
  children: React.ReactNode;
};

function Section({ title, children }: SectionProps) {
  return (
    <div className="flex flex-col gap-2 mb-4">
      <p className="font-semibold text-lg">{title}</p>
      <div className="flex flex-wrap gap-5">{children}</div>
    </div>
  );
}
