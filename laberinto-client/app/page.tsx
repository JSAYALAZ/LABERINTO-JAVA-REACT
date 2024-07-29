"use client";
import { useEffect, useState } from "react";
import CreateLaberinto from "@/components/Create/CreateLaberinto";
import DFS from "@/components/Search/DFS";
import BFS from "@/components/Search/BFS";
import Clean from "@/components/Search/Clean";
import Dinamic from "@/components/Search/Dinamic";
import Recursive from "@/components/Search/Recursive";
import Laberinto from "@/components/Laberinto";
import Summary from "@/components/Summary";
import { LabyrinthT, summaryT } from "@/src/types";
import { labyrinthInitial, summaryInitial } from "@/src/types/initials";
import Speed from "@/components/Control/Speed";
import AddRute from "@/components/Control/alterRute";
import AlterRute from "@/components/Control/alterRute";

export default function Page() {
  const [data, setData] = useState<LabyrinthT>(labyrinthInitial);
  const [summary, setSummary] = useState<summaryT>(summaryInitial);
  const [speedResp, setSpeedResp] = useState(1);
  const [speedSteps, setSpeedSteps] = useState(1);
  const [addMode, setAddMode] = useState(false);
  const [deleteMode, setDeleteMode] = useState(false);
  const [idModify, setIdModify] = useState("");

  useEffect(() => {
    setSummary(summaryInitial);
  }, [data]);

  return (
    <div className="text-center">
      <h1 className="text-6xl uppercase font-extrabold font-minecraft py-5">
        Laberinto
      </h1>

      <div className="bg-white bg-opacity-70 p-4 flex rounded-lg flex-col shadow-md md:px-10 lg:flex-row mx-5 gap-4 lg:w-4/5 lg:mx-auto">
        <div className="lg:w-1/2 w-full flex flex-col gap-4">
          <Section title="Creación">
            <CreateLaberinto setData={setData} />
          </Section>
          <Section title="Recorrido">
            <DFS setSummary={setSummary} />
            <BFS setSummary={setSummary} />
          </Section>
          <Section title="Respuestas">
            <Dinamic setSummary={setSummary} />
            <Recursive setSummary={setSummary} />
          </Section>
          <Section title="Limpiar">
            <Clean setSummary={setSummary} />
          </Section>
          <div className="mt-4">
            <Summary summary={summary} />
          </div>
        </div>
        <div className="lg:w-1/2 w-full flex flex-col gap-4">
          {data.end != "" ? (
            <>
              <div className="h-full">
                <div className="flex w-full justify-evenly items-center mb-1">
                  <p>Solucion</p>
                  <AlterRute ableSelect={setAddMode} text="Habilitar ruta" />
                  <AlterRute ableSelect={setDeleteMode} text="Desabilitar ruta" />
                  <Speed setData={setSpeedResp} />
                </div>
                <div>
                  <Laberinto
                    setIdModify={setIdModify}
                    selectMode={
                      addMode ? { name: 'add', state: true } :
                      deleteMode ? { name: 'delete', state: true } :
                      undefined
                    }
                    data={data}
                    summary={{ ...summary, recorrido: [] }}
                    speed={speedResp}
                  />
                </div>
              </div>
              <div className="h-full gap-2">
                <div className="flex w-full justify-evenly items-center mb-1">
                  <p>Completo</p>
                  <Speed setData={setSpeedSteps} />
                </div>
                <div>
                  <Laberinto
                    setIdModify={setIdModify}
                    selectMode={
                      addMode ? { name: 'add', state: true } :
                      deleteMode ? { name: 'delete', state: true } :
                      undefined
                    }
                    data={data}
                    summary={summary}
                    speed={speedSteps}
                  />
                </div>
              </div>
            </>
          ) : (
            <div className="h-full content-center">
              <p>Bienvenido</p>
            </div>
          )}
        </div>
      </div>

      <footer
        className="bottom-0 left-0 w-full lg:absolute 
        text-center bg-opacity-75 bg-gray-800 text-white p-2"
      >
        Derechos reservados{" "}
        <span className="font-pixelify text-xl">
          JSAYALAZ, RAETO0, STEVENCHIMBO
        </span>
      </footer>
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
