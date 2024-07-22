import axios from "axios";
import React, { Dispatch, MouseEvent, SetStateAction } from "react";
import RandomCreate from "./RandomCreate";
import Create from "./Create";

type propsT = {
  setData: Dispatch<SetStateAction<undefined>>;
};

export default function CreateLaberinto({ setData }: propsT) {
  
  return (
    <form className="flex h-10 gap-6">
      <div className="flex items-center gap-2">
        <label htmlFor="col" className="form_label">
          Columnas
        </label>
        <input className="form_input" type="number" name="col" id="col" />
      </div>
      <div className="flex items-center gap-2">
        <label htmlFor="row" className="form_label">
          Filas
        </label>
        <input className="form_input" type="number" name="row" id="row" />
      </div>
      <Create setData={setData}/>
      <RandomCreate setData={setData}/>
    </form>
  );
}
