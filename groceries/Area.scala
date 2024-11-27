package groceries
import scala.collection.mutable

class Area(val name: String,
           val description: String,
           val _items: mutable.Buffer[Item]):
  private val neighbors: mutable.HashMap[Direction, Area] =
    mutable.HashMap.empty

  def addItem(item: Item): Unit =
    this._items += item

  def removeItem(idx: Int): Option[Item] =
    this._items.lift(idx).map(
      item =>
        this._items.remove(idx)
        item
    )

  def setNeighbor(direction: Direction, neighbor: Area):Unit =
    neighbors.update(direction, neighbor)

  override def toString: String = s"$name: $description"
end Area