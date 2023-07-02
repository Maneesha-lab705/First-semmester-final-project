package lk.ijse.fitcore.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDto {
    private  String itemCode;
    private  int qty;
    private  Double unitPrice;
    private  Double tot;

}
