const base = {
    get() {
        const origin = window.location.origin;
        const projectPath = '/liulangdongwubeihua';
        return {
            url : `${origin}${projectPath}/`,
            name: "liulangdongwubeihua",
            // 退出到首页链接
            indexUrl: `${origin}${projectPath}/front/index.html`
        };
    },
    getProjectName(){
        return {
            projectName: "流浪动物管理系统"
        } 
    }
}
export default base
