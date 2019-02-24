# 校园电商项目

***
更新日志:<br>
(由于项目重启过，所以本更新日志前面有部分并不存在于github的commit记录)<br><br>

**2019.2.24** <br>
完成商品详情页的代码编写，并测试成功，可以正常访问<br>
修复bug：<br>
1.店铺详情页中，在使用商品名称搜索商品时，会出现同种商品出现多次的情况<br>
2.改变查询条件时，不再触发滚动加载<br>
3.商品详情页的详情图全部为缩略图<br>
解决方案：<br>
1.将搜索栏触发刷新的机制从input改为的change，因为input刷新过于快速，而导致未清空原有商品列表就渲染新的商品列表了<br>
2.原本滚动标识符是在加载完后删除，所以改变查询条件后，滚动标识符没了，现在改为hide，改变条件时show，从而避免此bug<br>
3.原因为底层dao配置中，map写错，与缩略图的字段名一样，导致只select到了缩略图的图片地址<br>
<br>

**2019.2.23** <br>
放了个假期。。<br>
完成店铺详情页的前端设计，可以正常打开并加载店铺信息和商品信息<br>
过程中debug2处：<br>
1.在Controller层url的英文字母少打了一个导致浏览器访问时后台数据无法取得<br>
2.同样在Controller层，在获取店铺信息成功时，忘记编写代码返回success=ture 导致js代码没有得到success，最终无法完成新标签的拼接从而无法渲染<br>
<br>

**2018.12.6** <br>
完成店铺列表页（首页店铺一级分类，店铺二级分类）的前端实现，页面可以正常按照搜索条件获取店铺列表<br>
修复2个店铺列表页bug：<br>
1.在全部商店页中，选择店铺分类后选择区域，将会使选择的分类无效<br>
2.修复如上bug后，在指定店铺分类页中（如：咖啡奶茶）,点击二级店铺分类无效<br>
处理方法：<br>
1.原本在shoplist.js中想要将两种店铺分类页合并，所以在每次查询完时将店铺分类的parentId清空，导致失效，所以清掉清空parentId的代码和判断parentId是否为空的代码<br>
2.少掉了清空和判断后，程序无法识别这是二级店铺分类，所以在shoplist.js代码中添加标示符ischild，来分别判断一级分类和二级分类<br>
完成店铺详情页的Controller层代码编写<br>
<br>

**2018.12.5** <br>
完成店铺列表页（首页店铺一级分类，店铺二级分类）的后端实现，在浏览器已经可以获取到正确的JSON<br>
<br>

**2018.12.3** <br>
完成头条查询功能的Dao层并单元测试成功<br>
修改店铺类别查询功能（增加一级类别查询）的Dao层并单元测试成功<br>
完成头条查询功能的Service层<br>
完成头条查询功能的Controller层并测试成功,可以获取到头条列表和店铺类别列表的json对象<br>
完成首页前端页面的编写，已经有头条滚动、店铺分类显示<br>
修改tomcat配置使其能辨别数据库中url定位到根目录<br>
<br>

**2018.11.22** <br>
对商品修改功能的Controller层测试，已经可以通过浏览器成功更新商品数据<br>
完成商品列表查询的Dao层，并单元测试成功<br>
完成商品列表查询的Service层<br>
完成商品列表查询的Controller层，tomcat测试成功<br>
完成商品列表查询的前端测试，可以在浏览器正常访问商品列表并可以进行上/下架操作和编辑操作<br>
添加在删除商品分类前将正在使用该商品分类的商品解除关联的功能<br>
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
