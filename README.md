# 校园电商项目

***
更新日志:<br>
(由于项目重启过，所以本更新日志前面有部分并不存在于github的commit记录)<br><br>

**2018.11.22** <br>
对商品修改功能的Controller层测试，已经可以通过浏览器成功更新商品数据<br>
完成商品列表查询的Dao层，并单元测试成功<br>
完成商品列表查询的Service层<br>
完成商品列表查询的Controller层，tomcat测试成功<br>
<br>

**2018.11.21** <br>
完成通过productId商品查询功能、商品更新功能、删除商品详情图功能的Dao层，并单元测试成功<br>
完成通过productId商品更新功能(包括商品查询、删除图片、更新功能)的Service层，并单元测试成功<br>
完成商品查询功能的Controller层，经过tomcat测试成功<br>
完成商品修改功能的Controller层，还未测试<br>
<br>

**2018.11.14** <br>
完成商品插入、商品图片插入的Controller层<br>
完成商品新建的前端HTML和JS并进行测试和debug，可以成功新建商品<br>
PS：本次bug是因为图片文件流的getName()方法无法获取到后面的后缀名，导致ImageUtil里方法出现String下标溢出问题(de了2小时bug..)要使用getOriginalFilename()方法才可以<br>
<br>

**2018.11.8** <br>
完成商品插入、商品图片插入的Dao层，并单元测试成功<br>
完成商品插入、商品图片插入的Service层，并单元测试成功<br>
其中ImageUtil工具类添加了生成正常图片水印的方法，Dto层添加了封装类ImageHolder来简化代码。Service层已经可以正常完成商品的缩略图和详情图片的添加<br>
<br>

**2018.11.7** <br>
编写商品类别查询Controller层,编写商品类别html、js、css文件，进行测试成功实现商品类别查询功能<br>
编写批量增加商品类别的Dao层、Service层、Controller层代码。单元测试完毕<br>
编写批量增加商品类别的html、js文件，修复小bug，新增商品类别测试成功<br>
PS：本次bug为js中的一个字符串打错<br>
编写删除商品类别Dao层，并进行单元测试，修复bug<br>
PS：要记住String用.equal()判断相等<br>
编写完成删除商品类别Service层和Controller层<br>
编写完成删除商品类别的js文件，经测试成功实现商品类别的删除操作<br>
<br>

**2018.11.5** <br>
店铺信息展示的Service层完成单元测试<br>
完成店铺信息展示的Controller层代码，附加登录验证页面跳转代码<br>
完成店铺信息展示的前端开发，经过测试成功实现，验证Controller层有效<br>
实现店铺管理页面的前端开发，完善店铺信息展示的前端开发。浏览器可正常访问，并进行基本权限认证<br>
编写商品类别查询的Dao层和Service层，Controller层进行到一半<br>
<br>

**2018.11.4** <br>
实现店铺信息编辑的前端js，修复严重bug，功能测试成功<br>
实现店铺信息展示的Dao层（查询店铺），完成单元测试并debug。<br>
PS：此次bug是店铺名称的模糊查询中like语句后面的字符串格式问题<br>
实现店铺信息展示的Service层,还未进行单元测试<br>
<br>

**2018.11.1** <br>
实现店铺信息编辑的Dao层和Service层，ImageUtil工具类添加删除图片功能。完成单元测试<br>
实现店铺信息编辑的Controller层,完善店铺注册Controller层（使用session获取用户信息）<br>
实现店铺信息编辑的前端js，初步完成功能<br>
<br>

**2018.10.28** <br>
修复上次更新的Controller层店铺注册功能bug，添加验证码功能。<br>
部署到tomcat进行测试，发现缩略图没有生成（单元测试是成功的）<br>
然后花了大把时间找问题，发现是tomcat部署路径的文件夹有“.”符号，导致路径读取有问题。。。我。。<br>
最终修复bug，测试成功<br>
店铺信息编辑功能开了个头
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
可以通过Service层注册店铺，实现保存缩略图（加水印）和写入数据库。
<br><br>

**2018.10.23** <br>
Dao层实现新增店铺、更新店铺功能,并进行单元测试
<br><br>

**2018.10.18** <br>
单元测试验证dao、service、controller配置。配置logback日志并进行配置。<br>
随后因为改动项目maven发生崩溃，没有及时找到解决办法，于是重开项目
<br><br>

**2018.10.17**<br> 
完成各层ssm配置
<br><br>

**2018.10.15**<br> 
实体类创建
<br><br>
