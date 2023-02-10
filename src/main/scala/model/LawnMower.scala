package model

case class LawnMower(initialSpatialPosition: SpatialPosition, lawn: Lawn) {
  require(
    lawn.inBound(initialSpatialPosition.position),
    "The lawnmower must be in the lawn bounds"
  )

  def doMovements(moves: List[Movement.Value]): SpatialPosition = {
    move(initialSpatialPosition, moves)
  }

  def move(
      pos: SpatialPosition,
      movements: List[Movement.Value]
  ): SpatialPosition = {
    if (movements == Nil) {
      pos
    } else {
      val calculatedNextPos = pos.calculateNextSpatialPosition(movements(0))
      if (lawn.inBound(calculatedNextPos.position)) {
        val nextPos = SpatialPosition(calculatedNextPos.position, calculatedNextPos.cardinalPoint)
        move(nextPos, movements.drop(1))
      } else {
        move(pos, movements.drop(1))
      }
    }
  }
}
