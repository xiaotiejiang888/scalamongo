import com.mongodb.casbah.Imports._
import scala.collection.mutable.ArrayBuffer
import Common._
import Control._

case class Stock (symbol: String, price: Double)

object Inserts {

  // (1) INSERTS

  def buildMongoDbObject(stock: Stock): MongoDBObject = {
    val builder = MongoDBObject.newBuilder
    builder += "symbol" -> stock.symbol
    builder += "price" -> stock.price
    builder.result
  }
  @throws(classOf[Exception])
  def saveStock(stock: Stock) {
    val mongoObj = buildMongoDbObject(stock)
    using(MongoFactory.getConnection) { conn => {
       val result = MongoFactory.getCollection(conn).save(mongoObj)
       println("RESULT: " + result)
       result
     }
    } 
  }

   def main(args: Array[String]): Unit = {
     println("dddddddd")
    val apple = Stock("AAPL", 600); saveStock(apple)
    val google = Stock("GOOG", 650); saveStock(google)
    val netflix = Stock("NFLX", 60); saveStock(netflix)
  }
}


