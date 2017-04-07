package <%= appPackage %>.di;

import dagger.Module;
import dagger.Provides;
import <%= appPackage %>.interactors.MyInteractor;
import <%= appPackage %>.interactors.MyInteractorImpl;

/**
 * Created by abbas on 7/5/16.
 */
@Module
public class InteractorMadule {


    @Provides
    public MyInteractor provideMyInteractor(MyInteractorImpl interactor) {
        return interactor;
    }
}
