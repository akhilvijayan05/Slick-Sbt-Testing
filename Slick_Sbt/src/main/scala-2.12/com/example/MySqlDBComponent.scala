package com.example
import slick.driver.MySQLDriver
import slick.jdbc.MySQLProfile

/**
  * Created by knoldus on 16/3/17.
  */
trait MySqlDBComponent extends DBComponent {

  val driver = MySQLProfile

  import driver.api._

  val db: Database=Database.forConfig("mySqlDB")

}
