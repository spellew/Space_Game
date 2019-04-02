package physics

class World(var gravity: Double) {
  var objects: List[PhysicalObject] = List()
  var boundaries: List[Boundary] = List()
}
