package pl.sda.crm.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.sda.crm.entity.CustomerType.COMPANY;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.sda.crm.dto.AddressDto;
import pl.sda.crm.dto.RegisterCustomerForm;
import pl.sda.crm.dto.RegisterCustomerForm.CustomerType;
import pl.sda.crm.entity.Customer;
import pl.sda.crm.exception.CustomerNameRegisteredException;
import pl.sda.crm.util.WithEntityManagerTest;


class CustomerServiceTest extends WithEntityManagerTest {

    @Autowired
    private CustomerService customerService;

    @Test
    void shouldRegisterCustomer() {
        // given
        final var form = new RegisterCustomerForm("Comp S.A.",
            CustomerType.COMPANY,
            new AddressDto("str", "Wawa", "03-100", "PL"));

        // when
        final var registeredCustomerId = customerService.registerCustomer(form);

        // then
        assertNotNull(registeredCustomerId.getId());
    }

    @Test
    void shouldNotRegisterCustomerIfNameExists() {
        // given
        persist(new Customer("XYZ S.A.", COMPANY));
        final var form = new RegisterCustomerForm("XYZ S.A.",
            CustomerType.COMPANY,
            new AddressDto("str", "Wawa", "03-100", "PL"));

        // then
        assertThrows(CustomerNameRegisteredException.class, () -> customerService.registerCustomer(form));
    }
}