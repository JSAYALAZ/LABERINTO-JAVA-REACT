import axios from "axios";
import React, { Dispatch, SetStateAction } from "react";
type propsT = {
  setResp: Dispatch<SetStateAction<string[]>>; 
  setRecorrido: Dispatch<SetStateAction<string[]>>; 
};
export default function Recursive({ setResp,setRecorrido }: propsT) {
  const handleDFS = async (e: React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault();
    setResp([])
    setRecorrido([])
    axios
      .get("http://localhost:8080/laberinto/recursivoSimple")
      .then((response) => {
        setResp(response.data);
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
