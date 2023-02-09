import file.FileIO
import model.Lawn

object Main extends App {
  def file: FileIO = new FileIO()

  //full fichier
  val instructions = file.readInput()

  //partie concernant les tondeuses
  val instructionsLawnMower = instructions.drop(1)

  //create Lawn
  val map = instructions(0)
  val Array(x, y) = map.split(" ").map(_.toInt)
  val lawn = Lawn(x, y)


  println(lawn)
  println(file.parseFile(instructionsLawnMower))

}
