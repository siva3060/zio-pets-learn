import models.Pet
import zio.{Task, ZIO, ZLayer}

import java.util.UUID

trait PetService {
  def savePet(name:String,species:String,ownerId:UUID):Task[Pet]
  def getAllPets():Task[List[Pet]]
  def getPetById(petId:UUID):Task[Option[Pet]]
  def deletePetById(petId:UUID):Task[Unit]
}

final case class PetServiceLive() extends PetService {
  
  val pet1 = Pet(UUID.randomUUID(),"Fluffy","cat",UUID.randomUUID())
  val pet2 = Pet(UUID.randomUUID(),"Strikey","dog",UUID.randomUUID())
  val pet3 = Pet(UUID.randomUUID(),"Goldy","fist",UUID.randomUUID())

  val petDatabase:Map[UUID,Pet] = Map(
       (pet1.petId -> pet1),
       (pet2.petId -> pet2),
       (pet3.petId -> pet3));



  override def savePet(name: String, species: String, ownerId: UUID): Task[Pet] =
    ZIO.attempt{
      val newPet = Pet(UUID.randomUUID(),name,species,UUID.randomUUID())
      petDatabase + (newPet.petId -> newPet)
      newPet
    }

  override def getAllPets(): Task[List[Pet]] =
    ZIO.succeed(petDatabase.values.toList)

  override def getPetById(petId: UUID): Task[Option[Pet]] =
    ZIO.succeed(Some(petDatabase(petId)))

  override def deletePetById(petId: UUID): Task[Unit] =
    ZIO.attempt(petDatabase - petId).unit
}
object PetService {
  val live:ZLayer[Any,Nothing,PetService] = ZLayer.succeed(PetServiceLive())
}
