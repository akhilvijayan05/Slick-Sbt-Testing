import com.example.{Dependent, Employee, EmployeeRepo1}
import org.scalatest.AsyncFunSuite

/**
  * Created by knoldus on 16/3/17.
  */
class EmployeeSpec extends AsyncFunSuite{

  test("Add new Employee ") {
    EmployeeRepo1.insert(Employee(17, "akhil", 32)).map( x =>assert(x == 1))
  }

  test("update Employee record ") {
    EmployeeRepo1.updateName(16,"pankaj").map(x=>assert(x == 1))
  }

  test("delete Employee by experience") {
    EmployeeRepo1.delete(23).map(x=>assert(x == 0 ))
  }

  test("get all employees") {
    EmployeeRepo1.getAll.map( x => assert( x.size == 3))
  }
}
