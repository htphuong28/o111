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

  def pickUp(idx: Int): Boolean =
    this.playerArea.removeItem(idx).map(this.player.addItem).isDefined

  def drop(idx: Int): Boolean =
    this.player.removeItem(idx).map(this.playerArea.addItem).isDefined

end Game
