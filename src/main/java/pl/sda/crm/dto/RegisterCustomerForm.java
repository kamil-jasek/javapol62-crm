package pl.sda.crm.dto;

import lombok.NonNull;
import lombok.Value;

@Value
public class RegisterCustomerForm {

    public enum CustomerType {
        COMPANY, PERSON
    }

    @NonNull
    String name;

    @NonNull
    CustomerType type;

    @NonNull
    AddressDto address;
}
