package com.example

/**
  * Created by knoldus on 12/3/17.
  */

//import slick.jdbc.{JdbcProfile, MySQLProfile, PostgresProfile}
import java.util.UUID

import slick.driver.JdbcProfile
import slick.jdbc.{H2Profile, PostgresProfile}

trait  DBComponent {

  val driver: JdbcProfile

  import driver.api._

  val db: Database

}


