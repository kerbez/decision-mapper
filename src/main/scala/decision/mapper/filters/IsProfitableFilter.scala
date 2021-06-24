package decision.mapper.filters

import decision.mapper.Company

case class IsProfitableFilter() extends Filter {
  override def filter(company: Company): Boolean = company.isProfitable
}