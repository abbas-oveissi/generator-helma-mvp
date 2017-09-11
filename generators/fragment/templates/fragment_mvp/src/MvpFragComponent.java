package <%= appPackage %>.features<%= fragmentPackageName %>;


import dagger.Subcomponent;


@Subcomponent(modules = {
  <%= fragmentName %>PresenterModule.class
})
public interface <%= fragmentName %>Component {

    void inject(<%= fragmentName %>Fragment fragment);
}
