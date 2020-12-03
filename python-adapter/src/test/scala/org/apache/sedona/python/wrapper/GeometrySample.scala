package org.apache.sedona.python.wrapper

import org.locationtech.jts.geom.Geometry

import scala.tools.nsc.interpreter.InputStream
import scala.io.Source

trait GeometrySample extends PythonTestSpec {
  self: TestToPythonSerialization =>

  private[python] val samplePoints: List[Geometry] =
    loadGeometriesFromResources("/python/samplePoints")

  private[python] val sampleLines: List[Geometry] =
    loadGeometriesFromResources("/python/sampleLines")

  private[python] val samplePolygons: List[Geometry] =
    loadGeometriesFromResources("/python/samplePolygons")

  private def loadGeometriesFromResources(fileName: String): List[Geometry] = {
    val resourceFileText = loadResourceFile(fileName)
    loadFromWktStrings(resourceFileText)
  }

  private def loadFromWktStrings(geometries: List[String]): List[Geometry] = {
    geometries.map(
      geometryWKT => wktReader.read(geometryWKT)
    )
  }

  private def loadResourceFile(fileName: String): List[String] = {
    val stream: InputStream = getClass.getResourceAsStream(fileName)
    Source.fromInputStream(stream).getLines.toList
  }
}
