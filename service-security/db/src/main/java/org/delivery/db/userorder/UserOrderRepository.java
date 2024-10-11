package org.delivery.db.userorder;

import java.util.List;
import org.delivery.db.userorder.enums.UserOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOrderRepository extends JpaRepository<UserOrderEntity, Long> {

    public List<UserOrderEntity> findAllByUserIdAndStatusOrderByIdDesc(Long userId, UserOrderStatus status);

    List<UserOrderEntity> findAllByUserIdAndStatusInOrderByIdDesc(Long userId, List<UserOrderStatus> statusList);

    UserOrderEntity findByIdAndUserId(Long id, Long userId);
    /**
     * SELECT * FROM user-order WHERE user-id = val.user-id AND status IN ('ACCEPT', 'ORDER', ...)
     */
//    public List<UserOrderEntity> findAllByUserIdAndStatusIn(Long userId, List<UserOrderStatus> userOrderStatuses);
}
