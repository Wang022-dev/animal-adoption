(function(){
  var origin = window.location.origin;
  var projectPath = '/liulangdongwubeihua';
  var base = {
    get: function() {
      return {
        url: origin + projectPath + '/',
        name: 'liulangdongwubeihua',
        indexUrl: origin + projectPath + '/front/index.html'
      };
    },
    getProjectName: function() {
      return { projectName: '流浪动物管理系统' };
    }
  };
  var s = document.createElement('script');
  s.src = './app.350f6d46.js';
  s.onload = function(){
    try {
      if (window.__webpack_require__) {
        var modules = window.__webpack_require__.m || {};
        Object.keys(modules).forEach(function(k){
          var fn = modules[k];
          var src = String(fn);
          if (src.indexOf('http://localhost:8080/liulangdongwubeihua/') !== -1 || src.indexOf('http://localhost:8080/liulangdongwubeihua/front/index.html') !== -1) {
            modules[k] = function(module, exports, __webpack_require__){
              module.exports = base;
            };
          }
        });
      }
    } catch (e) { console.error(e); }
  };
})();
