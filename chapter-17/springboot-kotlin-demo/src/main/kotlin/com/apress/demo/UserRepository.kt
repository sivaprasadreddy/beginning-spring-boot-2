package com.apress.demo


import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {

    fun findByEmail(email: String): Iterable<User>
}