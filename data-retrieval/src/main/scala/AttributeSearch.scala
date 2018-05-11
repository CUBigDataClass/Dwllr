import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import com.datastax.spark.connector._
//import org.apache.spark.sql.cassandra._

object AttributeSearch {
  def main(args: Array[String]) {
    println("Hello World")
    val conf = new SparkConf().setAppName("Dwllr").setMaster("local").set("spark.cassandra.connection.host", "localhost")
    val sc = new SparkContext(conf)
    val rdd = sc.cassandraTable("dwllr", "stats")

    val query = Map("medianrent"->1200, "population"->10000)

    val row = rdd.first()

    val sorted = rdd
      .filter { row => query.forall { case (k, _) => !row.isNullAt(k) } }
      .map { row => (query.map { case (k, v) => square(row.get[Float](k)-v) }.sum, row.get[String]("zip")) }
      .sortByKey()
      .collect()

    sorted.slice(0,5).foreach(println)
  }
  def square(x: Float): Float = x*x
}
