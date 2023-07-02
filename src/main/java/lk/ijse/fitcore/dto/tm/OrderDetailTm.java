package lk.ijse.fitcore.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Time;
import java.util.Date;


@Data
@AllArgsConstructor
public class OrderDetailTm {
    private String orderID;
    private double amount;
    private String date;
    private String time;
    private String memberId;
    private String orthers;
}
