package <%= appPackage %>.features.main;


import dagger.Subcomponent;


@Subcomponent(modules = {
        MainActivityPresenterModule.class
})
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);
}
