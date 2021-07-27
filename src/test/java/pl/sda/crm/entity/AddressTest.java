package pl.sda.crm.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AddressTest extends BaseEntityTest {

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