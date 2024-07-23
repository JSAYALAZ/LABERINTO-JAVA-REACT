import axios from "axios";
import React, { Dispatch, SetStateAction } from "react";

type propsT = {
  setData: Dispatch<SetStateAction<undefined>>;
};
export default function Create({ setData }: propsT) {
  const handleCreate = async (e: React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault();
    const cols = (document.getElementById("col") as HTMLInputElement).value;
    const rows = (document.getElementById("row") as HTMLInputElement).value;
    axios
      .post("http://localhost:8080/laberinto/create", null, {
        params: {
          row: rows,
          col: cols,
        },
      })
      .then((response) => {
        console.log("LABERINTO CREADO");
        console.log("LABERINTO CREADO");
        console.log("LABERINTO CREADO");
        console.log(response.data);
        setData(response.data);
      })
      .catch((error) => {
        console.error("Error creating the labyrinth:", error);
      });
  };
  return (
    <button
      className="bg-amber-400 h-10 px-5 rounded-lg"
      onClick={(e) => handleCreate(e)}
    >
      Crear
    </button>
  );
}
