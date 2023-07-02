package lk.ijse.fitcore.Model;

import lk.ijse.fitcore.dto.Payment;
import lk.ijse.fitcore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PaymentModel {
    public static String getNextId() throws SQLException {
        String sql ="SELECT Payment_Id FROM payment ORDER BY Payment_Id DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);

        String id = null;
        if (resultSet.next()){
            return nextId(resultSet.getString(1));
        }
        return nextId(id);
    }
    private static String nextId(String id) {

        if (id != null){
            String[] strings = id.split("P0");
            int Nid = Integer.parseInt(strings[1]);
            Nid++;

            return "P00"+Nid;
        }
        return "P001";
    }

    public static boolean payed(Payment payment) throws SQLException {
        String sql ="INSERT INTO payment(Payment_Id,Date,Time,Amount,Payment_Type,Exercise_Type,Member_Id)VALUES(?,?,?,?,?,?,?)";
        return  CrudUtil.execute(sql,payment.getPaymentId(),payment.getDate(),payment.getTime(),payment.getAmount(),payment.getPaymentype(),payment.getExtype(),payment.getMemberId());

    }


    public static List<Payment> getALl() throws SQLException {
        String sql ="SELECT * FROM payment";

        List<Payment> all =new ArrayList<>();

        ResultSet resultSet =CrudUtil.execute(sql);
        while (resultSet.next()){
            all.add(new Payment(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            ));
        }
        return all;
    }


    public static boolean serchId(String memberId) throws SQLException {
        String sql ="SELECT * FROM payment WHERE Member_Id =?";

        ResultSet resultSet =CrudUtil.execute(sql,memberId);

        if (resultSet.next()){
            return true;
        }
        return false;

    }

    public static List<String> getMemberId() throws SQLException {
        String sql ="SELECT Member_Id FROM member";

        List<String> ids =new ArrayList<>();

        ResultSet resultSet =CrudUtil.execute(sql);

        while (resultSet.next()){
            ids.add(resultSet.getString(1));
        }
        return ids;



    }

    public static List<String> getType() throws SQLException {
        String sql ="SELECT Exercize_Type_Name FROM exercize_type";

        List<String> id =new ArrayList<>();

        ResultSet resultSet =CrudUtil.execute(sql);

        while (resultSet.next()){
            id.add(resultSet.getString(1));
        }
        return  id;
    }
}

