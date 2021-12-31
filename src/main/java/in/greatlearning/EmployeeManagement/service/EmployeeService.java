package in.greatlearning.EmployeeManagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.greatlearning.EmployeeManagement.entities.Employee;

@Service
public interface EmployeeService {
	public List<Employee> findAll();

	Employee findById(int id);

	void save(Employee employee);

	void deleteById(int id);

	List<Employee> searchBy(String firstName, String email);

}
