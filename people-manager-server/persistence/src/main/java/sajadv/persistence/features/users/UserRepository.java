package sajadv.persistence.features.users;

import org.springframework.data.jpa.repository.JpaRepository;
import sajadv.domain.features.users.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
