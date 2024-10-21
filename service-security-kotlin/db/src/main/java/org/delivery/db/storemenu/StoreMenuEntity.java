package org.delivery.db.storemenu;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;
import org.delivery.db.storemenu.enums.StoreMenuStatus;

@Entity
@Getter
@Setter
@ToString
@SuperBuilder
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

    private StoreMenuStatus status;

    @Column(length = 250, nullable = false)
    private String thumbnailUrl;

    @Builder.Default
    @Column(length = 50, nullable = false)
    private int likeCount = 0;

    @Builder.Default
    @Column(nullable = false)
    private int sequence = 0;
}
