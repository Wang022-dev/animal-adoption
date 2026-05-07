const menu = {
    list() {
        return [
            {
                "backMenu": [
                    {
                        "menu": "系统用户管理",
                        "child": [
                            {"menu": "管理员管理", "menuJump": "列表", "tableName": "users", "buttons": ["查看", "新增", "修改", "删除"]},
                            {"menu": "普通用户管理", "menuJump": "列表", "tableName": "yonghu", "buttons": ["查看", "新增", "修改", "删除"]},
                            {"menu": "志愿者管理", "menuJump": "列表", "tableName": "ziyuanzhe", "buttons": ["查看", "新增", "修改", "删除"]}
                        ]
                    },
                    {
                        "menu": "流浪动物救助闭环",
                        "child": [
                            {"menu": "流浪动物申报审核", "menuJump": "列表", "tableName": "chongwujiyang", "buttons": ["查看", "审核", "删除"]},
                            {"menu": "动物档案管理", "menuJump": "列表", "tableName": "chongwu", "buttons": ["查看", "新增", "修改", "删除"]},
                            {"menu": "领养信息管理", "menuJump": "列表", "tableName": "chongwulingyang", "buttons": ["查看", "新增", "修改", "删除"]},
                            {"menu": "领养申请审核", "menuJump": "列表", "tableName": "chongwulingyangshenhe", "buttons": ["查看", "审核", "删除"]}
                        ]
                    },
                    {
                        "menu": "互动内容管理",
                        "child": [
                            {"menu": "动物留言管理", "menuJump": "列表", "tableName": "chongwuLiuyan", "buttons": ["查看", "修改", "删除"]},
                            {"menu": "动物收藏管理", "menuJump": "列表", "tableName": "chongwuCollection", "buttons": ["查看", "删除"]},
                            {"menu": "论坛管理", "menuJump": "列表", "tableName": "forum", "buttons": ["查看", "新增", "修改", "删除"]},
                            {"menu": "公告信息管理", "menuJump": "列表", "tableName": "news", "buttons": ["查看", "新增", "修改", "删除"]}
                        ]
                    },
                    {
                        "menu": "基础数据管理",
                        "child": [
                            {"menu": "动物类型管理", "menuJump": "列表", "tableName": "dictionaryChongwu", "buttons": ["查看", "新增", "修改", "删除"]},
                            {"menu": "公告类型管理", "menuJump": "列表", "tableName": "dictionaryNews", "buttons": ["查看", "新增", "修改", "删除"]},
                            {"menu": "轮播图管理", "menuJump": "列表", "tableName": "config", "buttons": ["查看", "新增", "修改", "删除"]}
                        ]
                    }
                ],
                "frontMenu": [],
                "hasBackLogin": "是",
                "hasBackRegister": "否",
                "hasFrontLogin": "否",
                "hasFrontRegister": "否",
                "roleName": "管理员",
                "tableName": "users"
            },
            {
                "backMenu": [
                    {
                        "menu": "救助工作台",
                        "child": [
                            {"menu": "流浪动物申报审核", "menuJump": "列表", "tableName": "chongwujiyang", "buttons": ["查看", "审核"]},
                            {"menu": "动物档案查看", "menuJump": "列表", "tableName": "chongwu", "buttons": ["查看"]},
                            {"menu": "领养信息查看", "menuJump": "列表", "tableName": "chongwulingyang", "buttons": ["查看"]},
                            {"menu": "领养申请审核", "menuJump": "列表", "tableName": "chongwulingyangshenhe", "buttons": ["查看", "审核"]}
                        ]
                    },
                    {
                        "menu": "公共信息",
                        "child": [
                            {"menu": "论坛管理", "menuJump": "列表", "tableName": "forum", "buttons": ["查看", "新增", "删除"]},
                            {"menu": "公告信息查看", "menuJump": "列表", "tableName": "news", "buttons": ["查看"]}
                        ]
                    }
                ],
                "frontMenu": [],
                "hasBackLogin": "是",
                "hasBackRegister": "否",
                "hasFrontLogin": "否",
                "hasFrontRegister": "否",
                "roleName": "自愿者",
                "tableName": "ziyuanzhe"
            },
            {
                "backMenu": [
                    {
                        "menu": "我的救助与领养",
                        "child": [
                            {"menu": "申报流浪动物", "menuJump": "列表", "tableName": "chongwujiyang", "buttons": ["查看", "新增", "删除"]},
                            {"menu": "可领养动物", "menuJump": "列表", "tableName": "chongwulingyang", "buttons": ["查看"]},
                            {"menu": "我的领养申请", "menuJump": "列表", "tableName": "chongwulingyangshenhe", "buttons": ["查看", "新增", "删除"]}
                        ]
                    },
                    {
                        "menu": "互动社区",
                        "child": [
                            {"menu": "动物档案浏览", "menuJump": "列表", "tableName": "chongwu", "buttons": ["查看"]},
                            {"menu": "动物留言管理", "menuJump": "列表", "tableName": "chongwuLiuyan", "buttons": ["查看", "新增", "删除"]},
                            {"menu": "动物收藏管理", "menuJump": "列表", "tableName": "chongwuCollection", "buttons": ["查看", "新增", "删除"]},
                            {"menu": "论坛交流", "menuJump": "列表", "tableName": "forum", "buttons": ["查看", "新增", "删除"]},
                            {"menu": "公告信息查看", "menuJump": "列表", "tableName": "news", "buttons": ["查看"]}
                        ]
                    }
                ],
                "frontMenu": [],
                "hasBackLogin": "是",
                "hasBackRegister": "否",
                "hasFrontLogin": "否",
                "hasFrontRegister": "否",
                "roleName": "用户",
                "tableName": "yonghu"
            }
        ]
    }
}
export default menu;
