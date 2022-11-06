package fix
import org.apache.spark.sql.{Dataset, SparkSession}
import org.apache.spark.sql.functions.{col, substring}

object DatasetGroupByKey {
  def inSource(sparkSession: SparkSession) {
    import sparkSession.implicits._
    val ds = List("Spark 2.4", "Spark 3.0", "Dotty", "Scala 2.13", "Scala 3.2.1", "test").toDS()
    val schemaDS = ds.schema
    val res = ds.groupByKey(l => l.substring(0, 3)).count()

    if ("key".contains(res.columns.head)) println("~~~~~> Spark 3.0") else println("~~~~~> Spark 2.4")
    val res1 = res.select(col("key"))
    val res2 = res.select('key)
    val res3 = res.select("key")
    val res4 = res.withColumnRenamed("key", "newKey")
    val res5 = res.dropDuplicates("key")
  }
}
