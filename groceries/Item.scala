package groceries

import scala.util.Random

trait Item(val name: String, val description: String):
  def onExpire: String
  def use(game: Game): String
  def hasExpired: Boolean
  override def toString: String = s"$name: $description"
end Item

class RegItem(name: String, description: String) extends Item(name, description):
  override def onExpire: String = ???
  override def use(game: Game): String = "You can't use that item!"
  val hasExpired = false
end RegItem

class Phone(name: String, description: String, private var uses: Int = 2) extends Item(name, description):
  def hasExpired = uses == 0

  override def onExpire: String = "Your phone explodes as it hears you murmur about the Chinese government."
  override def use(game: Game): String = uses match
    case 0 => "Your mom gets sick of your constant asking and refuses to answer."
    case number =>
      this.uses -= 1
      Random.nextInt(10) match
        case 0 | 2 | 1 | 5 =>
          game.addTurns(5)
          "Your mom allows you to take more time. 5 turns have been added."
        case other => "Your mom finds the time given perfectly fine and refuses to give you more time."
end Phone