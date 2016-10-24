CREATE DATABASE  IF NOT EXISTS `drca` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `drca`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: drca
-- ------------------------------------------------------
-- Server version	5.7.16-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `aluno`
--

DROP TABLE IF EXISTS `aluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aluno` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `creditos_eletivos` int(11) NOT NULL,
  `creditos_obrigatorios` int(11) NOT NULL,
  `curso_id` bigint(20) NOT NULL,
  `matricula` int(11) NOT NULL,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9o09o8qj4x9uf9okvf622jyec` (`curso_id`),
  CONSTRAINT `FK9o09o8qj4x9uf9okvf622jyec` FOREIGN KEY (`curso_id`) REFERENCES `curso` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aluno`
--

LOCK TABLES `aluno` WRITE;
/*!40000 ALTER TABLE `aluno` DISABLE KEYS */;
INSERT INTO `aluno` VALUES (1,4,50,130,1,98124812,'Alex Carvalho'),(2,0,0,100,1,9924812,'Jurema Torres'),(3,3,0,0,2,9915918,'José Vasconcelos'),(4,0,0,0,3,9914918,'João da Silva'),(5,1,0,0,4,12398,'Maria Antônia');
/*!40000 ALTER TABLE `aluno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aluno_disciplina`
--

DROP TABLE IF EXISTS `aluno_disciplina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aluno_disciplina` (
  `aluno_disciplinas_id` bigint(20) NOT NULL,
  `disciplina_id` bigint(20) DEFAULT NULL,
  KEY `FK4jb59gslc8wuq03saq52mjoy4` (`disciplina_id`),
  KEY `FKobbmjofyf358vrfaa0g9v1hly` (`aluno_disciplinas_id`),
  CONSTRAINT `FK4jb59gslc8wuq03saq52mjoy4` FOREIGN KEY (`disciplina_id`) REFERENCES `disciplina` (`id`),
  CONSTRAINT `FKobbmjofyf358vrfaa0g9v1hly` FOREIGN KEY (`aluno_disciplinas_id`) REFERENCES `aluno` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aluno_disciplina`
--

LOCK TABLES `aluno_disciplina` WRITE;
/*!40000 ALTER TABLE `aluno_disciplina` DISABLE KEYS */;
INSERT INTO `aluno_disciplina` VALUES (1,2),(1,4),(1,1),(2,2),(2,5);
/*!40000 ALTER TABLE `aluno_disciplina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curso`
--

DROP TABLE IF EXISTS `curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curso` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `secretaria_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7g10wfacu27s0dsa4b9clyh1o` (`secretaria_id`),
  CONSTRAINT `FK7g10wfacu27s0dsa4b9clyh1o` FOREIGN KEY (`secretaria_id`) REFERENCES `secretaria` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curso`
--

LOCK TABLES `curso` WRITE;
/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
INSERT INTO `curso` VALUES (1,0,'Engenharia de Computação',4),(2,0,'Bacharelado em Computação',4),(3,0,'Doutorado em Informática',5),(4,0,'Português e Inglês',6);
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departamento`
--

DROP TABLE IF EXISTS `departamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `departamento` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `drca_id` bigint(20) NOT NULL,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlhf31cerv8l3iob71qvnir23a` (`drca_id`),
  CONSTRAINT `FKlhf31cerv8l3iob71qvnir23a` FOREIGN KEY (`drca_id`) REFERENCES `drca` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departamento`
--

LOCK TABLES `departamento` WRITE;
/*!40000 ALTER TABLE `departamento` DISABLE KEYS */;
INSERT INTO `departamento` VALUES (1,0,1,'Departamento de Informática'),(2,0,1,'Departamento de Letras');
/*!40000 ALTER TABLE `departamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `disciplina`
--

DROP TABLE IF EXISTS `disciplina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `disciplina` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `codigo` varchar(255) NOT NULL,
  `creditos` int(11) NOT NULL,
  `curso_id` bigint(20) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `obrigatoria` bit(1) NOT NULL,
  `oferecida` bit(1) NOT NULL,
  `pre_requisitos` int(11) NOT NULL,
  `professor_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkhdiw1swjoa2ml3md0mt8g4sf` (`curso_id`),
  KEY `FKoqie7f1kx32b1atco9fpgxd7g` (`professor_id`),
  CONSTRAINT `FKkhdiw1swjoa2ml3md0mt8g4sf` FOREIGN KEY (`curso_id`) REFERENCES `curso` (`id`),
  CONSTRAINT `FKoqie7f1kx32b1atco9fpgxd7g` FOREIGN KEY (`professor_id`) REFERENCES `professor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disciplina`
--

LOCK TABLES `disciplina` WRITE;
/*!40000 ALTER TABLE `disciplina` DISABLE KEYS */;
INSERT INTO `disciplina` VALUES (1,0,'INF 1622',70,1,'Laboratório de Programação I','','',0,1),(2,0,'INF 1620',60,1,'Estruturas de Dados','','\0',0,2),(3,0,'INF 1624',40,1,'Projeto de Sistema de Software I','\0','',0,3),(4,1,'INF 1628',50,1,'Programação em Ponto Grande','\0','',0,1),(5,1,'INF 1001',40,2,'Introdução a Ciência da Computação','','',0,6),(6,0,'INF 1002',40,2,'Cálculo Numérico','','',100,2),(7,0,'INF 1600',50,2,'Software Básico','\0','',40,1),(8,0,'INF 1303',30,3,'Hipermídia Adaptativa','\0','',0,4),(9,0,'LET 1501',30,4,'Inglês I','','',0,5);
/*!40000 ALTER TABLE `disciplina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `disciplina_disciplina`
--

DROP TABLE IF EXISTS `disciplina_disciplina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `disciplina_disciplina` (
  `disciplina_id` bigint(20) DEFAULT NULL,
  `disciplina_disciplinas_requisito_id` bigint(20) NOT NULL,
  KEY `FKrwo3k4o49ado9axs9rqa7g4ob` (`disciplina_id`),
  KEY `FK3umkrxelln2q8miyfms5m69p9` (`disciplina_disciplinas_requisito_id`),
  CONSTRAINT `FK3umkrxelln2q8miyfms5m69p9` FOREIGN KEY (`disciplina_disciplinas_requisito_id`) REFERENCES `disciplina` (`id`),
  CONSTRAINT `FKrwo3k4o49ado9axs9rqa7g4ob` FOREIGN KEY (`disciplina_id`) REFERENCES `disciplina` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disciplina_disciplina`
--

LOCK TABLES `disciplina_disciplina` WRITE;
/*!40000 ALTER TABLE `disciplina_disciplina` DISABLE KEYS */;
INSERT INTO `disciplina_disciplina` VALUES (2,1),(2,3),(4,3),(2,4),(4,3);
/*!40000 ALTER TABLE `disciplina_disciplina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `drca`
--

DROP TABLE IF EXISTS `drca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `drca` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drca`
--

LOCK TABLES `drca` WRITE;
/*!40000 ALTER TABLE `drca` DISABLE KEYS */;
INSERT INTO `drca` VALUES (1,0,'PUC-Rio');
/*!40000 ALTER TABLE `drca` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matricula`
--

DROP TABLE IF EXISTS `matricula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `matricula` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matricula`
--

LOCK TABLES `matricula` WRITE;
/*!40000 ALTER TABLE `matricula` DISABLE KEYS */;
/*!40000 ALTER TABLE `matricula` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professor`
--

DROP TABLE IF EXISTS `professor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `professor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `departamento_id` bigint(20) NOT NULL,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9p7i98r41ujcw10nsu9qlcjak` (`departamento_id`),
  CONSTRAINT `FK9p7i98r41ujcw10nsu9qlcjak` FOREIGN KEY (`departamento_id`) REFERENCES `departamento` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professor`
--

LOCK TABLES `professor` WRITE;
/*!40000 ALTER TABLE `professor` DISABLE KEYS */;
INSERT INTO `professor` VALUES (1,0,1,'Arndt Von Staa'),(2,0,1,'Marcus Poggi'),(3,0,1,'Carlos Lucena'),(4,0,1,'Daniel Schawbe'),(5,0,2,'Ângela Souza'),(6,0,1,'Bruno Feijó');
/*!40000 ALTER TABLE `professor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `secretaria`
--

DROP TABLE IF EXISTS `secretaria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `secretaria` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `departamento_id` bigint(20) NOT NULL,
  `grad` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlv4na8xinaxesa8e6upbaprco` (`departamento_id`),
  CONSTRAINT `FKlv4na8xinaxesa8e6upbaprco` FOREIGN KEY (`departamento_id`) REFERENCES `departamento` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `secretaria`
--

LOCK TABLES `secretaria` WRITE;
/*!40000 ALTER TABLE `secretaria` DISABLE KEYS */;
INSERT INTO `secretaria` VALUES (4,0,1,''),(5,0,1,'\0'),(6,0,2,'');
/*!40000 ALTER TABLE `secretaria` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-23 20:47:58
