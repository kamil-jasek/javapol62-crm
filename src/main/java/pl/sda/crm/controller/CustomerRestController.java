package pl.sda.crm.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.crm.dto.RegisterCustomerForm;
import pl.sda.crm.dto.RegisteredCustomerId;
import pl.sda.crm.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
final class CustomerRestController {

    @NonNull
    private final CustomerService customerService;

    @PostMapping
    RegisteredCustomerId registerNewCustomer(@RequestBody RegisterCustomerForm form) {
        return customerService.registerCustomer(form);
    }
}
