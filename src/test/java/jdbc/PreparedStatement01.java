package jdbc;

import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","347040ec");
        Statement st = con.createStatement();

        // Example 1 : Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.

        //1. Adim:  Prepared statement query'sini oluştur.
        //Daha dinamik yapmis olduk
        String sql01 = "UPDATE companies SET number_of_employees = ? WHERE company = ?";

        //2. Adim:  PreparedStatement objesini olustur
       PreparedStatement pst01 = con.prepareStatement(sql01);

       //3. Adim: set...() methodlari ile soru isaretleri icin deger gir
       pst01.setInt(1,9999);
       pst01.setString(2,"IBM");

       //4.Adim: Execute Query
        int updateRowNum01 = pst01.executeUpdate();
        System.out.println("updateRowNum : " + updateRowNum01);


        String sql02 = "SELECT * FROM companies";
        ResultSet result01 = st.executeQuery(sql02);
        while (result01.next()){
            System.out.println(result01.getInt(1) + " " +
                                result01.getString(2) + " " +
                                result01.getInt(3));
        }

        // Prepared statement kullanarak company adı GOOGLE olan number_of_employees değerini 15000 olarak güncelleyin
        //3. Adim: set...() methodlari ile soru isaretleri icin deger gir
        pst01.setInt(1,15000);
        pst01.setString(2,"GOOGLE");

        //4.Adim: Execute Query
        int  updateRowNum02 = pst01.executeUpdate();
        System.out.println("updateRowNum02 : " + updateRowNum02);

        String sql03 = "SELECT * FROM companies";
        ResultSet result02 = st.executeQuery(sql03);
        while (result02.next()){
            System.out.println(result02.getInt(1) +
                               result02.getString(2) +
                               result02.getInt(3));
        }

        //2. Örnek: "SELECT * FROM <table name>" query'sini prepared statement ile kullanın.
        System.out.println("==================");
        read_data(con,"companies");

        /*String sql04 = "SELECT * FROM ?";
        PreparedStatement pst02 = con.prepareStatement(sql04);
        pst02.setString(1,"companies");
        ResultSet result03 = pst02.executeQuery(sql04);

        while (result03.next()){
            System.out.println(result03.getInt(1) + " " +
                    result03.getString(2) + " " +
                    result03.getInt(3));
        }
         */

    }

    //Bir tablonun istenilen datasını prepared statement ile çağırmak için kullanılan method.
    public static void read_data(Connection con, String tableName){

        try {
            String query = String.format("SELECT * FROM %s",tableName); //format() methodu dinamik String olusturmak icin kullanilir
           Statement statement = con.createStatement();
           ResultSet result01 = statement.executeQuery(query);

           while (result01.next()){
               System.out.println(result01.getString(1) + " " +
                       result01.getString(2) + " " +
                       result01.getInt(3));
           }

        } catch (Exception e) {
            System.out.println(e);;
        }
    }
}
