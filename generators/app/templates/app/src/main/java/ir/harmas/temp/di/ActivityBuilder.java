package <%= appPackage %>.di;

import android.app.Activity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import <%= appPackage %>.features.category.CategoryActivity;
import <%= appPackage %>.features.category.CategoryPresenterModule;

// helmamvp-needle-add-import-dagger-activitycomponent

/**
 * Created by abbas on 9/18/17.
 */

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {CategoryPresenterModule.class})
    abstract CategoryActivity bindCategoryActivity();

    // helmamvp-needle-add-dagger-activitycomponent

}
