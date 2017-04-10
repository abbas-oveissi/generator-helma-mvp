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
        message: 'esme actvity besoorate Pascal, mesle "OrderInfo" ?',
        store: true,
      },
      {
        name: 'actpackage',
        message: 'masire package actvity az package e feature, mesle ".user.order.orderinfo" ?',
        store: true,
      }];

    if(this.options['list']==true) {
      prompts.push( {
        name: 'listname',
        message: 'esme list, mesle "UserBooks" ?',
        store: true,
      });
    }



    return this.prompt(prompts).then(props => {
      this.props = props;
      this.props.activityName = props.actname;
      this.props.actvitiyPackageName = props.actpackage;
      this.props.listName = props.listname;
    });
  },

  writing: function () {
    var packageDir = this.config.get("appPackage").replace(/\./g, '/');
    this.props.appPackage = this.config.get("appPackage");
    this.props.appName = this.config.get("appName");
    var sufixPackageDir = this.props.actvitiyPackageName.replace(/\./g, '/');
    var justNameUnderScore=this.props.activityName.replace(/\.?([A-Z]+)/g, function (x,y){return "_" + y.toLowerCase()}).replace(/^_/, "")
    this.props.activityNameUnScored=justNameUnderScore;
    var justName=this.props.activityName;
    mkdirp('app/src/main/java/'+packageDir+'/features/' +sufixPackageDir);
    var fullpathToActivityPackage='app/src/main/java/'+packageDir+'/features/' +sufixPackageDir;

    this.props.haveList=false;
    this.props.haveFrag=false;

    if(this.options['nav-support']==true)
    {

      this.fs.copyTpl(this.templatePath('category_nav_support/CategoryComponent.java'),
        this.destinationPath(fullpathToActivityPackage+'/'+justName+'Component.java'), this.props);
      this.fs.copyTpl(this.templatePath('category_nav_support/CategoryContract.java'),
        this.destinationPath(fullpathToActivityPackage+'/'+justName+'Contract.java'), this.props);
      this.fs.copyTpl(this.templatePath('category_nav_support/CategoryPresenter.java'),
        this.destinationPath(fullpathToActivityPackage+'/'+justName+'Presenter.java'), this.props);
      this.fs.copyTpl(this.templatePath('category_nav_support/CategoryPresenterModule.java'),
        this.destinationPath(fullpathToActivityPackage+'/'+justName+'PresenterModule.java'), this.props);

      this.fs.copyTpl(this.templatePath('category_nav_support/activity_category_content.xml'),
        this.destinationPath('app/src/main/res/layout/activity_'+justNameUnderScore+'_content.xml'), this.props);

      this.fs.copy(this.templatePath('category_nav_support/nav_header_category.xml'),
        this.destinationPath('app/src/main/res/layout/nav_header_'+justNameUnderScore+'.xml'));

      this.fs.copy(this.templatePath('category_nav_support/ic_menu_camera.xml'),
        this.destinationPath('app/src/main/res/drawable/ic_menu_camera.xml'));
      this.fs.copy(this.templatePath('category_nav_support/side_nav_bar.xml'),
        this.destinationPath('app/src/main/res/drawable/side_nav_bar.xml'));


      if(this.options['nav-dynamic']==true) {
        this.props.isDynamic=true;
      }
      else
      {
        this.props.isDynamic=false;
        this.fs.copy(this.templatePath('category_nav_support/activity_category_drawer.xml'),
          this.destinationPath('app/src/main/res/menu/activity_'+justNameUnderScore+'_drawer.xml'));
      }

      this.fs.copyTpl(this.templatePath('category_nav_support/CategoryActivity.java'),
        this.destinationPath(fullpathToActivityPackage+'/'+justName+'Activity.java'), this.props);
      this.fs.copyTpl(this.templatePath('category_nav_support/activity_category.xml'),
        this.destinationPath('app/src/main/res/layout/activity_'+justNameUnderScore+'.xml'), this.props);




    }
    else
    {

      if(this.options['list']==true) {
        this.props.haveList=true;
        this.props.haveFrag=false;

        var listNameUnderScore = this.props.listName.replace(/\.?([A-Z]+)/g, function (x, y) {
          return "_" + y.toLowerCase()
        }).replace(/^_/, "")
        this.props.listNameUnderScore = listNameUnderScore;


        this.fs.copyTpl(this.templatePath('category/Movie.java'),
          this.destinationPath(fullpathToActivityPackage+'/Movie.java'), this.props);
        this.fs.copyTpl(this.templatePath('category/MovieAdapter.java'),
          this.destinationPath(fullpathToActivityPackage+'/'+this.props.listName+'Adapter.java'), this.props);

        this.fs.copyTpl(this.templatePath('category/row_movie.xml'),
          this.destinationPath('app/src/main/res/layout/row_'+listNameUnderScore+'.xml'), this.props);

      }
      else if (this.options['frag']==true){
        this.props.haveList = false;
        this.props.haveFrag = true;

        this.fs.copyTpl(this.templatePath('category/fragment_order.xml'),
          this.destinationPath('app/src/main/res/layout/fragment_order.xml'), this.props);
        this.fs.copyTpl(this.templatePath('category/OrderFragment.java'),
          this.destinationPath(fullpathToActivityPackage+'/OrderFragment.java'), this.props);


      }

      // Copy template files
      this.fs.copyTpl(this.templatePath('category/CategoryActivity.java'),
        this.destinationPath(fullpathToActivityPackage+'/'+justName+'Activity.java'), this.props);
      this.fs.copyTpl(this.templatePath('category/CategoryComponent.java'),
        this.destinationPath(fullpathToActivityPackage+'/'+justName+'Component.java'), this.props);
      this.fs.copyTpl(this.templatePath('category/CategoryContract.java'),
        this.destinationPath(fullpathToActivityPackage+'/'+justName+'Contract.java'), this.props);
      this.fs.copyTpl(this.templatePath('category/CategoryPresenter.java'),
        this.destinationPath(fullpathToActivityPackage+'/'+justName+'Presenter.java'), this.props);
      this.fs.copyTpl(this.templatePath('category/CategoryPresenterModule.java'),
        this.destinationPath(fullpathToActivityPackage+'/'+justName+'PresenterModule.java'), this.props);

      this.fs.copyTpl(this.templatePath('category/activity_category.xml'),
        this.destinationPath('app/src/main/res/layout/activity_'+justNameUnderScore+'.xml'), this.props);




    }












    const fullPath = 'app/src/main/AndroidManifest.xml';
    try {
      let act='<activity android:name=".features'+this.props.actvitiyPackageName+'.'+justName+'Activity"></activity>';

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
    const fullPathComponent = 'app/src/main/java/'+packageDir+'/di/ApplicationComponent.java';
    try {
      let act=justName+'Component plus('+justName+'PresenterModule module);';

      AbbasUtils.rewriteFile({
        file: fullPathComponent,
        needle: 'helmamvp-needle-add-dagger-component',
        splicable: [
          act
        ]
      }, this);
    } catch (e) {
      this.log(e.message);
    }
  }



});

