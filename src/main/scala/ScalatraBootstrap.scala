import com.plansum.johnsmith.scalatrahello._
import org.scalatra._
import javax.servlet.ServletContext

import com.mongodb.casbah.Imports._

class ScalatraBootstrap extends LifeCycle {

  val mongoClient = MongoClient()

  override def init(context: ServletContext) {

    val mongoColl = mongoClient("casbah_test")("test_data")

    context.mount(new MongoController(mongoColl), "/*")
    //context.mount(new MyScalatraServlet, "/*")
  }

  override def destroy(context: ServletContext){
  	mongoClient.close
  }
}
