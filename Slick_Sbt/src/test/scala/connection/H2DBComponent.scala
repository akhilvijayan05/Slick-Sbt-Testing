package connection

import java.util.UUID

import com.example.DBComponent
import slick.jdbc.H2Profile

/**
  * Created by knoldus on 15/3/17.
  */
trait H2DBComponent extends DBComponent{

  val driver = H2Profile

  import driver.api._

  //  val db: Database = Database.forConfig("myH2DB")

  val randomDB = "jdbc:h2:mem:test" +
    UUID.randomUUID().toString + ";"
  val h2Url = randomDB + "MODE=MySql;DATABASE_TO_UPPER=false;INIT=RUNSCRIPT FROM 'app/test/resources/schema.sql'\\;RUNSCRIPT FROM 'app/test/resources/initaldata.sql'"
  println(s"\n\n~~~~~~~~~~~~~~~~~~~~~             $h2Url         ~~~~~~~~~~~~~~~~~~~~~~~\n\n")
  val db: Database = Database.forURL(url = h2Url, driver = "org.h2.Driver")
}

