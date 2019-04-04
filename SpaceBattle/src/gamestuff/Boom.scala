package gamestuff

import java.util.Random


class Boom(){
  this.image = ShootGame.Boom
  width = image.getWidth
  height = image.getHeight
  y = -height
  val rand = new Random
  x = rand.nextInt(ShootGame.WIDTH - width)
  awardType = rand.nextInt(2)
  private var xSpeed = 1 //x move
  private val ySpeed = 2 //y move
 // private var awardType = 0
  //get award type
 // def getType: Int = awardType

  def outOfBounds: Boolean = y > ShootGame.HEIGHT

  def step(): Unit = {
    x += xSpeed
    y += ySpeed
    if (x > ShootGame.WIDTH - width) xSpeed = -1
    if (x < 0) xSpeed = 1
  }
}