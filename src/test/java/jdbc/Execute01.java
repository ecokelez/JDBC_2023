package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1. Registration the Driver
        Class.forName("org.postgresql.Driver");

        //2. Connect the Database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","347040ec");

        //3. Create Statement
        Statement st = con.createStatement();

        //4. Query calıstır
        //=>Example 1. "workers" adi altinda bir table olus. "worker_id,worker_name,worker_salary" sutunlarini ekleyin
        String sql01 = "CREATE TABLE workers(worker_id VARCHAR(50),worker_name VARCHAR(50),worker_salary INT)";
        boolean result = st.execute(sql01);
        System.out.println(result); // false return yapar ,cunku data cagrilmadi

        // Example 2. Alter table by adding worker_address column into the workers table
        //=> Table'a worker_address sutunu ekleyerek alter yapın
        String sql02 = "ALTER TABLE workers ADD worker_address VARCHAR(80)";
        st.execute(sql02);

        // Example 3. Drop workers table
        String sql03 = "DROP TABLE workers";
        st.execute(sql03);

        //5. Close the Connection and Statement
        con.close();
        st.close();


    }
}
