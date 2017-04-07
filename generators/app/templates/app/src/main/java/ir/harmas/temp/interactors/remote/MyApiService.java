package <%= appPackage %>.interactors.remote;

import rx.Observable;

/**
 * Created by Abbas on 30/04/16.
 */
public interface MyApiService {
    Observable<String> getNavigationDrawerItems();
}
