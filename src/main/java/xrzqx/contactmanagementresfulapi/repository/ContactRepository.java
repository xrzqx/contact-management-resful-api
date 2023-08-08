package xrzqx.contactmanagementresfulapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xrzqx.contactmanagementresfulapi.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact,String> {
}
