package lk.ijse.fitcore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Payment {
    private  String paymentId;
    private  String date;
    private  String time;
    private double amount;
    private  String paymentype;
    private  String extype;
    private String memberId;


}
