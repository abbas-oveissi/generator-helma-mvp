package <%= appPackage %>.features<%= actvitiyPackageName %>;

import dagger.Module;
import dagger.Provides;


@Module
public class <%= activityName %>PresenterModule {

    @Provides
    public <%= activityName %>Contract.Presenter providePresenter(<%= activityName %>Presenter presenter) {
        return presenter;
    }
}
