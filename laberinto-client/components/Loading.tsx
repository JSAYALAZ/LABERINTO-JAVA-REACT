
import React from 'react';

export default function Loading(){
  return (
    <div className="flex items-center justify-center min-h-screen">
      <div className="flex flex-col items-center">
        <svg
          className="animate-spin h-10 w-10 text-gray-500"
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
        >
          <circle
            className="opacity-25"
            cx="12"
            cy="12"
            r="10"
            stroke="currentColor"
            strokeWidth="4"
          ></circle>
          <path
            className="opacity-75"
            fill="currentColor"
            d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291l1.3 1.3A8.044 8.044 0 0112 20v4c-4.411 0-8-3.589-8-8h4z"
          ></path>
        </svg>
        <p className="text-gray-500 mt-2">Cargando...</p>
      </div>
    </div>
  );
};

