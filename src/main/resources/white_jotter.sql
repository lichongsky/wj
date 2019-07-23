/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : white_jotter

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-07-23 11:27:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for artical
-- ----------------------------
DROP TABLE IF EXISTS `artical`;
CREATE TABLE `artical` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `mdContent` text,
  `htmlContent` text,
  `summary` text,
  `cid` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `publishDate` datetime DEFAULT NULL,
  `editDate` datetime DEFAULT NULL,
  `state` int(11) DEFAULT '1' COMMENT '文章状态 启用1，关闭0',
  `pageView` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cid` (`cid`) USING BTREE,
  KEY `fk_uid` (`uid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of artical
-- ----------------------------
INSERT INTO `artical` VALUES ('1', 'Linux中安装zookeeper', 'watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)', '<p>最近打算出一个系列，介绍Dubbo的使用。</p>\n<hr />\n<p>分布式应用现在已经越来越广泛，Spring Could也是一个不错的一站式解决方案，不过据我了解国内目前貌似使用阿里Dubbo的公司比较多，一方面这个框架也确实很OK，另一方面可能也是因为Dubbo的中文文档比较全的缘故，据Dubbo官网上的消息，阿里已经重新开始了对Dubbo的维护，这也算是使用Dubbo的互联网公司的福音了吧。OK，废话不多说，今天我们就先来看看如何在Linux上安装zookeeper。</p>\n<hr />\n<p>了解过Dubbo的小伙伴都知道，Dubbo官方建议我们使用的注册中心就是zookeeper，zookeeper本来是Hadoop的一个子项目，现在发展成了Apache的顶级项目，看名字就知道Zookeeper就是动物园管理员，管理Hadoop(大象)、Hive(蜂房/蜜蜂)等动物。Apache上的Zookeeper分Linux版和Windows版，但是考虑到实际生产环境都是Linux，所以我们这里主要介绍Linux上Zookeeper的安装，Windows上Zookeeper的安装则比较简单，下载解压即可，和Tomcat差不多。</p>\n<p>OK，废话不多说，接下来我们就来看看zookeeper的安装步骤。</p>\n<hr />\n<p>环境：</p>\n<blockquote>\n<p>1.VMware® Workstation 12 Pro<br />\n2.CentOS7<br />\n3.zookeeper-3.4.10(本文写作时的最新稳定版)</p>\n</blockquote>\n<hr />\n<h1>安装步骤</h1>\n<p>1.下载zookeeper</p>\n<p>zookeeper下载地址如下，小伙伴们可以在第一个地址中选择适合自己的zookeeper版本，也可以直接点击第二个地址下载我们本文使用的zookeeper。</p>\n<blockquote>\n<p>1.<a href=\"http://mirrors.hust.edu.cn/apache/zookeeper/\" target=\"_blank\">http://mirrors.hust.edu.cn/apache/zookeeper/</a></p>\n</blockquote>\n<blockquote>\n<p>2.<a href=\"http://mirrors.hust.edu.cn/apache/zookeeper/zookeeper-3.4.10/zookeeper-3.4.10.tar.gz\" target=\"_blank\">http://mirrors.hust.edu.cn/apache/zookeeper/zookeeper-3.4.10/zookeeper-3.4.10.tar.gz</a></p>\n</blockquote>\n<p>2.将下载好的zookeeper上传到Linux服务器上</p>\n<p>上传方式多种多样，我这里采用了xftp，小伙伴们也可以直接使用putty上传，上传结果如下：<br />\n<img src=\"http://img.blog.csdn.net/20170825114622362?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>3.将文件解压到/opt目录下</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115122378?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>4.进入到刚刚解压好的目录中，创建两个文件夹，分别是data和logs，如下：</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115324970?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>5.将解压后zookeeper-3.4.10文件夹下的zoo_sample.cfg文件拷贝一份命名为zoo.cfg，如下：</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115426251?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>6.修改zoo.cfg文件，添加data和log目录，如下：</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115527367?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<blockquote>\n<p>1.2888 端口号是zookeeper服务之间通信的端口<br />\n2.3888 是zookeeper 与其他应用程序通信的端口<br />\n3.initLimit：这个配置项是用来配置 Zookeeper 接受客户端（这里所说的客户端不是用户连接 Zookeeper服务器的客户端，而是 Zookeeper 服务器集群中连接到 Leader 的 Follower 服务器）初始化连接时最长能忍受多少个心跳时间间隔数。当已经超过 10 个心跳的时间（也就是 tickTime）长度后 Zookeeper 服务器还没有收到客户端的返回信息，那么表明这个客户端连接失败。总的时间长度就是 5<em>2000=10 秒。<br />\n4.syncLimit：这个配置项标识 Leader 与 Follower 之间发送消息，请求和应答时间长度，最长不能超过多少个 tickTime 的时间长度，总的时间长度就是 2</em>2000=4 秒<br />\n5.server.A=B:C:D：其中 A 是一个数字，表示这个是第几号服务器；B 是这个服务器的IP地址或/etc/hosts文件中映射了IP的主机名；C 表示的是这个服务器与集群中的 Leader 服务器交换信息的端口；D 表示的是万一集群中的 Leader 服务器挂了，需要一个端口来重新进行选举，选出一个新的 Leader，而这个端口就是用来执行选举时服务器相互通信的端口。如果是伪集群的配置方式，由于 B 都是一样，所以不同的 Zookeeper 实例通信端口号不能一样，所以要给它们分配不同的端口号。</p>\n</blockquote>\n<p>7.在 dataDir=/opt/zookeeper-3.4.10/data下创建 myid文件 编辑myid文件，并在对应的IP的机器上输入对应的编号。如在zookeeper上，myid文件内容就是1。如果只在单点上进行安装配置，那么只有一个server.1。如下：</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115647920?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>8.在.bash_profile文件中增加zookeeper配置：</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115729473?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>9.使配置生效</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115807787?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>10.关闭防火墙</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115848488?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>11.启动并测试</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115938795?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<blockquote>\n<p>启动之后如果能看到Mode:standalone就表示启动成功了。</p>\n</blockquote>\n<p>12.关闭zookeeper</p>\n<p><img src=\"http://img.blog.csdn.net/20170825121021364?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>13.配置开机启动zookeeper</p>\n<p><img src=\"http://img.blog.csdn.net/20170825121059827?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<blockquote>\n<p><strong>注意注意</strong> 在centos7中，/etc/rc.local的权限被降低了，所以需要执行如下命令赋予其可执行权限<br />\n<code>chmod +x /etc/rc.d/rc.local</code></p>\n</blockquote>\n<p>OK,以上就是我们在CentOS7中安装zookeeper的全过程，做好这一切之后我们就可以在Dubbo中使用这个注册中心了，这个我们放在下一篇博客中介绍。</p>\n<p>更多JavaEE资料请关注公众号：</p>\n<p><img src=\"http://img.blog.csdn.net/20170823174820001?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"\" /></p>\n', '最近打算出一个系列，介绍Dubbo的使用。\n\n分布式应用现在已经越来越广泛，Spring Could', '1', '1', '2017-12-24 10:05:20', '2017-12-24 10:05:20', '1', '1');
INSERT INTO `artical` VALUES ('2', 'Linux中安装zookeeper', 'watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)', '<p>最近打算出一个系列，介绍Dubbo的使用。</p>\n<hr />\n<p>分布式应用现在已经越来越广泛，Spring Could也是一个不错的一站式解决方案，不过据我了解国内目前貌似使用阿里Dubbo的公司比较多，一方面这个框架也确实很OK，另一方面可能也是因为Dubbo的中文文档比较全的缘故，据Dubbo官网上的消息，阿里已经重新开始了对Dubbo的维护，这也算是使用Dubbo的互联网公司的福音了吧。OK，废话不多说，今天我们就先来看看如何在Linux上安装zookeeper。</p>\n<hr />\n<p>了解过Dubbo的小伙伴都知道，Dubbo官方建议我们使用的注册中心就是zookeeper，zookeeper本来是Hadoop的一个子项目，现在发展成了Apache的顶级项目，看名字就知道Zookeeper就是动物园管理员，管理Hadoop(大象)、Hive(蜂房/蜜蜂)等动物。Apache上的Zookeeper分Linux版和Windows版，但是考虑到实际生产环境都是Linux，所以我们这里主要介绍Linux上Zookeeper的安装，Windows上Zookeeper的安装则比较简单，下载解压即可，和Tomcat差不多。</p>\n<p>OK，废话不多说，接下来我们就来看看zookeeper的安装步骤。</p>\n<hr />\n<p>环境：</p>\n<blockquote>\n<p>1.VMware® Workstation 12 Pro<br />\n2.CentOS7<br />\n3.zookeeper-3.4.10(本文写作时的最新稳定版)</p>\n</blockquote>\n<hr />\n<h1>安装步骤</h1>\n<p>1.下载zookeeper</p>\n<p>zookeeper下载地址如下，小伙伴们可以在第一个地址中选择适合自己的zookeeper版本，也可以直接点击第二个地址下载我们本文使用的zookeeper。</p>\n<blockquote>\n<p>1.<a href=\"http://mirrors.hust.edu.cn/apache/zookeeper/\" target=\"_blank\">http://mirrors.hust.edu.cn/apache/zookeeper/</a></p>\n</blockquote>\n<blockquote>\n<p>2.<a href=\"http://mirrors.hust.edu.cn/apache/zookeeper/zookeeper-3.4.10/zookeeper-3.4.10.tar.gz\" target=\"_blank\">http://mirrors.hust.edu.cn/apache/zookeeper/zookeeper-3.4.10/zookeeper-3.4.10.tar.gz</a></p>\n</blockquote>\n<p>2.将下载好的zookeeper上传到Linux服务器上</p>\n<p>上传方式多种多样，我这里采用了xftp，小伙伴们也可以直接使用putty上传，上传结果如下：<br />\n<img src=\"http://img.blog.csdn.net/20170825114622362?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>3.将文件解压到/opt目录下</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115122378?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>4.进入到刚刚解压好的目录中，创建两个文件夹，分别是data和logs，如下：</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115324970?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>5.将解压后zookeeper-3.4.10文件夹下的zoo_sample.cfg文件拷贝一份命名为zoo.cfg，如下：</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115426251?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>6.修改zoo.cfg文件，添加data和log目录，如下：</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115527367?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<blockquote>\n<p>1.2888 端口号是zookeeper服务之间通信的端口<br />\n2.3888 是zookeeper 与其他应用程序通信的端口<br />\n3.initLimit：这个配置项是用来配置 Zookeeper 接受客户端（这里所说的客户端不是用户连接 Zookeeper服务器的客户端，而是 Zookeeper 服务器集群中连接到 Leader 的 Follower 服务器）初始化连接时最长能忍受多少个心跳时间间隔数。当已经超过 10 个心跳的时间（也就是 tickTime）长度后 Zookeeper 服务器还没有收到客户端的返回信息，那么表明这个客户端连接失败。总的时间长度就是 5<em>2000=10 秒。<br />\n4.syncLimit：这个配置项标识 Leader 与 Follower 之间发送消息，请求和应答时间长度，最长不能超过多少个 tickTime 的时间长度，总的时间长度就是 2</em>2000=4 秒<br />\n5.server.A=B:C:D：其中 A 是一个数字，表示这个是第几号服务器；B 是这个服务器的IP地址或/etc/hosts文件中映射了IP的主机名；C 表示的是这个服务器与集群中的 Leader 服务器交换信息的端口；D 表示的是万一集群中的 Leader 服务器挂了，需要一个端口来重新进行选举，选出一个新的 Leader，而这个端口就是用来执行选举时服务器相互通信的端口。如果是伪集群的配置方式，由于 B 都是一样，所以不同的 Zookeeper 实例通信端口号不能一样，所以要给它们分配不同的端口号。</p>\n</blockquote>\n<p>7.在 dataDir=/opt/zookeeper-3.4.10/data下创建 myid文件 编辑myid文件，并在对应的IP的机器上输入对应的编号。如在zookeeper上，myid文件内容就是1。如果只在单点上进行安装配置，那么只有一个server.1。如下：</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115647920?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>8.在.bash_profile文件中增加zookeeper配置：</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115729473?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>9.使配置生效</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115807787?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>10.关闭防火墙</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115848488?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>11.启动并测试</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115938795?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<blockquote>\n<p>启动之后如果能看到Mode:standalone就表示启动成功了。</p>\n</blockquote>\n<p>12.关闭zookeeper</p>\n<p><img src=\"http://img.blog.csdn.net/20170825121021364?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>13.配置开机启动zookeeper</p>\n<p><img src=\"http://img.blog.csdn.net/20170825121059827?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<blockquote>\n<p><strong>注意注意</strong> 在centos7中，/etc/rc.local的权限被降低了，所以需要执行如下命令赋予其可执行权限<br />\n<code>chmod +x /etc/rc.d/rc.local</code></p>\n</blockquote>\n<p>OK,以上就是我们在CentOS7中安装zookeeper的全过程，做好这一切之后我们就可以在Dubbo中使用这个注册中心了，这个我们放在下一篇博客中介绍。</p>\n<p>更多JavaEE资料请关注公众号：</p>\n<p><img src=\"http://img.blog.csdn.net/20170823174820001?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"\" /></p>\n', '最近打算出一个系列，介绍Dubbo的使用。\n\n分布式应用现在已经越来越广泛，Spring Could', '2', '1', '2017-12-24 10:05:20', '2017-12-24 10:05:20', '1', '1');
INSERT INTO `artical` VALUES ('3', 'Linux中安装zookeeper', 'watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)', '<p>最近打算出一个系列，介绍Dubbo的使用。</p>\n<hr />\n<p>分布式应用现在已经越来越广泛，Spring Could也是一个不错的一站式解决方案，不过据我了解国内目前貌似使用阿里Dubbo的公司比较多，一方面这个框架也确实很OK，另一方面可能也是因为Dubbo的中文文档比较全的缘故，据Dubbo官网上的消息，阿里已经重新开始了对Dubbo的维护，这也算是使用Dubbo的互联网公司的福音了吧。OK，废话不多说，今天我们就先来看看如何在Linux上安装zookeeper。</p>\n<hr />\n<p>了解过Dubbo的小伙伴都知道，Dubbo官方建议我们使用的注册中心就是zookeeper，zookeeper本来是Hadoop的一个子项目，现在发展成了Apache的顶级项目，看名字就知道Zookeeper就是动物园管理员，管理Hadoop(大象)、Hive(蜂房/蜜蜂)等动物。Apache上的Zookeeper分Linux版和Windows版，但是考虑到实际生产环境都是Linux，所以我们这里主要介绍Linux上Zookeeper的安装，Windows上Zookeeper的安装则比较简单，下载解压即可，和Tomcat差不多。</p>\n<p>OK，废话不多说，接下来我们就来看看zookeeper的安装步骤。</p>\n<hr />\n<p>环境：</p>\n<blockquote>\n<p>1.VMware® Workstation 12 Pro<br />\n2.CentOS7<br />\n3.zookeeper-3.4.10(本文写作时的最新稳定版)</p>\n</blockquote>\n<hr />\n<h1>安装步骤</h1>\n<p>1.下载zookeeper</p>\n<p>zookeeper下载地址如下，小伙伴们可以在第一个地址中选择适合自己的zookeeper版本，也可以直接点击第二个地址下载我们本文使用的zookeeper。</p>\n<blockquote>\n<p>1.<a href=\"http://mirrors.hust.edu.cn/apache/zookeeper/\" target=\"_blank\">http://mirrors.hust.edu.cn/apache/zookeeper/</a></p>\n</blockquote>\n<blockquote>\n<p>2.<a href=\"http://mirrors.hust.edu.cn/apache/zookeeper/zookeeper-3.4.10/zookeeper-3.4.10.tar.gz\" target=\"_blank\">http://mirrors.hust.edu.cn/apache/zookeeper/zookeeper-3.4.10/zookeeper-3.4.10.tar.gz</a></p>\n</blockquote>\n<p>2.将下载好的zookeeper上传到Linux服务器上</p>\n<p>上传方式多种多样，我这里采用了xftp，小伙伴们也可以直接使用putty上传，上传结果如下：<br />\n<img src=\"http://img.blog.csdn.net/20170825114622362?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>3.将文件解压到/opt目录下</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115122378?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>4.进入到刚刚解压好的目录中，创建两个文件夹，分别是data和logs，如下：</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115324970?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>5.将解压后zookeeper-3.4.10文件夹下的zoo_sample.cfg文件拷贝一份命名为zoo.cfg，如下：</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115426251?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>6.修改zoo.cfg文件，添加data和log目录，如下：</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115527367?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<blockquote>\n<p>1.2888 端口号是zookeeper服务之间通信的端口<br />\n2.3888 是zookeeper 与其他应用程序通信的端口<br />\n3.initLimit：这个配置项是用来配置 Zookeeper 接受客户端（这里所说的客户端不是用户连接 Zookeeper服务器的客户端，而是 Zookeeper 服务器集群中连接到 Leader 的 Follower 服务器）初始化连接时最长能忍受多少个心跳时间间隔数。当已经超过 10 个心跳的时间（也就是 tickTime）长度后 Zookeeper 服务器还没有收到客户端的返回信息，那么表明这个客户端连接失败。总的时间长度就是 5<em>2000=10 秒。<br />\n4.syncLimit：这个配置项标识 Leader 与 Follower 之间发送消息，请求和应答时间长度，最长不能超过多少个 tickTime 的时间长度，总的时间长度就是 2</em>2000=4 秒<br />\n5.server.A=B:C:D：其中 A 是一个数字，表示这个是第几号服务器；B 是这个服务器的IP地址或/etc/hosts文件中映射了IP的主机名；C 表示的是这个服务器与集群中的 Leader 服务器交换信息的端口；D 表示的是万一集群中的 Leader 服务器挂了，需要一个端口来重新进行选举，选出一个新的 Leader，而这个端口就是用来执行选举时服务器相互通信的端口。如果是伪集群的配置方式，由于 B 都是一样，所以不同的 Zookeeper 实例通信端口号不能一样，所以要给它们分配不同的端口号。</p>\n</blockquote>\n<p>7.在 dataDir=/opt/zookeeper-3.4.10/data下创建 myid文件 编辑myid文件，并在对应的IP的机器上输入对应的编号。如在zookeeper上，myid文件内容就是1。如果只在单点上进行安装配置，那么只有一个server.1。如下：</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115647920?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>8.在.bash_profile文件中增加zookeeper配置：</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115729473?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>9.使配置生效</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115807787?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>10.关闭防火墙</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115848488?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>11.启动并测试</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115938795?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<blockquote>\n<p>启动之后如果能看到Mode:standalone就表示启动成功了。</p>\n</blockquote>\n<p>12.关闭zookeeper</p>\n<p><img src=\"http://img.blog.csdn.net/20170825121021364?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>13.配置开机启动zookeeper</p>\n<p><img src=\"http://img.blog.csdn.net/20170825121059827?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<blockquote>\n<p><strong>注意注意</strong> 在centos7中，/etc/rc.local的权限被降低了，所以需要执行如下命令赋予其可执行权限<br />\n<code>chmod +x /etc/rc.d/rc.local</code></p>\n</blockquote>\n<p>OK,以上就是我们在CentOS7中安装zookeeper的全过程，做好这一切之后我们就可以在Dubbo中使用这个注册中心了，这个我们放在下一篇博客中介绍。</p>\n<p>更多JavaEE资料请关注公众号：</p>\n<p><img src=\"http://img.blog.csdn.net/20170823174820001?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"\" /></p>\n', '最近打算出一个系列，介绍Dubbo的使用。\n\n分布式应用现在已经越来越广泛，Spring Could', '3', '1', '2017-12-24 10:05:20', '2017-12-24 10:05:20', '1', '1');
INSERT INTO `artical` VALUES ('4', 'Linux中安装zookeeper', 'watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)', '<p>最近打算出一个系列，介绍Dubbo的使用。</p>\n<hr />\n<p>分布式应用现在已经越来越广泛，Spring Could也是一个不错的一站式解决方案，不过据我了解国内目前貌似使用阿里Dubbo的公司比较多，一方面这个框架也确实很OK，另一方面可能也是因为Dubbo的中文文档比较全的缘故，据Dubbo官网上的消息，阿里已经重新开始了对Dubbo的维护，这也算是使用Dubbo的互联网公司的福音了吧。OK，废话不多说，今天我们就先来看看如何在Linux上安装zookeeper。</p>\n<hr />\n<p>了解过Dubbo的小伙伴都知道，Dubbo官方建议我们使用的注册中心就是zookeeper，zookeeper本来是Hadoop的一个子项目，现在发展成了Apache的顶级项目，看名字就知道Zookeeper就是动物园管理员，管理Hadoop(大象)、Hive(蜂房/蜜蜂)等动物。Apache上的Zookeeper分Linux版和Windows版，但是考虑到实际生产环境都是Linux，所以我们这里主要介绍Linux上Zookeeper的安装，Windows上Zookeeper的安装则比较简单，下载解压即可，和Tomcat差不多。</p>\n<p>OK，废话不多说，接下来我们就来看看zookeeper的安装步骤。</p>\n<hr />\n<p>环境：</p>\n<blockquote>\n<p>1.VMware® Workstation 12 Pro<br />\n2.CentOS7<br />\n3.zookeeper-3.4.10(本文写作时的最新稳定版)</p>\n</blockquote>\n<hr />\n<h1>安装步骤</h1>\n<p>1.下载zookeeper</p>\n<p>zookeeper下载地址如下，小伙伴们可以在第一个地址中选择适合自己的zookeeper版本，也可以直接点击第二个地址下载我们本文使用的zookeeper。</p>\n<blockquote>\n<p>1.<a href=\"http://mirrors.hust.edu.cn/apache/zookeeper/\" target=\"_blank\">http://mirrors.hust.edu.cn/apache/zookeeper/</a></p>\n</blockquote>\n<blockquote>\n<p>2.<a href=\"http://mirrors.hust.edu.cn/apache/zookeeper/zookeeper-3.4.10/zookeeper-3.4.10.tar.gz\" target=\"_blank\">http://mirrors.hust.edu.cn/apache/zookeeper/zookeeper-3.4.10/zookeeper-3.4.10.tar.gz</a></p>\n</blockquote>\n<p>2.将下载好的zookeeper上传到Linux服务器上</p>\n<p>上传方式多种多样，我这里采用了xftp，小伙伴们也可以直接使用putty上传，上传结果如下：<br />\n<img src=\"http://img.blog.csdn.net/20170825114622362?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>3.将文件解压到/opt目录下</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115122378?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>4.进入到刚刚解压好的目录中，创建两个文件夹，分别是data和logs，如下：</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115324970?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>5.将解压后zookeeper-3.4.10文件夹下的zoo_sample.cfg文件拷贝一份命名为zoo.cfg，如下：</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115426251?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>6.修改zoo.cfg文件，添加data和log目录，如下：</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115527367?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<blockquote>\n<p>1.2888 端口号是zookeeper服务之间通信的端口<br />\n2.3888 是zookeeper 与其他应用程序通信的端口<br />\n3.initLimit：这个配置项是用来配置 Zookeeper 接受客户端（这里所说的客户端不是用户连接 Zookeeper服务器的客户端，而是 Zookeeper 服务器集群中连接到 Leader 的 Follower 服务器）初始化连接时最长能忍受多少个心跳时间间隔数。当已经超过 10 个心跳的时间（也就是 tickTime）长度后 Zookeeper 服务器还没有收到客户端的返回信息，那么表明这个客户端连接失败。总的时间长度就是 5<em>2000=10 秒。<br />\n4.syncLimit：这个配置项标识 Leader 与 Follower 之间发送消息，请求和应答时间长度，最长不能超过多少个 tickTime 的时间长度，总的时间长度就是 2</em>2000=4 秒<br />\n5.server.A=B:C:D：其中 A 是一个数字，表示这个是第几号服务器；B 是这个服务器的IP地址或/etc/hosts文件中映射了IP的主机名；C 表示的是这个服务器与集群中的 Leader 服务器交换信息的端口；D 表示的是万一集群中的 Leader 服务器挂了，需要一个端口来重新进行选举，选出一个新的 Leader，而这个端口就是用来执行选举时服务器相互通信的端口。如果是伪集群的配置方式，由于 B 都是一样，所以不同的 Zookeeper 实例通信端口号不能一样，所以要给它们分配不同的端口号。</p>\n</blockquote>\n<p>7.在 dataDir=/opt/zookeeper-3.4.10/data下创建 myid文件 编辑myid文件，并在对应的IP的机器上输入对应的编号。如在zookeeper上，myid文件内容就是1。如果只在单点上进行安装配置，那么只有一个server.1。如下：</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115647920?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>8.在.bash_profile文件中增加zookeeper配置：</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115729473?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>9.使配置生效</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115807787?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>10.关闭防火墙</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115848488?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>11.启动并测试</p>\n<p><img src=\"http://img.blog.csdn.net/20170825115938795?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<blockquote>\n<p>启动之后如果能看到Mode:standalone就表示启动成功了。</p>\n</blockquote>\n<p>12.关闭zookeeper</p>\n<p><img src=\"http://img.blog.csdn.net/20170825121021364?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<p>13.配置开机启动zookeeper</p>\n<p><img src=\"http://img.blog.csdn.net/20170825121059827?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"这里写图片描述\" /></p>\n<blockquote>\n<p><strong>注意注意</strong> 在centos7中，/etc/rc.local的权限被降低了，所以需要执行如下命令赋予其可执行权限<br />\n<code>chmod +x /etc/rc.d/rc.local</code></p>\n</blockquote>\n<p>OK,以上就是我们在CentOS7中安装zookeeper的全过程，做好这一切之后我们就可以在Dubbo中使用这个注册中心了，这个我们放在下一篇博客中介绍。</p>\n<p>更多JavaEE资料请关注公众号：</p>\n<p><img src=\"http://img.blog.csdn.net/20170823174820001?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdTAxMjcwMjU0Nw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast\" alt=\"\" /></p>\n', '最近打算出一个系列，介绍Dubbo的使用。\n\n分布式应用现在已经越来越广泛，Spring Could', '4', '1', '2017-12-24 10:05:20', '2017-12-24 10:05:20', '1', '1');

