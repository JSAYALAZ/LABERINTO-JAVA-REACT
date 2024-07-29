import React, { Dispatch, SetStateAction } from "react";
import RandomCreate from "./RandomCreate";
import Create from "./Create";
import { LabyrinthT } from "@/src/types";

type propsT = {
  setData: Dispatch<SetStateAction<LabyrinthT>>;
};

export default function CreateLaberinto({ setData }: propsT) {
  return (
    <div className="bg-white bg-opacity-50 rounded-lg py-2 px-2 w-full">
      <form className="flex gap-3 items-center">
        <div className="grid grid-cols-2 gap-4">

          <div className="input_container">
            <label htmlFor="col" className="form_label">Colum</label>
            <input 
            className="form_input" 
            type="number" 
            name="col" 
            id="col" min={0} required/>
          </div>
          <div className="input_container">
            <label htmlFor="row" className="form_label">Filas</label>
            <input 
            className="form_input" 
            type="number" 
            name="row" 
            id="row" min={0} required/>
          </div>
          <div className="input_container">
            <label htmlFor="start" className="form_label">
              Inicio
            </label>
            <input
              className="form_input"
              type="text"
              name="start"
              id="start"
              placeholder="Opc 2,4 - 5,2"
            />
          </div>
          <div className="input_container">
            <label htmlFor="end" className="form_label">
              Final
            </label>
            <input
              className="form_input"
              type="text"
              name="end"
              id="end"
              placeholder="Opc 9,5 - 2,4"
            />
          </div>
        </div>
        <Create setData={setData} />
        <RandomCreate setData={setData} />
      </form>
    </div>
  );
}
