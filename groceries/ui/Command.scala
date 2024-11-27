package groceries.ui

import groceries.{Direction, Game}
import groceries.ui.Command.Move

enum Command(repr: String):
  case Status extends Command("status")
  case PickUp(id: Int) extends Command("pick-up")
  case Move(direction: Direction) extends Command("move")
  case Drop(id: Int) extends Command("drop")
  case Examine(id: Int) extends Command("examine")
  case Help extends Command("help")
  case Quit extends Command("quit")
  
  def execute(game: Game): Unit =
    this match
      case Status => println(game.player)
      case PickUp(id) =>
        println(if game.pickUp(id) then
                s"You picked up the ${game.player.inventory.last.name}."
                else s"There's no ${game.playerArea.items(id)} to pick up here!")
      case Move(direction) => 
        println(if game.move(direction) then
                val textDir = direction.toString.toLowerCase      
                s"You move ${textDir}."
                else "You can't move there!")
      case Drop(id) => 
        println(if game.drop(id) then
                s"You dropped the ${game.playerArea.items.last.name}."
                else s"You don't have ${game.player.inventory(id)} in your inventory!")
      case Examine(id) =>
        println(if game.examine(id) then
                game.playerArea.items(id).description
                else s"You don't have ${game.player.inventory(id)} in your inventory!")
      case Help =>
        println("Available commands: status, move <direction>, pick-up <index>, drop <index>, examine <index>, quit, help")
      case Quit =>
        println("You quitted the game.")
        System.exit(0)
end Command

object Command:
  def parse(s: String): Option[Command] =
    val argv = s.trim.split(' ')
    argv.headOption.map(_.toLowerCase).flatMap({
      case "status" => Some(Command.Status)
      case "pick-up" =>
        argv.tail.headOption.flatMap(_.toIntOption).map(Command.PickUp.apply)
      case "move" =>
        argv.tail.headOption.map(_.toLowerCase).flatMap({
          case "north" => Some(Direction.North)
          case "south" => Some(Direction.South)
          case "east" => Some(Direction.East)
          case "west" => Some(Direction.West)
          case _ => None
        }).map(Command.Move.apply)
      case _ => None
    })

end Command