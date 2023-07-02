package lk.ijse.fitcore.Model;

import lk.ijse.fitcore.dto.Member;
import lk.ijse.fitcore.dto.Trainer;
import lk.ijse.fitcore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainerModel {


    public static boolean save(Trainer trainer) throws SQLException {
        Trainer trainer1 =  serch(trainer.getId());
        if (trainer1 != null){
            return false;
        }
        String sql ="INSERT INTO trainer(Trainer_Id, First_Name, Last_Name, NIC,Address, Contact,Exercize_Type_Id ) VALUES(?,?,?,?,?,?,?)";
        return  CrudUtil.execute(sql,trainer.getId(),trainer.getFirstName(),trainer.getLastName(),trainer.getNic(),trainer.getAddress(),trainer.getContact(),trainer.getExtypeId());

    }

    public static Trainer serch(String id) throws SQLException {
        String sql = "SELECT * FROM trainer WHERE Trainer_Id = ?";
        ResultSet resultSet = CrudUtil.execute(sql, id);

        if (resultSet.next()){
            String trainerId =resultSet.getString(1);
            String firstName =resultSet.getString(2);
            String lastName =resultSet.getString(3);
            String  nic =resultSet.getString(4);
            String address =resultSet.getString(5);
            int contact = Integer.parseInt(resultSet.getString(6));
            String extypeId =resultSet.getString(7);

            return  new Trainer(trainerId,firstName,lastName,nic,address,contact,extypeId);
        }
        return  null;

    }

    public static boolean delete(String id) throws SQLException {


        String sql ="DELETE FROM  trainer WHERE Trainer_Id = ?";
        return CrudUtil.execute(sql,id);
    }

    public static boolean update(Trainer trainer) throws SQLException {

        String sql ="UPDATE trainer SET  First_Name = ?,Last_Name = ?,NIC = ?,Address = ?, Contact =?, Exercize_Type_Id = ? WHERE Trainer_Id = ?";
        return  CrudUtil.execute(sql, trainer.getFirstName(),trainer.getLastName(),trainer.getNic(),trainer.getAddress(),trainer.getContact(),trainer.getExtypeId(),trainer.getId());
    }


    public static String getDescription(String id) throws SQLException {
        String sql = "SELECT Exercize_Type_Name FROM exercize_type WHERE Exercize_Type_Id = ?";
        ResultSet resultSet = CrudUtil.execute(sql,id);

        String name = "";
        if (resultSet.next()){
           name =  resultSet.getString(1);
        }
        return name;
    }
}