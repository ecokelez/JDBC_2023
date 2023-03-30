package jdbc;

import java.sql.*;

public class CallableStatement01 {

    //Java'da methodlar return type sahibi olsa da, void olsa da method olarak adlandırılır.
    //SQL'de ise data return ediyorsa "function" denir. Return yapmıyorsa "procedure" diye adlandırılır.

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","347040ec");
        Statement st = con.createStatement();

        //--FUNCTION OLUSTURMA
       //1. Örnek: İki parametre ile çalışıp bu parametreleri toplayarak return yapan bir fonksiyon oluşturun.
        // Step 1 : Write the Function Code-  Fonksiyon kodunu yaz
        String sql01 = "CREATE OR REPLACE FUNCTION toplamaF(x NUMERIC,y NUMERIC)\n" +
                        "RETURNS NUMERIC\n" +
                        "LANGUAGE plpgsql\n" +
                        "AS\n" +
                        "$$\n" +
                        "BEGIN\n" +
                        "\n" +
                        "RETURN x+y;\n" +
                        "\n" +
                        "END\n" +
                        "$$";
        //Step 2 : Execute Function
        st.execute(sql01);
        //Step 3 : Call the Function
         CallableStatement callSt01 = con.prepareCall("{? = call toplamaF (?,?)}");
        //Step 4 : Use the registerOutParameter() method for return and  set() method for parameters
        callSt01.registerOutParameter(1,Types.NUMERIC);
        callSt01.setInt(2,15);
        callSt01.setInt(3,25);
        //Step 5 : : Use the execute() method to run it.
        callSt01.execute();
        //Step 6 : :To call the result, use the appropriate "get" methods according to the return data type.
        // Sonucu çağırmak için return data tipine göre "get" methodlarından uygun olanı kullan.
        System.out.println(callSt01.getBigDecimal(1));


        //2. Örnek: Koninin hacmini hesaplayan bir function yazın.
        // Step 1 Fonksiyon kodunu yaz
        String sql02 = "CREATE OR REPLACE FUNCTION koniHacmi(r NUMERIC,h NUMERIC)\n" +
                        "RETURNS NUMERIC\n" +
                        "LANGUAGE plpgsql\n" +
                        "AS\n" +
                        "$$\n" +
                        "BEGIN\n" +
                        "\n" +
                        "RETURN 3.14*r*r*h/3\n" +
                        "\n" +
                        "END\n" +
                        "$$";

        //Step 2 : Execute Function
        st.execute(sql02);
        // Step 3 : Call the Function
        CallableStatement callSt02 = con.prepareCall("{? = call koniHacmi(? ?)}");
        //Step 4 : Use the registerOutParameter() method for return and  set() method for parameters
        ///(Return için registerOutParameter() methodunu, parametreler için set() methodlarından uygun olanları kullan)
        callSt02.registerOutParameter(1,Types.NUMERIC);
        callSt02.setInt(2,4);
        callSt02.setInt(3,6);
        //Step 5 : : Use the execute() method to run it.
        callSt02.execute();
        //Step 6 : :To call the result, use the appropriate "get" methods according to the return data type.
        System.out.println(callSt02.getBigDecimal(1));

    }
}
