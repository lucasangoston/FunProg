package fr.esgi.al.funprog

//import file.ReadFile
import progfun.model.{Lawn, LawnMower, Movement, Position, SpatialPosition}

object Main extends App {
//  val file: ReadFile = new ReadFile()
//
//  println(file.readInput())


  val initPos = Position(1,2)

  println(initPos.x)
  println(initPos.y)

  println("ATTENTION LES EMBROUILLES COMMENCENT !!!")

  println(initPos.x)
  println(initPos.y)
}
case class Manager(lawnUpperRightCorner: Position, mowersDirectives: List[(SpatialPosition, List[Movement.Value])]) {

  val lawn = Lawn(lawnUpperRightCorner.x, lawnUpperRightCorner.y)
  def finalPrint(): Unit =  { //print la position final des lawnMower
    mowersDirectives.foreach { m =>
      val lawnMower = LawnMower(m._1, lawn)
      val endPosition = lawnMower.doMovements(m._2)
      println(lawn)
      println(m._1)
      println(endPosition)
    }
  }
}
