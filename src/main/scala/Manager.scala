import Main.json
import file.{FileIO, PrintCsv}
import model.{Lawn, LawnMower, LawnMowerResult, Movement, Position, SpatialPosition}

case class Manager(lawnUpperRightCorner: Position, mowersDirectives: List[(SpatialPosition, List[Movement.Value])]) {

  val lawn = Lawn(lawnUpperRightCorner.x, lawnUpperRightCorner.y)
  val file: FileIO = new FileIO()


  def execute(): Unit = {
    val lawnMowerResults = mowersDirectives.map({ m =>
      val lawnMower = LawnMower(m._1, lawn)
      val endPosition = lawnMower.doMovements(m._2)
      LawnMowerResult(m._1, endPosition, m._2)
    })
    val printer = new PrintCsv()

    printer.writeCsv(lawnMowerResults)(printer.lawnMowerResultCsvConverter)

    createJson(lawnMowerResults)
  }


  private def createJson(lawnMowerResults: List[LawnMowerResult]): Unit = {
    val configName = "application.output-json-file"
    file.removeContentJson(configName)
    file.writeContent(json.parseLimit(lawn.width, lawn.length), configName)
    lawnMowerResults.zipWithIndex.foreach { case (m, index) =>
      if (index == lawnMowerResults.size - 1) {
        file.writeContent(json.parseLawnMower(m.initPosition, m.movements, m.lastPosition), configName)
      } else {
        file.writeContent(json.parseLawnMower(m.initPosition, m.movements, m.lastPosition), configName)
        file.writeContent(",", configName)
      }
    }
    file.writeContent(json.parseEndJson(), configName)
  }
}
