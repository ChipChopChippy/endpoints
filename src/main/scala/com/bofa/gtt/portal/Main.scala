package com.bofa.gtt.portal

import com.bofa.gtt.portal.endpoints.Endpoints
import zio.*
import zio.http.*

object Main extends ZIOAppDefault {

  private val app = Server.serve(Endpoints.routes)
    .provide(Server.defaultWithPort(10026))

  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] = app
}