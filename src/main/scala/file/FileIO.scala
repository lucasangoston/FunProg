package file
import better.files._
import model.{Movement, Orientation, Position, SpatialPosition}

class FileIO {

  def readInput(): List[String] = {
    val f: File = File(
      "intructions.txt"
    )

    f.lines.toList
  }

  def parseFile(fileLines: List[String]): List[(SpatialPosition, List[Movement.Value])] = {
    val instructions = fileLines.grouped(2).map {
      case Seq(position, movement) =>
        val Array(x, y, direction) = position.split(" ")
        (SpatialPosition(Position(x.toInt, y.toInt), Orientation.nameToOrientation(direction.head)), movement.filter(_ != ' ').toList.map( move => Movement.nameToMovement(move)))
    }.toList
    instructions
  }
}
