import java.io._

import com.mongo.spark.HDFSFileService

import scala.collection.mutable.ArrayBuffer

object FindTd {
  def main(args: Array[String]): Unit = {
    val conn = MongoFactory.getConnection
    var tds = conn("mpush")("td").find
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
    HDFSFileService.saveFile("td.txt")


    val outputStream = new FileOutputStream(new File("tdfromHadoop.txt"))
    val in = HDFSFileService.getFile("td.txt")
    var b = new Array[Byte](1024)
    var numBytes = in.read(b)
    while (numBytes > 0) {
      outputStream.write(b, 0, numBytes)
      numBytes = in.read(b)
    }
    outputStream.close()
    in.close()
    val localCheckReader = new BufferedReader(new FileReader("tdfromHadoop.txt"))
    print(localCheckReader.readLine)
    localCheckReader.close()
  }
}

