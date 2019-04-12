package ch04.examples.ejb;

import java.util.List;

import ch04.examples.model.Department;
import ch04.examples.model.Employee;

public interface DepartmentService {
	Employee addEmployee(int department_id, Employee e) ;
	Department addDepartment(String name);
	List<Department> getAllDepartments();
	List<Employee> getAllEmployees();
	Employee getEmployee(int employee_id);
}
