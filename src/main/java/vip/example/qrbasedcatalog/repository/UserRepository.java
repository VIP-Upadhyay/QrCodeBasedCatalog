package vip.example.qrbasedcatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vip.example.qrbasedcatalog.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}