import com.example._
import org.scalatest.AsyncFunSuite

/**
  * Created by knoldus on 16/3/17.
  */
class EmployeeSpec extends AsyncFunSuite{

  object testing extends EmployeeRepo with H2DBComponent
  test("Add new Employee ") {
    testing.insert(Employee(17, "akhil", 32)).map( x =>assert(x == 1))
  }

  test("update Employee record ") {
    testing.updateName(16,"pankaj").map(x=>assert(x == 1))
  }

  test("delete Employee by experience") {
    testing.delete(23).map(x=>assert(x == 0 ))
  }

  test("get all employees") {
    testing.getAll.map( x => assert( x.size == 3))
  }
}
