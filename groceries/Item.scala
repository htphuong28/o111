package groceries

class Item(val name: String, val price: Int):
  override def toString: String = s"$name: $price"
end Item