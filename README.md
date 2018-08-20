详情见《文档.docx》

功能

	分布式事务的tcc开源方案

	支持嵌套事务(nested transaction support)

	采用disruptor框架进行事务日志的异步读写，与rpc框架的性能毫无差别

	支持springboot-starter项目启动，使用简单

	rpc框架支持:dubbo,springcloud

	本地事务存储支持:redis,mongodb,zookeeper,file,mysql

	事务日志序列化支持:java，hessian，kryo，protostuff

	采用aspect aop切面思想与spring无缝集成，支持集群

	分布式事务场景demo工程，并有swagger-ui可视化界面可以快速体验

笔记

tcc try confirm cancel

aop：理解了aop 就理解tcc 就理解了分布式事务的实现

invoker去调用的 路由，集群，负载 filter

dubbo filter接口 在服务发起调用前走的，那么就可以做很多事情，就可以保存方法的所有的信息，方法签名，接口

要点：

1，aop根据添加的注解进行aop处理

2，rpc框架的特性，在调用前，会执行filter接口，会获取所调用的方法签名

3，threadlocal保存事务上下文，rpc框架的参数传递

4，事务日志，到底存储了什么东西？是怎么存储的？是怎么序列化的。

5，高效并发 jmeter压测

6，学一个框架开源，关键要学习里面代码的风格，设计模式，新的知识点

tcc的优缺点：

缺点：代码量多，使用中必须要知道confirm和cancel方法的正确书写。使用场景有限。需要记录事务日志：推荐使用高效的存储，推荐使用mongo集群 kroy序列化

优点：天然支持集群，不依赖事务，依赖事务日志，最终一致性
