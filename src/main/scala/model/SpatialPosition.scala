package model

import model.Movement.{MoveForward, TurnLeft, TurnRight}
import model.OrientationReal.{East, North, South, West}

case class SpatialPosition(position: Position, cardinalPoint: OrientationReal.Value) {

    val orientationMapping =
      Map(
        North -> Map(MoveForward -> North,
          TurnLeft -> West,
          TurnRight -> East),

        South -> Map(MoveForward -> South,
          TurnLeft -> East,
          TurnRight -> West),

        East -> Map(MoveForward -> East,
          TurnLeft -> North,
          TurnRight -> South),

        West -> Map(MoveForward -> West,
          TurnLeft -> South,
          TurnRight -> North)
      )

    def calculateNextSpatialPosition(movement: Movement.Value): SpatialPosition = {
      val finalOrientation = orientationMapping(cardinalPoint)(movement)
      val finalPos = movement match {
        case MoveForward => moveForward(finalOrientation)
        case _ => position
      }
      SpatialPosition(finalPos, finalOrientation)
    }

    def moveForward(finalDirection: OrientationReal.Value): Position = {
      val x = position.x
      val y = position.y
      finalDirection match {
        case North => Position(x, y + 1)
        case South => Position(x, y - 1)
        case East => Position(x + 1, y)
        case West => Position(x - 1, y)
      }
    }
}
