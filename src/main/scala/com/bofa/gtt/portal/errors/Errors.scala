package com.bofa.gtt.portal.errors

import zio.schema.{DeriveSchema, Schema}

object Errors {
  final case class NotAuthorised(message: String)
  object NotAuthorised {
    implicit val schema: Schema[NotAuthorised] = DeriveSchema.gen
  }
  
  final case class UserNotFound(message: String)
  object UserNotFound {
    implicit val schema: Schema[UserNotFound] = DeriveSchema.gen
  }
  
  final case class TimedOut(message: String)
  object TimedOut {
    implicit val schema: Schema[TimedOut] = DeriveSchema.gen
  }
}