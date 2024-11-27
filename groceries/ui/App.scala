package groceries.ui

import groceries.Game
import scala.io.StdIn

@main def main(): Unit =
  val game = Game()

  println("It is your sister's birthday, and your mom assigned you to buy:")
  println(game.player.shoppingList.mkString(", ").trim)
  println("Be sure to bring them back in time, or your mom will ground you!")
  println("To start, pick up the phone. If you need help remembering the list,")
  println("Mom might tell you. Then go out and do your task!")
  println("-------------------------------------------------------------------")

  while !game.hasWon && !game.hasLost do
    println(game.playerArea.toString)
    Command.parse(StdIn.readLine("> ")) match
      case Some(command) =>
        println(s"You have ${game.turnsLeft} turn(s) left before mom becomes Angry Bird.")
        command.execute(game)
      case None => println("Invalid command. See 'help' for a list of commands.")

  if game.hasWon then
    println("You bought enough stuffs to host your sister's birthday party.")
    println("Your sister is happy and your mom has decided not to ground you... for now.")
    println("Happy birthday, sis!")
  else
    println("Whoops! Unfortunately, your sister looked foward to this party a lot...")
    println("Now it's all ruined! She's sobbing and your mom got very, VERY, mad!")
    println("In the end, you got grounded for 2 whole months!")