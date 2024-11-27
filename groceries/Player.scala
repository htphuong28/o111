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

  override def toString: String =
    val task = "Your task is to buy these for your sister's birthday:\n" + this.shoppingList.mkString(", ").trim
    val inventory = "You have:\n" + this.inventory.mkString("\n").trim
    task + "\n" + inventory 
  
end Player