package com.tarena.shout

import gamestuff.{Bullet, Player, ship}
import java.awt.Graphics
import java.awt.image.BufferedImage
import java.util._
import javax.swing.JFrame
import javax.swing.JPanel
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent

object ShootGame {
  val WIDTH = 400
  val HEIGHT = 654
  var background: BufferedImage = null
  var start: BufferedImage = null
  var pause: BufferedImage = null
  var gameover: BufferedImage = null
  var ship: BufferedImage = null
  val START = 0
  val RUNNING = 1
  val PAUSE = 2
  val GAMEOVER = 3

  def main(args: Array[String]): Unit = {
    val frame = new JFrame("SpaceGame")
    val game = new ShootGame
    frame.add(game) 
    frame.setSize(WIDTH, HEIGHT)
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    frame.setVisible(true) 
    game.action()
  }
}

class ShootGame extends JPanel{
  var ship1:ship = new ship("theOne", new physics.PhysicalObject(new physics.PhysicsVector(0,0),new physics.PhysicsVector(3,3)), 9)
  var flyings: Array[ship] = new Array[ship](1)
  var state = ShootGame.START
  var hero = new Player
  var  bullet1: Bullet = new Bullet(0,0)
  var bullets: Array[Bullet] = new Array[Bullet](1)

  override 
  def paint(g: Graphics): Unit = {
  }

  def ships(g: Graphics): Unit = {
  }

  def paintBullets(g: Graphics): Unit = {
  }

  def patintState(g: Graphics): Unit = {
    if (state == ShootGame.START) {
      g.drawImage(ShootGame.start, 0, 0, null)
    }
    if (state == ShootGame.PAUSE) {
      g.drawImage(ShootGame.pause, 0, 0, null)
    }
    if (state == ShootGame.GAMEOVER) {
      g.drawImage(ShootGame.gameover, 0, 0, null)
    }
  }

  def nextOne: Nothing = {
    val rand = new Random
    val nextRand = rand.nextInt(20)
    if (nextRand < 4) {
      new Nothing
    } else if (nextRand == 5) {
      new Nothing
    } else {
      new Nothing
    }
  }

  private[shout] var flyEnteredIndex = 0

  def enterAction(): Unit = { 
    //10ms per
    flyEnteredIndex += 1
    if (flyEnteredIndex % 40 == 0) {
      val obj = nextOne 
      flyings.update(flyings.length + 1, ship1) 
      flyings(flyings.length - 1) = obj
    }
  }

  //bullet
  private[shout] var shootIndex = 0

  def shootAction(): Unit = {
    //add bullets
    shootIndex += 1
    if (shootIndex % 30 == 0) {
      val bs = hero.shoot
      bullets.update(bullets.length + bs.length, bullet1)
      System.arraycopy(bs, 0, bullets, bullets.length - bs.length, bs.length) //bs的追加
    }
  }

  def stepAction(): Unit = {
    hero.step()
    var i: Int = 0
    for(i < flyings.length){
      val f = flyings(i)
      f.step()
      }
    }
    var i = 0
    for(i < bullets.length){
      val b = bullets(i)
      b.step()
      }
  
  def outOfBoundsAction(): Unit = {
    var index = 0 
    val flyinglives = new Array[Any](flyings.length)
    var i = 0
    for(i <= flyings.length){
      val f = flyings(i)
      if (!f.outOfBounds) { 
        flyinglives(index) = f
        index += 1
      }
    }
    flyings(index, flyinglives) 
    index = 0 //归零
    val bulletLives = new Array[Bullet](bullets.length) 
    var i = 0
    for(i < bullets.length){
      val b = bullets(i)
      if (!b.outOfBounds) {
        bulletLives(index) = b
        index += 1
      }
    }
    bullets(bulletLives, index)
  }

  //all to all
  private[shout] var j = 0

  def boomAction(): Unit = {
    var i = 0
    while ( {
      i < bullets.length
    }) {
      boom(bullets(i), i)
      {
        i += 1; i - 1
      }
    }
  }

