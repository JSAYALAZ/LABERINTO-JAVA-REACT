import axios from "axios";
import React, { Dispatch, SetStateAction } from "react";

type propsT = {
  setPath: Dispatch<SetStateAction<string[]>>;
};
export default function DFS({ setPath }: propsT) {
  const handleDFS = async (e: React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault();
    const cols = (document.getElementById("col") as HTMLInputElement).value;
    const rows = (document.getElementById("row") as HTMLInputElement).value;
    axios
      .get("http://localhost:8080/laberinto/dfs")
      .then((response) => {
        setPath(response.data);
      })
      .catch((error) => {
        console.error("Error creating the labyrinth:", error);
      });
  };
  const clearPath = async (e: React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault();
    setPath([]);
  };
  return (
    <div className="flex h-10 gap-6 items-center my-5">
      <p className="text-lg">Seleccionar reccorido</p>
      <button
        className="bg-amber-400 h-10 px-5 rounded-lg"
        onClick={(e) => handleDFS(e)}
      >
        DFS
      </button>
      <button
        className="bg-amber-400 h-10 px-5 rounded-lg"
        onClick={(e) => clearPath(e)}
      >
        clean
      </button>
    </div>
  );
}
