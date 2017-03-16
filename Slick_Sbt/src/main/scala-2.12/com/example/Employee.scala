package com.example

import slick.jdbc.PostgresProfile
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

case class Employee(id: Int, name: String, experience: Double)

trait EmployeeTable {

  this:DBComponent =>
  import driver.api._
   class EmployeeTable(tag: Tag) extends Table[Employee](tag, "employee") {
    val id: Rep[Int] = column[Int]("id", O.PrimaryKey)
    val name: Rep[String] = column[String]("name")
    val experience: Rep[Double] = column[Double]("experience")

    def * = (id, name, experience) <> (Employee.tupled, Employee.unapply)

  }

  val employeeTableQuery = TableQuery[EmployeeTable]
}

trait EmployeeRepo extends EmployeeTable {

  this:DBComponent =>
  import driver.api._
  //val db = Database.forConfig("myPostgresDB")

  def create: Future[Unit] = db.run(employeeTableQuery.schema.create)

  def insert(emp: Employee): Future[Int] = db.run {
    employeeTableQuery += emp
  }
  def delete(exp: Double) = {
    val query = employeeTableQuery.filter(x => x.experience < exp)
    val action = query.delete
    db.run(action)
  }

  def updateName(id:Int, name:String) : Future[Int] = {
    val query = employeeTableQuery.filter(x => x.id === id)
      .map(_.name).update(name)
    db.run(query)
  }

def updateExperience(id:Int, experience:Double) : Future[Int] = {
    val query = employeeTableQuery.filter(x => x.id === id)
      .map(_.experience).update(experience)
    db.run(query)
  }

  def insertOrUpdate(employee:Employee) = {
    val query = employeeTableQuery.insertOrUpdate(employee)
    db.run(query)
  }
  def getAll: Future[List[Employee]] = {
    db.run { employeeTableQuery.to[List].result}
  }
  def truncate={

    val action = employeeTableQuery.delete
    db.run(action)
  }
}

object EmployeeRepo1 extends EmployeeRepo with H2DBComponent