package <%= appPackage %>.features.category;


import dagger.Subcomponent;


@Subcomponent(modules = {
        CategoryPresenterModule.class
})
public interface CategoryComponent {

    void inject(CategoryActivity taskDetailActivity);
}
