package com.plansum.johnsmith.scalatrahello

import org.scalatra._
import scalate.ScalateSupport

import com.mongodb.casbah.Imports._

class MongoController(mongoColl: MongoCollection) extends ScalatrahelloStack with SimpleMongoDbJsonConversion {

  /*
  get("/") {
    <html>
      <body>
        <h1>Hello, world!</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
        <p>Also, Scala is vaery gooodduh.</p>
      </body>
    </html>
  }
  

  get("/hello/:name"){
  	/*
    val ans = Store.all find {_.name == params("name")} match{
    	case Some(a) => a
    	case _       => "Not Found"
    }
    */

    <html>
      <body>
        <p> Hello, {params("name")} </p> 
      </body>
    </html> 
  }
  */

  get("/") {
    mongoColl.find()
    for { x <- mongoColl} yield x
    //mongoColl mkString("\n")
  }

	post("/insert") {
  	val key = params("key")
  	val value = params("value")
  	val newObj = MongoDBObject(key -> value)
  	mongoColl += newObj

  	Ok("Thank you.")
	}

	get("/query/:key/:value") {
    val q = MongoDBObject(params("key") -> params("value"))
    for ( x <- mongoColl.findOne(q) ) yield x
  }

}

/*
object Store{
	case class Hero(name: String, archetype: String){
		override def toString = name + " the " + archetype
	}

	val all = List(Hero("Harry", "Wizard"), Hero("Edward", "Vampire"), Hero("Doctor", "TimeLord"))
}
*/