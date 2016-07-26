package coole.data.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import coole.co.data.model.Employee;
import coole.co.data.model.PagedList;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class EmployeeDaoImpl implements IEmployeeDao {

  private JdbcTemplate jdbcTemplate;

  @Autowired
  public void setDataSource(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  @Override
  public List<Employee> getDepartmentEmployees(int departmentId) {
    System.out.println("Retriving all employees within departmernt :" + departmentId);
    
    String selectEmpSql = "SELECT id, name, email, department_id FROM employees WHERE department_id = ?";
    List<Employee> employees = jdbcTemplate.query(selectEmpSql, new Integer[] {departmentId}, new EmplloyeeMapper());
    return new PagedList<Employee> (employees, 22); //TODO: total records count
  }

  @Override
  public Employee getEmployee(int employeeId) {
    System.out.println("Select employee with id :" + employeeId);
    
    String selectEmpSql = "SELECT id, name, email, department_id FROM employees WHERE id = ?";
    Employee employee = jdbcTemplate.queryForObject(selectEmpSql, new Integer[] {employeeId}, new EmplloyeeMapper());
    return employee;
  }

  @Override
  public Employee addEmployee(final Employee employee) {
//      try {
          System.out.println("Add an employees to departmernt :" + employee.getDepartmentId());
            jdbcTemplate.update("upsert into employees values (name, email, department_id) values("+employee.getName()+","+employee.getEmailId()+",)");
//          Connection connection = jdbcTemplate.getDataSource().getConnection();
//          Statement statement = connection.createStatement();
//          statement.executeUpdate("upsert into employees values (1,'Hello World')");
          
          
          PreparedStatementCreator ps = new PreparedStatementCreator() {
          @Override
          public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
          final PreparedStatement ps =
          connection.prepareStatement("INSERT INTO employees (name, email, department_id) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
          ps.setString(1, employee.getName());
          ps.setString(2, employee.getEmailId());
          ps.setInt(3, employee.getDepartmentId());
          return ps;
          }
          };
          
          KeyHolder holder = new GeneratedKeyHolder();
          jdbcTemplate.update(ps, holder);
          
          employee.setId(holder.getKey().intValue());
          
          
          
          return employee;
//      } catch (SQLException ex) {
//          Logger.getLogger(EmployeeDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//          return null;
//      }
  }

  @Override
  public Employee updateEmployee(Employee employee) {
    System.out.println("Update an employees for employeeId:" + employee.getId());
    
    String deleteEmpSql = "UPDATE employees SET name = ?, email = ?, department_id = ? WHERE id = ?";
    jdbcTemplate.update(deleteEmpSql, employee.getName(), employee.getEmailId(), employee.getDepartmentId(), employee.getId());
    return employee;
  }

  @Override
  public void deleteEmployee(int employeeId) {
    System.out.println("Delete an employees with employeeId:" + employeeId);
    
    String deleteEmpSql = "DELETE FROM employees WHERE id = ?";
    jdbcTemplate.update(deleteEmpSql, employeeId);
  }

  private static final class EmplloyeeMapper implements RowMapper<Employee> {
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
      Employee employee = new Employee();
      employee.setId(rs.getInt("id"));
      employee.setName(rs.getString("name"));
      employee.setEmailId(rs.getString("email"));
      employee.setDepartmentId(rs.getInt("department_id"));
      return employee;
    }
  }

}
