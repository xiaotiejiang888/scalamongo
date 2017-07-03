import com.mongodb.casbah.Imports._

import scala.collection.mutable.ArrayBuffer
//class Point(xc: String, yc: String) extends AsQueryParam{
//  def asQueryParam(A: AsQueryParam)= a
//}
object FindTd {
  def main(args: Array[String]): Unit = {
    val conn = MongoFactory.getConnection
    var tds = conn("mpush")("td").find
    var apps = ArrayBuffer[String]()
    for (elem <- tds) {
      apps.append(elem.get("app")+"")
    }
    println("--- All td app ---")
    apps.foreach(println)
    println("--- search push by app ---")
    var collection = conn("mpush")("push")
    var y = "activity" $eq ("mockActivity_0")
//    val pt = new Point("$oid", "57e49b87691dc706e3713c47");
//    var y = "app" $eq (pt)
    var pushs = collection.find(y)
    pushs.foreach(println)

    var activitys = conn("mpush")("activity").find
    println("--- All tds ---")
    tds.foreach(println)
    println("--- source为push的td记录  ---")
    collection = conn("mpush")("td")
    var q = MongoDBObject("source" -> "push")
    tds = collection.find(q)
    tds.foreach(println)

    println("--- source为app的td记录---")
    q = MongoDBObject("source" -> "app")
    tds = collection.find(q)
    tds.foreach(println)

    conn.close
  }
}

