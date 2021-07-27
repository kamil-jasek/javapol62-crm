package pl.sda.crm.entity;

import static org.springframework.util.Assert.hasText;
import static org.springframework.util.Assert.state;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Entity
@Table(name = "addresses")
@NoArgsConstructor(access = AccessLevel.PRIVATE) // supports jpa/hibernate
@Getter
@EqualsAndHashCode
public final class Address {

    @Id
    private UUID id;
    private String street;
    private String city;
    private String zipCode;
    private String countryCode;

    public Address(String street, String city, String zipCode, String countryCode) {
        hasText(street, "street is empty text");
        hasText(city, "city is empty text");
        state(zipCode.matches("\\d{2}-\\d{3}"), "zip code is invalid");
        state(countryCode.matches("[A-Z]{2}"), "country code is invalid");
        this.id = UUID.randomUUID();
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.countryCode = countryCode;
    }
}
