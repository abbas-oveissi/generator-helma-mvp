package <%= appPackage %>.features.main;

import dagger.Module;
import dagger.Provides;


@Module
public class MainActivityPresenterModule {

    @Provides
    public MainActivityContract.Presenter providePresenter(MainActivityPresenter presenter) {
        return presenter;
    }
}
