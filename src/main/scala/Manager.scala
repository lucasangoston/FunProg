import Main.json
import file.{PrintCsv}
import model.{Lawn, LawnMower, LawnMowerResult, Movement, Position, SpatialPosition}

case class Manager(lawnUpperRightCorner: Position, mowersDirectives: List[(SpatialPosition, List[Movement.Value])]) {

  val lawn = Lawn(lawnUpperRightCorner.x, lawnUpperRightCorner.y)

  def execute(): Unit = {
    val lawnMowerResults = mowersDirectives.map({ m =>
      val lawnMower = LawnMower(m._1, lawn)
      val endPosition = lawnMower.doMovements(m._2)
      LawnMowerResult(m._1, endPosition, m._2)
    })
    val printer = new PrintCsv()
    printer.writeCsv(lawnMowerResults)(printer.lawnMowerResultCsvConverter)
    println(json.parseLimit(lawn.width,lawn.length))
    lawnMowerResults.foreach{ m =>
      println(json.parseLawnMower(m.initPosition, m.movements, m.lastPosition))
    }
    println(json.parseEndJson())
  }
}
