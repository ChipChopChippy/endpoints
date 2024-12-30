package com.bofa.gtt.portal.endpoints

import zio.http.codec.PathCodec.path
import zio.http.endpoint.Endpoint
import zio.http.endpoint.openapi.{OpenAPIGen, SwaggerUI}
import zio.http.{Method, Response, Root, Routes, handler}

object Endpoints {
  private val defaultEndPoint = Method.GET / Root -> handler(Response.text("Greetings at your service"))
  val defaultRoute: Routes[Any, Nothing] = defaultEndPoint.toRoutes
  val endpoints: Routes[Any, Nothing] = defaultRoute ++ UserEndpoint.route
  val openAPI = OpenAPIGen.fromEndpoints(title = "GTT Portal ", version = "0.0.1", UserEndpoint.userEndPoint)
  val swaggerRoutes: Routes[Any, Response] = SwaggerUI.routes("docs" / "gttportal", openAPI)
  val routes: Routes[Any, Response] = defaultRoute ++ UserEndpoint.route ++ swaggerRoutes
}