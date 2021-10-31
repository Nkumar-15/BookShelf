package Elasticsearch;

import LibraryShelf.AddtoLibCall;
import LibraryShelf.LibraryShelf;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.*;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

public class Retrivedata {

    public static ArrayList<JSONObject> booksdata=new ArrayList<JSONObject>();
    public static int totalvalues=0;

    public RestHighLevelClient client = new RestHighLevelClient(
            RestClient.builder(new HttpHost("localhost", 9200, "http")));
    private static FileWriter file;

//    public void retrivedata() throws IOException, ParseException {
//        //ArrayList<JSONObject> val=new ArrayList<JSONObject>();
//        booksdata.clear();
//        RestHighLevelClient client = new RestHighLevelClient(
//                RestClient.builder(new HttpHost("localhost", 9200, "http")));
//        SearchRequest searchRequest = new SearchRequest("bookshelf");
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(matchAllQuery());
//        searchSourceBuilder.size();
//        searchRequest.source(searchSourceBuilder);
//        searchRequest.scroll(TimeValue.timeValueMinutes(1L));
//        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//        String scrollId = searchResponse.getScrollId();
////        SearchHits hits = searchResponse.getHits();
//        SearchHit[] searchHits = searchResponse.getHits().getHits();
//
////        while (searchHits != null && searchHits.length > 0) {
////
////            SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
////            searchResponse = client.scroll(scrollRequest, RequestOptions.DEFAULT);
////            scrollId = searchResponse.getScrollId();
////            searchHits = searchResponse.getHits().getHits();
////        }
//
//        for(SearchHit hit : searchResponse.getHits())
//        {
//            //System.out.println("ID: "+hit.getSourceAsString("id"));
//            //System.out.println(hit.getId());
////            JSONObject value = new JSONObject(hit.getFields());
////            System.out.println(hit.getSourceAsString());
//            JSONParser parser = new JSONParser();
//            JSONObject json = (JSONObject) parser.parse(hit.getSourceAsString());
//
//            System.out.println(json.get("genre"));
//            booksdata.add(json);
//
//            //jsonwrite(json);
//
////            file=new FileWriter("D:\\Intellij Ultimate\\Projects\\Book Shelf\\Bookshelf_6_Elasticsearch_BULK_API\\searchresult.txt");
////            file.write(json.toJSONString());
//        }
//        ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
//        clearScrollRequest.addScrollId(scrollId);
//        ClearScrollResponse response = client.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
//
//        client.close();
//
//        //return val;
//
//    }


     public void retrivedata() throws IOException {
         booksdata.clear();
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("bookshelf");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        Map<String, Object> map=null;

        try {
            SearchResponse searchResponse = null;
            searchResponse =client.search(searchRequest, RequestOptions.DEFAULT);
            if (searchResponse.getHits().getTotalHits().value > 0) {
                SearchHit[] searchHit = searchResponse.getHits().getHits();
                //System.out.println(searchHit.length);
                totalvalues=searchHit.length;
                for (SearchHit hit : searchHit) {
                    JSONParser parser = new JSONParser();
                    JSONObject json = (JSONObject) parser.parse(hit.getSourceAsString());
                    booksdata.add(json);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        client.close();
    }

    public ArrayList<JSONObject> adminretrivedata() throws IOException {
        ArrayList<JSONObject> adminbooksdata=new ArrayList<JSONObject>();
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("bookshelf");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);


        try {
            SearchResponse searchResponse = null;
            searchResponse =client.search(searchRequest, RequestOptions.DEFAULT);
            if (searchResponse.getHits().getTotalHits().value > 0) {
                SearchHit[] searchHit = searchResponse.getHits().getHits();
                for (SearchHit hit : searchHit) {
                    JSONParser parser = new JSONParser();
                    JSONObject json = (JSONObject) parser.parse(hit.getSourceAsString());
                    adminbooksdata.add(json);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        client.close();
        return adminbooksdata;
    }

    public ArrayList<JSONObject> adminretrivesearchdata(String searchdata) throws IOException {
        ArrayList<JSONObject> adminbooksdata=new ArrayList<JSONObject>();
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
                    adminbooksdata.add(json);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        client.close();
        return adminbooksdata;
    }

    public ArrayList<JSONObject> adminretrivesortdata(String searchdata,String sortval,int flag) throws IOException {
        ArrayList<JSONObject> adminbooksdata=new ArrayList<JSONObject>();
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
            searchRequest.source(searchSourceBuilder.sort(sortval+".keyword",SortOrder.ASC));
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
                    adminbooksdata.add(json);
                    System.out.println("hello from sort retrive");
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        client.close();
        return adminbooksdata;
    }


    public ArrayList<JSONObject> adminlibretrivesearchdata(String searchdata) throws IOException {
        ArrayList<JSONObject> adminbooksdata=new ArrayList<JSONObject>();
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
                    if(LibraryShelf.bookids.contains(Integer.parseInt(json.get("bookid").toString())))
                    {
                        adminbooksdata.add(json);
                    }

                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        client.close();
        return adminbooksdata;
    }

    public ArrayList<JSONObject> adminlibretrivesortdata(String searchdata,String sortval,int flag) throws IOException {
        ArrayList<JSONObject> adminbooksdata=new ArrayList<JSONObject>();
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
            searchRequest.source(searchSourceBuilder.sort(sortval+".keyword",SortOrder.ASC));
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
                    if(LibraryShelf.bookids.contains(Integer.parseInt(json.get("bookid").toString())))
                    {
                        adminbooksdata.add(json);
                    }
                    System.out.println("hello from sort retrive");
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        client.close();
        return adminbooksdata;
    }

//    void jsonwrite(JSONObject jsonObject)
//    {
//        JSONParser jsonParser = new JSONParser();
//
//        try {
//            Object obj = jsonParser.parse(new FileReader("D:\\\\Intellij Ultimate\\\\Projects\\\\Book Shelf\\\\Bookshelf_6_Elasticsearch_BULK_API\\\\searchresult.json"));
//            JSONArray jsonArray = (JSONArray)obj;
//
//            System.out.println(jsonArray);
//            jsonArray.add(jsonObject);
//
//            System.out.println(jsonArray);
//
//            FileWriter file = new FileWriter("D:\\\\Intellij Ultimate\\\\Projects\\\\Book Shelf\\\\Bookshelf_6_Elasticsearch_BULK_API\\\\searchresult.json");
//            file.write(jsonArray.toJSONString());
//            file.flush();
//            file.close();
//
//        } catch (ParseException | IOException e) {
//            e.printStackTrace();
//        }
//
//    }
}
