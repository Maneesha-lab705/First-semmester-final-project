package lk.ijse.fitcore.Model;

import lk.ijse.fitcore.dto.Schedule;
import lk.ijse.fitcore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ShedulModel {
    public static boolean save(String id, String ExersizeTypeId, String date) throws SQLException {
        String sql= "INSERT INTO member_detail (Member_Id, Exercize_Type_Id, Date) VALUES(?,?,?)";
        return CrudUtil.execute(sql, id,ExersizeTypeId, date);
    }


    public static boolean update(String id, String exType) throws SQLException {
        String sql= "UPDATE member_detail SET  Exercize_Type_Id = ? WHERE Member_Id = ?";
        return CrudUtil.execute(sql, exType, id);
    }

    public static boolean save2(Schedule shedul) throws SQLException {
        String sql ="INSERT INTO shedul(Shedul_Id,Name,Time_duration,Description,Exercize_Type_Id)VALUES(?,?,?,?,?) ";
        return CrudUtil.execute(sql,shedul.getScheduled(),shedul.getName(),shedul.getTime(),shedul.getDescription(),shedul.getExId());
    }

    public static boolean updateAll(Schedule shedul) throws SQLException {

        String sql ="UPDATE shedul SET Name =?,Time_Duration =?,Description =?,Exercize_Type_Id = ? WHERE Shedul_Id =? ";
        return  CrudUtil.execute(sql,shedul.getName(),shedul.getTime(),shedul.getDescription(),shedul.getExId(),shedul.getScheduled());

    }

    public static Schedule getAll(String exTypeId) throws SQLException {
        String sql ="SELECT * FROM  shedul WHERE Exercize_Type_Id =?";

        ResultSet resultSet =CrudUtil.execute(sql,exTypeId);

        if (resultSet.next()){
            String sId =resultSet.getString(1);
            String name =resultSet.getString(2);
            String time =resultSet.getString(3);
            String description =resultSet.getString(4);
            String  eType =resultSet.getString(5);

            return new Schedule(sId,name,time,description,eType);

        }
        return   null;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM shedul WHERE Shedul_Id = ?";
        return CrudUtil.execute(sql,id);
    }


    public static String getShedulId() throws SQLException {
        String sql ="SELECT Shedul_Id FROM shedul ORDER BY Shedul_Id DESC LIMIT 1";
        ResultSet resultSet =CrudUtil.execute(sql);

        String shedulId =null;
            if (resultSet.next()){
                return  nexId(resultSet.getString(1));
            }
            return nexId(shedulId);
    }

    private  static  String nexId(String id){
        if (id!= null){
            String [] Strings =id.split("S00");
            int sId =Integer.parseInt(Strings [1]);
            sId++;

            return "S00"+sId;
        }
        return "S001";

    }

    public static String getExName(String exid) throws SQLException {
        String sql ="SELECT Exercize_Type_Name FROM exercize_type WHERE Exercize_Type_Id = ?";
        ResultSet resultSet = CrudUtil.execute(sql,exid);

        String name = "";
        if (resultSet.next()){
            name =  resultSet.getString(1);
        }
        return  name;
    }
}

