"use client";
import { Dispatch, SetStateAction, useState } from "react";

type PropsT = {
  setData: Dispatch<SetStateAction<number>>;
};

export default function Speed({ setData }: PropsT) {
  const [value, setValue] = useState<number>(1.0);

  // Function to handle the increase of speed value
  const handleIncrease = () => {
    setValue((prev) => {
      const newValue = prev + 0.5;
      if (newValue >= 2.1) {
        setData(0.5);
        return 0.5;
      }
      setData(newValue);
      return newValue;
    });
  };

  // Function to handle the decrease of speed value
  const handleDecrease = () => {
    setValue((prev) => {
      const newValue = prev - 0.5;
      if (newValue <= 0.4) {
        setData(2);
        return 2;
      }
      setData(newValue);
      return newValue;
    });
  };

  return (
    <div className="flex items-center space-x-2">
      <button
        onClick={handleDecrease}
        className="p-2 bg-white bg-opacity-75 rounded-md hover:bg-gray-300"
      >
        &lt;
      </button>
      <input
        type="number"
        value={value.toFixed(1)}
        min={0.5}
        max={2}
        step={0.1}
        disabled
        className="w-20 p-2 border bg-white bg-opacity-75 rounded-md text-center"
      />
      <button
        onClick={handleIncrease}
        className="p-2 bg-white bg-opacity-75 rounded-md hover:bg-gray-300"
      >
        &gt;
      </button>
    </div>
  );
}
