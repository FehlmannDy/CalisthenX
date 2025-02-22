"use client"

import { useState } from "react"
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { Textarea } from "@/components/ui/textarea"

export function WorkoutCreator() {
  const [workoutName, setWorkoutName] = useState("")
  const [exercises, setExercises] = useState("")

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault()
    console.log("Nouvelle séance créée:", { workoutName, exercises })
    // Ici, vous pourriez envoyer ces données à une API ou les stocker dans un état global
    setWorkoutName("")
    setExercises("")
  }

  return (
    <div>
      <h2 className="text-2xl font-semibold mb-4">Créer une Séance de Street Workout</h2>
      <form onSubmit={handleSubmit} className="space-y-4">
        <div>
          <Label htmlFor="workout-name">Nom de la séance</Label>
          <Input
            id="workout-name"
            value={workoutName}
            onChange={(e) => setWorkoutName(e.target.value)}
            placeholder="Ex: Séance Intensité Haute"
            required
          />
        </div>
        <div>
          <Label htmlFor="exercises">Exercices (un par ligne)</Label>
          <Textarea
            id="exercises"
            value={exercises}
            onChange={(e) => setExercises(e.target.value)}
            placeholder="Ex:
10 tractions
20 pompes
30 squats"
            required
          />
        </div>
        <Button type="submit">Créer la séance</Button>
      </form>
    </div>
  )
}

