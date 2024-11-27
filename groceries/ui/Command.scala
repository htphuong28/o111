package groceries.ui

import groceries.{Direction, Game}
import groceries.ui.Command.Move

enum Command(repr: String):
  case Status extends Command("status")
  case PickUp(id: Int) extends Command("pick-up")
  case Move(direction: Direction) extends Command("move")
  case Drop(id: Int) extends Command("drop")
  
  def execute(game: Game): Unit =
    this match
      case Status => println(game.player)
      case PickUp(id) => game.pickUp(id)
      case Move(direction) => game.move(direction)
      case Drop(id) => game.drop(id)

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