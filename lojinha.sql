-- MySQL dump 10.13  Distrib 8.0.28, for Linux (x86_64)
--
-- Host: localhost    Database: prjtLojinha
-- ------------------------------------------------------
-- Server version	8.0.28-0ubuntu0.21.10.4

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Categoria`
--

DROP TABLE IF EXISTS `Categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Categoria` (
  `IdCategoria` varchar(50) NOT NULL,
  `NomeCtgr` varchar(90) NOT NULL,
  `EstoqueCtgr` varchar(10) NOT NULL,
  PRIMARY KEY (`IdCategoria`),
  UNIQUE KEY `nome` (`NomeCtgr`),
  KEY `EstoqueCtgr` (`EstoqueCtgr`),
  CONSTRAINT `Categoria_ibfk_1` FOREIGN KEY (`EstoqueCtgr`) REFERENCES `Estoque` (`IdEstoque`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Categoria`
--

LOCK TABLES `Categoria` WRITE;
/*!40000 ALTER TABLE `Categoria` DISABLE KEYS */;
/*!40000 ALTER TABLE `Categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Cliente`
--

DROP TABLE IF EXISTS `Cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Cliente` (
  `IdUsuario` varchar(50) NOT NULL,
  `NomeUsr` varchar(18) NOT NULL,
  `SenhaUsr` varchar(20) NOT NULL,
  `ClienteP` varchar(12) NOT NULL,
  PRIMARY KEY (`IdUsuario`),
  UNIQUE KEY `NomeUsr` (`NomeUsr`),
  UNIQUE KEY `SenhaUsr` (`SenhaUsr`),
  KEY `ClienteP` (`ClienteP`),
  CONSTRAINT `Cliente_ibfk_1` FOREIGN KEY (`ClienteP`) REFERENCES `Pessoa` (`Rg`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cliente`
--

LOCK TABLES `Cliente` WRITE;
/*!40000 ALTER TABLE `Cliente` DISABLE KEYS */;
INSERT INTO `Cliente` VALUES ('rafalm19[B@4cb2c100','rafalm19','[B@4cb2c100','23435421290');
/*!40000 ALTER TABLE `Cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Endereco`
--

DROP TABLE IF EXISTS `Endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Endereco` (
  `IdEndereco` varchar(12) NOT NULL,
  `NumCep` varchar(9) NOT NULL,
  `NumCasa` int NOT NULL,
  `Complemento` varchar(20) DEFAULT NULL,
  `PontoRef` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`IdEndereco`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Endereco`
--

LOCK TABLES `Endereco` WRITE;
/*!40000 ALTER TABLE `Endereco` DISABLE KEYS */;
INSERT INTO `Endereco` VALUES ('end1','91141-412',57,'',''),('end2','6074124',151,'','Ao lado da lojinha'),('end3','6074124',306,'B','Esquina com a lojinha');
/*!40000 ALTER TABLE `Endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Estoque`
--

DROP TABLE IF EXISTS `Estoque`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Estoque` (
  `IdEstoque` varchar(10) NOT NULL,
  `GerenteEstq` varchar(7) NOT NULL,
  PRIMARY KEY (`IdEstoque`),
  KEY `GerenteEstq` (`GerenteEstq`),
  CONSTRAINT `Estoque_ibfk_1` FOREIGN KEY (`GerenteEstq`) REFERENCES `Gerente` (`IdGerente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Estoque`
--

LOCK TABLES `Estoque` WRITE;
/*!40000 ALTER TABLE `Estoque` DISABLE KEYS */;
/*!40000 ALTER TABLE `Estoque` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Funcionario`
--

DROP TABLE IF EXISTS `Funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Funcionario` (
  `Matricula` varchar(15) NOT NULL,
  `Setor` varchar(50) NOT NULL,
  `GerenteFncr` varchar(7) NOT NULL,
  `FuncionarioP` varchar(12) NOT NULL,
  PRIMARY KEY (`Matricula`),
  KEY `FuncionarioP` (`FuncionarioP`),
  CONSTRAINT `Funcionario_ibfk_1` FOREIGN KEY (`FuncionarioP`) REFERENCES `Pessoa` (`Rg`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Funcionario`
--

LOCK TABLES `Funcionario` WRITE;
/*!40000 ALTER TABLE `Funcionario` DISABLE KEYS */;
/*!40000 ALTER TABLE `Funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Gerente`
--

DROP TABLE IF EXISTS `Gerente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Gerente` (
  `IdGerente` varchar(12) NOT NULL,
  `GerenteP` varchar(12) NOT NULL,
  PRIMARY KEY (`IdGerente`),
  KEY `GerenteP` (`GerenteP`),
  CONSTRAINT `Gerente_ibfk_1` FOREIGN KEY (`GerenteP`) REFERENCES `Pessoa` (`Rg`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Gerente`
--

LOCK TABLES `Gerente` WRITE;
/*!40000 ALTER TABLE `Gerente` DISABLE KEYS */;
INSERT INTO `Gerente` VALUES ('2597129743','2597129743');
/*!40000 ALTER TABLE `Gerente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Pessoa`
--

DROP TABLE IF EXISTS `Pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Pessoa` (
  `NomePessoa` varchar(50) NOT NULL,
  `Rg` varchar(12) NOT NULL,
  `Cpf` varchar(11) NOT NULL,
  `Email` varchar(90) NOT NULL,
  `Telefone` varchar(15) NOT NULL,
  `IdadePessoa` int NOT NULL,
  `EnderecoPessoa` varchar(12) NOT NULL,
  PRIMARY KEY (`Rg`),
  UNIQUE KEY `Cpf` (`Cpf`),
  KEY `EnderecoPessoa` (`EnderecoPessoa`),
  CONSTRAINT `Pessoa_ibfk_1` FOREIGN KEY (`EnderecoPessoa`) REFERENCES `Endereco` (`IdEndereco`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Pessoa`
--

LOCK TABLES `Pessoa` WRITE;
/*!40000 ALTER TABLE `Pessoa` DISABLE KEYS */;
INSERT INTO `Pessoa` VALUES ('Rafael Lima','23435421290','2353143214','limarafa@hotmail.com','+55 81 55321267',19,'end3'),('Alex Blue','2597129743','9103413341','blues@work.com','+55 85 41113201',36,'end1');
/*!40000 ALTER TABLE `Pessoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Produto`
--

DROP TABLE IF EXISTS `Produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Produto` (
  `IdProduto` varchar(12) NOT NULL,
  `NomePrdt` varchar(90) NOT NULL,
  `CategoriaPrdt` varchar(50) NOT NULL,
  `QuantMax` int NOT NULL,
  `QuantAt` int NOT NULL,
  `QuantMin` int NOT NULL,
  `Valor` decimal(10,0) NOT NULL,
  `EstoquePrdt` varchar(10) NOT NULL,
  PRIMARY KEY (`IdProduto`),
  KEY `categoria` (`CategoriaPrdt`),
  KEY `EstoquePrdt` (`EstoquePrdt`),
  CONSTRAINT `Produto_ibfk_1` FOREIGN KEY (`CategoriaPrdt`) REFERENCES `Categoria` (`IdCategoria`),
  CONSTRAINT `Produto_ibfk_2` FOREIGN KEY (`EstoquePrdt`) REFERENCES `Estoque` (`IdEstoque`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Produto`
--

LOCK TABLES `Produto` WRITE;
/*!40000 ALTER TABLE `Produto` DISABLE KEYS */;
/*!40000 ALTER TABLE `Produto` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-02 11:15:46
