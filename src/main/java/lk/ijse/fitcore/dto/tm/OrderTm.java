package lk.ijse.fitcore.dto.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderTm {
    private  String code;
    private  String description;
    private  Integer qty;
    private  Double unitPrice;
    private  Double discount;
    private Double total;
    private Button btn;
}
