package com.apress.springbootgroovydemo

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="users")
class User {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id
    String name
    String email
}
