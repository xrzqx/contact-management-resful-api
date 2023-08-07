package xrzqx.contactmanagementresfulapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xrzqx.contactmanagementresfulapi.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
}
