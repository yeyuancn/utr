import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by yye on 9/12/18.
 */
public class ImportPlayer {

    public static void main(String[] args) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("/Users/yye/Desktop/players.csv"));
            //skip first line.
            String line = reader.readLine();
            line = reader.readLine();
            while (line != null) {
                //System.out.println(line);
                String[] elements = line.split(",");
                String lastName = elements[1].trim();
                String firstName = elements[2].trim();
                float utrScore = Float.valueOf(elements[3]);
                String email = elements[5];
                String phone = elements[6];
                if (phone.startsWith("+")) {
                    phone = phone.substring(1);
                }

                System.out.println("insert into player (first_name, last_name, email, phone, utr_score) " +
                        "values ('" + firstName + "','" + lastName + "','" + email + "','" +  phone +
                        "', " + utrScore + ");");
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
