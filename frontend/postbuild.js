const fs = require("fs");
const fse = require("fs-extra");

const sourceDir = "./dist";
const staticDir = "../src/main/resources/static";
const templatesDir = "../src/main/resources/templates";

if (!fs.existsSync(staticDir)) {
  fs.mkdirSync(staticDir, { recursive: true });
}

fse.copy(sourceDir, staticDir, function(err) {
  if (err) {
    console.error(err);
  } else {
    console.log("successfully move frontend build files from /dist to /static");
    moveIndexHtml();
  }
});

function moveIndexHtml() {
  fs.rename(staticDir + "/index.html", templatesDir + "/index.html", function(
    err
  ) {
    if (err) {
      console.error(err);
    } else {
      console.log("successfully move index.html from /static to /templates");
    }
  });
}
