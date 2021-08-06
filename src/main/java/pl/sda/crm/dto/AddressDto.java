package pl.sda.crm.dto;

import lombok.NonNull;
import lombok.Value;

@Value
public class AddressDto {

    @NonNull
    String street;

    @NonNull
    String city;

    @NonNull
    String zipCode;

    @NonNull
    String country;
}
