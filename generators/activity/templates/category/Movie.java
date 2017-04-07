package <%= appPackage %>.features<%= actvitiyPackageName %>;

/**
 * Created by Abbas on 25/05/2016.
 */
public class Movie {
    public Movie(Integer id, String title, String poster) {
        this.id = id;
        this.title = title;
        this.poster = poster;
    }
    public Integer id;
    public String title;
    public String poster;
}
