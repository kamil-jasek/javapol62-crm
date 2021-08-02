package pl.sda.crm.entity;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    @Query("select (count(c.id) > 0) from Customer c where upper(c.name) = upper(?1)")
    boolean isCustomerNameRegistered(String name);
}
