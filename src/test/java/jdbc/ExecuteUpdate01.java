package jdbc;

import java.sql.*;

public class ExecuteUpdate01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
      Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","347040ec");
        Statement st = con.createStatement();

        //Example 1: number_of_employees değeri ortalama çalışan sayısından az olan number_of_employees değerlerini 16000 olarak UPDATE edin.

        String sql01 = "UPDATE companies\n" +
                        "SET number_of_employees = 16000\n" +
                        "WHERE number_of_employees < (select AVG(number_of_employees)\n" +
                        "from companies)";

        int numUpdateRow = st.executeUpdate(sql01);
        System.out.println("number of Update Row : " + numUpdateRow);

        String sql02 = "SELECT * FROM companies";
        ResultSet result01 = st.executeQuery(sql02);

        while (result01.next()){
            System.out.println(result01.getInt(1) + " " +
                               result01.getString(2) + " " +
                               result01.getInt(3));
        }


    }
}
