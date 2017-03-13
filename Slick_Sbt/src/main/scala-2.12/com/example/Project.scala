package com.example

/**
  * Created by knoldus on 13/3/17.
  */

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait ProjectTable extends EmployeeTable with MySqlDBComponent{

  this:DBComponent =>
  import driver.api._
  class ProjectTable(tag: Tag) extends Table[Project](tag, "project") {
    val name = column[String]("name")
    val emp_id = column[Int]("emp_id")
    def empForeignKey = foreignKey("emp_project_fk", emp_id, employeeTableQuery)(_.id)
    def * = (emp_id, name) <>(Project.tupled, Project.unapply)
  }

  val projectTableQuery = TableQuery[ProjectTable]

}

case class Project(emp_id:Int, name: String)


object ProjectComponent extends ProjectTable with MySqlDBComponent {
  this: DBComponent =>

  import driver.api._

  def create = db.run(projectTableQuery.schema.create)

  def insert(project: Project) = db.run(projectTableQuery += project)

  def delete(empId: Int) = {
    val query = projectTableQuery.filter(x => x.emp_id === empId)
    val action = query.delete
    db.run(action)
  }

  def updateName(id: Int, name: String): Future[Int] = {
    val query = employeeTableQuery.filter(x => x.id === id)
      .map(_.name).update(name)
    db.run(query)
  }

  def insertOrUpdate(project: Project) = {
    val query = projectTableQuery.insertOrUpdate(project)
    db.run(query)

  }
  def getAll: Future[List[Project]] = {
    db.run { projectTableQuery.to[List].result}
  }
  def truncate={

    val action = projectTableQuery.delete
    db.run(action)
  }
}