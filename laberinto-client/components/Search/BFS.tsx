import axios from "axios";
import React, { Dispatch, SetStateAction } from "react";

type PropsT = {
  setResp: Dispatch<SetStateAction<string[]>>;
  setRecorrido: Dispatch<SetStateAction<string[]>>;
};

export default function BFS({ setResp, setRecorrido }: PropsT) {
  const handleBFS = async (e: React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault();
    setRecorrido([]);
    setResp([]);
    
    try {
      const response = await axios.get("http://localhost:8080/laberinto/bfs");
      setRecorrido(response.data);
    } catch (error) {
      console.error("Error executing BFS search:", error);
    }
  };

  return (
    <button
      className="bg-amber-400 w-full h-10 px-5 rounded-lg"
      onClick={handleBFS}
    >
      BFS
    </button>
  );
}
