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
import <%= appPackage %>.<%= appName %>Application;
import dagger.android.AndroidInjectionModule;



@Singleton
@Component( modules = {
        AndroidInjectionModule.class,
        FragmentBuilder.class,
        ActivityBuilder.class,
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
    void inject(<%= appName %>Application application);
}

