package lk.ijse.fitcore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
@AllArgsConstructor
public class Orders {
    private String order_Id;
    private Double amount;
    private Date date;
    private Time time;
    private String memberId;
    private String otherBuyers;

}
