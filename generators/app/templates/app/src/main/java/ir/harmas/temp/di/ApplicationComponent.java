package <%= appPackage %>.di;


  import javax.inject.Singleton;

  import dagger.Component;
  import <%= appPackage %>.di.common.ApiModule;
  import <%= appPackage %>.di.common.GsonModule;
  import <%= appPackage %>.di.common.OkHttpInterceptorModule;
  import <%= appPackage %>.di.common.RetrofitModule;
  import <%= appPackage %>.features.category.CategoryComponent;
  import <%= appPackage %>.features.category.CategoryPresenterModule;
  import <%= appPackage %>.<%= appName %>Application;
  import dagger.android.AndroidInjectionModule;
  import dagger.BindsInstance;

@Singleton
@Component( modules = {
  AndroidInjectionModule.class,
  FragmentBuilder.class,
  ActivityBuilder.class,
  AndroidModule.class,
  ApplicationModule.class,
  RetrofitModule.class,
  ApiModule.class,
  OkHttpInterceptorModule.class,
  GsonModule.class
})
public interface ApplicationComponent {
  void inject(<%= appName %>Application application);


  @Component.Builder
  interface Builder
  {
    ApplicationComponent build();
    @BindsInstance Builder application(<%= appName %>Application application);
  }
}

