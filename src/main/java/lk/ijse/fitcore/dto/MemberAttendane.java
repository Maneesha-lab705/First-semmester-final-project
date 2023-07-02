package lk.ijse.fitcore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberAttendane {
    private String memberId;
    private  String date;
    private String time;
    private  String isAttendance;



}
