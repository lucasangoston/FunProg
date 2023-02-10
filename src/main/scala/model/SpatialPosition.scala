package model

import model.Movement.{MoveForward, TurnLeft, TurnRight}
import model.Orientation.{East, North, South, West}

case class SpatialPosition(
    position: Position,
    cardinalPoint: Orientation.Value
) {

  val orientationMapping =
    Map(
      North -> Map(MoveForward -> North, TurnLeft -> West, TurnRight  -> East),
      South -> Map(MoveForward -> South, TurnLeft -> East, TurnRight  -> West),
      East  -> Map(MoveForward -> East, TurnLeft  -> North, TurnRight -> South),
      West  -> Map(MoveForward -> West, TurnLeft  -> South, TurnRight -> North)
    )

  def calculateNextSpatialPosition(
      movement: Movement.Value
  ): SpatialPosition = {

    val finalOrientation = orientationMapping(cardinalPoint)(movement)
    movement match {
      case MoveForward => moveForward(finalOrientation)
      case _           => SpatialPosition(position, cardinalPoint)
    }
  }

  def moveForward(finalDirection: Orientation.Value): SpatialPosition = {
    val x = position.x
    val y = position.y
    finalDirection match {
      case North =>  SpatialPosition(Position(x, y + 1), finalDirection)
      case South =>  SpatialPosition(Position(x, y - 1), finalDirection)
      case East  =>  SpatialPosition(Position(x + 1, y), finalDirection)
      case West  =>  SpatialPosition(Position(x - 1, y), finalDirection)
    }
  }
}
