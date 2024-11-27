package groceries

import scala.collection.mutable

class Game():
  private val player: Player = Player()
  private val map: Area = Area.map
  private val playerPath: mutable.Buffer[Direction] = mutable.Buffer.empty

  def playerArea: Area =
    this.map.traverse(this.playerPath.toVector) match
      case Some(area) => area
      case None => ???

  def move(direction: Direction): Boolean =
    if this.playerArea.neighbors.contains(direction) then
      this.playerPath.append(direction)
      true
    else false

end Game
