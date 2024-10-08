package org.delivery.db.storemenu;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.delivery.db.BaseEntity;
import org.delivery.db.store.enums.StoreStatus;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "store_menu")
public class StoreMenuEntity extends BaseEntity {
    @Column(nullable = false)
    private Long storeId;

    @Column(nullable = false)
    private String name;

    @Column(precision = 11, scale = 4, nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private StoreStatus status;

    @Column(length = 250, nullable = false)
    private String thumbnailUrl;

    @Column(length = 50, nullable = false)
    private int likeCount;

    @Column(nullable = false)
    @Positive
    private int sequence;
}
