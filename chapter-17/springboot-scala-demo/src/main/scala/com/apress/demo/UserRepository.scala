package com.apress.demo

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param

trait UserRepository extends PagingAndSortingRepository[User, java.lang.Long] {
  def findByEmail(@Param("email") name: String): List[User]
}