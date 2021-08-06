package pl.sda.crm.entity;

import static org.springframework.util.Assert.hasText;
import static org.springframework.util.Assert.notNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.sda.crm.dto.RegisterCustomerForm;

@Entity
@Table(name = "customers")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE) // for jpa
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public final class Customer {

    @Id
    @Include
    private UUID id;

    private String name;

    @Enumerated(EnumType.STRING)
    private CustomerType type;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private List<Address> addresses;

    @Embedded
    private PremiumStatus premiumStatus;

    public Customer(String name, CustomerType type) {
        hasText(name, "name is missing");
        notNull(type, "type is missing");
        this.id = UUID.randomUUID();
        this.name = name;
        this.type = type;
        this.addresses = new ArrayList<>();
        this.premiumStatus = new PremiumStatus();
    }

    public static Customer createWith(RegisterCustomerForm form) {
        final var customer = new Customer(form.getName(), CustomerType.valueOf(form.getType().name()));
        final var address = form.getAddress();
        customer.addAddress(new Address(address.getStreet(),
            address.getCity(),
            address.getZipCode(),
            address.getCountry()));
        return customer;
    }

    public List<Address> getAddresses() {
        return new ArrayList<>(addresses);
    }

    public void addAddress(Address address) {
        if (!addresses.contains(address)) {
            addresses.add(address);
        }
    }
}
