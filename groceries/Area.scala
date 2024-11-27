package groceries
import scala.collection.mutable

class Area(val name: String,
           val description: String):

  private val _items: mutable.Buffer[Item] = mutable.Buffer()
  private val _neighbors: mutable.HashMap[Direction, Area] =
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
    this._neighbors.update(direction, neighbor)
    
  def neighbors: Map[Direction, Area] = this._neighbors.toMap
    
  def traverse(path: Vector[Direction]): Option[Area] =
    path.headOption
      .map(this._neighbors.get)
      .flatMap(_.flatMap(_.traverse(path.tail)))

  override def toString: String = s"$name: $description"
end Area

object Area:
  def map: Area =
    val house         = Area("House", "Home sweet home. Not if you don't complete your mom's request,\nshe probably will ground you!")
    val mainStreet    = Area("Main Street", "Connects with many public spaces. Be sure to watch out for cars passing by!")
    val sideStreet    = Area("Side Street", "A smaller street, running along some houses, a park, and behind \na supermarket.")
    val supermarket   = Area("Supermarket", "You can literally find anything in here.")
    val park          = Area("Park", "The local park with a few amenities for children to play in.")
    val oldPort       = Area("Old Port", "A good place for fishing and finding dropped items.")
    val beach         = Area("Beach", "Swim, relax, find seashells, build sandcastles; it's chill here.")
    
    house.setNeighbor(Direction.West, sideStreet)
    house.setNeighbor(Direction.South, mainStreet)
    
    mainStreet.setNeighbor(Direction.North, house)
    mainStreet.setNeighbor(Direction.East, beach)
    mainStreet.setNeighbor(Direction.South, oldPort)
    mainStreet.setNeighbor(Direction.West, supermarket)
    
    sideStreet.setNeighbor(Direction.East, house)
    sideStreet.setNeighbor(Direction.West, park)
    sideStreet.setNeighbor(Direction.South, supermarket)
    
    supermarket.setNeighbor(Direction.North, sideStreet)
    supermarket.setNeighbor(Direction.East, mainStreet)
    supermarket.setNeighbor(Direction.West, park)
    
    park.setNeighbor(Direction.East, sideStreet)
    park.setNeighbor(Direction.South, supermarket)
    
    oldPort.setNeighbor(Direction.North, mainStreet)
    oldPort.setNeighbor(Direction.East, beach)
    
    beach.setNeighbor(Direction.West, mainStreet)
    beach.setNeighbor(Direction.South, oldPort)
    
    house

end Area