/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class Queries {

    //-------------------------------ZONE---------------------------------------
    public static final String SELECT_ALL_ZONES = "SELECT * FROM tblZone";

    //--------------------------- LINE & COLOR ---------------------------------  
    public static final String SELECT_LINE_AND_COLOR = "SELECT * FROM tblLine As L "
            + "join tblLineColor As LC on L.name = LC.lineName WHERE L.name = ?";

    //INSERT LINE transaction
    public static final String INSERT_LINE = "INSERT INTO tblLine VALUES (?,?,?,?)";
    public static final String INSERT_COLOR = "INSERT INTO tblLineColor VALUES (?,?)";

    // note: updating a line automatically deletes the color
    public static final String UPDATE_LINE = "UPDATE tblLine SET foundedYear = ?, "
            + "lineType = ?, lineLength = ? WHERE name = ?";

    // note: deleting a line automatically deletes the color
    public static final String DELETE_LINE = "DELETE FROM tblLine WHERE name = ?";

    //----------------------------- STATION ------------------------------------
    public static final String SELECT_STATION_ID_BY_NAME = "SELECT ID FROM tblStation As S WHERE S.name = ?";

    public static final String SELECT_STATION = "SELECT * FROM tblStation WHERE ID = ?";

    public static final String INSERT_STATION = "INSERT INTO tblStation VALUES (?,?,?,?)";

    public static final String UPDATE_STATION = "UPDATE tblStation SET name = ?, "
            + "platformNum = ?, kiosk = ?, zoneNumber = ? WHERE ID = ?";

    public final static String DELETE_STATION = "DELETE FROM tblStation WHERE ID = ?";

    //------------------------ STATION IN LINE ---------------------------------
    public static final String SELECT_STATIONS_OF_LINE = "SELECT * FROM tblStationInLine "
            + "As SIL join tblStation As S on SIL.stationID = S.ID WHERE SIL.lineName = ?";

    public static final String SELECT_LINES_OF_STATION = "SELECT L.*, LC.name "
            + "As 'colorName' FROM tblStationInLine As SIL join tblLine As L on "
            + "SIL.lineName = L.name join tblLineColor  As LC on L.name = "
            + "LC.lineName WHERE SIL.stationID = ? ";

    public static final String INSERT_STATION_IN_LINE = "INSERT INTO tblStationInLine VALUES (?,?)";

    public static final String DELETE_STATION_IN_LINE = "DELETE FROM tblStationInLine "
            + "WHERE stationID = ? AND  lineName = ?";

    //----------------------------- CARD ---------------------------------------
    public static final String SELECT_NEW_CARD = "SELECT TOP 1 number+1 As 'number' "
            + "FROM tblCard ORDER BY number DESC";

    public static final String SELECT_CARD = "SELECT * FROM tblCard "
            + "WHERE number = ? and purchaseDate = ?";

    public static final String INSERT_CARD = "INSERT INTO tblCard VALUES(?,?,?)";

    // note: deleting a card automatically deletes the paper/oyster card
    public static final String DELETE_CARD = "DELETE FROM tblCard WHERE number = ?";

    public static final String IS_CARD_BOUNDED = "SELECT CASE WHEN EXISTS "
            + "(SELECT * FROM tblActivity WHERE cardNumber = ?) "
            + "THEN CAST(1 AS BIT) ELSE CAST(0 AS BIT) END";
    //-------------------------- PAPER CARD ------------------------------------
    public static final String SELECT_ALL_PAPER_CARDS = "SELECT * FROM tblPaperCard";

    public static final String SELECT_PAPER_ISTOURIST = "SELECT isTourist "
            + "FROM tblPaperCard WHERE number = ?";

    public static final String INSERT_PAPER_CARD = "INSERT INTO tblPaperCard VALUES (?,?,?)";

    public static final String UPDATE_PAPER_CARD = "UPDATE tblPaperCard "
            + "SET isTourist = ? WHERE number = ?";

    //-------------------------- OYSTER CARD -----------------------------------
    public static final String SELECT_ALL_OYSTER_CARDS = "SELECT * FROM tblOysterCard";

    public static final String SELECT_OYSTER_PICTURE = "SELECT picture FROM tblOysterCard "
            + "WHERE number = ?";

    public static final String INSERT_OYSTER_CARD = "INSERT INTO tblOysterCard VALUES (?,?,?)";

    public static final String UPDATE_OYSTER_CARD = "UPDATE tblOysterCard "
            + "SET picture = ? WHERE number = ?";

    //------------------------ PAPER CARD AREAS --------------------------------
    public static final String SELECT_PAPER_PROGRAMS = "SELECT TOP 1 *, case "
            + "when PCA.cardLength='1' then DATEADD(dd,1,PCA.cardPurchaseDate) "
            + "when PCA.cardLength='2' then DATEADD(dd,3,PCA.cardPurchaseDate) "
            + "when PCA.cardLength='3' then DATEADD(dd,7,PCA.cardPurchaseDate) "
            + "when PCA.cardLength='4' then DATEADD(mm,1,PCA.cardPurchaseDate) "
            + "when PCA.cardLength='5' then DATEADD(mm,3,PCA.cardPurchaseDate) "
            + "when PCA.cardLength='6' then DATEADD(yyyy,1,PCA.cardPurchaseDate) "
            + "end as 'expirationDate' "
            + "FROM tblPaperCardAreas PCA "
            + "WHERE cardNumber = ? "
            + "ORDER BY zoneNumber DESC";

    public static final String INSERT_PAPER_PROGRAM = "INSERT INTO tblPaperCardAreas "
            + "VALUES (?,?,?,?)";

    public static final String DELETE_PAPER_PROGRAM = "DELETE FROM tblPaperCardAreas "
            + "WHERE cardNumber=? and cardPurchaseDate = ? and zoneNumber <= ? and cardLength = ?";

    public static final String PAPER_PROGRAM_ZONES_TO_ADD = "SELECT * FROM tblZone "
            + "WHERE number <= ? and number not in (SELECT zoneNumber FROM "
            + "tblPaperCardAreas WHERE cardNumber = ? and cardPurchaseDate = ?)";

    public static final String DELETE_ALL_PAPER_PROGRAMS_OF_CARD = "DELETE FROM "
            + "tblPaperCardAreas WHERE cardNumber = ?";

    //------------------------ OYSTER CARD AREAS -------------------------------
    public static final String SELECT_OYSTER_PROGRAMS = "SELECT TOP 1 *, case "
            + "when OCA.cardLength='1' then DATEADD(dd,1,OCA.cardPurchaseDate) "
            + "when OCA.cardLength='2' then DATEADD(dd,3,OCA.cardPurchaseDate) "
            + "when OCA.cardLength='3' then DATEADD(dd,7,OCA.cardPurchaseDate) "
            + "when OCA.cardLength='4' then DATEADD(mm,1,OCA.cardPurchaseDate) "
            + "when OCA.cardLength='5' then DATEADD(mm,3,OCA.cardPurchaseDate) "
            + "when OCA.cardLength='6' then DATEADD(yyyy,1,OCA.cardPurchaseDate) "
            + "end as 'expirationDate' "
            + "FROM tblOysterCardAreas OCA "
            + "WHERE cardNumber = ? "
            + "ORDER BY zoneNumber DESC";

    public static final String INSERT_OYSTER_PROGRAM = "INSERT INTO tblOysterCardAreas "
            + "VALUES (?,?,?,?)";

    public static final String DELETE_OYSTER_PROGRAM = "DELETE FROM tblOysterCardAreas "
            + "WHERE cardNumber=? and cardPurchaseDate = ? and zoneNumber <= ? and cardLength = ?";

    public static final String OYSTER_PROGRAM_ZONES_TO_ADD = "SELECT * FROM tblZone "
            + "WHERE number <= ? and number not in (SELECT zoneNumber FROM "
            + "tblOysterCardAreas WHERE cardNumber = ? and cardPurchaseDate = ?)";

    public static final String DELETE_ALL_OYSTER_PROGRAMS_OF_CARD = "DELETE FROM "
            + "tblOysterCardAreas WHERE cardNumber = ?";

    //------------------------------- SITE ---------------------------------------
    
    public static final String SELECT_SITEID_BY_NAME = "Select * From tblSite As S WHERE S.NAME = ?";

    public static final String SELECT_ALL_SITES = "Select * From tblSite";

    public static final String SELECT_LINE_NAME_FOR_STATION_NAME = "SELECT SIL.lineName FROM tblStationInLine SIL\n"
            + "JOIN tblStation s on sil.stationID = s.ID WHERE s.name = ?";
    public static final String SELECT_ONLY_STATION_WITH_LINES = "SELECT distinct [ID],[name] "
            + "FROM tblStation s join tblStationInLine sil "
            + "on s.ID = sil.stationID";

    public static final String INSERT_SITE = "INSERT INTO tblSite VALUES (?,?,?,?)";

    public static final String DELETE_SITE = "DELETE FROM tblSite WHERE ID = ?";

    public static final String UPDATE_SITE = "UPDATE tblSite  SET [name] = ?"
            + "  ,[siteDescription] = ?  ,[foundedYear] = ?  ,[siteType] = ? "
            + " WHERE ID = ?";

    public static final String SELECT_SITE_BY_SITEID = "Select * From tblSite As S WHERE S.ID = ?";

    public static final String SELECT_AllSITES = "Select * From tblSite";

    //------------------------------- SITE FROM EXIT---------------------------------------
    public static final String INSERT_SFE = "INSERT INTO tblSiteFromExit VALUES (?,?,?,?)";

    public static final String DELETE_SFE = "DELETE FROM [LondonU2].[dbo].[tblSiteFromExit] "
            + "where siteID = ? and stationID = ? and lineName = ?";
    public static final String SELECT_SFE_BY_SITEID = "Select * From tblSiteFromExit As SFE "
            + "join tblStation As S on SFE.stationID = S.ID WHERE SFE.siteID = ? ";

    //------------------------------- SITE FROM SITE---------------------------------------
    public static final String SELECT_SFS_BY_SITEID = "SELECT * FROM tblSiteFromSite As SFS\n" +
"          join tblSite As S on SFS.siteID2 = S.ID and s.ID in(\n" +
"          SELECT sfs.siteID2 FROM tblSiteFromSite As SFS)  where sfs.siteID1 = ?";
    
    public static final String INSERT_SFS = "INSERT INTO tblSiteFromSite VALUES (?,?,?)";

    public static final String DELETE_SFS = "DELETE FROM [LondonU2].[dbo].[tblSiteFromSite] \n"
            + "      WHERE siteID1 = ? and siteID2 = ?";

    //-------------------------- CARD LENGTHS ----------------------------------
    public static final String SELECT_ALL_LENGTHS = "SELECT * FROM tblCardLengths";

    public static final String INSERT_LENGTH = "INSERT INTO tblCardLengths VALUES (?,?)";

    public static final String DELETE_LENGTH = "DELETE FROM tblCardLengths WHERE "
            + "cardLength = ?";

    //-------------------------- OTHER QUERIES ---------------------------------
    public static final String QUERY1 = "select S.siteType, AVG(S.foundedYear) as 'average founded year',\n"
            + "COUNT(S.ID) as 'num of sites'\n"
            + "from tblSite S left outer join tblSiteFromExit SFE on S.ID=SFE.siteID\n"
            + "left outer join tblSiteFromSite SFS on (S.ID=SFS.siteID1 or\n"
            + "S.ID=SFS.siteID2)\n"
            + "where SFE.siteID is null and SFS.siteID1 is null and (S.siteType like\n"
            + "'%s%m%')\n"
            + "group by S.siteType\n"
            + "order by 'average founded year' desc";

    public static final String QUERY2 = "select PC.number, PC.purchaseDate, PC.isTourist,MAX(PCA.zoneNumber)\n"
            + "as 'maxZone'\n"
            + "from dbo.tblPaperCard as PC inner join dbo.tblPaperCardAreas as PCA\n"
            + "on PC.number = PCA.cardNumber\n"
            + "where DATEDIFF(day, PC.purchaseDate, getdate())<60 and PC.number not\n"
            + "in(select A.cardNumber\n"
            + " from dbo.tblActivity as A\n"
            + " where DATEDIFF(day, A.activityDate, getdate())<30)\n"
            + "group by PC.number, PC.purchaseDate, PC.isTourist";
    public static final String QUERY3 = "select SE.lineName,SE.stationID, COUNT(SE.siteID) as\n"
            + "numOfSitesForLine\n"
            + "from tblSiteFromExit SE\n"
            + "where SE.distance<0.5 and not exists(\n"
            + "select SE1.siteID\n"
            + "from tblSiteFromExit SE1\n"
            + "where SE1.stationID!=SE.stationID\n"
            + "and SE1.distance<0.5 and SE1.siteID=SE.siteID)\n"
            + "group by SE.lineName, SE.stationID\n"
            + "having COUNT(SE.siteID)>=3\n"
            + "order by SE.lineName,numOfSitesForLine desc";
    public static final String QUERY4 = "select S.ID, S.name, MIN(SFE.distance)as 'minDistance'\n"
            + "from dbo.tblStation as S inner join dbo.tblSiteFromExit SFE on S.ID =\n"
            + "SFE.stationID join dbo.tblSite as SI1 on SI1.ID=SFE.siteID\n"
            + "where S.platformNum > 4 and S.ID in\n"
            + "(select SIL1.stationID\n"
            + " from dbo.tblStationInLine as SIL1\n"
            + " where S.ID = SIL1.stationID\n"
            + " group by SIL1.stationID\n"
            + "having COUNT (SIL1.lineName) > 2\n"
            + "\n"
            + " intersect\n"
            + "\n"
            + " select SFX.stationID\n"
            + " from dbo.tblSiteFromExit as SFX\n"
            + " where S.ID = SFX.stationID and SFX.distance <= 1.5\n"
            + " group by SFX.stationID\n"
            + " having COUNT (SFX.siteID) >= 5)\n"
            + "group by S.ID, S.name";
    public static final String USE_LONDON2 = "USE [LondonU2]";
    public static final String IF_EXISTS_endDates = "IF  EXISTS (SELECT * "
            + "FROM sys.views WHERE object_id = OBJECT_ID(N'[dbo].[endDates]'))\n"
            + "DROP VIEW [dbo].[endDates]";
    public static final String IF_EXISTS_numOfCardsForPrice = "IF  EXISTS (SELECT * "
            + "FROM sys.views WHERE object_id = OBJECT_ID(N'[dbo].[numOfCardsForPrice]'))\n"
            + "DROP VIEW [dbo].[numOfCardsForPrice]";
    public static final String IF_EXISTS_NumOfOysterRides = "IF  EXISTS (SELECT * "
            + "FROM sys.views WHERE object_id = OBJECT_ID(N'[dbo].[NumOfOysterRides]'))\n"
            + "DROP VIEW [dbo].[NumOfOysterRides]";
    public static final String IF_EXISTS_yearsWithMoreThan5000 = "IF  EXISTS (SELECT * "
            + "FROM sys.views WHERE object_id = OBJECT_ID(N'[dbo].[yearsWithMoreThan5000]'))\n"
            + "DROP VIEW [dbo].[yearsWithMoreThan5000]";
    public static final String SET_ANSI_NULLS_ON = "SET ANSI_NULLS ON";
    public static final String SET_QUOTED_IDENTIFIER_ON = "SET QUOTED_IDENTIFIER ON";
    public static final String CREATE_VIEW_END_DATES = "create view [dbo].[endDates] as\n"
            + "select OCA.*, case\n"
            + "when OCA.cardLength='1' then DATEADD(dd,1,OCA.cardPurchaseDate)\n"
            + "when OCA.cardLength='3' then DATEADD(dd,3,OCA.cardPurchaseDate)\n"
            + "when OCA.cardLength='W' then DATEADD(dd,7,OCA.cardPurchaseDate)\n"
            + "when OCA.cardLength='M' then DATEADD(mm,1,OCA.cardPurchaseDate)\n"
            + "when OCA.cardLength='T' then DATEADD(mm,3,OCA.cardPurchaseDate)\n"
            + "when OCA.cardLength='Y' then DATEADD(yyyy,1,OCA.cardPurchaseDate)\n"
            + "end as 'date'\n"
            + "from tblOysterCardAreas OCA";

    public static final String QUERY5 = "select A.cardNumber,A.cardPurchaseDate\n"
            + "from tblActivity A inner join tblStation S on A.stationID=S.ID\n"
            + "where S.zoneNumber>(select MAX(OCA.zoneNumber)\n"
            + "from tblOysterCardAreas OCA\n"
            + "where A.cardNumber=OCA.cardNumber\n"
            + "and A.cardPurchaseDate=OCA.cardPurchaseDate)\n"
            + "UNION\n"
            + "select A.cardNumber,A.cardPurchaseDate\n"
            + "from tblActivity A inner join endDates ED on\n"
            + "A.cardNumber=ED.cardNumber and A.cardPurchaseDate=ED.cardPurchaseDate\n"
            + "where A.activityDate>ED.date or A.activityDate<A.cardPurchaseDate\n"
            + "group by A.cardNumber,A.cardPurchaseDate";

    public static final String QUERY6 = "select A.cardNumber, count (A.cardNumber)/2 as 'NumOfTrip'\n"
            + "from dbo.tblActivity A\n"
            + "where A.cardNumber in\n"
            + "(select A1.cardNumber\n"
            + " from dbo.tblActivity as A1\n"
            + "where DATEDIFF(month, A1.activityDate, getdate()) = 0\n"
            + "and A1.activityType = 'I' and exists\n"
            + " (select A2.cardNumber\n"
            + " from dbo.tblActivity as A2\n"
            + "where (convert(varchar(10), A2.activityDate,\n"
            + "120) = convert(varchar(10), A1.activityDate,\n"
            + "120) and (activityType = 'O') and\n"
            + "A1.cardNumber = A2.cardNumber))\n"
            + " group by A1.cardNumber\n"
            + "having COUNT (A1.activityDate) > 19)\n"
            + "and not exists\n"
            + "(select ACA.cardNumber\n"
            + " from dbo.tblOysterCardAreas as ACA\n"
            + "where ((ACA.cardNumber = A.cardNumber) and\n"
            + "(ACA.cardLength IN('1', '3', 'W'))))\n"
            + "group by A.cardNumber\n"
            + "order by NumOfTrip desc";
    public static final String CREATE_VIEW_numOfCardsForPrice
            = "create view [dbo].[numOfCardsForPrice] as\n"
            + "select GP.depositStartYear,GP.depositEndYear,GP.price,COUNT(*) as\n"
            + "'num of cards'\n"
            + "from tblGeneralParameters GP, tblOysterCard OC\n"
            + "where (GP.depositStartYear <= year(OC.purchaseDate))\n"
            + "and (GP.depositEndYear >= year(OC.purchaseDate))\n"
            + "group by GP.depositStartYear,GP.depositEndYear,GP.price\n"
            + "having (COUNT(*)/(GP.depositEndYear-GP.depositStartYear+1))>= 300";

    public static final String CREATE_VIEW_yearsWithMoreThan5000
            = "create view [dbo].[yearsWithMoreThan5000] as\n"
            + "select NOC.depositStartYear,NOC.depositEndYear,COUNT(*) as 'num of\n"
            + "rides'\n"
            + "from tblOysterCard OC inner join tblActivity A\n"
            + "on OC.number=A.cardNumber and\n"
            + "OC.purchaseDate=A.cardPurchaseDate, numOfCardsForPrice NOC\n"
            + "where (year(A.activityDate) >= NOC.depositStartYear)\n"
            + "and (year(A.activityDate) <= NOC.depositEndYear)\n"
            + "group by NOC.depositStartYear,NOC.depositEndYear\n"
            + "having COUNT(*)>5000";

    public static final String CREATE_VIEW_NumOfOysterRides = "create view [dbo].[NumOfOysterRides] as\n"
            + "select YEAR(A.activityDate) as theYear,COUNT(*) as 'num of rides for Oyster'\n"
            + "from tblOysterCard OC inner join tblActivity A\n"
            + "on OC.number=A.cardNumber and OC.purchaseDate=A.cardPurchaseDate,\n"
            + "yearsWithMoreThan5000 MT5\n"
            + "where (year(A.activityDate) >= MT5.depositStartYear)\n"
            + "and (year(A.activityDate) <= MT5.depositEndYear)\n"
            + "group by YEAR(A.activityDate)";

    public static final String QUERY7 = "select year(A.activityDate) as 'Year',\n"
            + "case when NOR.[num of rides for Oyster] is null then 'Low'\n"
            + "when NOR.[num of rides for Oyster] = COUNT(*) then 'High'\n"
            + "when (cast(NOR.[num of rides for Oyster] as\n"
            + "float)/COUNT(*))<0.2 then 'Low'\n"
            + "when (cast(NOR.[num of rides for Oyster] as\n"
            + "float)/COUNT(*))<0.6 then 'Medium'\n"
            + "when (cast(NOR.[num of rides for Oyster] as\n"
            + "float)/COUNT(*))>=0.6 then 'High'\n"
            + "end as 'how many oysters'\n"
            + "from tblActivity A left outer join NumOfOysterRides NOR on\n"
            + "year(A.activityDate) = NOR.theYear, yearsWithMoreThan5000 MT5\n"
            + "where (year(A.activityDate)>=MT5.depositStartYear and\n"
            + "year(A.activityDate)<=MT5.depositEndYear)\n"
            + "group by year(A.activityDate), NOR.[num of rides for Oyster]";
}
