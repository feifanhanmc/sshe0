# sshe0
Java Web教务管理系统搭建
一、	系统说明
实现用户管理、权限控制、成绩录入、成绩查询以及数据以Excel形式导出。
二、	选用框架
Struts2+Spring3+Hibernate4+Maven+EasyUI框架
三、	软件安装
1、	JDK/JRE
目前使用的是MyEclipse自带的JRE System Library【Java SE 1.6】
2、	服务器软件
目前使用的是MyEclipse自带的MyEclipse Tomcat 6
3、	IDE
MyEclipse 10.6
4、	包管理工具
Maven 3.3.9
5、	数据库
Oracle 11.2.0 及其辅助工具PLSQL Developer
6、	版本控制工具
Git
四、	技术选型
1、	Struts 2.3
2、	Spring 3.1
3、	Hibernate 4.1
4、	EasyUI 1.3
5、	Maven 3.3
五、	框架搭建
1、	MyEclipse连接Oracle数据库
在成功安装配置完Oracle和PLSQL Developer后，首先在MyEclipse建立连接。并且做了一个别无它用的Web Project，连接数据库，使用MyEclipse自动生成hbm.xml和类或者Annotation类。
2、	搭建Spring 3
使用Annotation，取代了以往的手动配置bean操作，测试Spring配置成功与否。
3、	搭建Struts 2
实际上是整合Struts 2和作为SSH框架的胶水Spring 3，使用注解完成mapping，编写Action类，使用测试类测试二者是否整合成功。成功后配置到Web上，并在浏览器中进行测试。
4、	搭建Hibernate 4
使用之前的数据库连接配置连接数据库，自动生成数据库Table对应的类文件，并编写DAO。整合Hibernate 4和Spring 3，并使用测试类测试@Autowired自动注入以及数据库操作是否成功。成功后配置到Web上，并在浏览器中进行测试。
5、	搭建EasyUI
编写JSP文件，并处理到Action类的跳转，框架搭建完成。
六、	系统开发
1、	数据库设计
1.1、	设计
详情见附件：数据库.txt
1.2、	反向生成
根据数据库表在MyEclipse中反向生成JAVA类文件，注意类文件中@Entity以及@Id的设置。
2、	前端开发
2.1、	逻辑设计
首先登陆界面会根据登陆账户的信息，进行权限控制。其通过界面左边的Tree来实现不同类型用户只能打开不同的页面。部分截图见：截图。主要操作步骤见：步骤记录。权限控制如下。
		 		/*
				 * 成绩管理 |
				 * 		    |成绩查询	student
		 		 * 教务管理|
				 * 		   |课程管理		admin
				 *         |成绩录入		teacher
				 * 系统管理|
				 *         |角色管理		admin
*/
2.2、	结合后台
编写Jsp文件，并指明需要跳转提交到后台.action类中的文件。数据参数传递通过相关类继承ModelDriven实现。
七、	功能说明
 
八、	结束语
1、模型驱动ModelDriven的使用
2、Memo见附件：Memo
3、参考资料：链接：http://pan.baidu.com/s/1jH8DwFG 密码：nkwm
4、项目Github地址： https://github.com/feifanhanmc/sshe0
5、联系邮箱：feifanhanmc@163.com
