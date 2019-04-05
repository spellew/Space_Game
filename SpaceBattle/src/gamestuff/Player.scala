package gamestuff

import java.awt.image.BufferedImage

class Player(){

  life = 9
  doubleFire = 0
  x = 150
  y = 400
  private var images = Array() //英雄机图片
  private var index = 0 //英雄机图片切换索引
  private var doubleFire = 0 //双倍火力
  private var life = 0 //命
  /** 获取双倍火力 */
  def isDoubleFire: Int = doubleFire

  /** 设置双倍火力 */
  def setDoubleFire(doubleFire: Int): Unit = this.doubleFire = doubleFire

  /** 增加火力 */
  def addDoubleFire(): Unit = doubleFire = 40

  /** 增命 */
  def addLife(): Unit = { //增命
    life += 1
  }

  /** 减命 */
  def subtractLife(): Unit = { //减命
    life -= 1
  }

  /** 获取命 */
  def getLife: Int = life

  /** 当前物体移动了一下，相对距离，x,y鼠标位置  */
  def moveTo(x: Int, y: Int): Unit = {
    this.x = x - width / 2
    this.y = y - height / 2
  }

  /** 越界处理 */
  def outOfBounds = false

  /** 发射子弹 */
  def shoot: Array[Nothing] = {
    val xStep = width / 4
    //4半
    val yStep = 20 //步
    if (doubleFire > 0) {
      val bullets = new Array[Nothing](2)
      bullets(0) = new Nothing(x + xStep, y - yStep) //y-yStep(子弹距飞机的位置)
      bullets(1) = new Nothing(x + 3 * xStep, y - yStep)
      bullets
    }
    else { //单倍火力
      val bullets = new Array[Nothing](1)
      bullets(0) = new Nothing(x + 2 * xStep, y - yStep)
      bullets
    }
  }

  /** 移动 */
  def step(): Unit = if (images.length > 0) image = images({
    index += 1; index - 1
  } / 10 % images.length) //切换图片hero0，hero1
  /** 碰撞算法 */
  def hit(other: Nothing): Boolean = {
    val x1 = other.x - this.width / 2
    //x坐标最小距离
    val x2 = other.x + this.width / 2 + other.width
    //x坐标最大距离
    val y1 = other.y - this.height / 2
    //y坐标最小距离
    val y2 = other.y + this.height / 2 + other.height
    //y坐标最大距离
    val herox = this.x + this.width / 2
    //英雄机x坐标中心点距离
    val heroy = this.y + this.height / 2 //英雄机y坐标中心点距离
    herox > x1 && herox < x2 && heroy > y1 && heroy < y2 //区间范围内为撞上了
  }
}