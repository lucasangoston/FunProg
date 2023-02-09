package model

case class Position(x: Int,y: Int) {

  def setmyvalues(valueX: Int, valueY: Int): Position = {
    Position(valueX, valueY)
  }

}
