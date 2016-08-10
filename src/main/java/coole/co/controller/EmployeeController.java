package coole.co.controller;

import coole.co.data.factory.ConnectionFactory;
import coole.co.data.factory.EntityFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Row;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.phoenix.jdbc.PhoenixConnection;
import org.apache.phoenix.query.ConnectionQueryServices;
import org.apache.phoenix.query.QueryConstants;
import org.apache.phoenix.util.SchemaUtil;
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

  private static final byte[] FAMILY_NAME_A = Bytes.toBytes(SchemaUtil.normalizeIdentifier("A"));
  private static final byte[] FAMILY_NAME_B = Bytes.toBytes(SchemaUtil.normalizeIdentifier("B"));
  private static final String HBASE_DYNAMIC_COLUMNS = "HBASE_DYNAMIC_COLUMNS";


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

  @RequestMapping(value = "/create", method = RequestMethod.GET)
  public String create() throws Exception{
    try {

      PhoenixConnection pconn = ConnectionFactory.getConnection().unwrap(PhoenixConnection.class);
      ConnectionQueryServices services = pconn.getQueryServices();
      try (HBaseAdmin admin = services.getAdmin()) {
        HTableDescriptor htd = new HTableDescriptor(TableName.valueOf(HBASE_DYNAMIC_COLUMNS));
        htd.addFamily(new HColumnDescriptor(QueryConstants.DEFAULT_COLUMN_FAMILY_BYTES));
        htd.addFamily(new HColumnDescriptor(FAMILY_NAME_A));
        htd.addFamily(new HColumnDescriptor(FAMILY_NAME_B));
        admin.createTable(htd);
      }

    } finally {
      //conn.close();
      return "create success";
    }
  }

  @RequestMapping(value = "/insert", method = RequestMethod.GET)
  public String insert() throws Exception {
    try {

      PhoenixConnection pconn = ConnectionFactory.getConnection().unwrap(PhoenixConnection.class);
      ConnectionQueryServices services = pconn.getQueryServices();
      HTableInterface hTable = services.getTable(Bytes.toBytes(HBASE_DYNAMIC_COLUMNS));
      try {
        // Insert rows using standard HBase mechanism with standard HBase "types"
        List<Row> mutations = new ArrayList<Row>();
        byte[] dv = Bytes.toBytes("DV");
        byte[] first = Bytes.toBytes("F");
        byte[] f1v1 = Bytes.toBytes("F1V1");
        byte[] f1v2 = Bytes.toBytes("F1V2");
        byte[] f2v1 = Bytes.toBytes("F2V1");
        byte[] f2v2 = Bytes.toBytes("F2V2");
        byte[] key = Bytes.toBytes("entry1");

        Put put = new Put(key);
        put.add(QueryConstants.DEFAULT_COLUMN_FAMILY_BYTES, dv, Bytes.toBytes("default"));
        put.add(QueryConstants.DEFAULT_COLUMN_FAMILY_BYTES, first, Bytes.toBytes("first"));
        put.add(FAMILY_NAME_A, f1v1, Bytes.toBytes("f1value1"));
        put.add(FAMILY_NAME_A, f1v2, Bytes.toBytes("f1value2"));
        put.add(FAMILY_NAME_B, f2v1, Bytes.toBytes("f2value1"));
        put.add(FAMILY_NAME_B, f2v2, Bytes.toBytes("f2value2"));
        mutations.add(put);

        hTable.batch(mutations);
        return "insert success";

        // Create Phoenix table after HBase table was created through the native APIs
        // The timestamp of the table creation must be later than the timestamp of the data
        // ensureTableCreated(getUrl(), HBASE_DYNAMIC_COLUMNS);
      } finally {
        hTable.close();
        return "hello world";
      }
    } finally {
      //conn.close();
      return "hello world";
    }

  }

  @RequestMapping(value = "/test", method = RequestMethod.GET)
  public ResultSet getTest() throws Exception {
    String query = "SELECT * FROM HBASE_DYNAMIC_COLUMNS (DV varchar)";

    Connection conn = ConnectionFactory.getConnection();
    try {
      PreparedStatement statement = conn.prepareStatement(query);
      ResultSet rs = statement.executeQuery();
      return rs;
    } finally {
      conn.close();
    }
  }

}
