package bilibili;

import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    static public void main(String argc[]) throws SQLException, ClassNotFoundException {
        Con MySql = new Con();

        Statement st = MySql.conn.createStatement();

    }
}
