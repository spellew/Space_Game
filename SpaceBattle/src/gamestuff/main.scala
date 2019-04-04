package gamestuff

import java.awt.Graphics
import java.awt.event.{MouseAdapter, MouseEvent}
import java.awt.image.BufferedImage
import java.util
import java.util.{Timer, TimerTask}
import javax.swing.{JFrame, JPanel}

import scala.collection.mutable.ListBuffer

object main {



  def removeLoser(playerList: ListBuffer[String], playerName: String): Unit ={
    for(i <- playerList){
      if(playerName == i){
        playerList -= playerName
      }
    }
  }

  def determineLoser(playerList: ListBuffer[ship]): ship ={
    for(i <- playerList) {
      if (i <= 0) {

      }
      else {
        null
      }
    }
  }

  def newShip(shipName: String): ship ={
    // var shipList: List[ship] = new List[ship]
    // var index: Int = 0
    var newOne: ship = new ship(shipName, playerList, 9)
    //    for(i <- playerList){
    //      i = new ship
    //      shipList[index] = i
    //    }

  }

  def main(args: Array[String]): Unit = {
    val game = new ShootGame


    val frame = new JFrame("game begin")

    frame.add(game)
    frame.setSize(WIDTH, HEIGHT)
    frame.setAlwaysOnTop(true)
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    frame.setLocationRelativeTo(null)
    frame.setVisible(true)

    var player: ship = new ship()
    game.action()
  }
}



}
