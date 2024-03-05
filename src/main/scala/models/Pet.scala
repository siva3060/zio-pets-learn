package models

import config.QuillContext.{querySchema, quote, schemaMeta}
import io.getquill.{EntityQuery, Quoted}
import zio.json.{DeriveJsonCodec, JsonCodec}
import zio.schema.{DeriveSchema, Schema}

import java.util.UUID
final case class Pet(
                    petId:UUID,
                    name: String,
                    species:String,
                    ownerId:UUID
                    )
object Pet {
//  implicit val petSchemaMeta: Quoted[EntityQuery[Pet]] = quote {
//    querySchema[Pet](
//      "PETS",
//      _.petId -> "PET_ID",
//      _.name -> "NAME",
//      _.species -> "SPECIES",
//      _.ownerId -> "OWNER_ID"
//    )
//  }
  implicit val codec: JsonCodec[Pet] = DeriveJsonCodec.gen[Pet]
  implicit val petSchema: Schema[Pet] = DeriveSchema.gen[Pet]

//  //added Schema Meta
//  implicit val petSchemaMeta: SchemaMeta[Pet] = schemaMeta[Pet]("pets")


}
