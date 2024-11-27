package groceries.ui

import groceries.Direction
import groceries.ui.Command.Move

enum Command(repr: String):
  case Status extends Command("status")
  case Take(id: Int) extends Command("take")
  case Move(direction: Direction) extends Command("move")
end Command

object Command:
  def parse(s: String): Option[Command] =
    val argv = s.trim.split(' ')
    argv.headOption.map(_.toLowerCase).flatMap({
      case "status" => Some(Command.Status)
      case "take" =>
        argv.tail.headOption.flatMap(_.toIntOption).map(Command.Take.apply)
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