package file

import model.{Movement, SpatialPosition}

class JsonParsing {

  def parseLimit(x: Int, y: Int): String = {
    val originalString = "{\n\t\"limite\": {\n\t\t\"x\": ,\n\t\t\"y\": \n\t}, \n\t\"tondeuses\":[ "

    val xString = x.toString
    val yString = y.toString
    val parseValue = originalString.replace("\"x\": ", s"""\"x\": $xString""")
      .replace("\"y\": ", s"""\"y\": $yString""")


    parseValue
  }

  def parseLawnMower(initPosition: SpatialPosition, instructions: List[Movement.Value], endPosition: SpatialPosition): String = {
    val originalStringBegin = "\t\t{\n\t\t\t\"debut\": {\n\t\t\t\t\"point\": { \n\t\t\t\t\t\"x\": , \n\t\t\t\t\t\"y\":  \n\t\t\t\t}, \n\t\t\t\t\"direction\":  \n\t\t\t }, \n\t\t\t\"instructions\": , \n\t\t\t"

    val initX = initPosition.position.x.toString
    val initY = initPosition.position.y.toString
    val initOrientation = initPosition.cardinalPoint.toString
    val instructionToString = instructions.mkString

    val parseValueBegin = originalStringBegin.replace("\"x\": ,", s"""\"x\": $initX,""")
                                          .replace("\"y\": ", s"""\"y\": $initY""")
                                          .replace("\"direction\": ", s"""\"direction\": \"$initOrientation\"""")
                                          .replace("\"instructions\": ,", s"""\"instructions\": \"$instructionToString\",""")


    val originalStringEnd = "\"fin\": { \n\t\t\t\t\"point\": { \n\t\t\t\t\t\"x\": , \n\t\t\t\t\t\"y\":  \n\t\t\t\t }, \n\t\t\t\t\"direction\":  \n\t\t\t} \n\t\t}"

    val endX = endPosition.position.x.toString
    val endY = endPosition.position.y.toString
    val endOrientation = endPosition.cardinalPoint.toString

    val parseValueEnd = originalStringEnd.replaceFirst("\"x\": ,", s"""\"x\": $endX,""")
                                  .replaceFirst("\"y\": ", s"""\"y\": $endY""")
                                  .replaceFirst("\"direction\": ", s"""\"direction\": \"$endOrientation\"""")

    val parseValue = parseValueBegin + parseValueEnd

    parseValue
  }

  def parseEndJson(): String = {
    val originalStringEnd = "\t] \n}"

    originalStringEnd
  }

}
