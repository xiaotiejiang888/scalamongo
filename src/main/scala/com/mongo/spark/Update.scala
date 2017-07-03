import com.mongodb.casbah.Imports._
import Common._

object Update extends App {

  val conn = MongoFactory.getConnection
  var stocks = conn("mpush")("stocks").find
  var collection = conn("mpush")("stocks")

  // a new version of the google stock to replace the old one
  val google = Stock("GOOG", 700)
  var q = MongoDBObject("symbol" -> "GOOG")
  collection.findAndModify(q, buildMongoDbObject(google))

  conn.close

}

