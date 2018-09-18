/**
 * Created by yye on 9/14/18.
 */

import java.sql.*;

public class MysqlUnit {
    public static void main(String args[]) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/utr?serverTimezone=PST", "yye", "252525");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select id, division_id from player where " +
                    "id not in (select player_id from player_result);");
            while (rs.next()) {
                long id = rs.getLong(1);
                long divisionId = rs.getLong(2);
                System.out.println("insert into player_result(player_id, division_id, match_won, match_lost, " +
                        "match_won_percent, game_won, game_lost, game_won_percent) values (" + id + ", " +
                divisionId + ", 0, 0, 0.0, 0, 0, 0.0);");
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}