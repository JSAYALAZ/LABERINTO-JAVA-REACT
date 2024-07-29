import React, { Dispatch, SetStateAction, useState } from 'react'

type propsT = {
    ableSelect: Dispatch<SetStateAction<boolean>>
    text: string
}
export default function AlterRute({ableSelect,text}:propsT) {

  const [pressed, setPressed]= useState(false)
    const handleSelect =()=>{
      setPressed(!pressed)
        ableSelect(!pressed)
    }
  return (
    <button onClick={handleSelect} className={`
    ${pressed&&text=='Habilitar ruta'&&'scale-110 bg-green-400'}
    ${pressed&&text=='Desabilitar ruta'&&'scale-110 bg-red-600'}
    `}>
        {text}
    </button>
  )
}
