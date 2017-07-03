import com.mongodb.casbah.Imports._
import Control._
import scala.collection.mutable.ArrayBuffer

object Find extends App {

  val conn = MongoFactory.getConnection
  var stocks = conn("mpush")("stocks").find

  println("\n--- All Stocks ---")
  stocks.foreach(println)

  println("\n--- .findOne() ---")
  var collection = conn("mpush")("stocks")
  var q = MongoDBObject("symbol" -> "GOOG")
  stocks = collection.find(q)
  stocks.foreach(println)

  println("\n--- .find ---")
  stocks = collection.find("price" $gt 500)
  stocks.foreach(println)

  conn.close

/*
  def getStocks: Array[Stock] = {
    var stocks = ArrayBuffer[Stock]()
    using(MongoFactory.getConnection) { conn =>
      val dbObjects = MongoFactory.getCollection(conn).find
      for (dbObject <- dbObjects) {
        stocks += convertDbObjectToStock(dbObject)
      }
    } 
    stocks.toArray
  }

  def convertDbObjectToStock(obj: MongoDBObject): Stock = {
    val symbol = obj.getAs[String]("symbol").get
    val price = obj.getAs[Double]("price").get
    Stock(symbol, price)
  }
*/

}

