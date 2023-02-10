package model

case class Lawn(width: Int, length: Int) {
  require(width >= 0, "a lawn must have a positive width")
  require(length >= 0, "a lawn must have a positive length")
  def inBound(pos: Position): Boolean = {
    pos.x < width - 1 && pos.y < length - 1
  }
}
