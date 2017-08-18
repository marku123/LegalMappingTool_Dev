-- MySQL dump 10.13  Distrib 5.5.55, for Linux (x86_64)
--
-- Host: localhost    Database: ukramcom_unhcr_legaltool
-- ------------------------------------------------------
-- Server version	5.5.55-cll

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
-- Table structure for table `analytical_narrative`
--

DROP TABLE IF EXISTS `analytical_narrative`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `analytical_narrative` (
  `CountryName` varchar(50) NOT NULL,
  `Narrative1` text,
  `Narrative2` text,
  `Narrative3` text,
  PRIMARY KEY (`CountryName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `countries`
--

DROP TABLE IF EXISTS `countries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `countries` (
  `CountryName` varchar(100) NOT NULL,
  `CountryIndex` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`CountryName`),
  UNIQUE KEY `CountryName_UNIQUE` (`CountryName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedback` (
  `CountryName` varchar(50) DEFAULT NULL,
  `UserName` varchar(100) DEFAULT NULL,
  `FeedbackText` text,
  `FileName` varchar(100) DEFAULT NULL,
  `TimeStamp` varchar(100) NOT NULL,
  PRIMARY KEY (`TimeStamp`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `legalframework_a1_const_app_poc`
--

DROP TABLE IF EXISTS `legalframework_a1_const_app_poc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `legalframework_a1_const_app_poc` (
  `CountryName` varchar(50) NOT NULL,
  `PoC` varchar(35) NOT NULL,
  `RightsApplyPoc` varchar(10) DEFAULT NULL,
  `Comments` text,
  PRIMARY KEY (`CountryName`,`PoC`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `legalframework_a1_const_intro`
--

DROP TABLE IF EXISTS `legalframework_a1_const_intro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `legalframework_a1_const_intro` (
  `CountryName` varchar(50) NOT NULL,
  `Constitution` varchar(3) DEFAULT '',
  `ConstitutionDate` varchar(10) DEFAULT '',
  `ConstitutionAmend` varchar(3) NOT NULL,
  `ConstitutionAmendDate` varchar(10) DEFAULT '',
  `ConstLinkOrig` varchar(300) DEFAULT NULL,
  `ConstLinkFrenEng` varchar(300) DEFAULT NULL,
  `BillLinkOrig` varchar(300) DEFAULT NULL,
  `BillLinkFrenEng` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`CountryName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `legalframework_a2_legalsystem_admin`
--

DROP TABLE IF EXISTS `legalframework_a2_legalsystem_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `legalframework_a2_legalsystem_admin` (
  `CountryName` varchar(50) NOT NULL,
  `AdminEntityIndex` varchar(2) NOT NULL,
  `AdminEntityName` varchar(100) DEFAULT NULL,
  `RefugessCanAccess` varchar(8) DEFAULT NULL,
  `IDPCanAccess` varchar(8) DEFAULT NULL,
  `ReturneesCanAccess` varchar(8) DEFAULT NULL,
  `StatelessCanAccess` varchar(8) DEFAULT NULL,
  `AsylumCanAccess` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`CountryName`,`AdminEntityIndex`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `legalframework_a2_legalsystem_intro`
--

DROP TABLE IF EXISTS `legalframework_a2_legalsystem_intro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `legalframework_a2_legalsystem_intro` (
  `CountryName` varchar(50) NOT NULL,
  `CommonCivilPlural` varchar(7) DEFAULT NULL,
  `PluralText` text,
  `FederalState` varchar(8) DEFAULT NULL,
  `TradMechComments` text NOT NULL,
  `Comments` text,
  PRIMARY KEY (`CountryName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `legalframework_a2_legalsystem_judicial`
--

DROP TABLE IF EXISTS `legalframework_a2_legalsystem_judicial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `legalframework_a2_legalsystem_judicial` (
  `CountryName` varchar(50) NOT NULL,
  `EntityCourtIndex` varchar(2) NOT NULL,
  `JudicialEntityCourt` varchar(100) DEFAULT NULL,
  `RefugessCanAccess` varchar(8) DEFAULT NULL,
  `IDPCanAccess` varchar(8) DEFAULT NULL,
  `ReturneesCanAccess` varchar(8) DEFAULT NULL,
  `StatelessCanAccess` varchar(8) DEFAULT NULL,
  `AsylumCanAccess` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`CountryName`,`EntityCourtIndex`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `legalframework_a2_legalsystem_trad`
--

DROP TABLE IF EXISTS `legalframework_a2_legalsystem_trad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `legalframework_a2_legalsystem_trad` (
  `CountryName` varchar(50) NOT NULL,
  `TradMechIndex` varchar(2) NOT NULL,
  `TradMechName` varchar(100) DEFAULT NULL,
  `RefugessCanAccess` varchar(8) DEFAULT NULL,
  `IDPCanAccess` varchar(8) DEFAULT NULL,
  `ReturneesCanAccess` varchar(8) DEFAULT NULL,
  `StatelessCanAccess` varchar(8) DEFAULT NULL,
  `AsylumCanAccess` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`CountryName`,`TradMechIndex`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `obstacles_c1`
--

DROP TABLE IF EXISTS `obstacles_c1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `obstacles_c1` (
  `CountryName` varchar(50) NOT NULL,
  `POC` varchar(40) NOT NULL,
  `RightsGroup` varchar(70) NOT NULL,
  `LegalStatusObs` varchar(30) DEFAULT NULL,
  `LegalStatusObsComment` text,
  `LegalObsGrps` varchar(300) NOT NULL,
  `FinancialObs` varchar(30) DEFAULT NULL,
  `FinancialObsComment` text,
  `FinObsGrps` varchar(300) NOT NULL,
  `DocObs` varchar(30) DEFAULT NULL,
  `DocObsComment` text,
  `DocObsGrps` varchar(300) NOT NULL,
  `GeoObs` varchar(30) DEFAULT NULL,
  `GeoObsComment` text,
  `GeoObsGrps` varchar(300) NOT NULL,
  `AdminObs` varchar(30) DEFAULT NULL,
  `AdminObsComment` text,
  `AdminObsGrps` varchar(300) NOT NULL,
  `SecObs` varchar(30) DEFAULT NULL,
  `SecObsComment` text,
  `SecObsGrps` varchar(300) NOT NULL,
  `DiscrimObs` varchar(30) DEFAULT NULL,
  `DiscrimObsComment` text,
  `DiscrimObsGrps` varchar(300) NOT NULL,
  `OtherNameObs` varchar(200) DEFAULT NULL,
  `OtherObs` varchar(30) DEFAULT NULL,
  `OtherObsComment` text,
  `OtherObsGrps` varchar(300) NOT NULL,
  PRIMARY KEY (`CountryName`,`POC`,`RightsGroup`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `obstacles_c1_narrative`
--

DROP TABLE IF EXISTS `obstacles_c1_narrative`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `obstacles_c1_narrative` (
  `CountryName` varchar(50) NOT NULL,
  `Narrative1` text,
  `Narrative2` text,
  PRIMARY KEY (`CountryName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `obstacles_c2_documentation`
--

DROP TABLE IF EXISTS `obstacles_c2_documentation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `obstacles_c2_documentation` (
  `CountryName` varchar(50) NOT NULL,
  `POC` varchar(40) NOT NULL,
  `FileStorageName` varchar(200) NOT NULL,
  `FileDisplayName` varchar(200) DEFAULT NULL,
  `FileURL` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`POC`,`FileStorageName`,`CountryName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `poc_priorities_d4_narrative`
--

DROP TABLE IF EXISTS `poc_priorities_d4_narrative`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `poc_priorities_d4_narrative` (
  `CountryName` varchar(50) NOT NULL,
  `Narrative1` text,
  `Narrative2` text,
  PRIMARY KEY (`CountryName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rightsgroup_b1_1_international`
--

DROP TABLE IF EXISTS `rightsgroup_b1_1_international`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rightsgroup_b1_1_international` (
  `InstrumentName` varchar(200) NOT NULL,
  `InstrumentType` varchar(20) DEFAULT NULL,
  `InstrumentID` varchar(10) NOT NULL,
  `RightsGroup` varchar(60) NOT NULL,
  `Articles` text NOT NULL,
  PRIMARY KEY (`InstrumentID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rightsgroup_b1_international`
--

DROP TABLE IF EXISTS `rightsgroup_b1_international`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rightsgroup_b1_international` (
  `CountryName` varchar(50) NOT NULL,
  `RightsGroup` varchar(70) NOT NULL,
  `InstrumentName` varchar(200) NOT NULL,
  `InstrumentType` varchar(20) DEFAULT NULL,
  `Ratified` varchar(15) DEFAULT NULL,
  `Articles` text,
  `Reservations` varchar(3) DEFAULT NULL,
  `ReservationsNature` text,
  `InstrumentID` varchar(10) NOT NULL,
  PRIMARY KEY (`CountryName`,`RightsGroup`,`InstrumentID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rightsgroup_b2_national`
--

DROP TABLE IF EXISTS `rightsgroup_b2_national`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rightsgroup_b2_national` (
  `CountryName` varchar(50) NOT NULL,
  `RightsGroup` varchar(70) NOT NULL,
  `InstrumentName` varchar(100) NOT NULL,
  `RefWorldLink` varchar(300) DEFAULT NULL,
  `FileStorageName` varchar(400) NOT NULL,
  `FileDisplayName` varchar(400) NOT NULL,
  `FileURL` varchar(500) NOT NULL,
  `FederalStateLocal` varchar(8) DEFAULT NULL,
  `ConsistentStandards` varchar(10) DEFAULT NULL,
  `ConsistentStandardsComm` text NOT NULL,
  `Consistent1951` varchar(10) DEFAULT NULL,
  `Consistent1951Comm` text NOT NULL,
  `Discrimination` varchar(100) DEFAULT NULL,
  `DiscriminationOther` varchar(100) DEFAULT NULL,
  `OtherLegis` varchar(5) DEFAULT NULL,
  `OtherLegisComments` text,
  `OtherLegisChecked` varchar(300) DEFAULT NULL,
  `Articles` text,
  `ArticleComments` text NOT NULL,
  `Comments` text,
  `SupportNat` varchar(10) DEFAULT NULL,
  `RestrictNat` varchar(10) DEFAULT NULL,
  `SupportRestrictNatComm` text,
  `SupportIDP` varchar(10) DEFAULT NULL,
  `RestrictIDP` varchar(10) DEFAULT NULL,
  `SupportRestrictIDPComm` text,
  `SupportRef` varchar(10) DEFAULT NULL,
  `RestrictRef` varchar(10) DEFAULT NULL,
  `SupportRestrictRefComm` text,
  `SupportAsyl` varchar(10) DEFAULT NULL,
  `RestrictAsyl` varchar(10) DEFAULT NULL,
  `SupportRestrictAsylComm` text,
  `SupportReturn` varchar(10) DEFAULT NULL,
  `RestrictReturn` varchar(10) DEFAULT NULL,
  `SupportRestrictReturnComm` text,
  `SupportState` varchar(10) DEFAULT NULL,
  `RestrictState` varchar(10) DEFAULT NULL,
  `SupportRestrictStateComm` text,
  PRIMARY KEY (`CountryName`,`RightsGroup`,`InstrumentName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-17 17:43:34
