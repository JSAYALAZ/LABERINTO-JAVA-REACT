import React, { Dispatch, SetStateAction } from "react";
type propsT = {
  setPath: Dispatch<SetStateAction<string[]>>;
};

export default function Clean({ setPath }: propsT) {
  const clearPath = async (e: React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault();
    setPath([]);
  };
  return (
    <button
      className="bg-amber-400 h-10 px-5 rounded-lg"
      onClick={(e) => clearPath(e)}
    >
      clean
    </button>
  );
}
