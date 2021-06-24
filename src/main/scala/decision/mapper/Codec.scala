package decision.mapper

import decision.mapper.filters.{Filter, IsProfitableFilter, NameContainsFilter}
import io.circe.generic.auto._
import io.circe.syntax._
import io.circe.{Decoder, Encoder, HCursor}

import java.io.InvalidObjectException
import scala.util.{Failure, Success, Try}

trait Codec {
  implicit val encodeFilter: Encoder[Filter] = Encoder.instance {
    case d: NameContainsFilter => d.asJson
    case d: IsProfitableFilter => d.asJson
  }

  implicit val decodeFilter: Decoder[Filter] = (c: HCursor) => {
    def `type`: Try[String] = c.downField("filterType").as[String].toTry

    `type` match {
      case Success(s) if s == "NameContains" => c.as[NameContainsFilter]
      case Success(s) if s == "IsProfitable" => c.as[IsProfitableFilter]
      case Success(s) => throw new InvalidObjectException(s"filter type `$s` is not supported")

      case Failure(exception) =>
        throw exception
    }
  }
}
