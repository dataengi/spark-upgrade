/*
rule=MigrateDatasetGroupByKey
 */
package fix
import org.apache.spark.sql.{Dataset, SparkSession}
import org.apache.spark.sql.functions.col
import org.apache.spark.sql.types.StructType

object DatasetGroupByKey {
  def inSource(sparkSession: SparkSession) {
    import sparkSession.implicits._
    val ds: Dataset[String] = List("Spark 2.4", "Spark 3.0", "Dotty", "Scala 2.13", "Scala 3.2.1", "test").toDS()
    val schemaDS1: StructType = ds.schema
    val res = ds.groupByKey(l => l.substring(0, 3)).count()

    if ("value".contains(res.columns.head)) println("~~~~~> Spark 2.4") else println("~~~~~> Spark 3.0")
    val res1 = res.select(col("value"))
    val res2 = res.select('value)
    val res3 = res.select("value")
    val res4 = res.withColumnRenamed("value", "newValue")
    val res5 = res.dropDuplicates("value")
  }
}
