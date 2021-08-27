### 一个基于netty框架的web服务器
&emsp;&emsp;这是一个基于netty框架的web服务器，基本功能类似于tomcat服务器，可以实现静态资源的访问以及通过ServiceHandler实现Controller功能进行业务代码模块的调用并返回结果。<br>
&emsp;&emsp;目前项目还处于早期demo阶段，通过测验可以实现的功能有请求html静态页面，通过ServiceHandler实现用HttpResponse返回字符串并显示在浏览器上。<br>
&emsp;&emsp;支持单例模式启动类、属性注入等IOC特性<br>
&emsp;&emsp;支持项目打包成jar包进行部署<br>
&emsp;&emsp;项目依赖于netty 5.0.0.Alpha2版本，IDE使用的是idea。<br>
