import axios from "axios";
import React, { Dispatch, SetStateAction } from "react";

type propsT = {
  setData: Dispatch<SetStateAction<undefined>>;
};

export default function CreateLaberinto({ setData }: propsT) {
  const handleCreate = async (formData: FormData) => {
    const cols = formData.get("col");
    const rows = formData.get("row");

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
    <form action={handleCreate} className="flex h-10 gap-6">
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
      <button className="bg-amber-400 h-10 px-5 rounded-lg" type="submit">
        Crear
      </button>
    </form>
  );
}
