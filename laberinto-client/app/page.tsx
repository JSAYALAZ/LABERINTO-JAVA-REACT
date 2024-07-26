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
import { LabyrinthT, summaryT } from "@/src/types";
import { labyrinthInitial, summaryInitial } from "@/src/types/initials";

export default function Page() {
  const [data, setData] = useState<LabyrinthT>(labyrinthInitial);
  const [summary, setSummary] = useState<summaryT>(summaryInitial)


  useEffect(()=>{
    setSummary(summaryInitial)
  },[data])

  return (
    <div className="bg-neutral-200 min-h-screen w-full text-center">
      <h1 className="text-3xl uppercase font-bold py-5">Laberinto</h1>

      <div className="flex flex-col lg:flex-row gap-4 bg-neutral-100 p-3 lg:w-4/5 lg:mx-auto shadow-md">
        <div className="lg:w-1/2 w-full flex flex-col gap-4">
          <Section title="Creación">
            <CreateLaberinto
              setData={setData}
            />
          </Section>
          <Section title="Recorrido">
            <DFS setSummary={setSummary} />
            <BFS setSummary={setSummary} />
          </Section>
          <Section title="Respuestas">
            <Dinamic setSummary={setSummary} />
            <Recursive setSummary={setSummary} />
          </Section>
          {/* <Section title="Análisis">

            <Best setResponse={setRespuesta}
              setSteps={setRecorrido}
              setNames={setNames}
              setPasos={setPasos}
            />
          </Section> */}
          <Section title="Limpiar">
            <Clean setSummary={setSummary}/>
          </Section>
          {summary.name!='' && (
            <div className="mt-4">
              <Summary summary={summary}/>
            </div>
          )}
        </div>
        <div className="lg:w-1/2 w-full flex flex-col gap-4">
          {data !== undefined && (
            <>
            <Laberinto data={data} summary={{...summary,recorrido:[]}} />
            <Laberinto data={data} summary={summary} />
            </>
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
