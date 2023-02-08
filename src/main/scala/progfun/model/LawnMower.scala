package progfun.model

import progfun.model.Orientation.{Orientation, calculateRotating}

class LawnMower(orientation: Orientation,position: Position, actionList: List[Char] 'A','G') {

  def move(): Unit = {
    actionsList.foreach(n => martching(n))
  }
}

