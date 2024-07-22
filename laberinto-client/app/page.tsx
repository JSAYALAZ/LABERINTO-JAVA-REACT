"use client";
import CreateLaberinto from "@/components/CreateLaberinto";
import DFS from "@/components/DFS";
import Laberinto from "@/components/Laberinto";
import Loading from "@/components/Loading";

import { useEffect, useState } from "react";

export default function page() {
  const [data, setData] = useState();
  const [path, setPath] = useState<string[]>([]);


  return (
    <div className="bg-neutral-200 h-dvh w-full text-center">
      <h1 className="text-3xl uppercase font-bold py-5">Laberinto</h1>
      <div className="flex h-3/4 shadow-md bg-neutral-100 p-3 lg:flex-row flex-col lg:w-3/4 lg:mx-auto">
        {/* CONTENEDOR DE ENTRADAS */}
        <div className="lg:w-1/2 w-full h-full">
          <p className="font-bold text-lg uppercase">Informacion extra</p>
          <CreateLaberinto setData={setData} />
          <DFS setPath={setPath}/>
        </div>

        {/* CONTENEDOR DE LABERINTO */}
        <div className="lg:w-1/2 w-full h-full">
          {data != undefined ? 
          <Laberinto matriz={data} path={path}/> : <Loading />}
        </div>
      </div>
    </div>
  );
}
