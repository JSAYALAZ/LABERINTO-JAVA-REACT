export type summaryT = {

    name: string;
    time: string;
    recorrido: string[];
    respuesta: string[];
    pasos: number;
  
};

export type LabyrinthT ={
    matriz: {[key: string]: string[]}
    start: string
    end: string
}