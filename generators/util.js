/**
 * Created by abbas on 3/31/17.
 */
const path = require('path');

module.exports = {
  rewrite,
  rewriteFile
};

function escapeRegExp(str) {
  return str.replace(/[\-\[\]\/\{\}\(\)\*\+\?\.\\\^\$\|]/g, '\\$&'); // eslint-disable-line
}

function rewriteFile(args, generator) {
  args.path = args.path || process.cwd();
  const fullPath = path.join(args.path, args.file);

  args.haystack = generator.fs.read(fullPath);

  const body = rewrite(args);

  console.log(body);
  generator.fs.write(fullPath, body);
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
