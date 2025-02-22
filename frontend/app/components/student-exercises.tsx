import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"
import { CheckCircle, XCircle } from "lucide-react"

const students = [
  { id: 1, name: "Alice", exercises: { pushups: true, pullups: false, squats: true, muscleups: false } },
  { id: 2, name: "Bob", exercises: { pushups: true, pullups: true, squats: false, muscleups: false } },
  { id: 3, name: "Charlie", exercises: { pushups: false, pullups: true, squats: true, muscleups: true } },
]

export function StudentExercises() {
  return (
    <div>
      <h2 className="text-2xl font-semibold mb-4">Exercices des Élèves</h2>
      <Table>
        <TableHeader>
          <TableRow>
            <TableHead>Nom</TableHead>
            <TableHead>Pompes</TableHead>
            <TableHead>Tractions</TableHead>
            <TableHead>Squats</TableHead>
            <TableHead>Muscle-ups</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {students.map((student) => (
            <TableRow key={student.id}>
              <TableCell>{student.name}</TableCell>
              <TableCell>
                {student.exercises.pushups ? (
                  <CheckCircle className="text-green-500" />
                ) : (
                  <XCircle className="text-red-500" />
                )}
              </TableCell>
              <TableCell>
                {student.exercises.pullups ? (
                  <CheckCircle className="text-green-500" />
                ) : (
                  <XCircle className="text-red-500" />
                )}
              </TableCell>
              <TableCell>
                {student.exercises.squats ? (
                  <CheckCircle className="text-green-500" />
                ) : (
                  <XCircle className="text-red-500" />
                )}
              </TableCell>
              <TableCell>
                {student.exercises.muscleups ? (
                  <CheckCircle className="text-green-500" />
                ) : (
                  <XCircle className="text-red-500" />
                )}
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </div>
  )
}

