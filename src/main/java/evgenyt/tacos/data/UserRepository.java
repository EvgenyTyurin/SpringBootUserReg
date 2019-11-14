package evgenyt.tacos.data;

import org.springframework.data.repository.CrudRepository;

import evgenyt.tacos.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
}
