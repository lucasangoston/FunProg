package progfun.model

import progfun.model.Lawn

//class LawnMower(orientation: Orientation,position: Position, actionList: List[Char] 'A','G') {
case class LawnMower(spatialPosition: SpatialPosition, lawn: Lawn) {
  require(lawn.inBound(spatialPosition.pos), "The lawnmower must be in the lawn bounds")

  def doMovements(moves: List[Movement.Value]): SpatialPosition = {
    def move(pos: SpatialPosition, movements: List[Movement.Value]): SpatialPosition = {
      if (movements == Nil) {
        pos
      }
      else {
        val calculatedNextPos = pos.calculateNextPosition(movements.head)
        val onLawnCoor = lawn.keepCoordinateOnTheLawn(calculatedNextPos.pos) // faire le boolean si else ne pas faire le movement
        val nextPos = SpatialPosition(onLawnCoor, calculatedNextPos.cardinalPoint)
        move(nextPos, movements.tail)
      }
    }
    move(spatialPosition, moves)
  }
}

