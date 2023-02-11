import exception.InvalidDataException
import file.{FileIO, JsonParsing}
import model.Position

object Main extends App {
  val file: FileIO = new FileIO()
  def json: JsonParsing = new JsonParsing()
  val instructions = file.readInput()

  val result = try {
    val instructionsLawnMower = instructions.drop(1)
    val map = instructions(0)
    val Array(x, y) = map.split(" ").map(_.toInt)

    val manager = Manager(Position(x, y), file.parseFile(instructionsLawnMower))
    manager.execute()
    Right(())
  } catch {
    case _: Exception => Left(new InvalidDataException("An error occurred while reading the input file."))
  }

  result match {
    case Right(_) =>
    // Do nothing
    case Left(error) => println(s"${error.getMessage}")
  }
}



