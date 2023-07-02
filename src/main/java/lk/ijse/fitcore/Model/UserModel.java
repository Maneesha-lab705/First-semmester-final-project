package lk.ijse.fitcore.Model;

import javafx.scene.control.Alert;
import lk.ijse.fitcore.db.DBConnection;
import lk.ijse.fitcore.dto.User;
import lk.ijse.fitcore.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {
    public static boolean save(User user) throws SQLException {
        try (Connection con= DBConnection.getInstance().getConnection()){
            String sql = "INSERT INTO user(User_Id, User_name, NIC,Email,Password) VALUES(?, ?, ?, ?,?)";

            PreparedStatement pstm = con.prepareStatement(sql);

            pstm.setString(1,user.getId());
            pstm.setString(2,user.getName());
            pstm.setString(3,user.getNic());
            pstm.setString(4,user.getEmail());
            pstm.setString(5,user.getPassword());

           return pstm.executeUpdate() > 0;

        }
    }


    public static boolean search(String name, String password) throws SQLException {
        String sql = "SELECT * FROM user WHERE User_Name = ? && Password= ?";
        ResultSet resultSet = CrudUtil.execute(sql,name,password);

        if (resultSet.next()){
            return true;
        }
        return false;
    }
}
