"use client"; // Indique que ce composant est exécuté côté client

import { useState } from "react";

// Définition du type des props
interface AthleteProps {
    id: number;
}

// Composant React
export default function AthleteWeek({ id }: AthleteProps) {
    const [week, setWeek] = useState(1);

    return (
        <div>
            <h2>Athlète ID: {id}</h2>
            <p>Semaine actuelle : {week}</p>
            <button onClick={() => setWeek(week + 1)}>Semaine suivante</button>
        </div>
    );
}