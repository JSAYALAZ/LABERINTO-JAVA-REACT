import React, { Dispatch, SetStateAction } from "react";
type propsT = {
  setResp: Dispatch<SetStateAction<string[]>>;
  setSteps: Dispatch<SetStateAction<string[]>>;
};

export default function Clean({setResp,setSteps}: propsT) {
  const clearPath = async (e: React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault();
    setResp([]);
    setSteps([]);
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
