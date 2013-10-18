package staticversion

import scala.language.experimental.macros
import scala.reflect.macros.Context
import scala.sys.process._

object AssetsPath {
  def getPath(f: String, params: Any*): String = macro getPath_impl

  def getPath_impl(c: Context)(f: c.Expr[String], params: c.Expr[Any]*): c.Expr[String] = {
    import c.universe._
    val Literal(Constant(file: String)) = f.tree
    val cmd = "git log -n1 --pretty=%%h public/%s".format(file)
    val version = cmd.!!
    val versionStr = if (version.isEmpty) "" else version.substring(0, version.length - 1)

    val suffixPos = file.lastIndexOf('.')
    val production = scala.util.Properties.envOrElse("PLAY_PRODUCTION", "0" )
    val filePath = if (production == "1") {
      "http://static.example.com/%s.v%s%s".format(file.substring(0, suffixPos), versionStr, file.substring(suffixPos))
    } else {
      "/assets/%s".format(file)
    }
    
    c.Expr[String](Literal(Constant(filePath)))
  }
}
