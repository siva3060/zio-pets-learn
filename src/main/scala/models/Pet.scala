package models

import config.QuillContext.{querySchema, quote}
import io.getquill.{EntityQuery, Quoted}
import zio.json.{DeriveJsonCodec, JsonCodec}
import zio.schema.{DeriveSchema, Schema}

import java.util.UUID

final case class Pet(
                    petId:String,
                    name: String,
                    species:String,
                    ownerId:String
                    )
object Pet {

  implicit val petSchemaMeta: Quoted[EntityQuery[Pet]] = quote {
    querySchema[Pet](
      "PETS",
      _.petId -> "PET_ID",
      _.name -> "NAME",
      _.species -> "SPECIES",
      _.ownerId -> "OWNER_ID"
    )
  }

  implicit val codec: JsonCodec[Pet] = DeriveJsonCodec.gen[Pet]
  implicit val petSchema: Schema[Pet] = DeriveSchema.gen[Pet]

}
