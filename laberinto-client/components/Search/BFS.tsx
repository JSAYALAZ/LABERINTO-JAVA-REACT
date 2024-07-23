import axios from "axios";
import React, { Dispatch, SetStateAction } from "react";

type propsT = {
  setPath: Dispatch<SetStateAction<string[]>>;
};
export default function BFS({ setPath }: propsT) {
  const handleDFS = async (e: React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault();
    axios
      .get("http://localhost:8080/laberinto/bfs")
      .then((response) => {
        console.log("RECORRIDO BFS");
        console.log("RECORRIDO BFS");
        console.log("RECORRIDO BFS");
        console.log(response.data);
        setPath(response.data);
      })
      .catch((error) => {
        console.error("Error creating the labyrinth:", error);
      });
  };

  return (
    <button
      className="bg-amber-400 h-10 px-5 rounded-lg"
      onClick={(e) => handleDFS(e)}
    >
      BFS
    </button>
  );
}
