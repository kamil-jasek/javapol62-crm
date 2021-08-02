package pl.sda.crm.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.crm.dto.RegisterCustomerForm;
import pl.sda.crm.dto.RegisteredCustomerId;
import pl.sda.crm.entity.Customer;
import pl.sda.crm.entity.CustomerRepository;
import pl.sda.crm.exception.CustomerNameRegisteredException;

@Service
@RequiredArgsConstructor
public class CustomerService {

    @NonNull
    private final CustomerRepository repository;

    @Transactional
    public RegisteredCustomerId registerCustomer(RegisterCustomerForm form) {
        if (repository.isCustomerNameRegistered(form.getName())) {
            throw new CustomerNameRegisteredException("customer name: " + form.getName());
        }

        final var customer = Customer.createWith(form);
        repository.save(customer);
        return new RegisteredCustomerId(customer.getId());
    }

}
