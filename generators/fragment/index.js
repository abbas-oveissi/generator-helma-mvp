'use strict';
const generator = require('yeoman-generator');
const chalk = require('chalk');
const yosay = require('yosay');
const util = require('util');
const mkdirp = require('mkdirp');
const AbbasUtils = require('../util');
const BaseGenerator = require('../generator-base');

module.exports = generator.extend({


  constructor: function (...args)
{
  generator.apply(this, args);

  this.configOptions = {};
  // This adds support for a `--nav-support` flag


}
,


initializing: function () {
  this.props = {};
}
,

prompting: function () {

  const prompts = [
    {
      name: 'fragname',
      message: 'What is fragment name in Pascal, like "OrderInfo" ?',
      store: true,
    },
    {
      name: 'fragpackage',
      message: 'What is package name from feature package name, like ".user.order.orderinfo" ?',
      store: true,
    }];


  return this.prompt(prompts).then(props => {
    this.props = props;
  this.props.fragmentName = props.fragname;
  this.props.fragmentPackageName = props.fragpackage;
});
},


writing: function () {
  var packageDir = this.config.get("appPackage").replace(/\./g, '/');
  this.props.appPackage = this.config.get("appPackage");
  this.props.appName = this.config.get("appName");


  var sufixPackageDir = this.props.fragmentPackageName.replace(/\./g, '/');
  var justNameUnderScore = this.props.fragmentName.replace(/\.?([A-Z]+)/g, function (x, y) {
    return "_" + y.toLowerCase()
  }).replace(/^_/, "")
  this.props.fragmentNameUnScored = justNameUnderScore;

  var justName = this.props.fragmentName;


  mkdirp('app/src/main/java/' + packageDir + '/features/' + sufixPackageDir);
  var fullpathToActivityPackage = 'app/src/main/java/' + packageDir + '/features/' + sufixPackageDir;

  ///////////////////////////////////////////////////////////////////
  //////////////        MVP Fragment    /////////////////
  ///////////////////////////////////////////////////////////////////


  //src folder
  this.fs.copyTpl(this.templatePath('fragment_mvp/src/MvpFragContract.java'),
    this.destinationPath(fullpathToActivityPackage + '/' + justName + 'Contract.java'), this.props);
  this.fs.copyTpl(this.templatePath('fragment_mvp/src/MvpFragPresenter.java'),
    this.destinationPath(fullpathToActivityPackage + '/' + justName + 'Presenter.java'), this.props);
  this.fs.copyTpl(this.templatePath('fragment_mvp/src/MvpFragPresenterModule.java'),
    this.destinationPath(fullpathToActivityPackage + '/' + justName + 'PresenterModule.java'), this.props);
  this.fs.copyTpl(this.templatePath('fragment_mvp/src/MvpFragFragment.java'),
    this.destinationPath(fullpathToActivityPackage + '/' + justName + 'Fragment.java'), this.props);


  //layout
  this.fs.copyTpl(this.templatePath('fragment_mvp/layout/fragment_mvp_frag.xml'),
    this.destinationPath('app/src/main/res/layout/fragment_' + justNameUnderScore + '.xml'), this.props);


  //////////////////////////////////////////////////////////
  //////////////////////////////////////////////////////////


  const fullPathComponent = 'app/src/main/java/' + packageDir + '/di/FragmentBuilder.java';
  try {
    let act1 = '@ContributesAndroidInjector(modules = {' + justName + 'PresenterModule.class})'
    let act2 = 'abstract ' + justName + 'Fragment bind' + justName + 'Fragment();';

    AbbasUtils.rewriteFile({
      file: fullPathComponent,
      needle: 'helmamvp-needle-add-dagger-fragmentcomponent',
      splicable: [
        act1
      ]
    }, this);

    AbbasUtils.rewriteFile({
      file: fullPathComponent,
      needle: 'helmamvp-needle-add-dagger-fragmentcomponent',
      splicable: [
        act2
      ]
    }, this);

  } catch (e) {
    this.log(e.message);
  }

  try {
    let act1 = 'import ' + this.props.appPackage + '.features' + this.props.fragmentPackageName + '.' + justName + 'PresenterModule;';
    let act2 = 'import ' + this.props.appPackage + '.features' + this.props.fragmentPackageName + '.' + justName + 'Fragment;';
    let act3 = 'import dagger.android.ContributesAndroidInjector;';


    AbbasUtils.rewriteFile({
      file: fullPathComponent,
      needle: 'helmamvp-needle-add-import-dagger-fragmentcomponent',
      splicable: [
        act1
      ]
    }, this);

    AbbasUtils.rewriteFile({
      file: fullPathComponent,
      needle: 'helmamvp-needle-add-import-dagger-fragmentcomponent',
      splicable: [
        act2
      ]
    }, this);

    AbbasUtils.rewriteFile({
      file: fullPathComponent,
      needle: 'helmamvp-needle-add-import-dagger-fragmentcomponent',
      splicable: [
        act3
      ]
    }, this);

  } catch (e) {
    this.log(e.message);
  }


  const fullPathAppClass = 'app/src/main/java/' + packageDir + '/' + this.props.appName + 'Application.java';

  const find = AbbasUtils.findStringFile('NoOpHasSupportFragmentInjector', {
    file: fullPathAppClass
  }, this);


  console.log("check NoOpHasSupportFragmentInjector");

  console.log(find);


  if (find == true) {

    AbbasUtils.replaceStringFromLast('HasSupportFragmentInjector', 'NoOpHasSupportFragmentInjector', {
      file: fullPathAppClass
    }, this);


    console.log("start fragment injector in appclass");


    try {


      let act1 = '@Inject'
      let act2 = 'DispatchingAndroidInjector<Fragment> dispatchingFragmentInjector;';
      let act3 = '@Override';
      let act4 = 'public AndroidInjector<Fragment> supportFragmentInjector() { return dispatchingFragmentInjector; }';

      AbbasUtils.rewriteFileWithoutCheck({
        file: fullPathAppClass,
        needle: 'helmamvp-needle-add-dagger-fragmentinjector',
        splicable: [
          act1
        ]
      }, this);

      AbbasUtils.rewriteFileWithoutCheck({
        file: fullPathAppClass,
        needle: 'helmamvp-needle-add-dagger-fragmentinjector',
        splicable: [
          act2
        ]
      }, this);

      AbbasUtils.rewriteFileWithoutCheck({
        file: fullPathAppClass,
        needle: 'helmamvp-needle-add-dagger-fragmentinjector',
        splicable: [
          act3
        ]
      }, this);

      AbbasUtils.rewriteFileWithoutCheck({
        file: fullPathAppClass,
        needle: 'helmamvp-needle-add-dagger-fragmentinjector',
        splicable: [
          act4
        ]
      }, this);

    } catch (e) {
      this.log(e.message);
    }

    try {



      let act1 = 'import dagger.android.support.HasSupportFragmentInjector;'
      let act2 = 'import android.support.v4.app.Fragment;'

      AbbasUtils.rewriteFile({
        file: fullPathAppClass,
        needle: 'helmamvp-needle-import-dagger-fragmentinjector',
        splicable: [
          act1
        ]
      }, this);

      AbbasUtils.rewriteFile({
        file: fullPathAppClass,
        needle: 'helmamvp-needle-import-dagger-fragmentinjector',
        splicable: [
          act2
        ]
      }, this);

    } catch (e) {
      this.log(e.message);
    }
  }



}

});

