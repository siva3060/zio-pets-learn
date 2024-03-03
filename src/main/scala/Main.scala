import zio.Console.printLine
import zio.{Scope, ZIO, ZIOAppArgs, ZIOAppDefault}

object Main extends ZIOAppDefault {
  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] =
    (for{
      pets <- ZIO.serviceWithZIO[PetService](_.getAllPets())
      _ <- printLine(pets)
    } yield ()).provide(PetService.live,config.QuillContext.dataSourceLayer)
}