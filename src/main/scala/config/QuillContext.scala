package config

import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
import io.getquill.{H2ZioJdbcContext, SnakeCase, UpperCase}
import models.Pet
import zio.ZLayer

import javax.sql.DataSource

object QuillContext extends H2ZioJdbcContext(UpperCase){

  val dburl = "jdbc:h2:./test"
  val userName = "sa"
  val password = "password"

  val h2dbConfig = new HikariConfig()
  h2dbConfig.setJdbcUrl(dburl)
  h2dbConfig.setUsername(userName)
  h2dbConfig.setPassword(password)

  val dataSource = new HikariDataSource(h2dbConfig)

  val dataSourceLayer:ZLayer[Any,Throwable,DataSource] = ZLayer.succeed(dataSource)


  //added Schema Meta
  implicit val petSchemaMeta: SchemaMeta[Pet] = schemaMeta[Pet]("pets")


}
