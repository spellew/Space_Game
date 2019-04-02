package tests

import org.scalatest._
import physics.{PhysicalObject, Physics, PhysicsVector}

class TestComputeLocation extends FunSuite{
  test("Check Location1") {
    var location: PhysicsVector = new PhysicsVector(1,2)
    var velocity: PhysicsVector = new PhysicsVector(1,1)
    var newObject: PhysicalObject = new PhysicalObject(location, velocity)
    var time: Double = 3.0

    var location1: PhysicsVector = new PhysicsVector(2,1)
    var velocity1: PhysicsVector = new PhysicsVector(3,2)
    var newObject1: PhysicalObject = new PhysicalObject(location1, velocity1)

    var location2: PhysicsVector = new PhysicsVector(3,3)
    var velocity2: PhysicsVector = new PhysicsVector(2, 3)
    var newObject2: PhysicalObject = new PhysicalObject(location2, velocity2)

    var first: PhysicsVector = Physics.computePotentialLocation(newObject, time)
    assert(first.x == 4)
    assert(first.y == 5)

    var second: PhysicsVector = Physics.computePotentialLocation(newObject1, time)
    assert(second.x == 11)
    assert(second.y == 7)

    var third: PhysicsVector = Physics.computePotentialLocation(newObject2, time)
    assert(third.x == 9)
    assert(third.y == 12)
  }


}

