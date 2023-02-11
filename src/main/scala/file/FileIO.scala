package file
import better.files._
import com.typesafe.config.{Config, ConfigFactory}
import exception.InvalidDataException
import model.{Movement, Orientation, Position, SpatialPosition}

class FileIO {
  val conf: Config = ConfigFactory.load()

  def readInput(): List[String] = {
    val inputFile: String = conf.getString("application.input-file")

    try {
      val f: File = File(
        inputFile
      )
      f.lines.toList
    } catch {
      case _: Exception => new InvalidDataException("The expected data was not provided or was not in the expected format.")
        List.empty
    }
  }

  def parseFile(fileLines: List[String]): List[(SpatialPosition, List[Movement.Value])] = {
    val instructions = fileLines.grouped(2).map {
      case Seq(position, movement) =>
        val Array(x, y, direction) = position.split(" ")
        (SpatialPosition(Position(x.toInt, y.toInt), Orientation.nameToOrientation(direction.head)), movement.filter(_ != ' ').toList.map( move => Movement.nameToMovement(move)))
    }.toList
    instructions
  }

  def writeContent(content: String, configName: String): Unit = {
    val outputFile: String = conf.getString(configName)

    val f = File(outputFile)

    f.createIfNotExists().appendLines(content)

    ()
  }

  def writeContentInline(content: String, configName: String): Unit = {
    val outputFile: String = conf.getString(configName)

    val f = File(outputFile)

    f.createIfNotExists().append(content)

    ()
  }

  def removeContentJson(configName: String): Unit ={
    val outputFile: String = conf.getString(configName)

    val f = File(outputFile)

    f.createIfNotExists().overwrite("")

    ()
  }
}
