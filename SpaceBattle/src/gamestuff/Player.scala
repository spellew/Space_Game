package gamestuff

import physics.PhysicsVector

class Player(){


  //set  double fire , life, width, height
  var width: Int = 160
  var height: Int = 300
  var index = 0
  var doubleFire = 2
  var life = 9
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
  var locax: Int = 0
  var locay: Int = 0
  def moveTo(x: Int, y: Int): Unit = {
    locax = x - width / 2
    locay = y - height / 2
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
    val x1 = other.x - width / 2
    val x2 = other.x + width / 2 + other.x
    val y1 = other.y - height / 2
    val y2 = other.y + height / 2 + other.y
    //define the distence
    val Playerx = locax + width / 2
    val Playery = locay + height/ 2
    //check the hit
    Playerx > x1 && Playerx < x2 && Playery > y1 && Playery < y2
  }
}