package Elasticsearch;

import LibraryShelf.LibraryShelf;
import UserBookShelf.UserBookShelf;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;

public class UserRetriveData {

    public RestHighLevelClient client = new RestHighLevelClient(
            RestClient.builder(new HttpHost("localhost", 9200, "http")));

    public ArrayList<JSONObject> userlibretrivesearchdata(String searchdata) throws IOException {
        ArrayList<JSONObject> userbooksdata=new ArrayList<JSONObject>();
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("bookshelf");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.multiMatchQuery(searchdata,new String[]{"title","publishername","authorname","genre","publisheddate"})
                        .type(MultiMatchQueryBuilder.Type.PHRASE_PREFIX)
//                .fuzziness(Fuzziness.AUTO)
//                .prefixLength(3)
//                .maxExpansions(10)
        );
        searchRequest.source(searchSourceBuilder);


        try {
            SearchResponse searchResponse = null;
            searchResponse =client.search(searchRequest, RequestOptions.DEFAULT);

            if (searchResponse.getHits().getTotalHits().value > 0) {
                SearchHit[] searchHit = searchResponse.getHits().getHits();
                for (SearchHit hit : searchHit) {
                    JSONParser parser = new JSONParser();
                    JSONObject json = (JSONObject) parser.parse(hit.getSourceAsString());
                    if(UserBookShelf.bookids.contains(Integer.parseInt(json.get("bookid").toString())))
                    {
                        userbooksdata.add(json);
                    }

                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        client.close();
        return userbooksdata;
    }


    public ArrayList<JSONObject> userlibretrivesortdata(String searchdata,String sortval,int flag) throws IOException {
        ArrayList<JSONObject> userbooksdata=new ArrayList<JSONObject>();
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("bookshelf");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        //searchSourceBuilder.sort(new FieldSortBuilder("id").order(SortOrder.ASC));
        System.out.println(searchdata);
        if(searchdata==null)
        {
            searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        }
        else {
            searchSourceBuilder.query(QueryBuilders.multiMatchQuery(searchdata, new String[]{"title", "publishername", "authorname", "genre", "publisheddate"})
                            .type(MultiMatchQueryBuilder.Type.PHRASE_PREFIX)
//                .fuzziness(Fuzziness.AUTO)
//                .prefixLength(3)
//                .maxExpansions(10)
            );
        }
        if(flag==1)
        {
            //searchSourceBuilder.sort(new FieldSortBuilder(sortval+".keyword").order(SortOrder.ASC));
            searchRequest.source(searchSourceBuilder.sort(sortval+".keyword", SortOrder.ASC));
        }
        else
        {
            //searchSourceBuilder.sort(new FieldSortBuilder(sortval+".keyword").order(SortOrder.DESC));
            searchRequest.source(searchSourceBuilder.sort(sortval+".keyword",SortOrder.DESC));
        }
        //searchRequest.source(searchSourceBuilder.sort("title.keyword",SortOrder.ASC));


        try {
            SearchResponse searchResponse = null;
            System.out.println("hello from sort retrive");
            searchResponse =client.search(searchRequest, RequestOptions.DEFAULT);
            System.out.println("hello from sort retrive");
            if (searchResponse.getHits().getTotalHits().value > 0) {
                SearchHit[] searchHit = searchResponse.getHits().getHits();
                for (SearchHit hit : searchHit) {
                    JSONParser parser = new JSONParser();
                    JSONObject json = (JSONObject) parser.parse(hit.getSourceAsString());
                    if(UserBookShelf.bookids.contains(Integer.parseInt(json.get("bookid").toString())))
                    {
                        userbooksdata.add(json);
                    }

                    System.out.println("hello from sort retrive");
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        client.close();
        return userbooksdata;
    }
}
