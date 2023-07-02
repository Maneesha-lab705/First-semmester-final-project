package lk.ijse.fitcore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.xml.transform.sax.SAXResult;
@Data
@AllArgsConstructor
public class Employee {
    private  String  id;
    private  String firstName;
    private String lastName;
    private String address;
    private String  nic;
    private Integer contact;
    private String role;
}
