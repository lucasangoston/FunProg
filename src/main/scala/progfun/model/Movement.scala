package progfun.model

object Movement extends Enumeration {

  type Movement = Value

  val TurnLeft = Value("G")
  val TurnRight = Value("D")
  val MoveForward = Value("A")
}
