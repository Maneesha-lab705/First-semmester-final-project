package lk.ijse.fitcore.Model;

import lk.ijse.fitcore.dto.Employee;
import lk.ijse.fitcore.dto.Member;
import lk.ijse.fitcore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {


    public static boolean save(Employee employee) throws SQLException {
        Employee employee1 =serch(employee.getId());
        if (employee1!= null){
            return  false;
        }
        String sql = "INSERT INTO employee(Employee_Id,First_Name,Last_Name,Address,NIC,Contact,Role)VALUES(?,?,?,?,?,?,?)";
        return CrudUtil.execute(sql,employee.getId(),employee.getFirstName(),employee.getLastName(),employee.getAddress(),employee.getNic(),employee.getContact(),employee.getRole());
    }

    public static Employee serch(String id) throws SQLException {

        String sql = "SELECT * FROM employee WHERE Employee_Id = ?";
        ResultSet resultSet = CrudUtil.execute(sql, id);

        if (resultSet.next()){
            String employeeId = resultSet.getString(1);
            String firstName = resultSet.getString(2);
            String lastName = resultSet.getString(3);
            String address = resultSet.getString(4);
            String nic = resultSet.getString(5);
            Integer contact = Integer.valueOf(resultSet.getString(6));
            String role = resultSet.getString(7);

            return  new Employee(employeeId,firstName,lastName,address,nic,contact,role);

        }
        return  null;

    }

    public static Employee serchNic(String nic) throws SQLException {
        String sql="SELECT * FROM employee WHERE NIC =? ";
        ResultSet resultSet =CrudUtil.execute(sql,nic);

        if (resultSet.next()){
            String employeeId = resultSet.getString(1);
            String firstName = resultSet.getString(2);
            String lastName = resultSet.getString(3);
            String address = resultSet.getString(4);
            String Nic = resultSet.getString(5);
            Integer contact = Integer.valueOf(resultSet.getString(6));
            String role = resultSet.getString(7);

            return  new Employee(employeeId,firstName,lastName,address,Nic,contact,role);
        }
        return null;
    }

    public static boolean delete(String id) throws SQLException {
        String sql ="DELETE FROM  employee WHERE Employee_Id = ?";
        return  CrudUtil.execute(sql,id);


    }

    public static boolean update(Employee employee) throws SQLException {
        String sql ="UPDATE employee SET  First_Name = ?,Last_Name = ?,Address = ?,NIC = ?, Contact =?, Role = ? WHERE Employee_Id = ?";
        return CrudUtil.execute(sql,employee.getFirstName(),employee.getLastName(),employee.getAddress(),employee.getNic(),employee.getContact(),employee.getRole(),employee.getId());

    }

    public static List<String> getId() throws SQLException {
        String sql ="SELECT Employee_Id FROM employee";

        List<String> id =new ArrayList<>();

        ResultSet resultSet =CrudUtil.execute(sql);

        while (resultSet.next()){
            id.add(resultSet.getString(1));
        }
        return id;

    }


}
