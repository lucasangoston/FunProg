package file

import model.{Lawn, LawnMower, LawnMowerResult, Movement, Position, SpatialPosition}

trait CsvConverter[T] {
  def toCsv(t: T): Unit
}

class PrintCsv {

  implicit val positionCsvConverter = new CsvConverter[Position] {
    def toCsv(pos: Position): Unit = {
      print(pos.x)
      print(";")
      print(pos.y)
    }
  }

  implicit val spatialPositionCsvConverter = new CsvConverter[SpatialPosition] {
    def toCsv(spatialPos: SpatialPosition): Unit = {
      positionCsvConverter.toCsv(spatialPos.position)
      print(";")
      print(spatialPos.cardinalPoint)
    }
  }

  implicit val lawnMowerCsvConverter = new CsvConverter[LawnMower] {
    def toCsv(lawnMower: LawnMower): Unit = {
      lawnCsvConverter.toCsv(lawnMower.lawn)
      print(";")
      spatialPositionCsvConverter.toCsv(lawnMower.initialSpatialPosition)
    }
  }

  implicit val lawnCsvConverter = new CsvConverter[Lawn] {
    def toCsv(lawn: Lawn): Unit = {
      print(lawn.width)
      print(";")
      print(lawn.length)
    }
  }

  implicit val lawnMowerResultCsvConverter = new CsvConverter[LawnMowerResult] {
    def toCsv(lawnMowerResult: LawnMowerResult): Unit = {
      spatialPositionCsvConverter.toCsv(lawnMowerResult.initPosition)
      print(";")
      spatialPositionCsvConverter.toCsv(lawnMowerResult.lastPosition)
      print(";")
      movementsListCsvConverter.toCsv(lawnMowerResult.movements)
    }
  }

  implicit val movementsListCsvConverter = new CsvConverter[List[Movement.Value]] {
    def toCsv(movements: List[Movement.Value]): Unit = {
      movements.foreach(move => print(move))
    }
  }

  def writeCsv[T](data: List[T])(implicit csvConverter: CsvConverter[T]): Unit = {
    println("numéro;début_x;début_y;début_direction;fin_x;fin_y;fin_direction;instructions")
    data.zip(1 to data.length).foreach { case (d, i) =>
      print(i)
      print(";")
      csvConverter.toCsv(d)
      println()
    }
  }
}