package gamestuff

import physics.PhysicsVector

class Player(){

  life = 9
  doubleFire = 0
  var x: Int = 160
  var y: Int = 300
  //set  double fire , life
  private var index = 0
  private var doubleFire = 0
  private var life = 0
  def isDoubleFire: Int = doubleFire

  //set double fire value
  def setDoubleFire(doubleFire: Int): Unit = this.doubleFire = doubleFire

  //add double fire
  def addDoubleFire(): Unit = doubleFire = 44

  //add life value
  def addLife(): Unit = {
    life += 1
  }

  //subtract life value
  def subtractLife(): Unit = {
    life -= 1
  }

  //get life value
  def getLife: Int = life

  //move position
  def moveTo(x: Int, y: Int): Unit = {
    x = x - width / 2
    y = y - height / 2
  }

  //CHECK for outOfBound
  def outOfBounds = false

  //shot
  //  def shoot: Array[Nothing] = {
  //    val xStep = width / 2
  //    val yStep = 40
  //    if (doubleFire > 0) {
  //      val bullets = new Array[Nothing](2)
  //      bullets(0) = new Nothing(x + xStep, y - yStep)
  //      bullets(1) = new Nothing(x + 3 * xStep, y - yStep)
  //      bullets
  //    } else {
  //      val bullets = new Array[Nothing](1)
  //      bullets(0) = new Nothing(x + 2 * xStep, y - yStep)
  //      bullets
  //    }
  //  }

  //algorithm for hit
  def hit(other: PhysicsVector): Boolean = {
    val x1 = other.x - this.x / 2
    val x2 = other.x + this.x / 2 + other.x
    val y1 = other.y - this.y / 2
    val y2 = other.y + this.y / 2 + other.y
    //define the distence
    val Playerx = x + this.x / 2
    val Playery = y + this.y / 2
    //check the hit
    Playerx > x1 && Playerx < x2 && Playery > y1 && Playery < y2
  }
}