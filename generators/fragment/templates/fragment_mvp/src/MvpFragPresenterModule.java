package <%= appPackage %>.features<%= fragmentPackageName %>;

import dagger.Module;
import dagger.Provides;


@Module
public class <%= fragmentName %>PresenterModule {

    @Provides
    public <%= fragmentName %>Contract.Presenter providePresenter(<%= fragmentName %>Presenter presenter) {
        return presenter;
    }
}
