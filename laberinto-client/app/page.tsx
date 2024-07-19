"use client";
import CreateLaberinto from "@/components/CreateLaberinto";
import Laberinto from "@/components/Laberinto";
import Loading from "@/components/Loading";

import {useState } from "react";

export default function page() {
  const [matriz, setMatriz] = useState<boolean[][]>();


  return (
    <div className="bg-neutral-200 h-dvh w-full text-center">
      <h1 className="text-3xl uppercase font-bold py-5">Laberinto</h1>
      <div className="flex h-3/4 shadow-md bg-neutral-100 p-3 lg:flex-row flex-col lg:w-3/4 lg:mx-auto">

      {/* CONTENEDOR DE ENTRADAS */}
        <div className="lg:w-1/2 w-full h-full">
          <p className="font-bold text-lg uppercase">Informacion extra</p>
          <CreateLaberinto setMatriz={setMatriz}/>
        </div>

      {/* CONTENEDOR DE LABERINTO */}  
        <div className="lg:w-1/2 w-full h-full">
          {matriz != undefined ? <Laberinto matriz={matriz} /> : <Loading />}
        </div>
      </div>
    </div>
  );
}
