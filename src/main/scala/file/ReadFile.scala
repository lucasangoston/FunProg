package file
import better.files._

class ReadFile {

  def readInput() : String = {
    val f: File = File("/Users/lucasangoston/Downloads/projet 2/funprog-al/intructions.txt")

    f.contentAsString
  }

}
