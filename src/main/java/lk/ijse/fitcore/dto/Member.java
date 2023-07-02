package lk.ijse.fitcore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Member {

    private String Id ;
    private String FirstName ;
    private String LastName ;
    private String Nic ;
    private String gender;
    private String address ;
    private int contact ;
    private String paymentype;

}
