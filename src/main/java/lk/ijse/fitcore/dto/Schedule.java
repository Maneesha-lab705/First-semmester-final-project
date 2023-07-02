package lk.ijse.fitcore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Schedule {
    private  String scheduled;
    private String name;
    private  String time;
    private String description;
    private  String exId;
}
