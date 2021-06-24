package decision.mapper.filters

import decision.mapper.Company

case class NameContainsFilter(text: String) extends Filter {
  def filter(company: Company): Boolean = {
    company.companyName.contains(text)
  }
}