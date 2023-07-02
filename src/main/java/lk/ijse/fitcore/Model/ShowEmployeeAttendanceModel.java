package lk.ijse.fitcore.Model;

import lk.ijse.fitcore.dto.ShowEmployeeAttend;
import lk.ijse.fitcore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowEmployeeAttendanceModel {
    public static List<ShowEmployeeAttend> getAll() throws SQLException {
        String sql ="SELECT Employee_Id,First_Name FROM employee";

        List<ShowEmployeeAttend> all=new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql);

        while (resultSet.next()){
         String id =resultSet.getString(1);
         String name =resultSet.getString(2);

         all.add(new ShowEmployeeAttend(id,name));
        }
        return all;

    }
}
