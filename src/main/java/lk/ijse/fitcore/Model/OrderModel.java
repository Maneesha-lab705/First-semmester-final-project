package lk.ijse.fitcore.Model;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import lk.ijse.fitcore.Controller.OrderFormController;
import lk.ijse.fitcore.dto.OrderDto;
import lk.ijse.fitcore.dto.Orders;
import lk.ijse.fitcore.dto.tm.OrderTm;
import lk.ijse.fitcore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class OrderModel {

    public static String nextOrderID() throws SQLException {
    String sql ="SELECT Order_id FROM Orders ORDER BY Order_id DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);

        String id = null;
        if (resultSet.next()){
           return nextId(resultSet.getString(1));
        }
            return nextId(id);
    }
    private static String nextId(String id) {

        if (id != null){
            String[] strings = id.split("O0");
            int Nid = Integer.parseInt(strings[1]);
            Nid++;

            return "O0"+Nid;
        }
        return "O001";
    }

    public static boolean save(String orderId, String netTotal, String date, String time, String memberId) throws SQLException {

        String sql="INSERT INTO orders(Order_id,Amount,Date,Time,Member_Id)VALUES(?,?,?,?,?)";
        return CrudUtil.execute(sql, orderId,netTotal,date,time,memberId);

    }


    public static boolean save1(String orderId, String netTotal, String now, String now1, String others) throws SQLException {
        String sql="INSERT INTO orders(Order_id,Amount,Date,Time,Other_Buyers)VALUES(?,?,?,?,?)";
        return CrudUtil.execute(sql, orderId,netTotal,LocalDate.now(),LocalTime.now(),others);
    }
}
