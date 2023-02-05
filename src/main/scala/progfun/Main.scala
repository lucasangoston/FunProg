package fr.esgi.al.funprog

import file.ReadFile

object Main extends App {
  def file: ReadFile =  new ReadFile()

  println(file.readInput())
}
