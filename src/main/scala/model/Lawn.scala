package model

case class Lawn(width: Int, lenght: Int) {

  def inBound(pos: Position): Boolean = {
    pos.x < width-1 && pos.y < lenght -1
  }
}
