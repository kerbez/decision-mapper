package decision.mapper.filters

import decision.mapper.Company

trait Filter {
  def filter(company: Company): Boolean
}