package <%= appPackage %>.features.category;

import dagger.Module;
import dagger.Provides;


@Module
public class CategoryPresenterModule {

    @Provides
    public CategoryContract.Presenter providePresenter(CategoryPresenter presenter) {
        return presenter;
    }
}
