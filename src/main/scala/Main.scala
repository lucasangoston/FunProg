import file.FileIO
import model.Position

object Main extends App {
  def file: FileIO = new FileIO()
  val instructions = file.readInput()

  val instructionsLawnMower = instructions.drop(1)
  val map = instructions(0)
  val Array(x, y) = map.split(" ").map(_.toInt)

  val manager = Manager(Position(x, y), file.parseFile(instructionsLawnMower))
  manager.finalPrint()
}
