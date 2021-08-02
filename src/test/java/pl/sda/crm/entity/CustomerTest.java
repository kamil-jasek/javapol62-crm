package pl.sda.crm.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import pl.sda.crm.util.WithEntityManagerTest;

class CustomerTest extends WithEntityManagerTest {

    @Test
    void shouldSaveCustomerToDatabase() {
        // given
        final var customer = new Customer("Jan Kowalski", CustomerType.PERSON);

        // when
        persist(customer);

        // then
        final var readCustomer = em.find(Customer.class, customer.getId());
        assertEquals(customer, readCustomer);
        assertEquals(customer.getName(), readCustomer.getName());
        assertEquals(customer.getType(), readCustomer.getType());
    }

    @Test
    void shouldAddAddressToCustomer() {
        // given
        final var customer = new Customer("Comp S.A.", CustomerType.COMPANY);
        final var address = new Address("str", "Wawa", "03-200", "PL");

        // when
        customer.addAddress(address);
        persist(customer);

        // then
        final var readCustomer = em.find(Customer.class, customer.getId());
        assertEquals(customer, readCustomer);
        assertEquals(customer.getAddresses(), readCustomer.getAddresses());
    }
}