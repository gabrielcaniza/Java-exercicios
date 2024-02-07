package aplication;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import conexao.DB;

public class program {

    public static void main(String[] args) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement(

                    "INSERT INTO seller "
                            + "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
                            + "VALUES"
                            + "(? , ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, "Carl puprle");
            st.setString(2, "carl@gmail.com");
            st.setDate(3, new java.sql.Date(sdf.parse("22/06/2014").getTime()));
            st.setDouble(4, 3000.0);
            st.setInt(5, 4);
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    System.out.println("Done! Id= " + id);
                }
            } else {
                System.out.println("Nenhuma linha foi alterada! ");
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            DB.Closestatement(st);
            DB.closeConnection();
        }

    }

}
