# 给女朋友写的一个打印平台的后台
使用到的技术：        
1. springboot
2. mysql
你可以快乐的拿去当期末综合设计，也可稍做修改投入生产环境。
# 整体实现的功能
登陆注册，申请成为商家，查看商家列表，商家介绍，下单等。（就是个普通的毕设啦）
# 前端部分
https://github.com/SkyZhan/printVue           
女朋友写的前端，贼菜，推荐重写。                
开发的话：                  
修改config里的globalUrl，npm run dev。              
部署的话：                        
npm run build                
配置好nginx，将dist文件夹下的文件丢到根目录即可。
# 后台如何部署
整个项目导入idea，表文件导入mysql，修改配置文件里的jdbc连接，填写七牛的key及bucket，即可运行项目。       
想要部署的话直接mvn install即可生成可部署的jar包。
# 最后
没有最后了
