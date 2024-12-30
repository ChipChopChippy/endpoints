package com.bofa.gtt.portal.model

import zio.schema.{DeriveSchema, Schema}
import zio.schema.annotation.description

final case class User (
  @description("First Name of User") firstName: String,
  @description("Last Name of User") lastName: String)

object User {
  implicit val schema: Schema[User] = DeriveSchema.gen
}

