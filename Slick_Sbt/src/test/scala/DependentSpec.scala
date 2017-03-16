import com.example._
import org.scalatest.AsyncFunSuite
import slick.driver.MySQLDriver
import slick.jdbc.H2Profile

/**
  * Created by knoldus on 15/3/17.
  */

class DependentSpec extends AsyncFunSuite{



  test("Add new Employee ") {
    DependentComponent.insert(Dependent(16, "akhil", "friend",Some(23))).map( x =>assert(x == 1))
  }

  test("update Employee record ") {
    DependentComponent.updateName(11,"pankaj").map(x=>assert(x == 1))
  }

  test("delete Employee ") {
    DependentComponent.delete(10).map(x=>assert(x == 1))
  }

  test("get all employees") {
    DependentComponent.getAll.map( x => assert( x.size == 2))
  }
//  test("trancate employee") {
//
//    testing.upsert(dependent).map( x => assert( x.size == 2))
//  }
  test("Cross Join Dependent and Employee") {
    DependentComponent.crossJoin.map(x => assert(x == List(("akhil","akhil"), ("akhil","raj"), ("raj","akhil"), ("raj","raj"), ("nitin","akhil"), ("nitin","raj"))))
  }

  test("Inner Join Dependent and Employee") {
    DependentComponent.innerJoin.map(x => assert(x == List(("akhil", "akhil"),("raj","raj"))))
  }

  test("Left Outer Join Dependent and Employee") {
    DependentComponent.leftOuterJoin.map(x => assert(x == List(("akhil", Some(Some(23))),("raj",Some(Some(22))),("nitin",None))))
  }

  test("Full Join Dependent and Employee") {
    DependentComponent.leftOuterJoin.map(x => assert(x == List(("akhil", Some(Some(23))),("raj",Some(Some(22))),("nitin",None))))
  }
}
