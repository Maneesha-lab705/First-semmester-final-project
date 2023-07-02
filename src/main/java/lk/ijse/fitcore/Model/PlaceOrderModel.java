package lk.ijse.fitcore.Model;

import lk.ijse.fitcore.db.DBConnection;
import lk.ijse.fitcore.dto.OrderDto;
import lk.ijse.fitcore.dto.Orders;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class PlaceOrderModel {
    public static boolean save(String orderId, String memberId, String netTotal, String date, String time, List<OrderDto> orderDtoList) throws SQLException {
        Connection connection =null;
        try {
            connection= DBConnection.getInstance().getConnection();
           connection.setAutoCommit(false);

           boolean isSave =OrderModel.save(orderId,netTotal,date,time,memberId);
                if (isSave){
                    boolean isUpdate=ItemModel.updateqty(orderDtoList);
                    if (isUpdate){
                        boolean isSaveOrderDetail=OderDetailModel.save(orderId,orderDtoList);
                        if (isSaveOrderDetail){
                            connection.commit();
                            return true;
                        }
                    }
                }
                return  false;

        } catch (SQLException e) {
           e.printStackTrace();
            connection.rollback();
            return false;
        }finally {
            connection.setAutoCommit(true);
        }

    }




    public static boolean save1(String orderId, String date, String time, String others, String netTotal, List<OrderDto> orderDtoList) throws SQLException {
        Connection connection =null;
        try {
            connection= DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isSave =OrderModel.save1(orderId,netTotal, date, time,others);
            if (isSave){
                boolean isUpdate=ItemModel.updateqty(orderDtoList);
                if (isUpdate){
                    boolean isSaveOrderDetail=OderDetailModel.save(orderId,orderDtoList);
                    if (isSaveOrderDetail){
                        connection.commit();
                        return true;
                    }
                }
            }
            return  false;

        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
            return false;
        }finally {
            connection.setAutoCommit(true);
        }
    }
}
