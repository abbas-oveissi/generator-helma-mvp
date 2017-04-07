package <%= appPackage %>.interactors;

import rx.Observable;

/**
 * Created by abbas on 7/10/16.
 */
public interface MyInteractor {
    Observable<String> getNavigationDrawerItems();
}
