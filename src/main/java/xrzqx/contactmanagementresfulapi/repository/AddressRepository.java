package xrzqx.contactmanagementresfulapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xrzqx.contactmanagementresfulapi.entity.Address;
import xrzqx.contactmanagementresfulapi.entity.Contact;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {
    Optional<Address> findFirstByContactAndId(Contact contact, String id);
}
