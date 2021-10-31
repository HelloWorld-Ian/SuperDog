## 一个基于netty框架的web服务器
### 1.项目基本介绍
&emsp;&emsp;这是一个基于netty框架的web服务器，基本功能类似于tomcat服务器， 例如请求静态资源或者请求动态资源，实现客户端与服务器的websocket连接。<br>
&emsp;&emsp;支持单例模式启动类、属性注入等IOC特性<br>
&emsp;&emsp;支持项目打包成jar包进行部署<br>
&emsp;&emsp;项目依赖于netty 5.0.0.Alpha2版本<br>

### 2.项目文件结构
&emsp;&emsp;项目源码主要分为两个部分。<br>
&emsp;&emsp;一个是核心代码部分，在文件夹Core中。<br>
&emsp;&emsp;另一个是服务器框架的使用示例，主要包括静态资源请求示例、动态资源请求示例、websocket连接示例，在文件夹sample中。
