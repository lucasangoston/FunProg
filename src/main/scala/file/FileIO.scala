package file
import better.files._
import model.{OrientationReal, Position, SpatialPosition}

class FileIO {

  def readInput() : List[String] = {
    val f: File = File("/Users/lucasangoston/Downloads/projet 2/funprog-al/intructions.txt")

    f.lines.toList
  }

  def parseFile(fileLines: List[String]): List[(SpatialPosition, List[Char])] = {
    val instructions = fileLines.grouped(2).map {
      case Seq(position, movement) =>
        val Array(x, y, direction) = position.split(" ")
        (SpatialPosition(Position(x.toInt, y.toInt), OrientationReal.nameToOrientation(direction.head)), movement.filter(_ != ' ').toList)
    }.toList
    instructions
  }
}
