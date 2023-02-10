import file.FileIO
import model.{Lawn, LawnMower, Movement, Position, SpatialPosition}

object Main extends App {
  def file: FileIO = new FileIO()

  //full fichier
  val instructions = file.readInput()

  //partie concernant les tondeuses
  val instructionsLawnMower = instructions.drop(1)

  //create Lawn
  val map = instructions(0)
  val Array(x, y) = map.split(" ").map(_.toInt)
  val lawn = Lawn(x, y)

  val manager = Manager(Position(x, y), file.parseFile(instructionsLawnMower))
  manager.finalPrint()
}

case class Manager(
    lawnUpperRightCorner: Position,
    mowersDirectives: List[(SpatialPosition, List[Movement.Value])]
) {

  val lawn = Lawn(lawnUpperRightCorner.x, lawnUpperRightCorner.y)
  def finalPrint(): Unit = { //print la position final des lawnMower
//    1 2 N
//      G A D A
//    3 0 S
//      D D D G A
//    val turnLeft = Movement.TurnLeft
//    val turnRight = Movement.TurnRight
//    val moveForward = Movement.MoveForward
//    val movements = turnLeft :: (turnRight :: (moveForward :: Nil))

    mowersDirectives.foreach { m =>
      val lawnMower = LawnMower(m._1, lawn)
      print("MOVEMENTS : ")
      println(m._2)
      println()
      print("INIT position : ")
      println(m._1)
      val endPosition = lawnMower.doMovements(m._2)
      println()
      print("END position : ")
      println(endPosition)
      println()
      println()
      println()
    }
  }
}
