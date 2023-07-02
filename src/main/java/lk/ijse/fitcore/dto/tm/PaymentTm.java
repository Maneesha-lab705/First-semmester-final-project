package lk.ijse.fitcore.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentTm {
    private  String paymentId;
    private  String date;
    private  String time;
    private double amount;
    private  String paymentype;
    private  String extype;
    private String memberId;
}
