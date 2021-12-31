package in.greatlearning.EmployeeManagement.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.greatlearning.EmployeeManagement.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	List<Employee> findByFirstNameContainsAndEmailContainsAllIgnoreCase(String firstName, String email);

}
