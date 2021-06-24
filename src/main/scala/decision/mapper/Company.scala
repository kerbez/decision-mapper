package decision.mapper

case class Company(companyName: String, revenue: Int, isProfitable: Boolean) {
  override def toString: String = {
    s"$companyName,$revenue,$isProfitable"
  }
}