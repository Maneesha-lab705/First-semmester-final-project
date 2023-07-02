package lk.ijse.fitcore.Model;

import lk.ijse.fitcore.dto.OrderDto;
import lk.ijse.fitcore.util.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public class OderDetailModel {
    public static boolean save(String orderId, List<OrderDto> orderDtoList) throws SQLException {
        for ( OrderDto dto:orderDtoList){
            if (!Save(orderId,dto)){
                return  true;
            }

        }
        return  false;

    }

    private static boolean Save(String orderId, OrderDto dto) throws SQLException {
        String sql ="INSERT INTO order_detail(Order_Id,Item_Code,Item_Qty,Unit_Price,Total)VALUES(?,?,?,?,?) ";
        return CrudUtil.execute(sql,orderId,dto.getItemCode(),dto.getQty(),dto.getUnitPrice(),dto.getTot());

    }
}
