package shevtsov.denis.calculator_api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shevtsov.denis.calculator_api.Entity.Users;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<Users,Integer> {
    Optional<Users> findByUsername(String username);
}
