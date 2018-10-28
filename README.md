# 校园电商项目

***
更新日志:<br>
(由于项目重启过，所以本更新日志前面有部分并不存在于github的commit记录)<br><br>

**2018.10.28** <br>
修复上次更新的Controller层店铺注册功能bug，添加验证码功能。<br>
部署到tomcat进行测试，发现缩略图没有生成（单元测试是成功的）<br>
然后花了大把时间找问题，发现是tomcat部署路径的文件夹有“.”符号，导致路径读取有问题。。。我。。<br>
最终修复bug，测试成功。
<br><br>

**2018.10.26** <br>
完善Controller层店铺注册功能，经过debug和测试，店铺注册信息的html已经可以获取店铺区域、店铺分类信息。
<br><br>

**2018.10.25** <br>
实现Controller层店铺注册功能，优化生成缩略图的方法<br>
实现店铺注册信息的html界面<br>
用js实现后台获取店铺区域、店铺分类信息，并获取表单
<br><br>

**2018.10.24** <br>
实现图片缩略图工具类，实现文件路径工具类,并测试<br>
实现店铺dto层，实现店铺注册的Service层并单元测试<br>
可以通过Service层注册店铺，实现保存缩略图（加水印）和写入数据库。<br><br>

**2018.10.23** <br>
Dao层实现新增店铺、更新店铺功能,并进行单元测试<br><br>

**2018.10.18** <br>
单元测试验证dao、service、controller配置。配置logback日志并进行配置。<br>
随后因为改动项目maven发生崩溃，没有及时找到解决办法，于是重开项目<br><br>

**2018.10.17**<br> 
完成各层ssm配置<br><br>

**2018.10.15**<br> 
实体类创建<br><br>
