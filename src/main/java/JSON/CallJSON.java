package JSON;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.SQLException;

public class CallJSON {
    public  static int count=0;
    public CallJSON() {
        Jsonwrite jsw=new Jsonwrite();
        try {
            jsw.jsontosql();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
