package lk.ijse.fitcore.Model;

import lk.ijse.fitcore.dto.OrderDetail;
import lk.ijse.fitcore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailModel {
    public static List<OrderDetail> getAll() throws SQLException {
        String sql ="SELECT * FROM orders";

        List<OrderDetail> all =new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(sql);

        while (resultSet.next()){
            String orderId =resultSet.getString(1);
            Double amount = Double.valueOf(resultSet.getString(2));
            String date =resultSet.getString(3);
            String time =resultSet.getString(4);
            String memberId=resultSet.getString(5);
            String others =resultSet.getString(6);

            all.add(new OrderDetail(orderId,amount,date,time,memberId,others));
        }
        return all;

    }
}
