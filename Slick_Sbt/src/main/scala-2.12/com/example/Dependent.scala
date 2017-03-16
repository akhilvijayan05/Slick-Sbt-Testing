package com.example

/**
  * Created by knoldus on 13/3/17.
  */
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait DependentTable extends EmployeeTable{

  this:DBComponent =>
  import driver.api._
  class DependentTable(tag: Tag) extends Table[Dependent](tag, "dependent") {
    val emp_id = column[Int]("emp_id")
    val name = column[String]("name")
    val relation = column[String]("relation")
    val age = column[Option[Int]]("age")
    def empForeignKey = foreignKey("emp_dependent_fk", emp_id, employeeTableQuery)(_.id)
    def * = (emp_id, name,relation,age) <>(Dependent.tupled, Dependent.unapply)
  }

  val dependentTableQuery = TableQuery[DependentTable]

}

case class Dependent(emp_id:Int, name: String,relation:String,age:Option[Int])


trait DependentComponent extends DependentTable {
  this:DBComponent=>
import driver.api._
  def create = db.run(dependentTableQuery.schema.create)

  def insert(dependent: Dependent) = db.run(dependentTableQuery += dependent)

  def delete(empId: Int) = {
    val query = dependentTableQuery.filter(x => x.emp_id === empId)
    val action = query.delete
    db.run(action)
  }

  def updateName(id: Int, name: String): Future[Int] = {
    val query = dependentTableQuery.filter(x => x.emp_id === id)
      .map(_.name).update(name)
    db.run(query)
  }

def updateAge(id: Int, age: Option[Int]): Future[Int] = {
    val query = dependentTableQuery.filter(x => x.emp_id === id)
      .map(_.age).update(age)
    db.run(query)
  }

def updateRelation(id: Int, relation: String): Future[Int] = {
    val query = dependentTableQuery.filter(x => x.emp_id === id)
      .map(_.relation).update(relation)
    db.run(query)
  }

  def insertOrUpdate(dependent: Dependent) = {
    val query = dependentTableQuery.insertOrUpdate(dependent)
    db.run(query)

  }
  def getAll: Future[List[Dependent]] = {
    db.run { dependentTableQuery.to[List].result}
  }
  def truncate={

      val action = dependentTableQuery.delete
      db.run(action)
  }
  def crossJoin: Future[List[(String, String)]] = {
    val query = for {
      (e, d) <- employeeTableQuery join dependentTableQuery
    } yield (e.name, d.name)
    db.run(query.to[List].result)
  }

  def innerJoin: Future[List[(String, String)]] = {
    val query = for {
      (e, d) <- employeeTableQuery join dependentTableQuery on(_.id === _.emp_id)
    } yield (e.name, d.name)
    db.run(query.to[List].result)
  }

  def leftOuterJoin: Future[List[(String, Option[Option[Int]])]] = {
    val query = for {
      (e, d) <- employeeTableQuery joinLeft dependentTableQuery on(_.id === _.emp_id)
    } yield (e.name, d.map(_.age))
    db.run(query.to[List].result)
  }

  def fullJoin = {
    val query = for {
      (e, d) <- employeeTableQuery joinFull dependentTableQuery on (_.id === _.emp_id)
    } yield (e.flatMap(_.name), d.map(_.age))
    db.run(query.to[List].result)
  }
}

object DependentComponent extends DependentComponent with MySqlDBComponent