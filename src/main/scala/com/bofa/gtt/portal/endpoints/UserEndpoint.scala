package com.bofa.gtt.portal.endpoints

import com.bofa.gtt.portal.errors.Errors.{NotAuthorised, UserNotFound}
import com.bofa.gtt.portal.model.User
import zio.*
import zio.http.{Handler, Method, Middleware, Routes}
import zio.http.Status.{NotFound, Unauthorized}
import zio.http.codec.Doc
import zio.http.endpoint.AuthType.None
import zio.http.endpoint.Endpoint

object UserEndpoint {
  val userEndPoint: Endpoint[Unit, Unit, Either[UserNotFound, NotAuthorised], User, None] = {
    Endpoint(Method.GET / "api" / "user")
      .out[User]
      .outError[NotAuthorised](Unauthorized)
      .outError[UserNotFound](NotFound) ?? Doc.p("Route for retrieving user information")
  }

  val userEndpointHandler:  Handler[Any, Either[UserNotFound, NotAuthorised], Any, User] =
    // logic to retrieve user data is put here
    Handler.succeed(User("Joanne", "Smith"))

  val route: Routes[Any, Nothing] =
    userEndPoint.implementHandler(userEndpointHandler).toRoutes
      @@ Middleware.requestLogging() @@ Middleware.timeout(20.seconds)

}