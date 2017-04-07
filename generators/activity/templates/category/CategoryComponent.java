package <%= appPackage %>.features<%= actvitiyPackageName %>;


import dagger.Subcomponent;


@Subcomponent(modules = {
        <%= activityName %>PresenterModule.class
})
public interface <%= activityName %>Component {

    void inject(<%= activityName %>Activity activity);
}
