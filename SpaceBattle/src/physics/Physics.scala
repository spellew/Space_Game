package physics

object Physics {
  def computePotentialLocation(obj: PhysicalObject, time: Double): PhysicsVector = {
    obj.location.x = obj.location.x + (obj.velocity.x * time)
    obj.location.y = obj.location.y + (obj.velocity.y * time)
    obj.location
  }

  def updateVelocity(obj: PhysicalObject, wor: World, x: Double): Unit ={
    obj.velocity.x = obj.velocity.x - (wor.gravity * x)
    obj.velocity.y = obj.velocity.y - (wor.gravity * x)
    }
}

