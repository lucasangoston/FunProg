package model

object Movement extends Enumeration {

  type Movement = Value

  val TurnLeft = Value("G")
  val TurnRight = Value("D")
  val MoveForward = Value("A")

  def nameToMovement(name: Char): Movement = {
    name match {
      case 'G' => TurnLeft
      case 'D' => TurnRight
      case 'A' => MoveForward
    }
  }
}
