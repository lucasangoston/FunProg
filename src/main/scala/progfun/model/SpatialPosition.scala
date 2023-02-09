package progfun.model

import progfun.model.Movement.{MoveForward, TurnLeft, TurnRight}
import progfun.model.OrientationReal.{East, North, South, West}

case class SpatialPosition(pos: Position, cardinalPoint: OrientationReal.Value) {

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

  def calculateNextPosition(movement: Movement.Value): SpatialPosition = {
    val finalOrientation = orientationMapping(cardinalPoint)(movement)
    val finalPos = movement match {
      case MoveForward => moveForward(finalOrientation)
      case _ => pos
    }
    SpatialPosition(finalPos, finalOrientation)
  }

  def moveForward(finalDirection: OrientationReal.Value): Position = {
    val x = pos.x
    val y = pos.y
    finalDirection match {
      case North => Position(x, y + 1)
      case South => Position(x, y - 1)
      case East => Position(x + 1, y)
      case West => Position(x - 1, y)
    }
  }

  override def toString = {
    this.pos.x + " " + this.pos.y + " " + this.cardinalPoint
  }
}
