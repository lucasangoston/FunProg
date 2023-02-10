package file

import play.api.libs.json.{JsValue, Json}

class PrintOut {

  def printToJSON(result: Unit): JsValue = {
      Json.toJson(result)
  }

  def printToCSV(): Unit = {

  }
}
