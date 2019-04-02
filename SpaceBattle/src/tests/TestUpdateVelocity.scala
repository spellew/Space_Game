package tests

import org.scalatest.FunSuite
import physics.{PhysicalObject, Physics, PhysicsVector}

class TestUpdateVelocity extends FunSuite {
  test("Check Velocity") {
    var location: PhysicsVector = new PhysicsVector(2, 4)
    var velocity: PhysicsVector = new PhysicsVector(3, 1)
    var newObject: PhysicalObject = new PhysicalObject(location, velocity)
    var time: Double = 3.0
    var wor1: physics.World = new physics.World(2)

    var location1: PhysicsVector = new PhysicsVector(1, 2)
    var velocity1: PhysicsVector = new PhysicsVector(2, 4)
    var newObject1: PhysicalObject = new PhysicalObject(location1, velocity1)

    var location2: PhysicsVector = new PhysicsVector(3, 3)
    var velocity2: PhysicsVector = new PhysicsVector(10, 8)
    var newObject2: PhysicalObject = new PhysicalObject(location2, velocity2)

    Physics.updateVelocity(newObject, wor1, time)
    assert(newObject.velocity.x == -3)
    assert(newObject.velocity.y == -5)

    Physics.updateVelocity(newObject1, wor1, time)
    assert(newObject1.velocity.x == -4)
    assert(newObject1.velocity.y == -2)

    Physics.updateVelocity(newObject2, wor1, time)
    assert(newObject2.velocity.x == 4)
    assert(newObject2.velocity.y == 2)
  }
}
