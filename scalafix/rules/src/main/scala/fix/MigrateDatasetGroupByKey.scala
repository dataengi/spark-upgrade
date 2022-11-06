package fix

import scalafix.v1._
import scala.meta._

class MigrateDatasetGroupByKey() extends SemanticRule("MigrateDatasetGroupByKey") {
  private val priorColName = "value"
  private val newColName = "key"

  override val isRewrite = true
  override def fix(implicit doc: SemanticDocument): Patch = {
    val readerMatcher = SymbolMatcher.normalized("org.apache.spark.sql.Dataset")

    def matchOnTree(t: Tree): Patch = {
      t match {
        case elem @ _ => ???
        case _        => Patch.empty
      }
    }

    matchOnTree(doc.tree)
  }
}
