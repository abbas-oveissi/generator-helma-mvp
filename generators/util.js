/**
 * Created by abbas on 3/31/17.
 */
const path = require('path');

module.exports = {
  rewrite,
  rewriteFile,
  findStringFile,
  replaceStringFromLast,
  rewriteFileWithoutCheck
};

function escapeRegExp(str) {
  return str.replace(/[\-\[\]\/\{\}\(\)\*\+\?\.\\\^\$\|]/g, '\\$&'); // eslint-disable-line
}


function findStringFile(substr,args, generator) {
  args.path = args.path || process.cwd();
  const fullPath = path.join(args.path, args.file);

  const body = generator.fs.read(fullPath);

  if(body.indexOf(substr) > -1) {
    return true;
  }
  return false;
}


function replaceStringFromLast(replace, str, args, generator) {
  args.path = args.path || process.cwd();
  const fullPath = path.join(args.path, args.file);

  console.log("start substring");

  var substr = generator.fs.read(fullPath);


  var intpos= substr.lastIndexOf(str);

  var part2=substr.substr(intpos)
  var part1=substr.substr(0,intpos)

  console.log(part2);

  part2=part2.replace(str,replace);

  console.log(part2);

  // const body =substr.replace(replace, str)

  const body=part1+part2;


  // var pat = new RegExp('(' + str + ')(?!.*\\1)', 'i')
  // const body = substr.replace(pat, replace);

  console.log(body);
  generator.fs.write(fullPath, body);

  console.log("end substring");

}



function rewriteFile(args, generator) {
  args.path = args.path || process.cwd();
  const fullPath = path.join(args.path, args.file);

  args.haystack = generator.fs.read(fullPath);

  const body = rewrite(args);

  // console.log(body);
  generator.fs.write(fullPath, body);
}

function rewriteFileWithoutCheck(args, generator) {
  args.path = args.path || process.cwd();
  const fullPath = path.join(args.path, args.file);

  args.haystack = generator.fs.read(fullPath);

  const body = rewriteWithoutCheck(args);

  // console.log(body);
  generator.fs.write(fullPath, body);
}



function rewriteWithoutCheck(args) {
  // Check if splicable is already in the body text
  const re = new RegExp(args.splicable.map(line => `\\s*${escapeRegExp(line)}`).join('\n'));


  const lines = args.haystack.split('\n');

  let otherwiseLineIndex = -1;
  lines.forEach((line, i) => {
    if (line.indexOf(args.needle) !== -1) {
    otherwiseLineIndex = i;
  }
});

  let spaces = 0;
  while (lines[otherwiseLineIndex].charAt(spaces) === ' ') {
    spaces += 1;
  }

  let spaceStr = '';

  while ((spaces -= 1) >= 0) { // eslint-disable-line no-cond-assign
    spaceStr += ' ';
  }

  lines.splice(otherwiseLineIndex, 0, args.splicable.map(line => spaceStr + line).join('\n'));

  return lines.join('\n');
}




function rewrite(args) {
  // Check if splicable is already in the body text
  const re = new RegExp(args.splicable.map(line => `\\s*${escapeRegExp(line)}`).join('\n'));

  if (re.test(args.haystack)) {
    return args.haystack;
  }



  const lines = args.haystack.split('\n');

  let otherwiseLineIndex = -1;
  lines.forEach((line, i) => {
    if (line.indexOf(args.needle) !== -1) {
      otherwiseLineIndex = i;
    }
  });

  let spaces = 0;
  while (lines[otherwiseLineIndex].charAt(spaces) === ' ') {
    spaces += 1;
  }

  let spaceStr = '';

  while ((spaces -= 1) >= 0) { // eslint-disable-line no-cond-assign
    spaceStr += ' ';
  }

  lines.splice(otherwiseLineIndex, 0, args.splicable.map(line => spaceStr + line).join('\n'));

  return lines.join('\n');
}
