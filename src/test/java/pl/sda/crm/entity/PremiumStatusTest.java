package pl.sda.crm.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class PremiumStatusTest extends BaseEntityTest {

    @Test
    void shouldSavePremiumStatusInDatabase() {
        // given
        final var premiumStatus = new PremiumStatus(LocalDateTime.now().plusDays(90));

        // when
        persist(premiumStatus);

        // then
        final var readPremiumStatus = em.find(PremiumStatus.class, premiumStatus.getId());
        assertEquals(premiumStatus, readPremiumStatus);
    }
}