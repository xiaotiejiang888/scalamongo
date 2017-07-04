import java.io.{File, PrintWriter}

import com.mongodb.casbah.Imports._

import scala.collection.mutable.ArrayBuffer
//class Point(xc: String, yc: String) extends AsQueryParam{
//  def asQueryParam(A: AsQueryParam)= a
//}
object FindTd {
  def main(args: Array[String]): Unit = {
    val conn = MongoFactory.getConnection
    var tds = conn("mpush")("td").find
//    tds.foreach(println)

    val writer = new PrintWriter(new File("td.txt" ))
    var tdsStr = ArrayBuffer[String]()
    var tds2 = "";
    for (elem <- tds) {
      tdsStr.append(elem.toString+"\n")
      tds2 += (elem.toString+"\n")
    }
    writer.write(tds2)
    writer.close()
    conn.close
  }
}

