"use client"

import { useEffect, useState } from "react"
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
} from "chart.js"
import { Line } from "react-chartjs-2"

ChartJS.register(CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend)

const options = {
  responsive: true,
  plugins: {
    legend: {
      position: "top" as const,
    },
    title: {
      display: true,
      text: "Progrès des Élèves",
    },
  },
}

const generateRandomData = () => {
  return Array.from({ length: 7 }, () => Math.floor(Math.random() * 4))
}

export function StudentProgressChart() {
  const [chartData, setChartData] = useState({
    labels: ["Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"],
    datasets: [
      {
        label: "Alice",
        data: generateRandomData(),
        borderColor: "rgb(255, 99, 132)",
        backgroundColor: "rgba(255, 99, 132, 0.5)",
      },
      {
        label: "Bob",
        data: generateRandomData(),
        borderColor: "rgb(53, 162, 235)",
        backgroundColor: "rgba(53, 162, 235, 0.5)",
      },
      {
        label: "Charlie",
        data: generateRandomData(),
        borderColor: "rgb(75, 192, 192)",
        backgroundColor: "rgba(75, 192, 192, 0.5)",
      },
    ],
  })

  useEffect(() => {
    // Simuler une mise à jour des données toutes les 5 secondes
    const interval = setInterval(() => {
      setChartData((prevData) => ({
        ...prevData,
        datasets: prevData.datasets.map((dataset) => ({
          ...dataset,
          data: generateRandomData(),
        })),
      }))
    }, 5000)

    return () => clearInterval(interval)
  }, [])

  return <Line options={options} data={chartData} />
}

