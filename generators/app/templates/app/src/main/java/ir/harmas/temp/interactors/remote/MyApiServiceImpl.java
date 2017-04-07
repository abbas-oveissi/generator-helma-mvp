package <%= appPackage %>.interactors.remote;


import rx.Observable;

/**
 * Created by Abbas on 30/04/16.
 */
public class MyApiServiceImpl implements MyApiService {
    private final MyApi api;

    public MyApiServiceImpl(MyApi api) {
        this.api = api;
    }


    @Override
    public Observable<String> getNavigationDrawerItems(){
        return api.getNavigationDrawerItems();
    }



}
