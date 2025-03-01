"use client"

import {useEffect, useState} from "react"
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs"
import { StudentCard } from "./components/student-card"
import WorkoutCreator from "./components/workout-creator"
import AthleteWeek from "./components/athlete-week"
import { Sidebar } from "./components/sidebar"
import axios from "axios";

const coachId = 1; //TODO localStorage.getItem("coachId")

export default function Dashboard() {
  const [activeTab, setActiveTab] = useState("students")
  const [students, setStudents] = useState([]); // Stocke les athlètes
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [isSidebarOpen, setIsSidebarOpen] = useState(false)
  const toggleSidebar = () => setIsSidebarOpen(!isSidebarOpen)


  const fetchAthletes = async () => {
    try {
      setLoading(true)
      setError("")
      const response = await axios.get(`http://localhost:8080/api/athletes/coached/${coachId}`, {
        auth: {
          username: "admin", // Remplace par ton vrai user
          password: "adminpassword",
        },
        headers: {
          "Content-Type": "application/json"
        }
      });

      console.log("Athlètes :", response.data);
      setStudents(response.data)
    } catch (error) {
      console.error("Erreur API :", error);
      setError("Can't load Athletes");
    } finally {
      setLoading(false)
    }
  };

  useEffect(() => {
    fetchAthletes()
  }, []);


  return (
      <div className="flex h-screen overflow-hidden">
        <Sidebar isOpen={isSidebarOpen} onToggle={toggleSidebar}/>
        <div className="flex-1 overflow-auto">
          <div className="container mx-auto p-4">
            <h1 className="text-3xl font-bold mb-6">Tableau de bord du Coach de Callisthénie</h1>

            <Tabs value={activeTab} onValueChange={setActiveTab} className="w-full">
              <TabsList>
                <TabsTrigger value="students">Progrès des Élèves</TabsTrigger>
                <TabsTrigger value="workout">Créer une Séance</TabsTrigger>
                <TabsTrigger value="athleteView">Vue de l'athlete</TabsTrigger>
              </TabsList>

              <TabsContent value="students">
                {loading ? (
                    <p>Chargement...</p>
                ) : error ? (
                    <p className="text-red-500">{error}</p>
                ) : (
                    <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                      {students.length > 0 ? (
                          students.map((student, index) => (
                              <StudentCard key={index} {...student} />
                          ))
                      ) : (
                          <p>Aucun élève trouvé.</p>
                      )}
                    </div>
                )}
              </TabsContent>

              <TabsContent value="workout">
                <WorkoutCreator/>
              </TabsContent>
              <TabsContent value="athleteView">
                <AthleteWeek id={1}/>
              </TabsContent>
            </Tabs>
          </div>
        </div>
      </div>
  )
}

