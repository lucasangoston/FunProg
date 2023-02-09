package file
import better.files._
import model.Position

class FileIO {

  def readInput() : List[String] = {
    val f: File = File("/Users/lucasangoston/Downloads/projet 2/funprog-al/intructions.txt")

    f.lines.toList
  }

  // je te renvois un truc de la forme : List((N,Position(1,2),List(G, A, D, A)), (S,Position(3,0),List(D, D, D, G, A)))
  def parseFile(fileLines: List[String]): List[(Char, Position, List[Char])] = {
    val instructions = fileLines.grouped(2).map {
      case Seq(position, movement) => val Array(x, y, direction) = position.split(" ")
        (direction.head, Position(x.toInt, y.toInt), movement.filter(_ != ' ').toList)
    }.toList
    instructions
  }
}
