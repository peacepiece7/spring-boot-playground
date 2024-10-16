package org.delivery.db.userorder;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;
import org.delivery.db.userorder.enums.UserOrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_order")
public class UserOrderEntity extends BaseEntity {
    @Column(nullable = false)
    private Long userId;    // user table 1:n

    @Column(nullable = false)
    private Long storeId;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private UserOrderStatus status;

    @Column(precision = 11, scale = 4, nullable = false)
    private BigDecimal amount;

    private LocalDateTime orderedAt;

    private LocalDateTime acceptedAt;

    private LocalDateTime cookingStartedAt;

    private LocalDateTime deliveryStartedAt;

    private LocalDateTime receivedAt;
}
