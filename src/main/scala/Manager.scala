import model.{Lawn, LawnMower, Movement, Position, SpatialPosition}

case class Manager(
                    lawnUpperRightCorner: Position,
                    mowersDirectives: List[(SpatialPosition, List[Movement.Value])]
                  ) {

  val lawn = Lawn(lawnUpperRightCorner.x, lawnUpperRightCorner.y)

  def finalPrint(): Unit = {
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
    }
  }
}
