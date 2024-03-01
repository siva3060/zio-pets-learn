package models

import java.util.UUID

final case class Pet(
                    petId:UUID,
                    name: String,
                    species:String,
                    ownerId:UUID
                    )
