import axios from "axios";
import React, { Dispatch, SetStateAction } from "react";

type propsT = {
  setSteps: Dispatch<SetStateAction<string[]>>;
  setResponse: Dispatch<SetStateAction<string[]>>;
  setPasos : Dispatch<SetStateAction<number[] >>
  setNames: Dispatch<SetStateAction<string[] >>
};
export default function Best({ setResponse, setSteps,setNames,setPasos }: propsT) {
  const handleBest = async (e: React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault();
    setSteps([]);
    axios
      .get("http://localhost:8080/laberinto/best")
      .then((response) => {
        
        if(response.data[0].pasos > response.data[1].pasos){
          setSteps(response.data[0].recorrido)
          setResponse(response.data[0].respuesta)
          setNames((prev)=>[...(prev || []),response.data[0].name])
          setNames((prev)=>[...(prev || []),response.data[1].name])
          setPasos((prev)=>[...(prev || []),response.data[0].pasos])
          setPasos((prev)=>[...(prev || []),response.data[1].pasos])
        }else{
          setSteps(response.data[0].recorrido)
          setResponse(response.data[0].respuesta)
          setNames((prev)=>[...(prev || []),response.data[0].name])
          setNames((prev)=>[...(prev || []),response.data[1].name])
          setPasos((prev)=>[...(prev || []),response.data[0].pasos])
          setPasos((prev)=>[...(prev || []),response.data[1].pasos])
        }
      })
      .catch((error) => {
        console.error("Error creating the labyrinth:", error);
      });
  };
  return (
    <button
      className="bg-amber-400 w-full h-10 px-5 rounded-lg"
      onClick={(e) => handleBest(e)}
    >
      Mejor metodo
    </button>
  );
}
