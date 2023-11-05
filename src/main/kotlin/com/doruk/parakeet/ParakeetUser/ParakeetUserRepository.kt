package com.doruk.parakeet.ParakeetUser

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ParakeetUserRepository : CrudRepository<ParakeetUser, String> {
}