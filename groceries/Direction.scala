package groceries

enum Direction:
  case North
  case West
  case South
  case East

  def opposite: Direction =
    this match
      case North => South
      case West  => East
      case South => North
      case East  => West
end Direction