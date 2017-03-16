package com.example

import slick.jdbc.PostgresProfile
import slick.driver.PostgresDriver

/**
  * Created by knoldus on 16/3/17.
  */
trait PostgresComponent extends DBComponent {

  val driver = PostgresProfile

  import driver.api._

  val db = Database.forConfig("myPostgresDB")

}
