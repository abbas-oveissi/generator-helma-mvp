package <%= appPackage %>.interactors.remote;


import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Abbas on 30/04/16.
 */
public interface MyApi {
    //http://oveissi.ir/varzesh3/v2/navigation_drawer.php
    @GET("/v2/navigation_drawer.php")
    Observable<String> getNavigationDrawerItems();
}
