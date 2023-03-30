package jdbc;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        //DBWork obj olustur
        DBWork db = new DBWork();
        //Connection fonk. dataBase'den cagirmis olduk
        Connection con = db.connect_to_db("techproed","postgres","347040ec");

        // Yeni table olusturma methodunu  cagir
        db.createTable(con,"employees");
    }
}
