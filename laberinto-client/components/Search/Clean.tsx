import { summaryT } from "@/src/types";
import { summaryInitial } from "@/src/types/initials";
import React, { Dispatch, SetStateAction } from "react";
type propsT = {
  setSummary: Dispatch<SetStateAction<summaryT>>;
};

export default function Clean({setSummary}: propsT) {
  const clearPath = async (e: React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault();
    setSummary(summaryInitial)
  };
  return (
    <button
      className="bg-amber-400 h-10 px-5 w-full rounded-lg"
      onClick={(e) => clearPath(e)}
    >
      clean
    </button>
  );
}
