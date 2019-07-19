/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : white_jotter

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-07-18 18:22:15
*/

SET FOREIGN_KEY_CHECKS=0;

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
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '123');
