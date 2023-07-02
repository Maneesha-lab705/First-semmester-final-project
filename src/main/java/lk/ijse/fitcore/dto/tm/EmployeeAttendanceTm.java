package lk.ijse.fitcore.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeAttendanceTm {
    private  String employeeId;
    private String date;
    private String time;
    private  String isAttendance;


    public EmployeeAttendanceTm() {

    }
}
