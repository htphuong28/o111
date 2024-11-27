package groceries

import scala.collection.mutable

class Game:
  val player: Player = Player(Vector(
    "cake", "dollhouse", "coke", "snack", "balloons"
  ))
  private val map: Area = Area.map
  private val playerPath: mutable.Buffer[Direction] = mutable.Buffer.empty
  private var _turnsLeft = 20
  
  def turnsLeft: Int = this._turnsLeft
  
  def addTurns(amount: Int): Unit = _turnsLeft += amount
  
  def playerArea: Area =
    this.map.traverse(this.playerPath.toVector) match
      case Some(area) => area
      case None => ???

  def move(direction: Direction): Boolean =
    if this.turnsLeft <= 0 then false
    else if this.playerArea.neighbors.contains(direction) then
      this.playerPath.append(direction)
      this._turnsLeft -= 1
      true
    else false

  def pickUp(idx: Int): Boolean =
    this.playerArea.removeItem(idx).map(this.player.addItem).isDefined

  def examine(idx: Int): Boolean =
    this.player.inventory.lift(idx).isDefined
  
  def drop(idx: Int): Boolean =
    this.player.removeItem(idx).map(this.playerArea.addItem).isDefined

  def use(idx: Int): Option[String] =
    this.player.inventory.lift(idx).flatMap({
      case item if item.hasExpired =>
        this.player.removeItem(idx)
        None
      case item =>
        val msg = item.use(this)
        if item.hasExpired then
          this.player.removeItem(idx)
          Some(msg + "\n" + item.onExpire)
        else Some(msg)
    })

  def hasWon: Boolean =
    this.playerArea.name == "House" &&
      this.player.shoppingList.forall(this.player.inventory.contains)
    
  def hasLost: Boolean =
    this.turnsLeft <= 0 &&
      (this.playerArea.name != "House" ||
        !this.player.shoppingList.forall(this.player.inventory.contains))

end Game
