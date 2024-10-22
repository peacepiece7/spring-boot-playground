package org.delivery.db.storeuser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;
import org.delivery.db.storeuser.enums.StoreUserRole;
import org.delivery.db.storeuser.enums.StoreUserStatus;

@Entity
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "store_user")
public class StoreUserEntity extends BaseEntity {

    @NotNull
    @Column(nullable = false)
    private Long storeId;

    @Email
    @NotNull
    @Column(length = 100, nullable = false)
    private String email;

    @NotNull
    @Column(length = 100)
    private String password;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StoreUserRole role;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StoreUserStatus status;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private LocalDateTime lastLoginAt;
}
