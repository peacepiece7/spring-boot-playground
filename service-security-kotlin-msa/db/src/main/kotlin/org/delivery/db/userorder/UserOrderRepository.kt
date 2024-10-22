package org.delivery.db.userorder

import org.delivery.db.userorder.enums.UserOrderStatus
import org.springframework.data.jpa.repository.JpaRepository

interface UserOrderRepository : JpaRepository<UserOrderEntity, Long> {
    fun findAllByUserIdAndStatusOrderByIdDesc(userId: Long?, status: UserOrderStatus?): List<UserOrderEntity>

    fun findAllByUserIdAndStatusInOrderByIdDesc(
        userId: Long?,
        statusList: List<UserOrderStatus>
    ): List<UserOrderEntity>

    fun findByIdAndUserId(id: Long?, userId: Long?): UserOrderEntity?

    // 특정 주문
    // select * from user_order where id = ? and status = ? and user_id = ?
    fun findAllByIdAndStatusAndUserId(id: Long?, status: UserOrderStatus?, userId: Long?): UserOrderEntity
    fun findAllByIdAndUserId(id: Long?, userOd: Long?): UserOrderEntity?
}
