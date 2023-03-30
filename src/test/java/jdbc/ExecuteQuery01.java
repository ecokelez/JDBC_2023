package jdbc;

import java.sql.*;

public class ExecuteQuery01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","347040ec");
        Statement st = con.createStatement();

        //Exeample 1 : Call the second highest number_of_employees value, company and number_of_employees, from the companies table.
        //Method 1: Use with OFFSET ve FETCH NEXT
        String sql01 = "SELECT company,number_of_employees FROM companies\n" +
                        "ORDER BY number_of_employees DESC\n"+
                        "OFFSET 1 ROW\n"+           // -- 1 satir atla
                        "FETCH NEXT 1 ROW ONLY";   // -- siradaki yalnizca bir satiri ver

        ResultSet result01 = st.executeQuery(sql01);

        while (result01.next()){
            System.out.println(result01.getString("company")+" "+ result01.getInt("number_of_employees"));
        }


        //Method 2: Use with Subquery
        String sql02 = "SELECT company,number_of_employees \n" +
                        "FROM companies\n" +
                        "WHERE number_of_employees = (SELECT MAX(number_of_employees )\n" +
                "                                     FROM companies\n" +
                "                                     WHERE number_of_employees < \n" +
                "                                     (select MAX(number_of_employees) FROM companies))";



        ResultSet result02 = st.executeQuery(sql02);
        while (result02.next()){
            System.out.println(result02.getString("company") + " " + result02.getInt("number_of_employees"));
        }

        con.close();
        st.close();
        result01.close();
        result02.close();
    }
}
