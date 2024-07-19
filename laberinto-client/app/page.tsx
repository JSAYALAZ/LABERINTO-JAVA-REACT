"use client";
import Laberinto from "@/components/Laberinto";
import Loading from "@/components/Loading";
import axios from "axios";
import React, { useEffect, useState } from "react";

export default function page() {
  const [matris, setMatris] = useState<boolean[][]>();

  // useEffect(() => {
  //   axios
  //     .post("http://localhost:8080/laberinto/create", null, {
  //       params: {
  //         row: 10,
  //         col: 10,
  //       },
  //     })
  //     .then((response) => {
  //       setMatris(response.data);
  //     })
  //     .catch((error) => {
  //       console.error("Error creating the labyrinth:", error);
  //     });
  // }, []);

  const handleCreate = async (formData: FormData) => {
    const cols = formData.get('col');
    const rows = formData.get('row');
    
    axios
      .post("http://localhost:8080/laberinto/create", null, {
        params: {
          row: rows,
          col: cols,
        },
      })
      .then((response) => {
        setMatris(response.data);
      })
      .catch((error) => {
        console.error("Error creating the labyrinth:", error);
      });
  };

  return (
    <div className="bg-neutral-200 h-dvh w-full text-center">
      <h1 className="text-3xl uppercase font-bold py-5">Laberinto</h1>
      <div className="flex h-3/4 border border-emerald-500">
        <div className="w-1/2 h-full">
          <p className="font-bold text-lg uppercase">Informacion extra</p>
          <form action={handleCreate}>
            <div className="">
              <label htmlFor="col" className="form_label">
                Columnas
              </label>
              <input className="form_input" type="number" name="col" id="col" />
            </div>
            <div>
              <label htmlFor="row" className="form_label">
                Filas
              </label>
              <input className="form_input" type="number" name="row" id="row" />
            </div>
            <button className="bg-amber-400 h-10 px-5 rounded-lg" type="submit">Crear</button>
          </form>
        </div>
        <div className="w-1/2 h-full">
          {matris != undefined ? <Laberinto matris={matris} /> : <Loading />}
        </div>
      </div>
    </div>
  );
}
