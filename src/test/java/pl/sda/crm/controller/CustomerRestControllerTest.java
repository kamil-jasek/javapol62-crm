package pl.sda.crm.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.sda.crm.dto.RegisteredCustomerId;
import pl.sda.crm.exception.CustomerNameRegisteredException;
import pl.sda.crm.service.CustomerService;

@WebMvcTest
class CustomerRestControllerTest {

    @MockBean
    private CustomerService customerService;

    @Autowired
    private MockMvc mvc;

    @Test
    void shouldRegisterCustomer() throws Exception {
        // given
        final var registeredCustomerId = new RegisteredCustomerId(UUID.randomUUID());
        given(customerService.registerCustomer(any())).willReturn(registeredCustomerId);

        // when
        mvc.perform(post("/api/customers")
            .content(requestExample())
            .header(CONTENT_TYPE, APPLICATION_JSON_VALUE))
            .andDo(print())
        // then
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(registeredCustomerId.getId().toString())));
    }

    @Test
    void shouldFailOnNonUniqueCustomerName() throws Exception {
        // given
        given(customerService.registerCustomer(any()))
            .willThrow(new CustomerNameRegisteredException("customer test exists"));

        // when
        mvc.perform(post("/api/customers")
            .content(requestExample())
            .header(CONTENT_TYPE, APPLICATION_JSON_VALUE))
            .andDo(print())
        // then
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.message", is("customer test exists")))
            .andExpect(jsonPath("$.code", is(400)));
    }

    private String requestExample() {
        return "{"
            + "\"name\": \"Compa S.A.\","
            + "\"type\": \"COMPANY\","
            + "\"address\": {"
            + "\"street\": \"Str\","
            + "\"city\": \"Wawa\","
            + "\"zipCode\": \"02-300\","
            + "\"country\": \"PL\""
            + "}"
            + "}";
    }
}