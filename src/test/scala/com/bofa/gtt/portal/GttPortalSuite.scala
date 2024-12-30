package com.bofa.gtt.portal

import zio.*
import zio.http.*
import zio.test.*

def baseRequest(): ZIO[Server, Nothing, Request] =
  ZIO.serviceWith[Server](_.port)
    .map(port => Request.get(url = URL.root.port(10099)))

extension (testServer: TestServer)
  def addRoutes[R](routes: Routes[R, Response]): ZIO[R, Nothing, Unit] =
    for {
      r        <- ZIO.environment[R]
      provided =  routes.provideEnvironment(r)
      _        <- testServer.driver.addApp(provided, r)
    } yield ()

object GttPortalSuite extends ZIOSpecDefault:
  def spec: Spec[TestEnvironment & Scope, Throwable] = suite("WebServerTest") {
    suite("NonEnvironmental") {
      test("FrameworkTest") {
        ZIO.succeed(assertTrue(true))
      }
    }
  } +
    test("TestServerTest") {
      for {
        client      <- ZIO.service[Client]
        testRequest <- baseRequest()
        _           <- TestServer.addRequestResponse(testRequest, Response(Status.Ok))
        response    <- client(testRequest)
      } yield assertTrue(response.status == Status.Ok)
    }.provide(
    TestServer.layer,
    ZClient.default,
    Scope.default,
    Driver.default,
    ZLayer.succeed(Server.Config.default.port(10099)))