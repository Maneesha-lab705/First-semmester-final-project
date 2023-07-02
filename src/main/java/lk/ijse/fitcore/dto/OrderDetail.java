package lk.ijse.fitcore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Time;
import java.util.Date;


@Data
@AllArgsConstructor
public class OrderDetail {
    private String orderID;
    private double amount;
    private String date;
    private String time;
    private String memberId;
    private String orthers;

}
