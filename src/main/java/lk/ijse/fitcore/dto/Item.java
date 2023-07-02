package lk.ijse.fitcore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data


public class Item {
    private String itemCode;
    private String description;
    private Integer qntOnHand;
    private Double unitPrice;
    private Double discount;
    private String itemType;

    public Item(String itemCode, String description, Integer qntOnHand, Double unitPrice, Double discount, String itemType) {
        this.itemCode = itemCode;
        this.description = description;
        this.qntOnHand = qntOnHand;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.itemType = itemType;
    }
}
