import config.QuillContext._
import models.Pet
import zio.{Task, ZEnvironment, ZIO, ZLayer}

import java.sql.SQLException
import java.util.UUID
import javax.sql.DataSource

trait PetService {
  def savePet(name:String,species:String,ownerId:UUID):Task[Pet]
  def getAllPets(): ZIO[DataSource,SQLException,List[Pet]]
  def getPetById(petId:UUID):Task[Option[Pet]]
  def deletePetById(petId:UUID):Task[Unit]
}

final case class PetServiceLive(dataSource: DataSource) extends PetService {

  override def savePet(name: String, species: String, ownerId: UUID): Task[Pet] = ???

  override def getAllPets(): ZIO[DataSource,SQLException,List[Pet]] =
                run(query[Pet]).provideEnvironment(ZEnvironment(dataSource))

  override def getPetById(petId: UUID): Task[Option[Pet]] = ???

  override def deletePetById(petId: UUID): Task[Unit] = ???
}
object PetService {
  val live:ZLayer[DataSource,Nothing,PetService] =  ZLayer{
    for{
      datasource <- ZIO.service[DataSource]
    } yield PetServiceLive(datasource)
  }
}