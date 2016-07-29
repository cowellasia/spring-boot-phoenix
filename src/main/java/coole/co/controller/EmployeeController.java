package coole.co.controller;

import coole.co.data.factory.ConnectionFactory;
import coole.co.data.factory.EntityFactory;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import coole.co.data.model.Employee;
import coole.co.data.model.service.IEmployeeService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import org.codehaus.jackson.map.ObjectMapper;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
  private static Connection con;
  protected String DEFAULT_LIMIT = "25";
  protected String DEFAULT_COUNTBY = "hostname";
  protected String LOGIC_AND = "AND";
  protected String LOGIC_OR = "OR";
  protected String TRACING_TABLE = "SYSTEM.TRACING_STATS";
  @Autowired
  private IEmployeeService employeeService;

//  @RequestMapping(method = RequestMethod.GET)
//  @ResponseBody
//  public List<Employee> getEmployees(int departmentId) {
//
//    return employeeService.getDepartmentEmployees(departmentId);
//    
//    
//  }
  @RequestMapping(method = RequestMethod.GET)
  @ResponseBody
  public String getEmployees(int departmentId) {
    String sqlQuery = "SELECT * FROM employees";
    String json = getResults(sqlQuery);
    return json;
  }
  @RequestMapping(value = "/feeds", method = RequestMethod.GET)
  @ResponseBody
  public String getFeeds() {
    String sqlQuery = "SELECT * FROM feeds";
    String json = getResults(sqlQuery);
    return json;
  }


  protected String getJson(String json) {
    String output = json.toString().replace("_id\":", "_id\":\"")
        .replace(",\"hostname", "\",\"hostname")
        .replace(",\"parent", "\",\"parent")
        .replace(",\"end", "\",\"end");
    return output;
  }
  


 /* @RequestMapping(value = "/{employeeId}", method = RequestMethod.GET)
  public String getEmployee(@PathVariable int employeeId) {

    String sqlQuery = "SELECT * FROM employees";
    String json = getResults(sqlQuery);
    return json;
  } */

//  @RequestMapping(value = "/{employeeId}", method = RequestMethod.GET)
//  public Employee getEmployee(@PathVariable int employeeId) {
//
//    return employeeService.getEmployee(employeeId);
//  }
  
  @RequestMapping(method = RequestMethod.POST)
  public Employee addEmployee(@RequestBody Employee employee) {
    
    return employeeService.addEmployee(employee);
  }
  
  @RequestMapping(value = "/{employeeId}", method = RequestMethod.PUT)
  public Employee updateEmployee(@PathVariable int employeeId, @RequestBody Employee newEmployee) {
    Employee oldEmployee = employeeService.getEmployee(employeeId);

    if (oldEmployee != null) {
      oldEmployee.setName(newEmployee.getName());
      oldEmployee.setEmailId(newEmployee.getEmailId());
      oldEmployee.setDepartmentId(newEmployee.getDepartmentId());

      return employeeService.updateEmployee(oldEmployee);
    }

    return null;
  }
  
  @RequestMapping(value = "/{employeeId}", method = RequestMethod.DELETE)
  @ResponseStatus(code = HttpStatus.OK)
  public void delete(@PathVariable int employeeId) {
    employeeService.deleteEmployee(employeeId);
  }
  
   protected String getResults(String sqlQuery) {
    String json = null;
    if(sqlQuery == null){
      json = "{error:true,msg:'SQL was null'}";
    }else{
    try {
      con = ConnectionFactory.getConnection();
      EntityFactory nutrientEntityFactory = new EntityFactory(con,sqlQuery);
      List<Map<String, Object>> nutrients = nutrientEntityFactory
          .findMultiple(new Object[] {});
      ObjectMapper mapper = new ObjectMapper();
      json = mapper.writeValueAsString(nutrients);
    } catch (Exception e) {
      json = "{error:true,msg:'Serrver Error:"+e.getMessage()+"'}";
    } finally {
      if (con != null) {
        try {
          con.close();
        } catch (SQLException e) {
          json = "{error:true,msg:'SQL Serrver Error:"+e.getMessage()+"'}";
        }
      }
    }
    }
    return json;
  }

}
