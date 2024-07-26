import { summaryT } from "@/src/types";
import { summaryInitial } from "@/src/types/initials";
import axios from "axios";
import React, { Dispatch, SetStateAction } from "react";
type propsT = {
  setSummary: Dispatch<SetStateAction<summaryT>>;
};
export default function Recursive({ setSummary }: propsT) {
  const handleDFS = async (e: React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault();
    setSummary(summaryInitial)
    axios
      .get("http://localhost:8080/laberinto/recursivoSimple")
      .then((response) => {
        setSummary(response.data);
      })
      .catch((error) => {
        console.error("Error creating the labyrinth:", error);
      });
  };

  return (
    <button
      className="bg-amber-400 w-full h-10 px-5 rounded-lg"
      onClick={(e) => handleDFS(e)}
    >
      Recursivo simple
    </button>
  );
}
