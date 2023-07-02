package lk.ijse.fitcore.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberAttendanceTm {
    private  String memberId;
    private String date;
    private String time;
    private  String isAttendance;

    public MemberAttendanceTm() {


    }
}
