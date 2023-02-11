import file.{FileIO, PrintJson}
import model.Position

object Main extends App {
  def file: FileIO = new FileIO()
  def json: PrintJson = new PrintJson()
  val instructions = file.readInput()

  val instructionsLawnMower = instructions.drop(1)
  val map = instructions(0)
  val Array(x, y) = map.split(" ").map(_.toInt)

  val manager = Manager(Position(x, y), file.parseFile(instructionsLawnMower))
  manager.execute()


}
