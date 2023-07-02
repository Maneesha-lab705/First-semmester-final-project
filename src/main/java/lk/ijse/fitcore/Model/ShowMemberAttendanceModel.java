package lk.ijse.fitcore.Model;

import lk.ijse.fitcore.dto.ShowMemberAttend;
import lk.ijse.fitcore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowMemberAttendanceModel {
    public static List<ShowMemberAttend> getAll() throws SQLException {
        String sql = "SELECT Member_Id,First_Name FROM member ";
        List<ShowMemberAttend> get = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql);

        while (resultSet.next()){
            String id=resultSet.getString(1);
            String name=resultSet.getString(2);

            get.add(new ShowMemberAttend(id,name));
        }
        return get;

    }
}
