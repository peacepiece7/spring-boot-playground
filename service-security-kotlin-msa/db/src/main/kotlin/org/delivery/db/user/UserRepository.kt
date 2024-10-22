package org.delivery.db.user

import org.delivery.db.user.enums.UserStatus
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<UserEntity, Long> {

    fun findFirstByIdAndStatusOrderByIdDesc(userId: Long?, status: UserStatus?): Optional<UserEntity?>?

    fun findFirstByEmailAndPasswordAndStatusOrderByIdDesc(
        email: String?,
        password: String?,
        status: UserStatus?
    ): Optional<UserEntity?>?
}
