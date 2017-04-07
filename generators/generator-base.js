const path = require('path');
const Generator = require('yeoman-generator');
const fs = require('fs');
const HelmaUtils = require('./util');



module.exports = class extends Generator {

    constructor(args, opts) {
        super(args, opts);

    }

  addActivityToManifest (name, isLouncher) {
    const fullPath = 'app/src/main/AndroidManifest.xml';
    try {
      let act='<activity android:name=".helma"></activity>';

      HelmaUtils.rewriteFile({
        file: fullPath,
        needle: 'helmamvp-needle-manifest-add-activity',
        splicable: [
          act
        ]
      }, this);
    } catch (e) {
      this.log(e.message);
    }
  }

};
