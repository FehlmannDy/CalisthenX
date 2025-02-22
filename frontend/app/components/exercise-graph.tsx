"use client"

import { useRef, useCallback } from "react"
import ForceGraph2D from "react-force-graph-2d"

interface Node {
  id: string
  name: string
  val: number
}

interface Link {
  source: string
  target: string
}

interface ExerciseGraphProps {
  data: {
    nodes: Node[]
    links: Link[]
  }
}

export function ExerciseGraph({ data }: ExerciseGraphProps) {
  const fgRef = useRef()

  const handleNodeClick = useCallback((node) => {
    // Zoom sur le nœud cliqué
    const distance = 40
    const distRatio = 1 + distance / Math.hypot(node.x, node.y, node.z)

    if (fgRef.current) {
      fgRef.current.cameraPosition({ x: node.x * distRatio, y: node.y * distRatio, z: distance }, node, 2000)
    }
  }, [])

  return (
    <ForceGraph2D
      ref={fgRef}
      graphData={data}
      nodeLabel="name"
      nodeAutoColorBy="group"
      nodeVal={(node) => node.val}
      linkWidth={2}
      linkDirectionalParticles={2}
      linkDirectionalParticleSpeed={0.005}
      onNodeClick={handleNodeClick}
      width={600}
      height={400}
    />
  )
}

