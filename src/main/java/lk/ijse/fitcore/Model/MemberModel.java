package lk.ijse.fitcore.Model;

import javafx.scene.control.Alert;
import lk.ijse.fitcore.dto.Member;
import lk.ijse.fitcore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberModel {

    public static boolean save(Member member) throws SQLException {

        Member member1 =  sharch(member.getId());
        if (member1 != null){
            return false;
        }
        String sql ="INSERT INTO member(Member_Id, First_Name, Last_Name, NIC,Gender,Address, Contact,Payment_Type ) VALUES(?,?,?,?,?,?,?,?)";
        return CrudUtil.execute(sql,member.getId(),member.getFirstName(),member.getLastName(),member.getNic(),member.getGender(),member.getAddress(),member.getContact(),member.getPaymentype());
    }



    public static Member sharch(String id) throws SQLException {
        String sql = "SELECT * FROM member WHERE Member_Id = ?";
        ResultSet resultSet = CrudUtil.execute(sql, id);

        if (resultSet.next()){
            String mId = resultSet.getString(1);
            String fName = resultSet.getString(2);
            String lName = resultSet.getString(3);
            String nic = resultSet.getString(4);
            String gender = resultSet.getString(5);
            String address = resultSet.getString(6);
            int contact = Integer.parseInt(String.valueOf(Integer.valueOf(resultSet.getString(7))));
            String pType = resultSet.getString(8);

            return new Member(mId,fName,lName,nic,gender,address,contact,pType);
        }
        return null;
    }
    public static Member sharchNic(String nic) throws SQLException {
        String sql = "SELECT * FROM member WHERE NIC = ?";
        ResultSet resultSet = CrudUtil.execute(sql, nic);

        if (resultSet.next()){
            String mId = resultSet.getString(1);
            String fName = resultSet.getString(2);
            String lName = resultSet.getString(3);
            String sNic = resultSet.getString(4);
            String gender = resultSet.getString(5);
            String address = resultSet.getString(6);
            int contact = Integer.parseInt(String.valueOf(Integer.valueOf(resultSet.getString(7))));
            String pType = resultSet.getString(8);

            return new Member(mId,fName,lName,sNic,gender,address,contact,pType);
        }
        return null;
    }

    public static String getMemberWithType(Member member) throws SQLException {


        String sql = "select Exercize_Type_Id from member_detail where Member_Id = ?";

        ResultSet resultSet = CrudUtil.execute(sql, member.getId());

        String ExType = null;
        if (resultSet.next()) {
            ExType = resultSet.getString(1);
        }
        return ExType;
    }

    public static boolean delete(String id) throws SQLException {

        String sql ="DELETE FROM member WHERE Member_Id = ?";
        return CrudUtil.execute(sql,id);
    }

    public static String getMemberWithTypeNic(Member member) throws SQLException {

       String id = getMemberId(member);

        String sql = "select Exercize_Type_Id from member_detail where Member_Id = ?";

        ResultSet resultSet = CrudUtil.execute(sql, id);

        String ExType = null;
        if (resultSet.next()) {
            ExType = resultSet.getString(1);
        }
        return ExType;

    }

    private static String getMemberId(Member member) throws SQLException {

        String sql = "select Member_Id from member where NIC = ?";

        ResultSet resultSet = CrudUtil.execute(sql, member.getNic());

        String id = null;
        if (resultSet.next()) {
             id  = resultSet.getString(1);
        }
        return id;
    }

    public static boolean update(Member member) throws SQLException {
        String sql ="UPDATE member SET  First_Name = ?,Last_Name = ?,NIC = ?,Gender = ?,Address = ?, Contact =?, Payment_Type = ? WHERE Member_Id = ?";

        return CrudUtil.execute(sql,member.getFirstName(),member.getLastName(),member.getNic(),member.getGender(),member.getAddress(),member.getContact(),member.getPaymentype(),member.getId());
    }

    public static List<String> getId() throws SQLException {
        String sql = "SELECT Member_Id FROM member";

        List<String> ids = new ArrayList<>();

        ResultSet resultSet =CrudUtil.execute(sql);
        while (resultSet.next()) {
            ids.add(resultSet.getString(1));
        }
        return ids;

    }

    public static String getMemberName(String custId) throws SQLException {
        String sql ="SELECT First_Name FROM member WHERE Member_Id = ?";

        ResultSet resultSet =CrudUtil.execute(sql,custId);

        String name = null;
        if (resultSet.next()){
           name = resultSet.getString(1);
        }
        return name;
    }

    public static String getExTypeName(String typename) throws SQLException {
        String sql ="SELECT Exercize_Type_Name FROM exercize_type WHERE Exercize_Type_Id = ?";
        ResultSet resultSet = CrudUtil.execute(sql,typename);

        String name = "";
        if (resultSet.next()){
            name =  resultSet.getString(1);
        }
        return  name;


    }

    public static List<String> getMemberId() throws SQLException {
        String sql ="SELECT Member_Id FROM member ";
        List<String> ids =new ArrayList<>();

        ResultSet resultSet =CrudUtil.execute(sql);

        while (resultSet.next()){
            ids.add(resultSet.getString(1));

        }
        return ids;

    }
}
