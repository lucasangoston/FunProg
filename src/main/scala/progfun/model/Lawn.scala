package progfun.model

case class Lawn(width: Int, length: Int) {
  require(width >= 0, "a lawn must have a positive width")
  require(length >= 0, "a lawn must have a positive length")

  def keepCoordinateOnTheLawn(pos: Position): Position = {
    val x = manageOutboundCoordinates(pos.x, width)
    val y = manageOutboundCoordinates(pos.y, length)
    Position(x, y)
  }

  private def manageOutboundCoordinates(a: Int, upperLimit: Int) = {
    if (a < 0)
      0
    else if (a > upperLimit)
      upperLimit
    else a
  }
  def inBound(pos: Position): Boolean = {
    pos.x < width-1 && pos.y < length -1
  }
}
