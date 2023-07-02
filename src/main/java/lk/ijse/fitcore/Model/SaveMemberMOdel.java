package lk.ijse.fitcore.Model;

import lk.ijse.fitcore.db.DBConnection;
import lk.ijse.fitcore.dto.Member;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class SaveMemberMOdel {

    public static boolean save(Member member, String exType) throws SQLException {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();

            connection.setAutoCommit(false);

            boolean isSave = MemberModel.save(member);
            if (isSave){
                boolean isSaveShedule = ShedulModel.save(member.getId(),exType, String.valueOf(LocalDate.now()));
                if (isSaveShedule){
                    connection.commit();
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
            return false;
        }finally {
            {
                System.out.println("finaly");
                connection.setAutoCommit(true);

            }
        }
    }
    public static boolean update(Member member, String exType) throws SQLException {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();

            connection.setAutoCommit(false);

            boolean isUpdate = MemberModel.update(member);
            if (isUpdate){
                boolean isUpdateShedule = ShedulModel.update(member.getId(),exType);
                if (isUpdateShedule){
                    connection.commit();
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
            return false;
        }finally {
            {
                connection.setAutoCommit(true);

            }
        }
    }
}

