import { summaryT } from "@/src/types";
import { summaryInitial } from "@/src/types/initials";
import axios from "axios";
import React, { Dispatch, SetStateAction } from "react";

type PropsT = {
  setSummary: Dispatch<SetStateAction<summaryT>>;
};

export default function BFS({ setSummary }: PropsT) {
  const handleBFS = async (e: React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault();
    setSummary(summaryInitial)
    try {
      const response = await axios.get("http://localhost:8080/laberinto/bfs");
      setSummary(response.data);
    } catch (error) {
      console.error("Error executing BFS search:", error);
    }
  };

  return (
    <button
      className="bg-amber-400 w-full h-10 px-5 rounded-lg font-pixelify"
      onClick={handleBFS}
    >
      BFS
    </button>
  );
}
