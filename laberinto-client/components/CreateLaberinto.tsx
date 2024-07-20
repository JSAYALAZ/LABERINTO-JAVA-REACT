import axios from "axios";
import React, { Dispatch, MouseEvent, SetStateAction } from "react";
import RandomCreate from "./RandomCreate";
import Create from "./Create";

type propsT = {
  setData: Dispatch<SetStateAction<undefined>>;
};

export default function CreateLaberinto({ setData }: propsT) {
  const handleCreate = async (e: React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault();
    const cols = (document.getElementById('col') as HTMLInputElement).value;
    const rows = (document.getElementById('row') as HTMLInputElement).value;
    axios
      .post("http://localhost:8080/laberinto/create", null, {
        params: {
          row: rows,
          col: cols,
        },
      })
      .then((response) => {
        setData(response.data);
      })
      .catch((error) => {
        console.error("Error creating the labyrinth:", error);
      });
  };

  
  return (
    <form className="flex h-10 gap-6">
      <div className="flex items-center gap-2">
        <label htmlFor="col" className="form_label">
          Columnas
        </label>
        <input className="form_input" type="number" name="col" id="col" />
      </div>
      <div className="flex items-center gap-2">
        <label htmlFor="row" className="form_label">
          Filas
        </label>
        <input className="form_input" type="number" name="row" id="row" />
      </div>
      <Create setData={setData}/>
      <RandomCreate setData={setData}/>
    </form>
  );
}
