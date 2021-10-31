package Elasticsearch;

import com.example.BookShelf_practise.EditDeleteCall;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.refresh.RefreshRequest;
import org.elasticsearch.action.admin.indices.refresh.RefreshResponse;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ESConnect extends HttpServlet {
    int succesflag=0;
    public RestHighLevelClient client = new RestHighLevelClient(
            RestClient.builder(new HttpHost("localhost", 9200, "http")));




    //IndexRequest indexRequest = new IndexRequest("bookshelf");

    //BulkRequest bulkRequest = new BulkRequest();

        public int adddata(ArrayList<Booksdata> booksdata) throws IOException {
//            BulkRequest bulkRequest = new BulkRequest();
//            IndexRequest indexRequest = new IndexRequest("bookshelf");
//            indexRequest.id(String.valueOf(bsd.bookid));
//            indexRequest.source(new ObjectMapper().writeValueAsString(bsd), XContentType.JSON);
//            System.out.println("in adddata");
//            bulkRequest.add(indexRequest);
//            BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);
//            System.out.println("response id: "+bulkResponse);
//            //System.out.println("response name: "+bulkResponse.getResult().name());
//            //bulkProcessor.close();                  //Using bulkRequest


            BulkProcessor.Listener listener = new BulkProcessor.Listener() {
                @Override
                public void beforeBulk(long executionId, BulkRequest request) {
                    int numberOfActions = request.numberOfActions();
                    System.out.println("Executing bulk [{}] with {} requests "+executionId+" "+numberOfActions);
                }

                @Override
                public void afterBulk(long executionId, BulkRequest request,
                                      BulkResponse response) {
                    if (response.hasFailures()) {
                        System.out.println("Bulk [{}] executed with failures "+ executionId);
                    } else {
                        succesflag=1;
                        System.out.println("Bulk [{}] completed in {} milliseconds "+executionId+" "+response.getTook().getMillis());
                    }

                    //return 1;
                }

                @Override
                public void afterBulk(long executionId, BulkRequest request,
                                      Throwable failure) {
                    System.out.println("Failed to execute bulk "+failure);
                }
            };

            BulkProcessor bulkProcessor = BulkProcessor.builder(
                    (request, bulkListener) ->
                            client.bulkAsync(request, RequestOptions.DEFAULT, bulkListener),
                    listener, "bulk-processor-name").build();

            BulkProcessor.Builder builder = BulkProcessor.builder(
                    (request, bulkListener) ->
                            client.bulkAsync(request, RequestOptions.DEFAULT, bulkListener),
                    listener, "bulk-processor-name");
            builder.setBulkActions(500);
            builder.setBulkSize(new ByteSizeValue(1L, ByteSizeUnit.MB));
            builder.setConcurrentRequests(0);
            builder.setFlushInterval(TimeValue.timeValueSeconds(10L));
            builder.setBackoffPolicy(BackoffPolicy
                    .constantBackoff(TimeValue.timeValueSeconds(1L), 3));



            IndexRequest indexRequest = new IndexRequest("bookshelf");
            for(int i=0;i<booksdata.size();i++)
            {
                indexRequest.id(String.valueOf(booksdata.get(i).bookid));
                indexRequest.source(new ObjectMapper().writeValueAsString(booksdata.get(i)), XContentType.JSON);
                System.out.println("in adddata");
                bulkProcessor.add(indexRequest);
            }

            //listener.notify();
            bulkProcessor.close();
            System.out.println("SUccessflag:"+succesflag);

            return 1;

        }

        public void refreshdata() throws IOException {
            RefreshRequest request = new RefreshRequest("bookshelf");
            RefreshResponse refreshResponse = client.indices().refresh(request, RequestOptions.DEFAULT);

        }

        public int updatedata(Booksdata bsd) throws IOException {
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("bookid",bsd.bookid);
            jsonMap.put("title",bsd.title);
            jsonMap.put("authorname",bsd.authorname);
            jsonMap.put("publishername",bsd.publishername);
            jsonMap.put("publisheddate",bsd.publisheddate);
            jsonMap.put("genre",bsd.genre);

            int succesflag=0;

            UpdateRequest updateRequest = new UpdateRequest("bookshelf",String.valueOf(bsd.bookid)).doc(jsonMap);
//                updateRequest.upsert(bsd, XContentType.JSON);
            UpdateResponse updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);

            succesflag=Integer.parseInt(updateResponse.getId());
//            Retrivedata retriveData=new Retrivedata();
//            retriveData.retrivedata();
//            for(JSONObject book:Retrivedata.booksdata)
//            {
//                System.out.println(book);
//            }
            refreshdata();

            return succesflag;

        }

        public int deletedata(String bookid) throws IOException {
            DeleteRequest deleteRequest = new DeleteRequest("bookshelf", bookid);
            DeleteResponse deleteResponse = client.delete(
                    deleteRequest, RequestOptions.DEFAULT);
            System.out.println("response id: " + deleteResponse.getId());
            System.out.println("response name: " + deleteResponse.getResult().name());
            int successflag=Integer.parseInt(deleteResponse.getId());

            return successflag;
        }



}
