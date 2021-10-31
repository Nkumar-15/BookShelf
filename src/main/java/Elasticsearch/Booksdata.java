package Elasticsearch;

public class Booksdata {

    public int bookid;
    public String title;
    public String authorname;
    public String publishername;
    public String publisheddate;
    public String genre;




    public Booksdata(int bookid,String title,String authorname,String publishername,String publisheddate,String genre) {
        this.bookid = bookid;
        this.title = title;
        this.authorname = authorname;
        this.publishername = publishername;
        this.publisheddate = publisheddate;
        this.genre = genre;

    }

}
