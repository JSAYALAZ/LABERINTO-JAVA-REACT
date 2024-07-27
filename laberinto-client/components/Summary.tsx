import { summaryT } from "@/src/types";
import React from "react";

type propsT = {
  summary: summaryT
}


export default function Summary({summary}: propsT) {
  return (

      <div className="w-full bg-opacity-80 bg-white p-4 rounded-lg shadow-lg flex flex-col items-center">
        <h2 className="text-xl font-bold mb-2 text-indigo-600">Resumen de metodo</h2>
        <p className="text-gray-700 mb-2">
          Nombre: <span className="font-semibold font-mono">{summary.name}</span>
        </p>
        <p className="text-gray-700 mb-2">
          Tiempo implementado: <span className="font-semibold font-mono">{summary.time} s</span>
        </p>
        <p className="text-gray-700 mb-2">
          Pasos de recorrido: <span className="font-semibold font-mono">{summary.recorrido.length}</span>
        </p>
        <p className="text-gray-700 mb-2">
          Pasos hasta respuesta: <span className="font-semibold font-mono">{summary.respuesta.length}</span>
        </p>
      </div>

  );
}
