package tests

import org.scalatest._
import gamestuff.{main}
import scala.collection.mutable.ListBuffer

class TestRemoveLoser extends FunSuite{
  test("Check remove function"){
    var list1: ListBuffer[String] = ListBuffer("tony", "sam", "name", "name3", "bazooka", "name4")
    var list2: ListBuffer[String] = ListBuffer("realname1", "realname2", "realname3", "realname4", "realname5", "realname6")
    var list3: ListBuffer[String] = ListBuffer("word", "otherword", "mac", "book", "pro", "number")

    main.removeLoser(list1, "name")
    main.removeLoser(list2, "realname5")
    main.removeLoser(list3, "otherword")

    assert(list1(2) == "name3")
    assert(list2(4) == "realname6")
    assert(list3(1) == "mac")
  }

}
