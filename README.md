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
即可生成最终的部署文件

# 后台如何部署
整个项目导入idea，表文件导入mysql，修改配置文件里的jdbc连接，填写七牛的key及bucket，即可运行项目。       
想要部署的话直接mvn install即可生成可部署的jar包。

# 最后
## 如何丢入生产环境
### 后台部分
配好jdk1.8，mysql，导入表，安装nginx，将conf替换为本项目目录下的nginx.conf（记得地址改成自己的服务器）。
运行mvn install生成的.jar文件。
### 前端部分
直接将dist文件丢进nginx的根目录即可。
## 演示站点
<a href="http://print.heartqiu.cn">请点这里</a>                    
ps：天知道这个服务能运行多久，点进来后看不到很正常。                      
2019年3月15日15:40:10          

