package <%= appPackage %>.di;


import javax.inject.Singleton;

import dagger.Component;
import <%= appPackage %>.di.common.CacheModule;
import <%= appPackage %>.di.common.CallAdapterModule;
import <%= appPackage %>.di.common.ClientModule;
import <%= appPackage %>.di.common.ConverterModule;
import <%= appPackage %>.di.common.GsonModule;
import <%= appPackage %>.di.common.InterceptorModule;
import <%= appPackage %>.di.common.LoggerModule;
import <%= appPackage %>.di.common.MyApiModule;
import <%= appPackage %>.features.category.CategoryComponent;
import <%= appPackage %>.features.category.CategoryPresenterModule;
// helmamvp-needle-add-import-dagger-component


@Singleton
@Component( modules = {
        AndroidModule.class,
        ApplicationModule.class,
        MyApiModule.class,
        ConverterModule.class,
        CallAdapterModule.class,
        CacheModule.class,
        InterceptorModule.class,
        InteractorMadule.class,
        ClientModule.class,
        LoggerModule.class,
        GsonModule.class
})
public interface ApplicationComponent {
    CategoryComponent plus(CategoryPresenterModule module);

    // helmamvp-needle-add-dagger-component
}

