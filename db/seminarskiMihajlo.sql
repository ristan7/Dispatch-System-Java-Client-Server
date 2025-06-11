/*
SQLyog Community v13.3.0 (64 bit)
MySQL - 8.0.42 : Database - dispecer_sistem
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`dispecer_sistem` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `dispecer_sistem`;

/*Table structure for table `dispecer` */

DROP TABLE IF EXISTS `dispecer`;

CREATE TABLE `dispecer` (
  `idDispecera` bigint NOT NULL AUTO_INCREMENT,
  `imeDispecera` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prezimeDispecera` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `emailDispecera` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `telefonDispecera` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `korisnickoIme` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `lozinka` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`idDispecera`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `dispecer` */

insert  into `dispecer`(`idDispecera`,`imeDispecera`,`prezimeDispecera`,`emailDispecera`,`telefonDispecera`,`korisnickoIme`,`lozinka`) values 
(1,'Ana','Anic','ana.anic@example.com','0601234567','anica','lozinkaAna1!'),
(2,'Marko','Markovic','marko.markovic@example.com','0649876543','markom','tajnaMarko22!'),
(3,'Jovan','Jovanovic','jovan.jovanovic@example.com','0612223334','jovanj','jovanSifra#33'),
(4,'Maja','Mijic','maja.majic@example.com','0651122334','majam','majaPass44!'),
(5,'Milos','Milosevic','milos.milosevic@example.com','0623344556','milosm','Milos@12345'),
(6,'Mina','Petrovic','mina.petrovic@gmail.com','0607896100','minaP','minaP');

/*Table structure for table `jedinica_mere` */

DROP TABLE IF EXISTS `jedinica_mere`;

CREATE TABLE `jedinica_mere` (
  `idJedinice` bigint NOT NULL AUTO_INCREMENT,
  `nazivJedinice_oznaka` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`idJedinice`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `jedinica_mere` */

insert  into `jedinica_mere`(`idJedinice`,`nazivJedinice_oznaka`) values 
(1,'KOMAD_KOM'),
(2,'KILOGRAM_KG'),
(3,'LITAR_L'),
(4,'METAR_M'),
(5,'PALETA_PAL'),
(6,'KUTIJA_KUT'),
(7,'VRECA_VRE'),
(8,'TONA_T'),
(9,'GAJBA_GAJ'),
(10,'PAKET_PAK');

/*Table structure for table `mesto` */

DROP TABLE IF EXISTS `mesto`;

CREATE TABLE `mesto` (
  `idMesta` bigint NOT NULL AUTO_INCREMENT,
  `nazivMesta` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `drzava` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `postanskiBroj` int NOT NULL,
  PRIMARY KEY (`idMesta`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `mesto` */

insert  into `mesto`(`idMesta`,`nazivMesta`,`drzava`,`postanskiBroj`) values 
(1,'Beograd','Srbija',11000),
(2,'Novi Sad','Srbija',21000),
(3,'Nis','Srbija',18000),
(4,'Subotica','Srbija',24000),
(5,'Kragujevac','Srbija',34000),
(6,'Cacak','Srbija',32000),
(7,'Novi Pazar','Srbija',36300),
(8,'Amsterdam','Holandija',1012),
(9,'Sarajevo','BiH',71000);

/*Table structure for table `nalog_za_transport_robe` */

DROP TABLE IF EXISTS `nalog_za_transport_robe`;

CREATE TABLE `nalog_za_transport_robe` (
  `idNaloga` bigint NOT NULL AUTO_INCREMENT,
  `datumKreiranja` date NOT NULL,
  `datumIzvrsenja` date NOT NULL,
  `adresaUtovara` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `adresaIstovara` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` bigint NOT NULL,
  `ukupanIznosPosla` float NOT NULL,
  `dispecer` bigint NOT NULL,
  `poslovni_partner` bigint NOT NULL,
  PRIMARY KEY (`idNaloga`),
  KEY `dispecer` (`dispecer`),
  KEY `poslovni_partner` (`poslovni_partner`),
  KEY `status` (`status`),
  CONSTRAINT `nalog_za_transport_robe_ibfk_1` FOREIGN KEY (`dispecer`) REFERENCES `dispecer` (`idDispecera`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `nalog_za_transport_robe_ibfk_2` FOREIGN KEY (`poslovni_partner`) REFERENCES `poslovni_partner` (`idPoslovnogPartnera`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `nalog_za_transport_robe_ibfk_3` FOREIGN KEY (`status`) REFERENCES `status_naloga` (`idStatusaNaloga`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `nalog_za_transport_robe` */

insert  into `nalog_za_transport_robe`(`idNaloga`,`datumKreiranja`,`datumIzvrsenja`,`adresaUtovara`,`adresaIstovara`,`status`,`ukupanIznosPosla`,`dispecer`,`poslovni_partner`) values 
(1,'2025-06-08','2025-06-20','Bulevar Milutina Milankovica 13/6, Beograd','Bulevar Vojvode Putnika 17/2, Cacak',1,135000,1,1),
(2,'2025-05-15','2025-06-08','Bulevar Oslobodjenja 164, Beograd','Majke Jugovica 15/22 , Novi Pazar',2,58026,1,4);

/*Table structure for table `poslovni_partner` */

DROP TABLE IF EXISTS `poslovni_partner`;

CREATE TABLE `poslovni_partner` (
  `idPoslovnogPartnera` bigint NOT NULL AUTO_INCREMENT,
  `nazivPartnera` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `pib` varchar(9) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `adresaPartnera` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `emailPartnera` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `mesto` bigint NOT NULL,
  PRIMARY KEY (`idPoslovnogPartnera`),
  KEY `mesto` (`mesto`),
  CONSTRAINT `poslovni_partner_ibfk_1` FOREIGN KEY (`mesto`) REFERENCES `mesto` (`idMesta`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `poslovni_partner` */

insert  into `poslovni_partner`(`idPoslovnogPartnera`,`nazivPartnera`,`pib`,`adresaPartnera`,`emailPartnera`,`mesto`) values 
(1,'DELHAIZE SERBIA d.o.o.','100032989','Bulevar Milutina Milankovica 11b','info@delhaize.rs',1),
(2,'Imlek a.d.','070044415','Micka Antica 2','office@imlek.rs',1),
(3,'Frikom a.d.','100298204','Autoput za Novi Sad 120','info@frikom.rs',1),
(4,'Carnex d.o.o.','086259754','Industrijska 3','office@carnex.com',2),
(5,'Swisslion-Takovo d.o.o.','101983611','Save Kovacevica 30','office@swisslion.com',5),
(6,'Mlekara Subotica','101234567','Beogradski put 23','info@mlekarasubotica.rs',4),
(7,'Jaffa Crvenka','100677306','Bulevar 12. februar 9','office@jaffa.rs',3),
(16,'Amsterdam Logistics BV','','Damrak 1/21','info@amsterdamlogistics.nl',8),
(17,'Zlatiborac d.o.o','111000999','Manjak 12/50','rs@zlatiborac.rs',6);

/*Table structure for table `roba` */

DROP TABLE IF EXISTS `roba`;

CREATE TABLE `roba` (
  `idRobe` bigint NOT NULL AUTO_INCREMENT,
  `nazivRobe` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `jedinicaMere` bigint NOT NULL,
  `cena` float NOT NULL,
  PRIMARY KEY (`idRobe`),
  KEY `roba_ibfk_1` (`jedinicaMere`),
  CONSTRAINT `roba_ibfk_1` FOREIGN KEY (`jedinicaMere`) REFERENCES `jedinica_mere` (`idJedinice`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `roba` */

insert  into `roba`(`idRobe`,`nazivRobe`,`jedinicaMere`,`cena`) values 
(1,'Mleko 2.8% mm',3,125),
(2,'Voda 6x1.5l',5,450),
(3,'Brasno tip 400 1kg',2,105),
(4,'Pileci file',2,780),
(5,'Cokolada 100g',1,150),
(6,'Paleta suncokretovog ulja',6,13500),
(7,'Sok narandza 6x1l',5,720),
(8,'Toalet papir 10/1',5,380),
(9,'Jabuka crvena',2,98),
(10,'Hleb beli',1,70);

/*Table structure for table `sertifikat` */

DROP TABLE IF EXISTS `sertifikat`;

CREATE TABLE `sertifikat` (
  `idSertifikata` bigint NOT NULL AUTO_INCREMENT,
  `dispecer` bigint NOT NULL,
  `strucnaSprema` bigint NOT NULL,
  `nazivSertifikata` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `datumIzdavanja` date NOT NULL,
  PRIMARY KEY (`idSertifikata`,`dispecer`,`strucnaSprema`),
  KEY `dispecer` (`dispecer`),
  KEY `stucnaSprema` (`strucnaSprema`),
  CONSTRAINT `sertifikat_ibfk_1` FOREIGN KEY (`dispecer`) REFERENCES `dispecer` (`idDispecera`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sertifikat_ibfk_2` FOREIGN KEY (`strucnaSprema`) REFERENCES `strucna_sprema` (`idStrucneSpreme`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `sertifikat` */

insert  into `sertifikat`(`idSertifikata`,`dispecer`,`strucnaSprema`,`nazivSertifikata`,`datumIzdavanja`) values 
(1,1,1,'Sertifikat za rad na racunaru','2022-09-01'),
(2,1,8,'ADR Sertifikat (prevoz opasne robe)','2023-05-15'),
(3,1,10,'Sertifikat iz logistike','2024-02-20'),
(4,2,5,'Sertifikat iz osnova bezbednosti saobracaja','2021-11-12'),
(5,2,12,'Sertifikat za upravljanje transportom','2023-07-01'),
(6,3,3,'Sertifikat za GPS pracenje voznog parka','2024-01-30'),
(7,4,5,'Engleski jezik B2','2023-03-22'),
(8,4,11,'ISO 9001:2015','2022-12-10'),
(9,5,4,'Sertifikat za rad sa telematikom','2024-04-01');

/*Table structure for table `status_naloga` */

DROP TABLE IF EXISTS `status_naloga`;

CREATE TABLE `status_naloga` (
  `idStatusaNaloga` bigint NOT NULL AUTO_INCREMENT,
  `nazivStatusa` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`idStatusaNaloga`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `status_naloga` */

insert  into `status_naloga`(`idStatusaNaloga`,`nazivStatusa`) values 
(1,'U_PRIPREMI'),
(2,'U_TRANSPORTU'),
(3,'ISPORUCENO'),
(4,'OTKAZANO');

/*Table structure for table `stavka_naloga` */

DROP TABLE IF EXISTS `stavka_naloga`;

CREATE TABLE `stavka_naloga` (
  `nalog` bigint NOT NULL,
  `rb` bigint NOT NULL AUTO_INCREMENT,
  `kolicina` float NOT NULL,
  `cenaPoJedinici` float NOT NULL,
  `iznos` float NOT NULL,
  `roba` bigint NOT NULL,
  PRIMARY KEY (`nalog`,`rb`),
  KEY `rb` (`rb`),
  KEY `roba` (`roba`),
  CONSTRAINT `stavka_naloga_ibfk_1` FOREIGN KEY (`nalog`) REFERENCES `nalog_za_transport_robe` (`idNaloga`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `stavka_naloga_ibfk_2` FOREIGN KEY (`roba`) REFERENCES `roba` (`idRobe`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `stavka_naloga` */

insert  into `stavka_naloga`(`nalog`,`rb`,`kolicina`,`cenaPoJedinici`,`iznos`,`roba`) values 
(1,1,10,13500,135000,6),
(2,2,3,450,1350,2),
(2,3,10,150,1500,5),
(2,4,12,98,1176,9),
(2,5,4,13500,54000,6);

/*Table structure for table `strucna_sprema` */

DROP TABLE IF EXISTS `strucna_sprema`;

CREATE TABLE `strucna_sprema` (
  `idStrucneSpreme` bigint NOT NULL AUTO_INCREMENT,
  `nazivStrucneSpreme` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tipStrucneSpreme` bigint NOT NULL,
  PRIMARY KEY (`idStrucneSpreme`),
  KEY `tipStrucneSpreme` (`tipStrucneSpreme`),
  CONSTRAINT `strucna_sprema_ibfk_1` FOREIGN KEY (`tipStrucneSpreme`) REFERENCES `tip_strucne_spreme` (`idTipaSpreme`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `strucna_sprema` */

insert  into `strucna_sprema`(`idStrucneSpreme`,`nazivStrucneSpreme`,`tipStrucneSpreme`) values 
(1,'Saobracajna skola',1),
(2,'Ekonomska skola',1),
(3,'Tehnicka skola',1),
(4,'Gimnazija',1),
(5,'Visa saobracajna skola',2),
(6,'Visa tehnicka skola',2),
(7,'Visa poslovna skola',2),
(8,'Fakultet saobracaja',3),
(9,'Fakultet organizacionih nauka',3),
(10,'Fakultet tehnickih nauka',3),
(11,'Fakultet ekonomije',3),
(12,'Master akademske studije',4),
(13,'Doktorske studije',5),
(33,'Visa ekonomska skola',2);

/*Table structure for table `tip_strucne_spreme` */

DROP TABLE IF EXISTS `tip_strucne_spreme`;

CREATE TABLE `tip_strucne_spreme` (
  `idTipaSpreme` bigint NOT NULL AUTO_INCREMENT,
  `nazivTipaSpreme` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`idTipaSpreme`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `tip_strucne_spreme` */

insert  into `tip_strucne_spreme`(`idTipaSpreme`,`nazivTipaSpreme`) values 
(1,'SREDNJA'),
(2,'VISA_STRUCNA_SPREMA'),
(3,'VISOKA_STRUCNA_SPREMA'),
(4,'MASTER'),
(5,'DOKTORAT');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
