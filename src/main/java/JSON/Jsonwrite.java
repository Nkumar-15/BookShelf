package JSON;


import Elasticsearch.Booksdata;
import Elasticsearch.ESConnect;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.http.HttpServlet;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Jsonwrite extends HttpServlet {

    public void jsontosql() throws FileNotFoundException, IOException, ParseException, ClassNotFoundException, SQLException {
        ArrayList<Booksdata> booksdata=new ArrayList<Booksdata>();
        JSONParser jsonParser=new JSONParser();
        try{
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("D:\\Intellij Ultimate\\Projects\\Book Shelf\\bookdata.json"));
            JSONArray jsonArray = (JSONArray) jsonObject.get("books_data");

            for(Object object : jsonArray) {
                JSONObject record = (JSONObject) object;
                String bookid= (String) record.get("bookid");
                String title = (String) record.get("Title");
                String authorname = (String) record.get("Authorname");
                String publishername = (String) record.get("Publisher");
                String pdate = (String) record.get("Publisheddate");
                String genre = (String) record.get("Genre");

                Booksdata bsd = new Booksdata(Integer.parseInt(bookid),title,authorname,publishername,pdate,genre);
                booksdata.add(bsd);

            }
            ESConnect esConnect=new ESConnect();
            int flag=esConnect.adddata(booksdata);
            booksdata.clear();
        }
        catch (Exception e) {

            e.printStackTrace();
        }

    }




}
