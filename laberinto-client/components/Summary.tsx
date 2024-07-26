import { summaryT } from "@/src/types";
import React from "react";

type propsT = {
  summary: summaryT
}


export default function Summary({summary}: propsT) {
  return (
    <div className="flex flex-col lg:flex-row items-center justify-around gap-4 p-4 bg-gray-100 rounded-lg shadow-md">
      <div className="w-full lg:w-1/2 bg-white p-4 rounded-lg shadow-lg flex flex-col items-center">
        <h2 className="text-xl font-bold mb-2 text-indigo-600">Summary</h2>
        <p className="text-gray-700 mb-2">
          Nombre: <span className="font-semibold">{summary.name}</span>
        </p>
        <p className="text-gray-700 mb-2">
          Tiempo implementado: <span className="font-semibold">{summary.time}</span>
        </p>
        <p className="text-gray-700 mb-2">
          Pasos total: <span className="font-semibold">{summary.pasos}</span>
        </p>
        <p className="text-gray-700 mb-2">
          Pasos de recorrido: <span className="font-semibold">{summary.recorrido.length}</span>
        </p>
        <p className="text-gray-700 mb-2">
          Pasos hasta respuesta: <span className="font-semibold">{summary.respuesta.length}</span>
        </p>
      </div>
    </div>
  );
}
