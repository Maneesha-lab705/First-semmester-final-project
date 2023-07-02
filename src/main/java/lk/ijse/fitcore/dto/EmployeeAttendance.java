package lk.ijse.fitcore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Time;
import java.util.Date;
@Data
@AllArgsConstructor
public class EmployeeAttendance {
    private  String employeeId;
    private String date;
    private String time;
    private  String isAttendance;
}
