package pl.sda.crm.entity;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Entity
@Table(name = "premium_statuses")
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE) // for jpa
public final class PremiumStatus {

    @Id
    private UUID id;
    private boolean premium;
    private LocalDateTime premiumExpiration;

    public PremiumStatus(LocalDateTime premiumExpiration) {
        Assert.state(premiumExpiration.isAfter(LocalDateTime.now()), "premium expiration is invalid");
        this.id = UUID.randomUUID();
        this.premium = true;
        this.premiumExpiration = premiumExpiration;
    }
}
