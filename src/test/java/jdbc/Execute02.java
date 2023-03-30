package jdbc;

import java.sql.*;

public class Execute02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.Register Driver
        Class.forName("org.postgresql.Driver");
        //2.Connection the Database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","347040ec");
        //3.Create statement
        Statement st = con.createStatement();

        // Example - 1: Call country names that id of region  is 1
        String sql01 = "SELECT country_name from countries WHERE region_id = 1";
        boolean r01 = st.execute(sql01);
        System.out.println(r01);
        //Recordlari gormek icin executeQuery() methodunu kullanmaliyiz
        ResultSet result01 = st.executeQuery(sql01);
        while (result01.next()){
            System.out.println(result01.getString("country_name"));
        }

        // Example - 2 : "region_id"nin 2'den büyük olduğu "country_id" ve "country_name" değerlerini çağırın.
        String sql02 = "SELECT country_id,country_name FROM countries WHERE region_id > 2";

        ResultSet result02 = st.executeQuery(sql02);
        while(result02.next()){
            System.out.println(result02.getString("country_id")+"->"+result02.getString("country_name"));
        }

        // Example-3 : "number_of_employees" değeri en düşük olan satırın tüm değerlerini çağırın.
        String sql03 = "SELECT * FROM companies WHERE number_of_employees = (SELECT MIN(number_of_employees) FROM companies)";
        ResultSet result03 = st.executeQuery(sql03);

        while (result03.next()){
            System.out.println(result03.getInt("company_id") + "-->" +
                                result03.getString("company") + "-->" +
                                result03.getInt("number_of_employees"));
        }

        // Method 2 :
        System.out.println("With using Index ");
        while (result03.next()){
            System.out.println(result03.getInt(1) + "-->" +
                    result03.getString(2) + "-->" +
                    result03.getInt(3));
        }

        con.close();
        st.close();

    }
}
