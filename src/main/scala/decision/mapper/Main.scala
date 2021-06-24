package decision.mapper

import decision.mapper.filters.Filter

import java.io.InvalidObjectException
import scala.io.{BufferedSource, Source}
import io.circe.parser._

object Main extends App with Codec {

  def getCompanies(path: String): List[Company] = {
    val source: BufferedSource = Source.fromFile(path)
    val lines = source.getLines().toList
    source.close()
    lines.tail.map{ x =>
      val elems = x.split(",").toList
      try {
        Company(elems.head, elems(1).toInt, elems(2).toBoolean)
      } catch {
        case _: Throwable =>
          throw new InvalidObjectException("invalid companies file")
      }
    }
  }

  def getFilters(path: String): List[Filter] = {
    val source: BufferedSource = Source.fromFile(path)
    val jsonAsString = source.mkString
    source.close()
    decode[List[Filter]](jsonAsString).getOrElse(
      throw new InvalidObjectException(s"invalid filters file")
    )
  }

  def filterCompanies(filters: List[Filter], companies: List[Company]): List[Company] = {
    companies.filter(c => filters.map(f => f.filter(c)).reduce(_&&_))
  }

  def getTopCompanies(companies: List[Company], top: Int): List[Company] = {
    companies.sortWith(_.revenue > _.revenue).slice(0, top.min(companies.size))
  }

  try{
    println(s"\n-------------Part 1--------------")

    val companies = getCompanies("data/companies.csv")
    companies.foreach{
      x=>println(x)
    }

    println(s"\n-------------Part 2--------------")

    val filters = getFilters("data/filters.json")
    val filteredCompanies = filterCompanies(filters, companies)

    filteredCompanies.foreach( x => println(x))

    println(s"\n-------------Part 3--------------")

    val topCompanies = getTopCompanies(filteredCompanies, 3)
    topCompanies.foreach( x => println(x))
  } catch {
    case ex: Throwable => println(s"[ERROR] ${ex.getMessage}")
  }
}