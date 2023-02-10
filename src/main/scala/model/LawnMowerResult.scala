package model

case class LawnMowerResult(initPosition: SpatialPosition, lastPosition: SpatialPosition, movements: List[Movement.Value]) {
}
