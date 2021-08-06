package pl.sda.crm.entity;

import static org.springframework.util.Assert.state;

import java.time.LocalDateTime;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public final class PremiumStatus {

    private boolean premium;
    private LocalDateTime premiumExpiration;

    public PremiumStatus(LocalDateTime premiumExpiration) {
        state(premiumExpiration.isAfter(LocalDateTime.now()), "premium expiration is invalid");
        this.premium = true;
        this.premiumExpiration = premiumExpiration;
    }
}
