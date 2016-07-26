package coole.co.data.model.dao;

import java.sql.SQLException;
import java.util.List;

import coole.co.data.model.Employee;

public interface IEmployeeDao {

  public List<Employee> getDepartmentEmployees(int departmentId);

  public Employee getEmployee(int employeeId);

  public Employee addEmployee(Employee employee);

  public Employee updateEmployee(Employee employee);

  public void deleteEmployee(int employeeId);

}
