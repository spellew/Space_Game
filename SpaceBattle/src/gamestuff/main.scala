package gamestuff

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
      if ( <= 0) {
        i
      }
      else {
        null
      }
    }
  }

  def newShip(playerName: String): ship ={
    var player: ship =
  }


}