-- ----------------------------
-- Table structure for artical_catigory
-- ----------------------------
DROP TABLE IF EXISTS `artical_catigory`;
CREATE TABLE `artical_catigory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `catigory_name` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of artical_catigory
-- ----------------------------
INSERT INTO `artical_catigory` VALUES ('1', 'linux', '2019-07-23 09:55:53');
INSERT INTO `artical_catigory` VALUES ('2', 'java', '2019-07-23 09:56:03');
INSERT INTO `artical_catigory` VALUES ('3', 'python', '2019-07-23 09:56:16');
INSERT INTO `artical_catigory` VALUES ('4', 'javascript', '2019-07-23 09:56:27');

-- ----------------------------
-- Table structure for artical_tags
-- ----------------------------
DROP TABLE IF EXISTS `artical_tags`;
CREATE TABLE `artical_tags` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `artical_id` int(11) DEFAULT NULL,
  `artical_catigory_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `articalId` (`artical_id`) USING BTREE,
  KEY `artical_catigory_id` (`artical_catigory_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of artical_tags
-- ----------------------------
INSERT INTO `artical_tags` VALUES ('1', '1', '1');
INSERT INTO `artical_tags` VALUES ('2', '1', '2');
INSERT INTO `artical_tags` VALUES ('3', '1', '3');
INSERT INTO `artical_tags` VALUES ('4', '1', '5');

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cover` varchar(255) DEFAULT '',
  `title` varchar(255) NOT NULL DEFAULT '',
  `author` varchar(255) DEFAULT '',
  `date` varchar(20) DEFAULT '',
  `press` varchar(255) DEFAULT '',
  `abs` text,
  `cid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_book_category_on_cid` (`cid`),
  CONSTRAINT `fk_book_category_on_cid` FOREIGN KEY (`cid`) REFERENCES `category` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('102', 'https://img3.doubanio.com/view/subject/m/public/s29904966.jpg', '不能与不会', '[美]莉迪亚·戴维斯', '2019-6', '中信出版集团/楚尘文化', '内容简介：\r\n这是布克国际奖得主、美国当代著名女作家莉迪亚·戴维斯全新力作，全书包含122个短篇故事，集微型小说、轶事、笑话、预言、神话、格言、祷词、书信于一体。既有丰富的内心独白，也不乏微妙的幽默讽刺；既有对伟大文学的致敬之作，也有复原梦境的絮语。\r\n在书中，你会看到这样一群人，他们是生活的旁观者、永远在路上的旅人、迷恋冷风景的瞭望员、善于深入语...', '1');
INSERT INTO `book` VALUES ('103', 'https://img3.doubanio.com/view/subject/m/public/s32326465.jpg', '新艺术运动', '[英] 斯蒂芬·埃斯克里特', '2019-7', '后浪丨湖南美术出版社', '在历史语境中理解艺术\r\n探索视觉形式背后的观念\r\n20世纪现代设计史上的光辉篇章\r\n美感与灵感的源泉，现代装饰界的璀璨明珠\r\n◎ 编辑推荐\r\n☆ 详实清晰，带领读者走近那真正为新的时代而生的“崭新的艺术”\r\n巴黎地铁站的曲线造型铁艺、莱俪不对称曲线珠宝、蒂芙尼日本风格的玻璃器皿…… 这些我们如今熟悉又陌生的装饰语言都与19世纪末欧美视觉领域酝酿的变化息息相关。衔接两个...', '1');
INSERT INTO `book` VALUES ('104', 'https://img1.doubanio.com/view/subject/m/public/s33296228.jpg', '银河界区三部曲Ⅰ深渊上的火', '[美]弗诺·文奇', '2019-7', '北京联合出版公司', '●  他创造了太空歌剧的奇迹，也奠定了赛博朋克的基石，更预见了科技的现在和未来。\r\n1981年，弗诺·文奇在《真名实姓》中构建的赛博空间成为威廉·吉布森、尼尔·斯蒂芬森创作赛博朋克故事的书写核心；为初代互联网人带去曙光和灵感。\r\n●  硬科幻代表作家，雨果奖五冠王，科幻大师中的大师。\r\n雨果奖、星云奖双奖科幻作家大卫·布林、英国雨果奖科幻作家查尔斯·斯特罗斯...', '2');
INSERT INTO `book` VALUES ('105', 'https://img3.doubanio.com/view/subject/m/public/s33300134.jpg', '汴京之围', '郭建龙', '2019-7-1', '天地出版社', '【内容推荐】\r\n一卷帝国衰亡史，一部成败启示录\r\n本书追溯北宋末年靖康之难的完整历史细节，讲述宋、辽、金三方的和与战，聚焦北宋历史大变局的关键时刻，以及帝国由内而外全局性危局大爆发的前因后果。北宋宣和年间，帝国上下一片繁荣景象，然而盛世之下的隐患已成暗涌。财政困难、军事痼疾、恶性党争等内部危机，北方辽、金两国的军事威胁等外部危机，使得帝国渐成风雨...', '3');
INSERT INTO `book` VALUES ('106', 'https://img1.doubanio.com/view/subject/m/public/s33303518.jpg', '白城恶魔', '[美] 埃里克·拉森', '2019-7', '南海出版公司4', '《白城恶魔》是美国作家埃里克·拉森的长篇犯罪纪实小说代表作。\r\n1893年，镀金时代的美国，芝加哥世界博览会即将举行，旧世界正在崩塌，荣耀与罪恶争相上演。\r\n总设计师伯纳姆孜孜以求，集合知名的建筑师和规划师，只为打造一场令世人赞叹的世博会，重塑芝加哥的形象。他说，这不会只是一个梦。\r\n而在几个街区之外，一位年轻英俊的医生踏出列车，手中提着手术箱。对不知...', '4');
INSERT INTO `book` VALUES ('107', 'https://img3.doubanio.com/view/subject/m/public/s33203210.jpg', '波拉尼奥：最后的访谈', '[智]罗贝托·波拉尼奥 ', '2019-7', '中信出版集团', '1998年，罗贝托·波拉尼奥的小说《荒野侦探》发表，记者玛丽斯坦发现了这位\"可以和自己的读者做朋友\"的作家。几封书信往来之后，两人不仅建立了深厚的友谊，还就\"真理\"与\"结果\"进行了一场长久的讨论，也成为波拉尼奥生前的最后一次访谈。这次访谈首次译成中文，和另外几篇同时收录，其中包括波拉尼奥与南美的几位记者所做的访谈，为读者理解这位\"拉丁美洲的T.S.艾略特或弗吉尼亚·伍尔夫\"的作品提供了丰厚的图景。围绕波拉尼奥的创作、奇书《2666》的诞生、作家与同时代作家好友的交往等，这些轻松而精彩的对话，都在他的巨著《2666》的写作期间完成，它们展现了作家的处世态度，对爱的追求，以及对致命疾病的现实最为深邃的个人忧虑。。。', '1');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '文学');
INSERT INTO `category` VALUES ('2', '流行');
INSERT INTO `category` VALUES ('3', '文化');
INSERT INTO `category` VALUES ('4', '生活');
INSERT INTO `category` VALUES ('5', '经管');
INSERT INTO `category` VALUES ('6', '科技');

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `id` int(11) NOT NULL,
  `artical_id` int(11) DEFAULT NULL,
  `content` text,
  `publish_date` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `artical_id` (`artical_id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comments
-- ----------------------------
INSERT INTO `comments` VALUES ('1', '1', '写的不错！', '2019-07-23 10:17:43', '2');
INSERT INTO `comments` VALUES ('2', '2', '可以可以', '2019-07-23 10:18:25', '3');

-- ----------------------------
-- Table structure for pv
-- ----------------------------
DROP TABLE IF EXISTS `pv`;
CREATE TABLE `pv` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `count_date` datetime DEFAULT NULL,
  `pv` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`uid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pv
-- ----------------------------
INSERT INTO `pv` VALUES ('1', '2017-12-24 00:00:00', '20', '1');
INSERT INTO `pv` VALUES ('2', '2017-12-25 00:00:00', '14', '1');
INSERT INTO `pv` VALUES ('4', '2017-12-26 00:00:00', '40', '1');
INSERT INTO `pv` VALUES ('5', '2017-12-27 00:00:00', '23', '1');
INSERT INTO `pv` VALUES ('6', '2017-12-28 00:00:00', '11', '1');
INSERT INTO `pv` VALUES ('7', '2017-12-29 00:00:00', '32', '1');
INSERT INTO `pv` VALUES ('26', '2017-12-30 00:00:00', '2', '1');
INSERT INTO `pv` VALUES ('27', '2017-12-31 00:00:00', '77', '1');

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('1', '超级管理员');
INSERT INTO `roles` VALUES ('2', '普通用户');
INSERT INTO `roles` VALUES ('3', '测试角色1');
INSERT INTO `roles` VALUES ('4', '测试角色2');
INSERT INTO `roles` VALUES ('5', '测试角色3');

-- ----------------------------
-- Table structure for roles_user
-- ----------------------------
DROP TABLE IF EXISTS `roles_user`;
CREATE TABLE `roles_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rid` (`rid`) USING BTREE,
  KEY `roles_user_ibfk_2` (`uid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roles_user
-- ----------------------------
INSERT INTO `roles_user` VALUES ('1', '1', '1');
INSERT INTO `roles_user` VALUES ('2', '2', '2');
INSERT INTO `roles_user` VALUES ('3', '3', '3');
INSERT INTO `roles_user` VALUES ('4', '4', '4');
INSERT INTO `roles_user` VALUES ('5', '5', '5');

-- ----------------------------
-- Table structure for tags
-- ----------------------------
DROP TABLE IF EXISTS `tags`;
CREATE TABLE `tags` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tags
-- ----------------------------
INSERT INTO `tags` VALUES ('1', '666');
INSERT INTO `tags` VALUES ('2', 'Ajax');
INSERT INTO `tags` VALUES ('3', 'Dubbo');
INSERT INTO `tags` VALUES ('4', 'git');
INSERT INTO `tags` VALUES ('5', 'Linux');
INSERT INTO `tags` VALUES ('6', 'mongodb');
INSERT INTO `tags` VALUES ('7', 'spring');
INSERT INTO `tags` VALUES ('8', 'SpringSecurity');
INSERT INTO `tags` VALUES ('9', 'websocket');
INSERT INTO `tags` VALUES ('10', 'Zookeeper');
INSERT INTO `tags` VALUES ('11', '图片上传');
INSERT INTO `tags` VALUES ('12', '图片预览');
INSERT INTO `tags` VALUES ('13', '学习资料');
INSERT INTO `tags` VALUES ('14', '杂谈');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `gender` tinyint(2) DEFAULT '1' COMMENT '男1:，女0',
  `avatar` varchar(255) DEFAULT NULL,
  `enable` tinyint(2) DEFAULT '1' COMMENT '启用1，禁用2',
  `regtime` datetime DEFAULT NULL COMMENT '注册时间',
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '123', 'admin', '1', '1', '1', '2019-07-23 10:11:43', 'lichongsky@qq.com');
INSERT INTO `user` VALUES ('2', 'james', '123', 'king', '1', '1', '1', '2019-07-23 10:11:43', 'lichongsky@qq.com');
INSERT INTO `user` VALUES ('3', 'kobe', '123', 'fly', '1', '1', '1', '2019-07-23 10:11:43', 'lichongsky@qq.com');
INSERT INTO `user` VALUES ('4', 'wade', '123', 'light', '1', '1', '1', '2019-07-23 10:11:43', 'lichongsky@qq.com');
INSERT INTO `user` VALUES ('5', 'durant', '123', 'grim Reaper', '1', '1', '1', '2019-07-23 10:11:43', 'lichongsky@qq.com');
