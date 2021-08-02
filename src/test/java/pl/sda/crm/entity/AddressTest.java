package pl.sda.crm.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import pl.sda.crm.util.WithEntityManagerTest;

class AddressTest extends WithEntityManagerTest {

    @Test
    void shouldSaveAddressInDatabase() {
        // given
        final var address = new Address("str", "Wawa", "22-200", "PL");

        // when
        persist(address);

        // then
        final var readAddress = em.find(Address.class, address.getId());
        assertEquals(address, readAddress);
    }
}