package lk.ijse.fitcore.Model;

import lk.ijse.fitcore.dto.MemberAttendane;
import lk.ijse.fitcore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MemberAttendModel {
    public static boolean save(MemberAttendane memberAttendane) throws SQLException {
        String sql ="INSERT INTO memberattendance (Member_Id,Date,In_Time,IsAttend)VALUES(?,?,?,?)";
        return CrudUtil.execute(sql,memberAttendane.getMemberId(), memberAttendane.getDate(), memberAttendane.getTime(),memberAttendane.getIsAttendance());


    }


    public static List<MemberAttendane> getAll(String text) throws SQLException {
        String sql ="SELECT * FROM memberattendance WHERE Member_Id =?";

        List<MemberAttendane> all=new ArrayList<>();

        ResultSet resultSet =CrudUtil.execute(sql,""+text+"");
        while (resultSet.next()){
            String id =resultSet.getString(1);
            String date =resultSet.getString(2);
            String time=resultSet.getString(3);
            String isAttend =resultSet.getString(4);

            all.add(new MemberAttendane(id,date,time,isAttend));


        }
        return all;
    }

    public static List<String> getIds() throws SQLException {
        String sql ="SELECT Member_Id FROM member";

        List<String > ids =new ArrayList<>();
        ResultSet resultSet =CrudUtil.execute(sql);

        while (resultSet.next()){
            ids.add(resultSet.getString(1));
        }
        return ids;
    }
}
