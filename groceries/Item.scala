package groceries

class Item(val name: String, val description: String):
  override def toString: String = s"$name: $description"
end Item