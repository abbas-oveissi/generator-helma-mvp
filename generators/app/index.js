'use strict';
const generator = require('yeoman-generator');
const chalk = require('chalk');
const yosay = require('yosay');
const util = require('util');
const mkdirp = require('mkdirp');
const HelmaUtils = require('../util');
const BaseGenerator = require('../generator-base');

module.exports = generator.extend({

  initializing: function () {
    this.props = {};
  },

  prompting: function () {
    this.log(yosay(
      'Welcome to the rad ' + chalk.red('Helma MVP Starter') + ' generator!'
    ));

    const prompts = [
      {
        name: 'name',
        message: 'What are you calling your app?',
        store: true,
        default: this.appname // Default to current folder name
      },
      {
        name: 'package',
        message: 'What package will you be publishing the app under?',
        store: true
      },
      {
        name: 'targetSdk',
        message: 'What Android SDK will you be targeting?',
        store: true,
        default: 25 // Android 7.1 (Nougat)
      },
      {
        name: 'minSdk',
        message: 'What is the minimum Android SDK you wish to support?',
        store: true,
        default: 17 // Android 4.2 (Jelly Bean)
      },
      {
        name: 'buildVersion',
        message: 'What is the Build Version you use?',
        store: true,
        default: '25.0.2' // Android 7.1 (Nougat)
      },
      {
        name: 'supportVersion',
        message: 'What is the Support Version Number?',
        store: true,
        default: '23.4.0' // Android 6.0 (Marshmallow)
      }];

    return this.prompt(prompts).then(props => {
      this.props.appPackage = props.package;
      this.props = props;
      this.props.appPackage = props.package;
      this.props.appName = props.name;
      this.appPackage = props.package;
      this.props.androidTargetSdkVersion = props.targetSdk;
      this.props.androidMinSdkVersion = props.minSdk;
      this.props.androidSupportVersion = props.supportVersion;
      this.props.androidBuildToolsVersion = props.buildVersion;
    });
  },

  writing: function () {
    var packageDir = this.props.appPackage.replace(/\./g, '/');

    this.config.set("appPackage", this.props.appPackage);
    this.config.set("appName", this.props.appName);

    mkdirp(this.props.appName); // create app directory that all files will create in that and will be root of project
    mkdirp(this.props.appName + '/app');
    mkdirp(this.props.appName + '/app/src/main/assets');
    mkdirp(this.props.appName + '/app/src/main/java/' + packageDir);
    mkdirp(this.props.appName + '/app/src/androidTest/java/' + packageDir);
    mkdirp(this.props.appName + '/app/src/test/java/' + packageDir);

    // Copy files
    this.fs.copy(this.templatePath('_gitignore'), this.destinationPath(this.props.appName + '/.gitignore'));
    this.fs.copy(this.templatePath('app/_gitignore'), this.destinationPath(this.props.appName + '/app/.gitignore'));
    this.fs.copy(this.templatePath('build.gradle'), this.destinationPath(this.props.appName + '/build.gradle'));
    this.fs.copy(this.templatePath('gradle.properties'), this.destinationPath(this.props.appName + '/gradle.properties'));
    this.fs.copy(this.templatePath('gradlew.bat'), this.destinationPath(this.props.appName + '/gradlew.bat'));
    this.fs.copy(this.templatePath('settings.gradle'), this.destinationPath(this.props.appName + '/settings.gradle'));
    // Copy directories
    this.fs.copy(this.templatePath('gradle'), this.destinationPath(this.props.appName + '/gradle'));
    this.fs.copy(this.templatePath('app/src/main/assets'), this.destinationPath(this.props.appName + '/app/src/main/assets'));
    this.fs.copy(this.templatePath('app/src/main/res'), this.destinationPath(this.props.appName + '/app/src/main/res'));


    // Copy template files
    this.fs.copyTpl(this.templatePath('app/proguard-rules.pro'), this.destinationPath(this.props.appName + '/app/proguard-rules.pro'), this.props);
    this.fs.copyTpl(this.templatePath('libraries.gradle'), this.destinationPath(this.props.appName + '/libraries.gradle'), this.props);
    this.fs.copyTpl(this.templatePath('app/build.gradle'), this.destinationPath(this.props.appName + '/app/build.gradle'), this.props);
    this.fs.copyTpl(this.templatePath('app/src/main/AndroidManifest.xml'), this.destinationPath(this.props.appName + '/app/src/main/AndroidManifest.xml'), this.props);
    this.fs.copyTpl(this.templatePath('app/src/main/res/values/strings.xml'), this.destinationPath(this.props.appName + '/app/src/main/res/values/strings.xml'), this.props);

    // Copy template directory
    this.fs.copyTpl(this.templatePath('app/src/main/res/layout'), this.destinationPath(this.props.appName + '/app/src/main/res/layout'), this.props);
    this.fs.copyTpl(this.templatePath('app/src/androidTest/java/ir/harmas/temp'), this.destinationPath(this.props.appName + '/app/src/androidTest/java/' + packageDir), this.props);
    this.fs.copyTpl(this.templatePath('app/src/test/java/ir/harmas/temp'), this.destinationPath(this.props.appName + '/app/src/test/java/' + packageDir), this.props);
    this.fs.copyTpl(this.templatePath('app/src/main/java/ir/harmas/temp'), this.destinationPath(this.props.appName + '/app/src/main/java/' + packageDir), this.props);


    this.fs.delete(this.destinationPath(this.props.appName + '/app/src/main/java/' + packageDir) + '/FloraShopApplication.java');
    this.fs.copyTpl(this.templatePath('app/src/main/java/ir/harmas/temp/FloraShopApplication.java'), this.destinationPath(this.destinationPath(this.props.appName + '/app/src/main/java/' + packageDir) + '/' + 'A.java'), this.props);

    this.fs.move(this.destinationPath(this.contextRoot + '/.yo-rc.json'), this.destinationPath(this.props.appName + '/.yo-rc.json')); // copy .yo-rc.json to project root directory

  }



});
