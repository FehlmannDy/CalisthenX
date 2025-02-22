"use client"

import { useState } from "react"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Progress } from "@/components/ui/progress"
import { Pie } from "react-chartjs-2"
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from "chart.js"
import { MoreVertical, Eye, Plus, MessageSquare, ActivitySquare } from "lucide-react"
import { Button } from "@/components/ui/button"
import { DropdownMenu, DropdownMenuContent, DropdownMenuItem, DropdownMenuTrigger } from "@/components/ui/dropdown-menu"
import { Dialog, DialogContent, DialogDescription, DialogHeader, DialogTitle } from "@/components/ui/dialog"
import { Input } from "@/components/ui/input"
import { Textarea } from "@/components/ui/textarea"
import { ExerciseGraph } from "./exercise-graph"

ChartJS.register(ArcElement, Tooltip, Legend)

interface Goal {
  id: number
  title: string
  targetValue: number
  validated: boolean
  createdAt: string
  subGoals: Goal[]
}

interface StudentCardProps {
  username: string
  goals: Goal[]
}

export function StudentCard({ username, goals }: StudentCardProps) {
  const [isCommentDialogOpen, setIsCommentDialogOpen] = useState(false)
  const [isGraphDialogOpen, setIsGraphDialogOpen] = useState(false)
  const [comment, setComment] = useState("")
  const [videoLink, setVideoLink] = useState("")

  // Détermination de l'objectif principal et des secondaires
  const mainGoal = goals.length > 0 ? goals[0] : null
  const secondaryGoals = goals.length > 1 ? goals.slice(1) : []

  const pieData = {
    labels: ["Complété", "Restant"],
    datasets: [
      {
        data: mainGoal
            ? [mainGoal.validated ? 100 : (mainGoal.targetValue / 100) * 100, 100 - (mainGoal.targetValue / 100) * 100]
            : [0, 100],
        backgroundColor: ["#10B981", "#E5E7EB"],
        borderColor: ["#059669", "#D1D5DB"],
        borderWidth: 1,
      },
    ],
  }

  const pieOptions = {
    responsive: true,
    plugins: {
      legend: {
        display: false,
      },
      tooltip: {
        callbacks: {
          label: (context: any) => `${context.label}: ${context.raw}%`,
        },
      },
    },
  }

  const handleCommentSubmit = () => {
    console.log("Commentaire soumis:", { comment, videoLink })
    setIsCommentDialogOpen(false)
    setComment("")
    setVideoLink("")
  }

  return (
      <Card className="w-full max-w-sm">
        <CardHeader className="flex flex-row items-center justify-between space-y-0 pb-2">
          <CardTitle>{username}</CardTitle>
          <DropdownMenu>
            <DropdownMenuTrigger asChild>
              <Button variant="ghost" className="h-8 w-8 p-0">
                <span className="sr-only">Ouvrir le menu</span>
                <MoreVertical className="h-4 w-4" />
              </Button>
            </DropdownMenuTrigger>
            <DropdownMenuContent align="end">
              <DropdownMenuItem>
                <Eye className="mr-2 h-4 w-4" />
                <span>Voir les séances</span>
              </DropdownMenuItem>
              <DropdownMenuItem>
                <Plus className="mr-2 h-4 w-4" />
                <span>Ajouter une séance</span>
              </DropdownMenuItem>
              <DropdownMenuItem onSelect={() => setIsCommentDialogOpen(true)}>
                <MessageSquare className="mr-2 h-4 w-4" />
                <span>Ajouter un commentaire</span>
              </DropdownMenuItem>
              <DropdownMenuItem onSelect={() => setIsGraphDialogOpen(true)}>
                <ActivitySquare className="mr-2 h-4 w-4" />
                <span>Voir le graphe des exercices</span>
              </DropdownMenuItem>
            </DropdownMenuContent>
          </DropdownMenu>
        </CardHeader>
        <CardContent>
          {mainGoal && (
              <div className="mb-4">
                <h3 className="text-lg font-semibold mb-2">Objectif Principal: {mainGoal.title}</h3>
                <div className="w-32 h-32 mx-auto">
                  <Pie data={pieData} options={pieOptions} />
                </div>
                <p className="text-center mt-2">{mainGoal.validated ? "100%" : `${(mainGoal.targetValue / 100) * 100}%`} complété</p>
              </div>
          )}
          <div>
            <h3 className="text-lg font-semibold mb-2">Objectifs Secondaires</h3>
            {secondaryGoals.length > 0 ? (
                secondaryGoals.map((goal) => (
                    <div key={goal.id} className="mb-2">
                      <div className="flex justify-between mb-1">
                        <span>{goal.title}</span>
                        <span>{goal.validated ? "100%" : `${(goal.targetValue / 100) * 100}%`}</span>
                      </div>
                      <Progress value={goal.validated ? 100 : (goal.targetValue / 100) * 100} className="w-full" />
                    </div>
                ))
            ) : (
                <p>Aucun objectif secondaire</p>
            )}
          </div>
        </CardContent>

        <Dialog open={isGraphDialogOpen} onOpenChange={setIsGraphDialogOpen}>
          <DialogContent className="sm:max-w-[700px]">
            <DialogHeader>
              <DialogTitle>Graphe des exercices de {username}</DialogTitle>
              <DialogDescription>Visualisation des exercices et de leurs connexions.</DialogDescription>
            </DialogHeader>
            <ExerciseGraph data={{ nodes: [], links: [] }} />
          </DialogContent>
        </Dialog>

        <Dialog open={isCommentDialogOpen} onOpenChange={setIsCommentDialogOpen}>
          <DialogContent className="sm:max-w-[425px]">
            <DialogHeader>
              <DialogTitle>Ajouter un commentaire</DialogTitle>
              <DialogDescription>
                Ajoutez un commentaire et un lien vidéo optionnel pour la séance de {username}.
              </DialogDescription>
            </DialogHeader>
            <div className="grid gap-4 py-4">
              <div className="grid grid-cols-4 items-center gap-4">
                <Textarea
                    id="comment"
                    className="col-span-4"
                    placeholder="Votre commentaire ici..."
                    value={comment}
                    onChange={(e) => setComment(e.target.value)}
                />
              </div>
              <div className="grid grid-cols-4 items-center gap-4">
                <Input
                    id="videoLink"
                    className="col-span-4"
                    placeholder="Lien vidéo (optionnel)"
                    value={videoLink}
                    onChange={(e) => setVideoLink(e.target.value)}
                />
              </div>
            </div>
            <Button onClick={handleCommentSubmit}>Soumettre le commentaire</Button>
          </DialogContent>
        </Dialog>
      </Card>
  )
}
