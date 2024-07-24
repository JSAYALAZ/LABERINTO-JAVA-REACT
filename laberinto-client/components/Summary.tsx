import React from "react";

type propsT = {
  method1: string;
  method2: string;
  reccorido1?: string[];
  recorrido2?: string[];
  respuesta1?: string[];
  respuesta2?: string[];
  pasos1: number;
  pasos2: number;
};
export default function Summary({
  method1,
  method2,
  pasos1,
  pasos2,

}: propsT) {
  return (
    <div className="flex flex-col lg:flex-row items-center justify-around gap-4 p-4 bg-gray-100 rounded-lg shadow-md">
      <div className="w-full lg:w-1/2 bg-white p-4 rounded-lg shadow-lg flex flex-col items-center">
        <h2 className="text-xl font-bold mb-2 text-indigo-600">{method1}</h2>
        <p className="text-gray-700 mb-2">
          Pasos: <span className="font-semibold">{pasos1}</span>
        </p>
      </div>

      <div className="w-full lg:w-1/2 bg-white p-4 rounded-lg shadow-lg flex flex-col items-center">
        <h2 className="text-xl font-bold mb-2 text-indigo-600">{method2}</h2>
        <p className="text-gray-700 mb-2">
          Pasos: <span className="font-semibold">{pasos2}</span>
        </p>
      </div>
    </div>
  );
}
