package groceries

import scala.collection.mutable

class Game:
  val player: Player = Player()
  private val map: Area = Area.map
  private val playerPath: mutable.Buffer[Direction] = mutable.Buffer.empty
  private var turnsLeft = 30

  def playerArea: Area =
    this.map.traverse(this.playerPath.toVector) match
      case Some(area) => area
      case None => ???

  def move(direction: Direction): Boolean =
    if this.turnsLeft <= 0 then false
    else if this.playerArea.neighbors.contains(direction) then
      this.playerPath.append(direction)
      this.turnsLeft -= 1
      true
    else false

  def pickUp(idx: Int): Boolean =
    this.playerArea.removeItem(idx).map(this.player.addItem).isDefined

  def drop(idx: Int): Boolean =
    this.player.removeItem(idx).map(this.playerArea.addItem).isDefined
    
  def hasWon: Boolean =
    this.playerArea.name == "House" &&
      this.player.shoppingList.forall(this.player.inventory.contains)
    
  def hasLost: Boolean =
    this.turnsLeft <= 0 &&
      (this.playerArea.name != "House" ||
        !this.player.shoppingList.forall(this.player.inventory.contains))

end Game
