package in.greatlearning.EmployeeManagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.greatlearning.EmployeeManagement.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User getByUsername(String username);

}
