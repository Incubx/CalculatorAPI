package shevtsov.denis.calculator_api.Repository;

import org.springframework.data.repository.CrudRepository;
import shevtsov.denis.calculator_api.Entity.User;

public interface UserRepository  extends CrudRepository<User,Integer> {
}