  //one to all
  private[shout] var score = 0

  def boom(b: Bullet, bu: Int): Unit = {
    var index = -1
    var i = 0
    while ( {
      i < flyings.length
    }) {
      val f = flyings(i)
      if (f.shootBy(b)) { 
        index = i
      }
      {
        i += 1; i - 1
      }
    }
    if (index != -1) {
      val one = flyings(index)
      if (one.isInstanceOf[Nothing]) { 
        val e = one.asInstanceOf[Nothing] 
        //score += e.getScore
        val t = flyings(index)
        flyings(index) = flyings(flyings.length - 1)
        flyings(flyings.length - 1) = t
        flyings(flyings, flyings.length - 1) 
      }
      if (one.isInstanceOf[Nothing]) {
        j += 1
        if (j == 5) {
          val e = one.asInstanceOf[Nothing]
         // score += e.getScore
          j = 0
          val t = flyings(index)
          flyings(index) = flyings(flyings.length - 1)
          flyings(flyings.length - 1) = t
          flyings(flyings, flyings.length - 1)
        }
      }
//      if (one.isInstanceOf[Nothing]) {
//        val a = one.asInstanceOf[Nothing]
//        val `type` = a.getType
//        `type` match {
//          case Award.DOUBLE_FIRE =>
// award is double fire
//            hero.addDoubleFire()
//
//          case Award.LIFE =>
// award is life
//            hero.addLife()
//          
//        }
//        val t = flyings(index)
//        flyings(index) = flyings(flyings.length - 1)
//        flyings(flyings.length - 1) = t
//        flyings(flyings, flyings.length - 1)
//      }
      val tBullet = bullets(bu)
      bullets(bu) = bullets(bullets.length - 1)
      bullets(bullets.length - 1) = tBullet
      bullets(bullets, bullets.length - 1) //drop bullet
    }
  }

  //player crash
  def checkGameOverAction(): Unit = if (isGameOver) state = ShootGame.GAMEOVER

  //check game end
  def isGameOver: Boolean = {
    var i = 0
    while ( {
      i < flyings.length
    }) {
      val f = flyings(i)
      if (hero.hit(f)) { 
        hero.subtractLife()
       // hero.clealDoubleFire
        val t = flyings(i) 
        flyings(i) = flyings(flyings.length - 1)
        flyings(flyings.length - 1) = t
        flyings(flyings, flyings.length - 1)
      }
      {
        i += 1; i - 1
      }
    }
    hero.getLife <= 0 
  }

  def action(): Unit = {
    val l = new MouseAdapter() {
      override def mouseMoved(e: MouseEvent): Unit = if (state == ShootGame.RUNNING) {
        val x = e.getX 
        val y = e.getY
        hero.moveTo(x, y)
      } 
    override def mouseClicked(e: MouseEvent): Unit = state match {
      case ShootGame.START =>
        state = ShootGame.RUNNING
      case ShootGame.GAMEOVER =>
        state = ShootGame.START
        score = 0
        hero = new Nothing
        flyings = new Array[ship](0)
        bullets = new Array[Bullet](0)
   
    } 
    override 
    def mouseExited(e: MouseEvent): Unit = { 
      if (state == ShootGame.RUNNING) {
        state = ShootGame.PAUSE
      } 
    } 
    override 
    def mouseEntered(e: MouseEvent): Unit = {
      if (state == ShootGame.PAUSE) {
        state = ShootGame.RUNNING
      }
    }
    }
    this.addMouseListener(l) 
    this.addMouseMotionListener(l) 
    
    //timer set
    val timer = new Timer //ms per
    
    timer.schedule(new TimerTask() {
          override def run(): Unit = {
            if (state == ShootGame.RUNNING) {
              enterAction() 
              stepAction() 
              shootAction()
              boomAction()
              outOfBoundsAction()
              checkGameOverAction() 
            }
            repaint()
          }
        }, 10, 10) 
  }
}