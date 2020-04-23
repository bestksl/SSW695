-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: hobbymatcher
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `blog`
--

DROP TABLE IF EXISTS `blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blog` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(128) NOT NULL,
  `html` mediumtext NOT NULL,
  `on_datetime` datetime NOT NULL,
  `clap_count` int NOT NULL,
  `by_user_id` int NOT NULL,
  `for_hobby_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_blog_user1_idx` (`by_user_id`),
  KEY `fk_blog_event1_idx` (`for_hobby_id`),
  CONSTRAINT `fk_blog_event1` FOREIGN KEY (`for_hobby_id`) REFERENCES `event` (`id`),
  CONSTRAINT `fk_blog_user1` FOREIGN KEY (`by_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog`
--

LOCK TABLES `blog` WRITE;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Art'),(2,'Education'),(3,'Entertainment'),(4,'Sports');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event` (
  `id` int NOT NULL AUTO_INCREMENT,
  `hobby_id` int NOT NULL,
  `title` varchar(64) NOT NULL,
  `on_datetime` datetime NOT NULL,
  `location` varchar(128) DEFAULT NULL,
  `location_short` varchar(64) DEFAULT NULL,
  `geo_lat` double DEFAULT NULL,
  `geo_lon` double DEFAULT NULL,
  `capacity` double DEFAULT NULL,
  `description` varchar(2048) DEFAULT NULL,
  `plus18_only` tinyint DEFAULT NULL,
  `fee` double DEFAULT NULL,
  `organizer` varchar(128) DEFAULT NULL,
  `photo_id` varchar(42) DEFAULT NULL,
  `created_by_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_event_hobby1_idx` (`hobby_id`),
  KEY `fk_event_user1_idx` (`created_by_id`),
  CONSTRAINT `fk_event_hobby1` FOREIGN KEY (`hobby_id`) REFERENCES `hobby` (`id`),
  CONSTRAINT `fk_event_user1` FOREIGN KEY (`created_by_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (1,2,'2020 summer camp class','2020-05-25 12:00:00','纽约州, 美国','',43.1561681,-75.8449946,20,'CLASS PERIOD:    camper can take multiple periods per day. call for details.\n10am – 12:30pm\n1pm—3:30pm\n4pm – 6:30pm\nCAMP OPTION: \nFull Summer ( all 10 weeks )\nHalf Summer ( any consecutive 5 weeks )               \nAny 10 days \nAny 20 days \nAny 30 days\nAny 40 days\nEarly Birds discounts will be available by 5/30/2019.   Group registration ( more than 4 campers ) discounts are available Car service is available for additional cost.  Please contact us for details regarding discounts and pick ups.\n',0,5000,'NEW YORK BADMINTON CENTER','81b62c25-f5fb-4d25-80f2-50b37bbfa650',2),(2,3,'Select Training  at the College World Series (Ages 12-14)','2020-06-19 12:00:00','Western Iowa Tech Community College, South Lakeport Street, Regency of Sioux City, Sioux City, Woodbury County, 艾奥瓦州, 51106, 美国','Sioux City',42.4834592,-96.3478053,20,'This event is invitation-only.You will have the opportunity to attend the College World Series at TD AMERITRADE Park in Omaha, NE and see the stars of the future performing on the greatest college stage in front of thousands of fans. This is the premier event for all of college baseball and you will be able to watch it in person. Baseball Factory players will attend ONE 2019 Men’s College World Series game. ',0,4500,'Iowa Western Community College','b15d933b-81a3-47c3-87ed-d6339ee4f3c5',2),(3,10,'Stevens basketballl hall practicing 5 people wanted','2020-04-30 12:00:00','Stevens Institute of Technology, Babbio Drive, Hoboken, Hudson County, 新泽西州, 07030, 美国','Hoboken',40.744809599999996,-74.02524356385591,5,'Who is interested in playing basketball, take your ID card with you, let\'s play basketball together',0,0,'Jerry','3fdba12e-0dc2-46f5-812e-1b3caabc4c6d',2),(4,7,'WHITE PLAINS, NY OLYMPIA QUALIFIER','2020-05-18 12:00:00','Westchester County Center, Tarrytown Road, Fairview, Town of Greenburgh, Westchester County, 纽约州, 10606, 美国','',41.0371454,-73.77884192672681,NULL,'This contest is ONLY open to IFBB Professional athletes in good standing with the IFBB Pro League. Contact the IFBB Pro League once to request a contract to compete. NPC amateur athletes are not eligible to compete in the NY PRO.',0,60,'WNBF(World natural bodybuilding Federation)','5e15bcb7-2472-4a79-896e-9b994be7fb84',2),(5,11,'FINA Diving Grand Prix - Calgary','2020-04-25 12:00:00','Diving Dock, Chemin Tamaracouta, Mille-Isles, Argenteuil, Laurentides, 魁北克, 加拿大','',45.8304031,-74.2056675,20,'Many of our athletes begin diving as youths in our junior programs conducted through more than 200 clubs nationwide. Some go on to the senior level to become top national and international competitive divers. And our masters program supports those who no longer compete at the senior level, but wish to continue diving well into adulthood.Connection through our membership gives coaches, divers, and fans access to the latest diving programs, events, news, and more. ',0,0,'Fina diving grand prix','8746bca7-d33b-49de-a6ef-0552dd7bcc93',2),(6,4,'Drink & Draw Wednesdays!','2020-05-06 12:00:00','Living Arts Gallery, Akoni Pule Highway, Kapaau CDP, Hawaiʻi County, 夏威夷州, 96719, 美国','',20.2381992,-155.8294239,20,'Join us Every Wednesday for Drink & Draw! We provide drawing materials, wine, and a live figure drawing model!',0,10,'The Living Gallery','795cdaa8-1f49-41dd-9d85-bbc98db4b811',2),(7,5,'Basic Photography Workshop - Grace and Shine photography','2020-04-30 12:00:00','207, Starr Street, Medina, Ridgeway Town, Orleans County, 纽约州, 14103, 美国','',43.218361,-78.38363579513482,20,'Basic Studio workshop\n\nBasic appointment planning\nCamera settings\nKnow your setup (Planning)\nCamera gear (Lenses)\nModifiers\nBasic Disscussions (Lighting, continuous)\nModel Ideas (Shoot ideas)\nmodel release and TFP\nPosing\nExperimenting\nFind your comfort zone',0,35,'Grace And Shine Photography LLC','38a159f1-7241-43d1-ad62-7c402f0216de',2),(8,17,'New York adoption event weekends','2020-05-05 12:00:00','Broadway, Depot Road, Murray Hill, Queens, Queens County, 纽约, 纽约州, 11358, 美国','纽约',40.7617198,-73.8018206,20,'Let love change your world! Join us for adoptions at Petco stores all across the country and give a pet a loving home. Every month, the Petco Foundation hosts adoption events nationwide. Find an adoption event near you—and discover love.Did you adopt a new pet? We’d love to see a family photo! Whether you’re on Facebook, Twitter or Instagram, tag us or use #PetcoFamily and we may share your happiness!',0,25,'Petco foundation (212) 358-0692','a14ff18f-c6e9-48ff-9411-97c2680d8300',2),(9,18,'Alamo Orchid Society Show','2020-04-27 12:00:00','圣安东尼奥, Bexar County, 得克萨斯州, 美国','圣安东尼奥',29.4246002,-98.4951405,20,'Alamo Orchid Society Show is being held April 22 - 24, 2019 at the San Antonio Garden Center, 3310 North New Braunfels Ave., San Antonio, TX.\nJudging is being held on April 22, 2019 at 6:00 PM.',0,0,'American Orchid Society','608eb618-5cb5-4f63-950a-f58c565f729d',2),(10,20,'Watching films and relexing!','2020-05-22 12:00:00','ny, Suffolk County, 纽约州, 美国','',40.741235950000004,-73.35669116536101,15,'Let\'s get together and watch the famous movie Inception! We can talk to each other our thoughts about the film. Let\'s make friends and chat together happily!',0,10,'Jerry','c5e96e28-4ab7-4214-a46e-a4cf223d28ba',2),(11,19,'Who is the fastest?','2020-05-08 12:00:00','Culver\'s, 1024, Bowles Avenue, Fenton, Saint Louis County, 密蘇里州, 63026, 美国','Fenton',38.52450965,-90.47183835680619,30,'The latest 1000 chips of puzzle! Who will be the first one to finish it? Join us today for the competition!',0,10,'American Puzzles Association','d7523305-07de-4164-a0e2-cac8edb7c167',2),(12,16,'Chess competition!','2020-05-30 12:00:00','55, Riverwalk, Hampton, Rockingham County, 新罕布什尔州, 03842, 美国','',42.94528750334554,-70.8473246708326,10,'Come and participate in our chess game today! See who is the most powerful chess player!',0,0,'Jerry','f7f26298-8b55-42dd-86aa-6a27c932187e',2),(13,15,'Calligraphy Show!','2020-05-20 12:00:00','Grandstreet Theatre, North Park Avenue, Last Chance Gulch, Helena, Lewis and Clark County, 蒙大拿州, 59601, 美国','Helena',46.590217100000004,-112.03989497359072,50,'It\'s a visual feast! Join us and enjoy these beautiful calligraphys!',0,0,'Chinese Calligraphy Association','898cd828-89d8-40de-8744-8b0b3c6b29f7',2),(14,14,'Table tennis competition! ','2020-06-03 12:00:00','Hoboken, Hudson County, 新泽西州, 美国','Hoboken',40.7433066,-74.0323752,20,'Join us for the exciting table tennis competition! ',0,15,'American Table Tennis Association','5f5d8ae0-aa0e-4666-a93e-d75f68c961b3',2),(15,13,'New baking courses!','2020-05-20 12:00:00','Jersey City, Hudson County, 新泽西州, 美国','Jersey City',40.7281575,-74.0776417,30,'It\'s a brand new course for baking! If you have interests in baking, just join us!',0,1500,'New Jersey Baking Association','d9c0e1fd-2622-4adf-a540-3c9f55a5aafc',2),(16,2,'Badminton sparring!','2020-06-18 12:00:00','Bergen County, 新泽西州, 美国','',40.9678345,-74.0563254,2,'I\'m gonna attend some badminton competitions this summer, so I want to find someone to help me practice. We can be good friends!',0,0,'Annnnn','0129404b-7020-459d-bd1e-2799ad30b7f9',5),(17,1,'Jogging together!','2020-06-01 12:00:00','Heights, Texas City, Galveston County, 得克萨斯州, 77590, 美国','Texas City',29.3888447,-94.930479,2,'Looking for new partners to jogging together in the moring!',0,0,'Annnn','347e89d9-6ce9-495a-b406-82b50017584c',5),(18,17,'Join us to play with cats! We have whatever cats you want!','2020-06-10 12:00:00','Jersey City, Hudson County, 新泽西州, 美国','Jersey City',40.7281575,-74.0776417,20,'We are a professional cat house. Recently I want to hold a cat sucking activity, let everyone know about the daily life with cats',0,20,'New Jersey Cat House','9284ecba-e944-4091-8608-93cf6e37342f',5),(19,3,'Baseball trainning course','2020-05-14 12:00:00','Union City, Hudson County, 新泽西州, 07087, 美国','Union City',40.7795455,-74.0237512,20,'We are openning new courses for baseball lovers. Here you can receive professional training of baseball!',0,2000,'American Baseball Association','e291053c-66b0-4dc0-8c2d-91315214a5bd',5),(20,14,'Table tennis intro training course!','2020-05-21 12:00:00','West New York, Hudson County, 新泽西州, 美国','West New York',40.785529,-74.00830025,20,'We are openning a brand new table tennis intro training course! We want everyone can know about the table tennis and enjoy it!',0,2000,'Hudson Table Tennis Association','be1d70d2-55de-46f5-8fc4-f216573712af',5),(21,9,'Guitar intro training!','2020-05-27 12:00:00','Jersey City, Hudson County, 新泽西州, 美国','Jersey City',40.7281575,-74.0776417,15,'We are a professional music training institution. We are now offering new training courses for guitar lovers! Come and see if you are interested in! You won\'t be disappointed!',0,1000,'Jersey City Guitar Association','dddc0fff-3110-4ad2-af1e-1e34d1d7890a',5),(22,16,'Chess training course!','2020-06-04 12:00:00','Fort Lee, Bergen County, 新泽西州, 美国','Fort Lee',40.8509333,-73.9701382,15,'We are now opening a new course to teach people how to play chess! All our instructor are professional! You don\'t want to miss this, trust me!',0,1800,'Bergen County Chess Association','0a7d1975-9e7e-4a3d-84fb-2e2e302f0015',5),(23,12,'Join us for an exciting electronic game competition!','2020-06-17 12:00:00','Hoboken, Hudson County, 新泽西州, 美国','Hoboken',40.7433066,-74.0323752,20,'We want to opne a competition of electronic games this summer. Come and see if you could win the championship! Discover the championship reward by yourselves!',0,40,'Hoboken Electronic Game Association','69c66a3f-54ee-453b-b3a4-564e3de15748',5),(24,11,'Diving course','2020-06-20 12:00:00','Fort Lee, Salem Neck, Salem, Essex County, 马萨诸塞州, 美国','',42.53157175,-70.87435745660403,15,'We want to let more people get knowledge of diving! Because many unexpected things may happen during the diving, so you must learn about it! We will offer some professional certificate after you finish the course!',0,1500,'Mass Diving Association','60132d3c-8f8b-49bf-9443-6e1edd189cf4',5),(25,17,'Cat Adoption','2020-06-10 12:00:00','纽约, 纽约州, 美国','',40.7127281,-74.0060152,50,'We want to let people adopt these cats! They are all good and collected from many areas. You will be attracted by them definately!',0,0,'New York Animal Charity','5eb87e23-1d98-4f1e-995e-d5f2c2ce1133',5),(26,4,'Drawing Intro Course','2020-05-25 12:00:00','Union City, Hudson County, 新泽西州, 07087, 美国','Union City',40.7795455,-74.0237512,20,'We are offering a new drawing training course. In this course you will learn how to draw some basic pictures!',0,1500,'Union City Art Association','ce11f4d1-7fe6-40cf-9282-d25d5273ad69',5),(27,18,'Raising Orchids Course','2020-06-15 12:00:00','West New York, Hudson County, 新泽西州, 美国','West New York',40.785529,-74.00830025,20,'We want to teach people how to raise orchids! Everyone is welcome to join our course!',0,500,'Hudson Flower Association','97ceb3d6-f049-48c2-89fc-74eccfbf2478',5),(28,15,'Calligraphy Training Course','2020-05-27 12:00:00','Fort Lee, Bergen County, 新泽西州, 美国','Fort Lee',40.8509333,-73.9701382,20,'We are offering courses for people who wants to learn the beautiful calligraphy! We have the best teachers!',0,1500,'Fort Lee Calligraphy Association','3ebe760c-8afb-4533-b642-3e6faa9a5c28',5),(29,10,'Basketball Competition!','2020-06-30 12:00:00','Stevens Institute of Technology, Babbio Drive, Hoboken, Hudson County, 新泽西州, 07030, 美国','Hoboken',40.744809599999996,-74.02524356385591,15,'We are about to start a basketball game, welcome people who like basketball to join us! You can form the teams by yourselves.',0,50,'SIT Basketball School Team','391c420c-5db9-46bb-b236-ea057b635cec',5),(30,9,'Guitar Show','2020-04-01 12:00:00','West New York, Hudson County, 新泽西州, 美国','West New York',40.785529,-74.00830025,30,'We are going to open a guitar show. The performers are from professional bands. You can enjoy unbelievble music at this show! Don\'t miss this!',0,20,'West New York Music Association','61f5b30c-f51e-4652-9b41-069cc4a3f475',3),(31,12,'Electronic Sports Club','2020-04-10 12:00:00','Hoboken, Hudson County, 新泽西州, 美国','Hoboken',40.7433066,-74.0323752,20,'We want to hold an electronic training club for everyone who wants to attend this field. Join us and enjoy it!',0,50,'Hoboken Electronic Game Association','19ff2c6a-2822-418c-84e1-e9527db27b3c',3),(32,10,'BasketBall Training Course','2020-04-06 12:00:00','Jersey City, Hudson County, 新泽西州, 美国','Jersey City',40.7281575,-74.0776417,20,'We are offering basketball intro courses to everyone! If you want to learn how to play basketball, just join us! You won\'t regret!',0,2000,'Jersey City Basketball Association','8a9a2ee9-9996-42be-93f9-ccd73467f838',3),(33,7,'Fitness Training Course','2020-04-11 12:00:00','Jersey, Jersey Township, Licking County, 俄亥俄州, 43001:43062, 美国','',40.0536746,-82.7232266,20,'We now have courses about how to work out safely. Many people get injuryed during work out time. Everyone must learn the correct way.',0,1000,'Licking County Fitness Association','e15b9a25-2d6c-4618-8d4b-48829bea4af0',3),(34,20,'Film Symposium','2020-04-15 12:00:00','Manhattan, 纽约县, 纽约, 纽约州, 美国','纽约',40.7896239,-73.9598939,20,'We want to open a symposium for people who wants to exchange their opinions with each other about different films. We can learn from other people and know more interesting films together!',0,50,'Manhattan Film Association','a8499bce-7be5-473b-b93d-6ed65ae761c5',3),(35,8,'Embroidery Intro Course','2020-04-25 12:00:00','Union City, Hudson County, 新泽西州, 07087, 美国','Union City',40.7795455,-74.0237512,20,'We are opening a course that teaches people how to do embroidery. People might encounter many problems when they start to do this. Join us today and know how to do it carefully and efficiently!',0,1000,'Union City Art Association','95dba011-bdb9-4c4b-bd0f-0e818b72558c',3),(36,8,'Embroidery Show','2020-04-30 12:00:00','Union City, Hudson County, 新泽西州, 07087, 美国','Union City',40.7795455,-74.0237512,100,'We are going to open a public show so that everyone can know the fascination of the embroidery! It\'s really beautiful and attractive!',0,0,'Union City Art Association','47efbbae-db7a-4b83-be26-a5d8b727e103',3),(37,2,'Badminton Competition','2020-05-14 12:00:00','Stevens Institute of Technology, Babbio Drive, Hoboken, Hudson County, 新泽西州, 07030, 美国','Hoboken',40.744809599999996,-74.02524356385591,20,'We want to hold a competition for badminton! Anyone who is interested can join only if you are a stevens student! Others can come and watch this competiton! There will be reward in the end!',0,0,'SIT Sports Association','5d9acc7e-3fbb-4a81-b7de-cee4d692059b',3),(38,6,'Book Selling with signature','2020-04-30 12:00:00','Newport, Cumberland County, 新泽西州, 08345, 美国','',39.2967811,-75.1765748,200,'We are selling books written by J. K. Rowling. Also, she will appear on the scene to sign for the books. If you like these books or you want to have a meet with her, just come and join!',0,50,'Newport Reading Association','86c4f891-a9c6-4165-a51d-c50bddcce398',1),(39,5,'Photography Intro Course','2020-05-07 12:00:00','West New York, Hudson County, 新泽西州, 美国','West New York',40.785529,-74.00830025,20,'We are going to hold a photography intro course to teach everyone who wants to  learn how to take good photos!',0,1500,'West New York Photography Society','96af9f49-4359-4ee0-b9dd-cc301c3dee3d',1),(40,5,'Photography Exhibition','2020-05-31 12:00:00','West New York, Hudson County, 新泽西州, 美国','West New York',40.785529,-74.00830025,200,'We are holding a photography exhibition. There will be many beautiful photos that beyond your imagination.',0,0,'West New York Photography Society','f4b8c916-8f07-4fee-a9bb-34fd4e779445',1);
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follower`
--

DROP TABLE IF EXISTS `follower`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follower` (
  `user_id` int NOT NULL,
  `hobby_id` int NOT NULL,
  `on_datetime` datetime NOT NULL,
  KEY `fk_user_has_hobby_hobby1_idx` (`hobby_id`),
  KEY `fk_user_has_hobby_user1_idx` (`user_id`),
  CONSTRAINT `fk_user_has_hobby_hobby1` FOREIGN KEY (`hobby_id`) REFERENCES `hobby` (`id`),
  CONSTRAINT `fk_user_has_hobby_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follower`
--

LOCK TABLES `follower` WRITE;
/*!40000 ALTER TABLE `follower` DISABLE KEYS */;
/*!40000 ALTER TABLE `follower` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hobby`
--

DROP TABLE IF EXISTS `hobby`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hobby` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `category_id` int NOT NULL,
  `plus18_only` tinyint DEFAULT NULL,
  `photo_id` varchar(42) NOT NULL,
  `created_by_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `fk_hobby_category_idx` (`category_id`),
  KEY `fk_hobby_user1_idx` (`created_by_id`),
  CONSTRAINT `fk_hobby_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `fk_hobby_user1` FOREIGN KEY (`created_by_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hobby`
--

LOCK TABLES `hobby` WRITE;
/*!40000 ALTER TABLE `hobby` DISABLE KEYS */;
INSERT INTO `hobby` VALUES (1,'Jogging','',4,0,'03d81111-da86-4c7c-966d-be3e4f3b4cdf',1),(2,'Badminton','Badminton is a racquet sport played using racquets to hit a shuttlecock across a net. The most common forms of the game are \"singles\" and \"doubles\" Badminton is often played as a casual outdoor activity in a yard or on a beach.',4,0,'5ae515eb-1f84-487d-9702-0718ee640b08',2),(3,'Baseball','A bat-and-ball game played between two opposing teams who take turns batting and fielding. One team need to hit the ball into the field of play, and run the bases. One need to prevent runners\' advance around the bases.',4,0,'beadca2b-cbe8-4921-8cc3-175ec1fa8f47',3),(4,'Drawing','Drawing is a form of visual art in which a person uses various drawing instruments to mark paper or another two-dimensional medium. ',1,0,'0d3b378b-5423-42d8-a612-b57aa7a5ac31',4),(5,'Photograpghy','Photography is the art, application and practice of creating durable images by recording light or other electromagnetic radiation, by means of an image sensor, or by means of a light-sensitive material such as photographic film.',1,0,'710cbef6-d7b7-4d9e-bb93-f7cf47b29154',5),(6,'Reading','Reading is an activity that uses language to obtain information, understand the world, develop thinking, and obtain aesthetic experience and knowledge',2,0,'480242e1-30a8-48a6-a6f1-ca6c61d51e9f',5),(7,'Fitness','Fitness is a sport that can increase strength, flexibility, endurance, coordination, and the ability to control all parts of the body to make the body strong.',4,0,'6c378ffa-4bae-4fc6-9ff4-fd0d0799edab',5),(8,'Embroidery','Embroidery is an art of decorative fabrics that use needles to pierce silk threads or other fibers and yarns with a certain pattern and color on the embroidering material and form patterns with embroidery',1,0,'45d780a1-7bb5-4439-a3e8-9868b7c25bf1',5),(9,'Guitar','The guitar can be filled with youthful melody, play a soothing music space, can express joy and sorrow, can also cultivate solemn and elegant temperament charm, beautiful sound and chic playing can add its own charm',1,0,'489f509a-fd6b-4005-9963-2ada9ceb6dec',5),(10,'Basketball','Basketball is the core event of the Olympic Games, and it is a hand-centered physical confrontation sports',4,0,'be8e8b7e-05c3-426e-b0f9-faf5c5d6a680',3),(11,'Diving','Diving is the sport of jumping or falling into water from a platform or springboard. Diving is an internationally recognized sport that is part of the Olympic Games. In addition, unstructured and non-competitive diving is a recreational pastime.',4,0,'5fb01c06-f7f8-46d2-9c54-35e39287cc8f',3),(12,'Electronic Sports','E-sports is the use of electronic devices as sports equipment to conduct intellectual confrontation between people. You can exercise and improve thinking ability, reaction ability, limb coordination ability, willpower and cultivate team spirit.',3,0,'b88df850-39bc-4235-94bc-265a52c07fb2',3),(13,'Baking','Baking is based on cereals, oils, sugar, eggs, etc. as raw materials, adding appropriate amounts of auxiliary materials, and through the process of noodles, molding, baking and other foods with diverse tastes and rich nutrition.',3,0,'5f425cd9-f833-4a9d-b4b9-9a9eb6be0a28',3),(14,'Table tennis','Table tennis is a popular ball game in many parts of the world. Players of both sides are playing against each other with a racket and a net table. The two sides hit each other until one side cannot return to the ball, and the other can score easily.',4,0,'0bf3d23f-e849-489f-8df8-74805e6c3bd2',3),(15,'Calligraphy','Calligraphy is an art that gives symbols a harmonious, expressive and smart style. Calligraphy penetrates two levels longitudinally: \"use\" and \"aesthetic\"; horizontally spans two levels of text: \"text\" and \"painting\".',2,0,'497a06f4-272f-409c-9642-5ce6f8813ac0',3),(16,'Chess','Chess is a two-player tactical board game and one of the most popular in the world. Millions of people around the world play in the form of communication chess or games at home, in clubs, and on the Internet.',3,0,'c04cb4f3-e6c2-4136-bb14-25c0d2bdf062',3),(17,'Rasing cats','Raising a cat requires that you provide not only for its most basic needs but also for its health, its comfort, and its mind. Interested in raising a cat, take the time to understand the commitment you are making and learn how to raise it right.',3,0,'d619db8f-3dd5-4482-a2af-c43511f94edb',1),(18,'Raising orchids','To grow an orchid, you have to think like an orchid. The golden rule for orchid success is to duplicate the plant\'s natural conditions.The showy orchids are usually either phalaenopsis hybrids (so-called moth orchids) or dendrobium hybrids.',1,0,'e7cfd127-af92-42bd-a488-0356d0a611db',1),(19,'Jigsaw Puzzle','Jigsaw is a game that solves the problem of filling and arranging plane spaces. Players are required to group hundreds of flat zero pieces with partial patterns on them. All pieces are joined to form a geometric plane. Complete pattern.',2,0,'b370ad84-14d1-4f97-88f8-69fc9723ac6d',1),(20,'Watching films','Film is a continuous image developed by the combination of active photography and slide show. It is a visual and auditory modern art. It is also a theater, photography, painting, music, dance, text, sculpture , Architecture, and other art complexes.',3,0,'053a6724-1a15-4e65-8e00-90c90f1762ce',1);
/*!40000 ALTER TABLE `hobby` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participation`
--

DROP TABLE IF EXISTS `participation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `participation` (
  `user_id` int NOT NULL,
  `event_id` int NOT NULL,
  `requested_on_datetime` datetime DEFAULT NULL,
  `approved_on_datetime` datetime DEFAULT NULL,
  `participated_on_datetime` datetime DEFAULT NULL,
  KEY `fk_user_has_event_event1_idx` (`event_id`),
  KEY `fk_user_has_event_user1_idx` (`user_id`),
  CONSTRAINT `fk_user_has_event_event1` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`),
  CONSTRAINT `fk_user_has_event_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participation`
--

LOCK TABLES `participation` WRITE;
/*!40000 ALTER TABLE `participation` DISABLE KEYS */;
/*!40000 ALTER TABLE `participation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `id` int NOT NULL AUTO_INCREMENT,
  `owner_type` enum('event','blog') NOT NULL,
  `owner_id` int NOT NULL,
  `parent_id` int DEFAULT NULL,
  `by_user_id` int NOT NULL,
  `content` varchar(256) NOT NULL,
  `on_datetime` datetime NOT NULL,
  `rate` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_review_user1_idx` (`by_user_id`),
  KEY `fk_review_review1_idx` (`parent_id`),
  CONSTRAINT `fk_review_review1` FOREIGN KEY (`parent_id`) REFERENCES `review` (`id`),
  CONSTRAINT `fk_review_user1` FOREIGN KEY (`by_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(64) NOT NULL,
  `last_name` varchar(64) DEFAULT NULL,
  `photo_id` varchar(42) DEFAULT NULL,
  `nick_name` varchar(32) DEFAULT NULL,
  `email` varchar(64) NOT NULL,
  `password` varchar(256) NOT NULL,
  `date_of_birth` varchar(45) DEFAULT NULL,
  `gender` char(16) DEFAULT NULL,
  `permissions` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `email_addr_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Jafar','Abbasi','57ab0aef-a81f-4065-831b-ad4f3c95cd4f','jafar','jafar@gmail.com','$2a$10$X7LSWNZuN8V1jrPWeWxU8eQEDToMmZWL1g1wn8NjYgUdIiRAPqkKe','1991-03-03 13:00:00.0','Male',NULL),(2,'Jerry','Wang','c8fb681e-f6c1-4507-9edd-1dffd429a657','jerry','jerry@gmail.com','$2a$10$45UIre/M5/6NE5eKJ1uRx.OZ/pDS6yB74VnDXRHVWcIfxJ5dPtTAu','1991-12-27 13:00:00.0','Male',NULL),(3,'James','Clinton','a3bb3738-0829-49e5-b041-24226cd3ede1','james','james@gmail.com','$2a$10$4vk/RcZrsoyBY.uhx370gOy2IgaphgjdUL8JbDXwJDo3wFOAYEDZq','1991-03-03 13:00:00.0','Male',NULL),(4,'Jane','Carter',NULL,'jane','jane@gmail.com','$2a$10$TPV41eqa2T9pj.pUVRgNoOfiieZsLPhszSUWrfThWvMKgYh3yTaXC','1991-03-03 13:00:00.0',NULL,NULL),(5,'Ann','Rice',NULL,'ann','ann@gmail.com','$2a$10$TCaj8pG7Meitj064ViuSjuYDFm1/kcl7naC0RvrktOAI7PjbUVl0W','1991-03-03 13:00:00.0',NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-23  0:59:01
