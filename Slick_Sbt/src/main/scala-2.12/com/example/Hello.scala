package com.example

import scala.concurrent.ExecutionContext.Implicits.global

object Hello extends App {

  DependentComponent.create
  val insertRes = DependentComponent.insert(Dependent(11, "nitin","friend",Some(22)))
  val res = insertRes.map { res => s"$res row inserted" }.recover {
    case ex: Throwable => ex.getMessage
  }
  res.map(println(_))
//  val value=DependentComponent.truncate
//  value.map(println(_))
  Thread.sleep(10000)
}
