package com.example

/**
  * Created by knoldus on 12/3/17.
  */

//import slick.jdbc.{JdbcProfile, MySQLProfile, PostgresProfile}
import slick.driver.{JdbcProfile, MySQLDriver, PostgresDriver}
import slick.jdbc.PostgresProfile

trait  DBComponent {

  val driver: JdbcProfile

  import driver.api._

  val db: Database

}
trait MySqlDBComponent extends DBComponent {

  val driver = MySQLDriver

  import driver.api._

  val db: Database=Database.forConfig("mySqlDB")

}
trait PostgresComponent extends DBComponent {

  val driver = PostgresProfile

  import driver.api._

  val db = Database.forConfig("myPostgresDB")

}
