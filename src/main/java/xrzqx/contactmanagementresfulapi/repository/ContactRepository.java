package xrzqx.contactmanagementresfulapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xrzqx.contactmanagementresfulapi.entity.Contact;
import xrzqx.contactmanagementresfulapi.entity.User;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact,String> {

    Optional<Contact> findFirstByUserAndId(User user, String id);

}
