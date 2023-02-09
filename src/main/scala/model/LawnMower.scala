package model

case class LawnMower(initialSpatialPosition: SpatialPosition, lawn: Lawn) {
  require(lawn.inBound(initialSpatialPosition.position), "The lawnmower must be in the lawn bounds")

  def doMovements(moves: List[Movement.Value]): SpatialPosition = {
    move(initialSpatialPosition, moves)
  }

  def move(pos: SpatialPosition, movements: List[Movement.Value]): SpatialPosition = {
    if (movements == Nil) {
      pos
    }
    else {
      val calculatedNextPos = pos.calculateNextSpatialPosition(movements(0))

      //      val onLawnCoor = lawn.keepCoordinateOnTheLawn(calculatedNextPos.position) // faire le boolean si else ne pas faire le movement
      if(lawn.inBound(calculatedNextPos.position)){

        val nextPos = SpatialPosition(pos.position, calculatedNextPos.cardinalPoint)
        move(nextPos, movements.drop(1))

      }else {
        move(pos, movements.drop(1))
      }
    }
  }
}
