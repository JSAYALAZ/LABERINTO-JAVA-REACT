import axios from 'axios';
import React, { Dispatch, SetStateAction } from 'react'

type propsT = {
    setData: Dispatch<SetStateAction<undefined>>;
  };
  const generateRandomColsAndRows = () => {
    let colsRandom = Math.floor(Math.random() * 51);
    let rowsRandom = Math.floor(Math.random() * 51);

    while (Math.abs(colsRandom - rowsRandom) > 10) {
      colsRandom = Math.floor(Math.random() * 51);
      rowsRandom = Math.floor(Math.random() * 51);
    }
    return { colsRandom, rowsRandom };
  };

export default function RandomCreate({setData}:propsT) {
    const handleCreate = async (e: React.MouseEvent<HTMLButtonElement>) => {
        e.preventDefault();
        const {colsRandom, rowsRandom} = generateRandomColsAndRows();

        (document.getElementById('col') as HTMLInputElement).value = String(colsRandom);
        (document.getElementById('row') as HTMLInputElement).value = String(rowsRandom);
     
        axios
          .post("http://localhost:8080/laberinto/create", null, {
            params: {
              row: rowsRandom,
              col: colsRandom,
            },
          })
          .then((response) => {
            setData(response.data);
          })
          .catch((error) => {
            console.error("Error creating the labyrinth:", error);
          });
      };
  return (
    <button 
    className="bg-amber-400 h-10 px-5 rounded-lg"
    onClick={(e)=>handleCreate(e)}
    >
        Random
    </button>
  )
}
