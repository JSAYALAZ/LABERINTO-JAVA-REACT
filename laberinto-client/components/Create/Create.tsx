import { LabyrinthT, summaryT } from "@/src/types";
import axios from "axios";
import React, { Dispatch, SetStateAction } from "react";

type propsT = {
  setData: Dispatch<SetStateAction<LabyrinthT>>;
};
export default function Create({ setData }: propsT) {
  const handleCreate = async (e: React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault();
    const cols = (document.getElementById("col") as HTMLInputElement).value;
    const rows = (document.getElementById("row") as HTMLInputElement).value;
    let start = (document.getElementById("start") as HTMLInputElement).value;
    let end = (document.getElementById("end") as HTMLInputElement).value;

    if (start == "") {
      start = "0,0";
    }
    if (end == "") {
      end = `${+rows-1},${+cols-1}`;
    }

    const regex = /^\d+,\d+$/;

    if (!regex.test(start)) {
      alert("Formato inicio invalido");
      return;
    }
    if (!regex.test(end)) {
      alert("Formato meta invalido");
      return;
    }

    axios
      .post("http://localhost:8080/laberinto/create", null, {
        params: {
          row: rows,
          col: cols,
          start: start,
          end: end
        },
      })
      .then((response) => {
        console.log(response.data);

        setData({
          matriz: response.data.graph,
          end: response.data.end,
          start: response.data.start,
        });
      })
      .catch((error) => {
        console.error("Error creating the labyrinth:", error);
      });
  };
  return (
    <button
      className="bg-amber-400 h-10 px-5 rounded-lg font-pixelify"
      onClick={(e) => handleCreate(e)}
    >
      Crear
    </button>
  );
}
