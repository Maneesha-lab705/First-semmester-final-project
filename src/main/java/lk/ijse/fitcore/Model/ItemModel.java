package lk.ijse.fitcore.Model;

import javafx.scene.control.Button;
import lk.ijse.fitcore.dto.Item;
import lk.ijse.fitcore.dto.OrderDto;
import lk.ijse.fitcore.dto.tm.ItemTm;
import lk.ijse.fitcore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemModel {
    public static boolean save(Item item) throws SQLException {
        String sql ="INSERT INTO item(Item_Code,Description,Qty_On_Hand,Unit_Price,Discount,Type_Of_Item) VALUES (?,?,?,?,?,?)";
        return CrudUtil.execute(sql,item.getItemCode(),item.getDescription(),item.getQntOnHand(),item.getUnitPrice(),item.getDiscount(),item.getItemType());

    }


//    public static Item serch(String typeOfItem) throws SQLException {
//
//        String sql ="SELECT * FROM item WHERE Type_Of_Item = ?";
//        ResultSet resultSet =CrudUtil.execute(sql,typeOfItem);
//
//        if (resultSet.next()){
//            String itemCode =resultSet.getString(1);
//            String description =resultSet.getString(2);
//            Integer qtyOnHand = Integer.valueOf(resultSet.getString(3));
//            Double unitPrice = Double.valueOf(String.valueOf(resultSet.getDouble(4)));
//            Double discount = Double.valueOf(String.valueOf(resultSet.getDouble(5)));
//            String itemType =resultSet.getString(6);
//
//            return  new Item(itemCode,description,qtyOnHand,unitPrice,discount,itemType);
//        }
//        return null;
//    }

    public static Item serchByCode(String code) throws SQLException {
        String sql = "SELECT * FROM item WHERE Item_Code = ?";
        ResultSet resultSet =CrudUtil.execute(sql,code);

        if (resultSet.next()){
            String itemCode =resultSet.getString(1);
            String description =resultSet.getString(2);
            Integer qtyOnHand = Integer.valueOf(resultSet.getString(3));
            Double unitPrice = Double.valueOf(String.valueOf(resultSet.getDouble(4)));
            Double discount = Double.valueOf(String.valueOf(resultSet.getDouble(5)));
            String itemType =resultSet.getString(6);

            return  new Item(itemCode,description,qtyOnHand,unitPrice,discount,itemType);
        }
        return  null;
    }

    public static boolean delete(String code) throws SQLException {
        String sql ="DELETE FROM item WHERE Item_Code = ?";
        return  CrudUtil.execute(sql,code);
    }

    public static boolean update(Item item) throws SQLException {
        String sql ="UPDATE item SET  Description = ?,Qty_On_Hand = ?,Unit_Price =?,Discount = ?,Type_Of_Item =? WHERE Item_Code = ?";
        return CrudUtil.execute(sql,item.getDescription(),item.getQntOnHand(),item.getUnitPrice(),item.getDiscount(),item.getItemType(),item.getItemCode());


    }

    public static List<String> getItemId() throws SQLException {

        String sql ="SELECT Item_Code FROM item ";
        List<String> id =new ArrayList<>();

        ResultSet resultSet =CrudUtil.execute(sql);
        while (resultSet.next()){
            id.add(resultSet.getString(1));
        }
        return  id;
    }

    public static Item getDiscription(String itemId) throws SQLException {
        String sql ="SELECT * FROM item WHERE Item_Code = ?";

        ResultSet resultSet =CrudUtil.execute(sql,itemId);


        if (resultSet.next()){
        String id = resultSet.getString(1);
        String name  = resultSet.getString(2);
        Integer qty = Integer.valueOf(resultSet.getString(3));
        Double price = Double.valueOf(resultSet.getString(4));
        Double discount= Double.valueOf(resultSet.getString(5));
        String type = resultSet.getString(6);

        return new Item(id,name,qty,price,discount,type);
        }
        return null;
    }

    public static List<Item> getAll() throws SQLException {
        String sql ="SELECT * FROM item";
        List<Item> all =new ArrayList<>();

        ResultSet resultSet =CrudUtil.execute(sql);
        while (resultSet.next()){
            String code=  resultSet.getString(1);
            String description =resultSet.getString(2);
            int qty = Integer.parseInt(resultSet.getString(3));
            Double unitPrice = Double.valueOf(resultSet.getString(4));
            Double discount = Double.valueOf(resultSet.getString(5));
            String itemType =resultSet.getString(6);

            all.add(new Item(code,description,qty,unitPrice,discount,itemType));
        }
        return all;
    }

    public static boolean updateqty(List<OrderDto> orderDtoList) throws SQLException {
        String sql ="UPDATE item SET Qty_On_Hand = (Qty_On_Hand-?) WHERE Item_Code = ?";


        for (OrderDto dto: orderDtoList){
            if (!updateqty(dto)){
                return false;
            }
        }return  true;
    }

    private static boolean updateqty(OrderDto dto) throws SQLException {
        String sql ="UPDATE item SET Qty_On_Hand = (Qty_On_Hand-?) WHERE Item_Code = ?";
        return  CrudUtil.execute(sql,dto.getQty(),dto.getItemCode());
    }
}
