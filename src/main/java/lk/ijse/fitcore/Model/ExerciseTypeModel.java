package lk.ijse.fitcore.Model;

import lk.ijse.fitcore.dto.Exercize;
import lk.ijse.fitcore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExerciseTypeModel {

    public static List<String> getTypes() throws SQLException {
        String sql ="SELECT Exercize_Type_Id FROM exercize_type";
        ResultSet resultSet = CrudUtil.execute(sql);

        List<String>types=new ArrayList<>();

        while (resultSet.next()){
            types.add(resultSet.getString(1));
        }
        return types;
    }


    public static boolean save(Exercize exercize) throws SQLException {
        String sql="INSERT INTO exercize_type(Exercize_Type_Id,Exercize_Type_Name,Hourly_Fee)VALUES(?,?,?)";
        return CrudUtil.execute(sql,exercize.getExtypeId(),exercize.getExName(),exercize.getFee());
    }

    public static Exercize serch(String text) throws SQLException {
        String sql = "SELECT * FROM exercize_type WHERE Exercize_Type_Id = ?";
        ResultSet resultSet = CrudUtil.execute(sql, text);

        if (resultSet.next()){
            String id =resultSet.getString(1);
            String name =resultSet.getString(2);
            double fee = Double.parseDouble(resultSet.getString(3));

            return new Exercize(id,name,fee);
        }
        return null;

    }

    public static boolean delete(String id) throws SQLException {
        String sql ="DELETE FROM exercize_type WHERE Exercize_Type_Id = ?";
        return CrudUtil.execute(sql,id);
    }

    public static boolean update(Exercize exercize) throws SQLException {
        String sql ="UPDATE exercize_type SET  Exercize_Type_Name = ? , Hourly_Fee = ? WHERE Exercize_Type_Id =?";
        return CrudUtil.execute(sql,exercize.getExName(),exercize.getFee(),exercize.getExtypeId());
    }
}
