package groceries
import scala.collection.mutable

class Player(val shoppingList: Vector[String]):
  private val _inventory: mutable.Buffer[Item] =
    mutable.Buffer.empty

  def inventory: Vector[Item] = this._inventory.toVector
  def addItem(item: Item): Unit = this._inventory += item
  def removeItem(idx: Int): Option[Item] =
    this._inventory.lift(idx).map(
      item =>
        this._inventory.remove(idx)
        item
    )

end Player