package model

object Orientation extends Enumeration {

  type Orientation = Value

  val North = Value("N")
  val South = Value("S")
  val East = Value("E")
  val West = Value("W")

  def nameToOrientation(name: Char): Orientation = {
    name match {
      case 'N' => North
      case 'E' => East
      case 'S' => South
      case 'W' => West
    }
  }
}
