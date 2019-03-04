package bilibili;

import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    static public void main(String argc[]) throws SQLException, ClassNotFoundException {
        ConnectClass MySql = new ConnectClass();

        Statement st = MySql.conn.createStatement();

    }
}
