package in.greatlearning.EmployeeManagement.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;

import in.greatlearning.EmployeeManagement.entities.Employee;
import in.greatlearning.EmployeeManagement.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeesController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/list")
	public String listEmployees(Model model) {
		List<Employee> employees = employeeService.findAll();

		model.addAttribute("Employees", employees);

		return "list-Employees";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// get the Employee from the service

		Employee employee = new Employee();
		// set Employee as a model attribute to pre-populate the form
		theModel.addAttribute("Employee", employee);

		// send over to our form
		return "Employee-form";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {

		// get the Employee from the service
		Employee theEmployee = employeeService.findById(theId);

		// set Employee as a model attribute to pre-populate the form
		theModel.addAttribute("Employee", theEmployee);

		// send over to our form
		return "Employee-form";
	}

	@PostMapping("/save")
	public String saveEmployee(@RequestParam("id") String id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("email") String email) {

		System.out.println(id);
		Integer employeeId = 0;
		try {
			if (id != null)
				employeeId = Integer.parseInt(id);
		} catch (Exception e) {

		}
		Employee theEmployee;
		if (employeeId != 0) {
			theEmployee = employeeService.findById(employeeId);
			theEmployee.setFirstName(firstName);
			theEmployee.setLastName(lastName);
			theEmployee.setEmail(email);
		} else
			theEmployee = new Employee(firstName, lastName, email);
		// save the Employee
		employeeService.save(theEmployee);

		// use a redirect to prevent duplicate submissions
		return "redirect:/employees/list";

	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId) {

		// delete the Employee
		employeeService.deleteById(theId);

		// redirect to /Employees/list
		return "redirect:/employees/list";

	}

	@RequestMapping("/search")
	public String search(@RequestParam("firstName") String firstName, @RequestParam("email") String email,
			Model theModel) {

		// check names, if both are empty then just give list of all Employees

		if (firstName.trim().isEmpty() && email.trim().isEmpty()) {
			return "redirect:/employees/list";
		} else {
			// else, search by name and email
			List<Employee> theEmployees = employeeService.searchBy(firstName, email);

			// add to the spring model
			theModel.addAttribute("Employees", theEmployees);

			// send to list-Employees
			return "list-Employees";
		}

	}

	@RequestMapping(value = "/403")
	public ModelAndView accessDenied(Principal user) {
		ModelAndView model = new ModelAndView();
		if (user != null) {
			model.addObject("msg", "Hi" + user.getName() + "you do not have permission to access this page!");
		} else {
			model.addObject("msg", "you do not have permission to access this page!");

		}
		model.setViewName("403");
		return model;

	}

}
