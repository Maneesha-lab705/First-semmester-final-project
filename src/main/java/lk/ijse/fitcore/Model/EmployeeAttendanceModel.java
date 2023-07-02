package lk.ijse.fitcore.Model;

import lk.ijse.fitcore.dto.EmployeeAttendance;
import lk.ijse.fitcore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeAttendanceModel {
    public static boolean save(EmployeeAttendance employeeAttendance) throws SQLException {

        String sql="INSERT INTO attendance(Employee_Id,Date,In_Time,IsAttend)VALUES(?,?,?,?)";
        return CrudUtil.execute(sql,employeeAttendance.getEmployeeId(),employeeAttendance.getDate(),employeeAttendance.getTime(),employeeAttendance.getIsAttendance());


    }

    public static List<EmployeeAttendance> getall(String text) throws SQLException {

        String sql="SELECT * FROM attendance WHERE Employee_Id =?";

        List<EmployeeAttendance> all =new ArrayList<>();

        ResultSet resultSet =CrudUtil.execute(sql,""+text+"");

        while (resultSet.next()){
            String id =resultSet.getString(1);
            String date =resultSet.getString(2);
            String time =resultSet.getString(3);
            String prosent_Or_abson=resultSet.getString(4);

            all.add(new EmployeeAttendance(id,date,time,prosent_Or_abson));
        }
        return all;
    }
}
