package gamestuff
import physics._

class ship(playerName: String, movement: physics.PhysicalObject, lives: Int) {

  protected var x = 0 //x
  protected var y = 0 //y
  protected var width = 0 //width
  protected var height = 0 //height
  protected var image: Null = null
  def getX: Int = {
    x
  }

  def setX(x: Int): Unit = {
    this.x = x
  }

  def getY: Int = {
    y
  }

  def setY(y: Int): Unit = {
    this.y = y
  }

  def getWidth: Int = {
    width
  }

  def setWidth(width: Int): Unit = {
    this.width = width
  }

  def getHeight: Int = {
    height
  }

  def setHeight(height: Int): Unit = {
    this.height = height
  }

  def getImage: Unit = {
    image
  }

  def setImage(image: Nothing): Unit = {
    this.image = image
  }

  /**
    * @param bullet(x,y)
    * @return true means shoted
    */
  def shootBy(bullet: Bullet): Boolean = {
    val x = bullet.x
    val y = bullet.y
    this.x < x && x < this.x + width && this.y < y && y < this.y + height
  }

  /**
    * getScore
    */
  @Override
  def getScore: Int = {
    5
  }

  /**
    * outOfBound
    */
  @Override
  def outOfBounds: Boolean = {
    x > ShootGame.WIDTH  || y > ShootGame.HEIGHT
  }

  /**
    * move
    */
  @Override
  def step(): Unit ={
    y += movement.velocity.y
    x += movement.velocity.x
  }

}
