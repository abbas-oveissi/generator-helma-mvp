'use strict';
const generator = require('yeoman-generator');
const chalk = require('chalk');
const yosay = require('yosay');
const util = require('util');
const mkdirp = require('mkdirp');
const AbbasUtils = require('../util');
const BaseGenerator = require('../generator-base');

module.exports = generator.extend({


  constructor: function (...args) {
    generator.apply(this, args);

    this.configOptions = {};
    // This adds support for a `--nav-support` flag
    this.option('nav-support', {
      desc: 'create activity with nav support',
      type: Boolean,
      defaults: false
    });

  this.option('nav-custom', {
    desc: 'create activity with custom navigation drawer',
    type: Boolean,
    defaults: false
  });

    this.option('nav-dynamic', {
      desc: 'navigation with dynamic items',
      type: Boolean,
      defaults: false
    });

    this.option('list', {
      desc: 'activity with recyclerview',
      type: Boolean,
      defaults: false
    });

    this.option('frag', {
      desc: 'activity with fragment',
      type: Boolean,
      defaults: false
    });

  },


  initializing: function () {
    this.props = {};
  },

  prompting: function () {

    const prompts = [
      {
        name: 'actname',
        message: 'What is activity name in Pascal, like "OrderInfo" ?',
        store: true,
      },
      {
        name: 'actpackage',
        message: 'What is package name from feature package name, like ".user.order.orderinfo" ?',
        store: true,
      }];

    if(this.options['list']==true) {
      prompts.push( {
        name: 'listname',
        message: 'What is list name, like "UserBooks" ?',
        store: true,
      });
    }

    if(this.options['frag']==true) {
      prompts.push( {
        name: 'fragname',
        message: 'What is fragment name, like "UserBooks" ?',
        store: true,
      });
    }

    return this.prompt(prompts).then(props => {
      this.props = props;
    this.props.activityName = props.actname;
    this.props.fragmentName = props.fragname;
      this.props.actvitiyPackageName = props.actpackage;
      this.props.listName = props.listname;
    });
  },

  writing: function () {
    var packageDir = this.config.get("appPackage").replace(/\./g, '/');
    this.props.appPackage = this.config.get("appPackage");
    this.props.appName = this.config.get("appName");


    var sufixPackageDir = this.props.actvitiyPackageName.replace(/\./g, '/');
    var justNameUnderScore = this.props.activityName.replace(/\.?([A-Z]+)/g, function (x, y) {
      return "_" + y.toLowerCase()
    }).replace(/^_/, "")
    this.props.activityNameUnScored = justNameUnderScore;
    var justName = this.props.activityName;
    mkdirp('app/src/main/java/' + packageDir + '/features/' + sufixPackageDir);
    var fullpathToActivityPackage = 'app/src/main/java/' + packageDir + '/features/' + sufixPackageDir;





    this.props.haveList = false;
    this.props.haveFrag = false;




    if (this.options['nav-support'] == true) {


      this.fs.copyTpl(this.templatePath('category_nav_support/CategoryContract.java'),
        this.destinationPath(fullpathToActivityPackage + '/' + justName + 'Contract.java'), this.props);
      this.fs.copyTpl(this.templatePath('category_nav_support/CategoryPresenter.java'),
        this.destinationPath(fullpathToActivityPackage + '/' + justName + 'Presenter.java'), this.props);
      this.fs.copyTpl(this.templatePath('category_nav_support/CategoryPresenterModule.java'),
        this.destinationPath(fullpathToActivityPackage + '/' + justName + 'PresenterModule.java'), this.props);

      this.fs.copyTpl(this.templatePath('category_nav_support/activity_category_content.xml'),
        this.destinationPath('app/src/main/res/layout/activity_' + justNameUnderScore + '_content.xml'), this.props);

      this.fs.copy(this.templatePath('category_nav_support/nav_header_category.xml'),
        this.destinationPath('app/src/main/res/layout/nav_header_' + justNameUnderScore + '.xml'));

      this.fs.copy(this.templatePath('category_nav_support/ic_menu_camera.xml'),
        this.destinationPath('app/src/main/res/drawable/ic_menu_camera.xml'));
      this.fs.copy(this.templatePath('category_nav_support/side_nav_bar.xml'),
        this.destinationPath('app/src/main/res/drawable/side_nav_bar.xml'));


      if (this.options['nav-dynamic'] == true) {
        this.props.isDynamic = true;
      }
      else {
        this.props.isDynamic = false;
        this.fs.copy(this.templatePath('category_nav_support/activity_category_drawer.xml'),
          this.destinationPath('app/src/main/res/menu/activity_' + justNameUnderScore + '_drawer.xml'));
      }

      this.fs.copyTpl(this.templatePath('category_nav_support/CategoryActivity.java'),
        this.destinationPath(fullpathToActivityPackage + '/' + justName + 'Activity.java'), this.props);
      this.fs.copyTpl(this.templatePath('category_nav_support/activity_category.xml'),
        this.destinationPath('app/src/main/res/layout/activity_' + justNameUnderScore + '.xml'), this.props);


    }
    else  if (this.options['nav-custom'] == true) {

      ///////////////////////////////////////////////////////////////////
      //////////////        Activity With Custom Nav    /////////////////
      ///////////////////////////////////////////////////////////////////


      //src folder

      this.fs.copyTpl(this.templatePath('activity_nav_custom/src/MainActivityContract.java'),
        this.destinationPath(fullpathToActivityPackage + '/' + justName + 'Contract.java'), this.props);
      this.fs.copyTpl(this.templatePath('activity_nav_custom/src/MainActivityPresenter.java'),
        this.destinationPath(fullpathToActivityPackage + '/' + justName + 'Presenter.java'), this.props);
      this.fs.copyTpl(this.templatePath('activity_nav_custom/src/MainActivityPresenterModule.java'),
        this.destinationPath(fullpathToActivityPackage + '/' + justName + 'PresenterModule.java'), this.props);
      this.fs.copyTpl(this.templatePath('activity_nav_custom/src/MainActivity.java'),
        this.destinationPath(fullpathToActivityPackage + '/' + justName + 'Activity.java'), this.props);


      //src -> drawer layout
      this.fs.copyTpl(this.templatePath('activity_nav_custom/src/drawerlayout/NavigationAdapter.java'),
        this.destinationPath(fullpathToActivityPackage + '/drawerlayout/NavigationAdapter.java'), this.props);
      this.fs.copyTpl(this.templatePath('activity_nav_custom/src/drawerlayout/NavigationDrawerBuilder.java'),
        this.destinationPath(fullpathToActivityPackage + '/drawerlayout/NavigationDrawerBuilder.java'), this.props);
      this.fs.copyTpl(this.templatePath('activity_nav_custom/src/drawerlayout/NavigationItem.java'),
        this.destinationPath(fullpathToActivityPackage + '/drawerlayout/NavigationItem.java'), this.props);


      //layout
      this.fs.copyTpl(this.templatePath('activity_nav_custom/layout/activity_main.xml'),
        this.destinationPath('app/src/main/res/layout/activity_' + justNameUnderScore + '.xml'), this.props);
      this.fs.copyTpl(this.templatePath('activity_nav_custom/layout/drawer_header.xml'),
        this.destinationPath('app/src/main/res/layout/drawer_header.xml'), this.props);
      this.fs.copyTpl(this.templatePath('activity_nav_custom/layout/drawer_item.xml'),
        this.destinationPath('app/src/main/res/layout/drawer_item.xml'), this.props);
      this.fs.copyTpl(this.templatePath('activity_nav_custom/layout/drawer_seprator.xml'),
        this.destinationPath('app/src/main/res/layout/drawer_seprator.xml'), this.props);
      this.fs.copyTpl(this.templatePath('activity_nav_custom/layout/navigation_drawer_layout.xml'),
        this.destinationPath('app/src/main/res/layout/navigation_drawer_layout.xml'), this.props);


      //////////////////////////////////////////////////////////
      //////////////////////////////////////////////////////////

    }
    else {

      if (this.options['list'] == true) {
        this.props.haveList = true;
        this.props.haveFrag = false;

        var listNameUnderScore = this.props.listName.replace(/\.?([A-Z]+)/g, function (x, y) {
          return "_" + y.toLowerCase()
        }).replace(/^_/, "")
        this.props.listNameUnderScore = listNameUnderScore;


        this.fs.copyTpl(this.templatePath('category/Movie.java'),
          this.destinationPath(fullpathToActivityPackage + '/Movie.java'), this.props);
        this.fs.copyTpl(this.templatePath('category/MovieAdapter.java'),
          this.destinationPath(fullpathToActivityPackage + '/' + this.props.listName + 'Adapter.java'), this.props);

        this.fs.copyTpl(this.templatePath('category/row_movie.xml'),
          this.destinationPath('app/src/main/res/layout/row_' + listNameUnderScore + '.xml'), this.props);

      }
      else if (this.options['frag'] == true) {

        var justFragmentNameUnderScore = this.props.fragmentName.replace(/\.?([A-Z]+)/g, function (x, y) {
          return "_" + y.toLowerCase()
        }).replace(/^_/, "")
        var justFragmentName = this.props.fragmentName;
        this.props.fragmentNameUnScored = justFragmentNameUnderScore;


        this.props.haveList = false;
        this.props.haveFrag = true;

        this.fs.copyTpl(this.templatePath('category/fragment_order.xml'),
          this.destinationPath('app/src/main/res/layout/fragment_' + justFragmentNameUnderScore + '.xml'), this.props);
        this.fs.copyTpl(this.templatePath('category/OrderFragment.java'),
          this.destinationPath(fullpathToActivityPackage + '/'+justFragmentName+'Fragment.java'), this.props);


      }

      // Copy template files
      this.fs.copyTpl(this.templatePath('category/CategoryActivity.java'),
        this.destinationPath(fullpathToActivityPackage + '/' + justName + 'Activity.java'), this.props);
      this.fs.copyTpl(this.templatePath('category/CategoryContract.java'),
        this.destinationPath(fullpathToActivityPackage + '/' + justName + 'Contract.java'), this.props);
      this.fs.copyTpl(this.templatePath('category/CategoryPresenter.java'),
        this.destinationPath(fullpathToActivityPackage + '/' + justName + 'Presenter.java'), this.props);
      this.fs.copyTpl(this.templatePath('category/CategoryPresenterModule.java'),
        this.destinationPath(fullpathToActivityPackage + '/' + justName + 'PresenterModule.java'), this.props);

      this.fs.copyTpl(this.templatePath('category/activity_category.xml'),
        this.destinationPath('app/src/main/res/layout/activity_' + justNameUnderScore + '.xml'), this.props);


    }


    const fullPath = 'app/src/main/AndroidManifest.xml';
    try {
      let act = '<activity android:name=".features' + this.props.actvitiyPackageName + '.' + justName + 'Activity"></activity>';

      AbbasUtils.rewriteFile({
        file: fullPath,
        needle: 'helmamvp-needle-manifest-add-activity',
        splicable: [
          act
        ]
      }, this);
    } catch (e) {
      this.log(e.message);
    }
    //
    //
    //
    //


    const fullPathComponent = 'app/src/main/java/' + packageDir + '/di/ActivityBuilder.java';
    try {
      let act1 ='@ContributesAndroidInjector(modules = {'+justName+'PresenterModule.class})'
      let act2 ='abstract '+justName+'Activity bind'+justName+'Activity();';

      AbbasUtils.rewriteFile({
        file: fullPathComponent,
        needle: 'helmamvp-needle-add-dagger-activitycomponent',
        splicable: [
          act1
        ]
      }, this);

      AbbasUtils.rewriteFile({
        file: fullPathComponent,
        needle: 'helmamvp-needle-add-dagger-activitycomponent',
        splicable: [
          act2
        ]
      }, this);

    } catch (e) {
      this.log(e.message);
    }

    try {
      let act1 = 'import ' +this.props.appPackage +'.features' + this.props.actvitiyPackageName + '.' + justName + 'PresenterModule;';
      let act2 = 'import ' +this.props.appPackage +'.features' + this.props.actvitiyPackageName + '.' + justName + 'Activity;';


      AbbasUtils.rewriteFile({
        file: fullPathComponent,
        needle: 'helmamvp-needle-add-import-dagger-activitycomponent',
        splicable: [
          act1
        ]
      }, this);


      AbbasUtils.rewriteFile({
        file: fullPathComponent,
        needle: 'helmamvp-needle-add-import-dagger-activitycomponent',
        splicable: [
          act2
        ]
      }, this);
    } catch (e) {
      this.log(e.message);
    }
  }
});

