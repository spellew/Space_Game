package gamestuff

import com.tarena.shout.ShootGame

class Bullet(val x: Int, val y: Int){
  this.x = x
  this.y = y
  this = ShootGame.bullet
  private val speed = 3

  @Override
  def step(): Unit = {
    y -= speed
  }

  @Override
  def outOfBounds: Boolean = {
    y < -height
  }
}