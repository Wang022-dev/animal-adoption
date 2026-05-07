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
  window.base = base;
})();
