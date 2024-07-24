import axios from "axios";
import React, { Dispatch, MouseEvent, SetStateAction } from "react";
import RandomCreate from "./Create/RandomCreate";
import Create from "./Create/Create";

type propsT = {
  setData: Dispatch<SetStateAction<undefined>>;
  setResp: Dispatch<SetStateAction<string[]>>;
  setSteps: Dispatch<SetStateAction<string[]>>;
  setNames: Dispatch<SetStateAction<string[]>>;
  setPasos:Dispatch<SetStateAction<number[]>>
};

export default function CreateLaberinto({ setData,setResp,setSteps,setPasos,setNames }: propsT) {
  return (
    <div className="bg-neutral-200 rounded-lg py-2 px-2">
      <form className="flex h-10 gap-3">
        <div className="flex items-center gap-2">
          <label htmlFor="col" className="form_label"> Columnas </label>
          <input 
          className="form_input" 
          type="number" 
          name="col" 
          id="col" />
        </div>
        <div className="flex items-center gap-2">
          <label htmlFor="row" className="form_label"> Filas </label>
          <input 
          className="form_input" 
          type="number" 
          name="row" 
          id="row" />
        </div>
        <Create setData={setData}setResp={setResp} setSteps={setSteps} setNames={setNames} setPasos={setPasos}/>
        <RandomCreate setData={setData}setResp={setResp} setSteps={setSteps} setNames={setNames} setPasos={setPasos}/>
      </form>
    </div>
  );
}
