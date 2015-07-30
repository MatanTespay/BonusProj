/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author asus
 */
public class Queries {

    //---------------------------- ACTIVITY ------------------------------------
    /**
     * @no: 1
     * @purpose: selects an activity given its PK
     * @usage: Activity form
     */
    public static final String SELECT_ACTIVITY = "SELECT A.*, S.name FROM "
            + "tblActivity As A join tblStation As S on A.stationID = S.ID "
            + "WHERE A.cardNumber = ? and A.cardPurchaseDate = ? and A.activityDate = ?";

    /**
     * @no: 2
     * @purpose: checks if a card already have 1 activity or more given its PK
     * @usage: Activity form
     */
    public static final String IS_CARD_1ST_ACTIVITY = "SELECT TOP 1 * FROM "
            + "tblActivity As A WHERE A.cardNumber = ? and A.cardPurchaseDate = ?";
    
    /**
     * @no: 3
     * @purpose: returns the activities of card given its PK
     * @usage: Activity form
     */
    public static final String SELECT_ACTIVITIES_OF_CARD = "SELECT activityDate "
            + "FROM tblActivity WHERE cardNumber = ? and cardPurchaseDate = ?";
    
    /**
     * @no: 4
     * @purpose: returns all the number of card that are have 1 activity or more given its PK
     * @usage: ActivityDialog form
     */
    public static final String SELECT_CARD_NUMBERS_WITH_ACTIVITIES = "SELECT "
            + "distinct C.number FROM tblCard As C join tblActivity t on "
            + "c.number = t.cardNumber";
    
    /**
     * @no: 5
     * @purpose: inserts an activity
     * @usage: Activity form
     */
    public static final String INSERT_ACTIVITY = "INSERT INTO tblActivity "
            + "VALUES (? ,? ,? ,? ,?, ?)";
    
    /**
     * @no: 6
     * @purpose: updates an activity given its PK
     * @usage: Activity form
     */
    public static final String UPDATE_ACTIVITY = "UPDATE tblActivity SET "
            + "activityType = ?,stationID = ?,lineName = ? WHERE cardNumber = ? "
            + " and cardPurchaseDate = ? and activityDate = ?";
    
    /**
     * @no: 7
     * @purpose: deletes an activity given its PK
     * @usage: Activity form
     */
    public static final String DELETE_ACTIVITY = "DELETE FROM tblActivity WHERE "
            + "cardNumber = ? and cardPurchaseDate = ? and activityDate = ?";

    //------------------------------ ZONE --------------------------------------
    /**
     * @no: 8
     * @purpose: return all the zones
     * @usage: zone comboboxes in Station and Card forms
     */
    public static final String SELECT_ALL_ZONES = "SELECT * FROM tblZone";

    //--------------------------- LINE & COLOR ---------------------------------  
    /**
     * @no: 9
     * @purpose: return a line and it's color for a given the line's PK
     * @usage: Line form
     */
    public static final String SELECT_LINE_AND_COLOR = "SELECT * FROM tblLine As L "
            + "join tblLineColor As LC on L.name = LC.lineName WHERE L.name = ?";
    
    /**
     * @no: 10
     * @purpose: returns all lines and their names
     * @usage: line combobox in Line dialog and Station form
     */
    public static final String SELECT_ALL_LINE_NAMES = "SELECT name FROM tblLine";

    /**
     * @no: 11
     * @purpose: inserts a line
     * @usage: Line form
     */
    public static final String INSERT_LINE = "INSERT INTO tblLine VALUES (?,?,?,?)";
    /**
     * @no: 12
     * @purpose: inserts a lineColor
     * @usage: Line form
     */
    public static final String INSERT_COLOR = "INSERT INTO tblLineColor VALUES (?,?)";

    /**
     * @no: 13
     * @purpose: updates a line  given its PK
     * @usage: Line form
     */
    public static final String UPDATE_LINE = "UPDATE tblLine SET foundedYear = ?, "
            + "lineType = ?, lineLength = ? WHERE name = ?";
    /**
     * @no: 14
     * @purpose: updates a color's name given a line's PK
     * @usage: Line form
     */
    public static final String UPDATE_COLOR = "UPDATE tblLineColor SET name = ? "
            + "WHERE lineName = ?";

    // note: 
    /**
     * @no: 15
     * @purpose: deletes a line given its PK
     * @usage: Line form
     * @note: deletes also its related color
     */
    public static final String DELETE_LINE = "DELETE FROM tblLine WHERE name = ?";

    //----------------------------- STATION ------------------------------------
    /**
     * @no: 16
     * @purpose: return a station ID given its name
     * @usage: Station form
     */
    public static final String SELECT_STATION_ID_BY_NAME = "SELECT ID FROM tblStation As S WHERE S.name = ?";

    /**
     * @no: 17
     * @purpose: returns all station IDs and their names
     * @usage: station comboboxes in Line form and Station dialog
     */
    public static final String SELECT_ALL_STATION_IDS_AND_NAMES = "SELECT ID, name FROM tblStation";

    /**
     * @no: 18
     * @purpose: returns a station given its PK
     * @usage: Activity and Station forms
     */
    public static final String SELECT_STATION = "SELECT * FROM tblStation WHERE ID = ?";

    /**
     * @no: 19
     * @purpose: inserts a station
     * @usage: Station form
     */
    public static final String INSERT_STATION = "INSERT INTO tblStation VALUES (?,?,?,?)";

    /**
     * @no 20
     * @purpose: updates a station
     * @usage: Station form
     */
    public static final String UPDATE_STATION = "UPDATE tblStation SET name = ?, "
            + "platformNum = ?, kiosk = ?, zoneNumber = ? WHERE ID = ?";

    /**
     * @no: 21
     * @purpose: deletes a station given its PK
     * @usage: Station form
     */
    public final static String DELETE_STATION = "DELETE FROM tblStation WHERE ID = ?";

    //------------------------ STATION IN LINE ---------------------------------
    /**
     * @no: 22
     * @purpose: returns station of a line given its PK
     * @usage: Line form
     */
    public static final String SELECT_STATIONS_OF_LINE = "SELECT * FROM tblStationInLine "
            + "As SIL join tblStation As S on SIL.stationID = S.ID WHERE SIL.lineName = ?";

    /**
     * @no: 23
     * @purpose: returns lines of a station (and their colors) given its PK
     * @usage: Station form
     */
    public static final String SELECT_LINES_OF_STATION = "SELECT L.*, LC.name "
            + "As 'colorName' FROM tblStationInLine As SIL join tblLine As L on "
            + "SIL.lineName = L.name join tblLineColor  As LC on L.name = "
            + "LC.lineName WHERE SIL.stationID = ? ";

    /**
     * @no: 24
     * @purpose: returns station IDs and names that have one line or more
     * @usage: Activity form
     */
    public static final String SELECT_STATIONS_WITH_LINES = "SELECT distinct "
            + "sil.stationID ,s.name FROM  tblStation s  inner join  "
            + "tblStationInLine sil on s.ID = sil.stationID";

    /**
     * @no: 25
     * @purpose: insert a StationInLine
     * @usage: Line and Station forms
     */
    public static final String INSERT_STATION_IN_LINE = "INSERT INTO tblStationInLine VALUES (?,?)";

    /**
     * @no: 26
     * @purpose: deletes a stationInLine
     * @usage: Line and Station forms
     */
    public static final String DELETE_STATION_IN_LINE = "DELETE FROM tblStationInLine "
            + "WHERE stationID = ? AND  lineName = ?";

    //----------------------------- CARD ---------------------------------------
    /**
     * @no: 27
     * @purpose: returns a new (unused) card number
     * @usage: Card form
     */
    public static final String SELECT_NEW_CARD = "SELECT TOP 1 number+1 As 'number' "
            + "FROM tblCard ORDER BY number DESC";

    /**
     * @no: 28
     * @purpose: returns a card given its PK
     * @usage: Card form
     */
    public static final String SELECT_CARD = "SELECT * FROM tblCard WHERE "
            + "number = ? and purchaseDate = ?";

    /**
     * @no: 29
     * @purpose: return all the card numbers
     * @usage: card comboboxes in Card dialog and Activity form
     */
    public static final String SELECT_ALL_CARD_NUMBERS = "SELECT number FROM tblCard";

    /**
     * @no: 30
     * @purpose: returns the purchase dates of a card given its number
     * @usage: purchase date comboboxes in Activity form and Activity dialog
     */
    public static final String SELECT_PURCHASE_DATES_OF_CARD = "SELECT C.purchaseDate FROM tblCard As C WHERE C.number = ?";

    /**
     * @no: 31
     * @purpose: inserts a card
     * @usage: Card form
     */
    public static final String INSERT_CARD = "INSERT INTO tblCard VALUES(?,?,?)";

    // note: deleting a card automatically deletes the paper/oyster card
    /**
     * @no: 32
     * @purpose: deletes a card given its number
     * @usage: Card form
     */
    public static final String DELETE_CARD = "DELETE FROM tblCard WHERE number = ?";

    /**
     * @no: 33
     * @purpose: return true of false whether the card has an activity (bounded) given its number
     * @usage: Card form
     */
    public static final String IS_CARD_BOUNDED = "SELECT CASE WHEN EXISTS "
            + "(SELECT * FROM tblActivity WHERE cardNumber = ?) "
            + "THEN CAST(1 AS BIT) ELSE CAST(0 AS BIT) END";
    //-------------------------- PAPER CARD ------------------------------------
    
    /**
     * @no: 34
     * @purpose: returns if the paper card isTourist field given its number
     * @usage: Card form
     */
    public static final String SELECT_PAPER_ISTOURIST = "SELECT isTourist "
            + "FROM tblPaperCard WHERE number = ?";

    /**
     * @no: 35
     * @purpose: inserts a new paper card
     * @usage: Card form
     */
    public static final String INSERT_PAPER_CARD = "INSERT INTO tblPaperCard VALUES (?,?,?)";

    /**
     * @no: 36
     * @purpose: updates a paper card given its number
     * @usage: Card form
     */
    public static final String UPDATE_PAPER_CARD = "UPDATE tblPaperCard "
            + "SET isTourist = ? WHERE number = ?";

    //-------------------------- OYSTER CARD -----------------------------------
    /**
     * @no: 37
     * @purpose: returns all the oyster cards
     * @usage: Card form
     */
    public static final String SELECT_ALL_OYSTER_CARDS = "SELECT * FROM tblOysterCard";

    /**
     * @no: 38
     * @purpose: returns the picture of an oyster card given is number
     * @usage: Card form
     */
    public static final String SELECT_OYSTER_PICTURE = "SELECT picture FROM tblOysterCard "
            + "WHERE number = ?";

    /**
     * @no: 39
     * @purpose: inserts an oyster card
     * @usage: Card form
     */
    public static final String INSERT_OYSTER_CARD = "INSERT INTO tblOysterCard VALUES (?,?,?)";

    /**
     * @no: 40
     * @purpose: updates an oyster card given its number
     * @usage: Card form
     */
    public static final String UPDATE_OYSTER_CARD = "UPDATE tblOysterCard "
            + "SET picture = ? WHERE number = ?";

    //------------------------ PAPER CARD AREAS --------------------------------
    /**
     * @no: 41
     * @purpose: returns a paper card program (and expiration date) given its card number
     * @usage: Card form
     */
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

    /**
     * @no: 42
     * @purpose: inserts paper program
     * @usage: Card form
     */
    public static final String INSERT_PAPER_PROGRAM = "INSERT INTO tblPaperCardAreas "
            + "VALUES (?,?,?,?)";

    /**
     * @no: 43
     * @purpose: deletes a paper program given its PK
     * @usage: Card form
     */
    public static final String DELETE_PAPER_PROGRAM = "DELETE FROM tblPaperCardAreas "
            + "WHERE cardNumber=? and cardPurchaseDate = ? and zoneNumber <= ? and cardLength = ?";

    /**
     * @no: 44
     * @purpose: returns all the zones a new paper card should have
     * @usage: Card form
     */
    public static final String PAPER_PROGRAM_ZONES_TO_ADD = "SELECT * FROM tblZone "
            + "WHERE number <= ? and number not in (SELECT zoneNumber FROM "
            + "tblPaperCardAreas WHERE cardNumber = ? and cardPurchaseDate = ?)";

    /**
     * @no: 45
     * @purpose: deletes the paper program of a card given its number
     * @usage: Card form
     */
    public static final String DELETE_PAPER_PROGRAM_OF_CARD = "DELETE FROM "
            + "tblPaperCardAreas WHERE cardNumber = ?";

    //------------------------ OYSTER CARD AREAS -------------------------------
    /**
     * @no: 46
     * @purpose: returns all oyster card programs (and their expiration date) given their card number
     * @usage: Card form
     */
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

    /**
     * @no: 47
     * @purpose: inserts a paper program
     * @usage: Card form
     */
    public static final String INSERT_OYSTER_PROGRAM = "INSERT INTO tblOysterCardAreas "
            + "VALUES (?,?,?,?)";

    /**
     * @no: 48
     * @purpose: deletes an oyster program
     * @usage: Card form
     */
    public static final String DELETE_OYSTER_PROGRAM = "DELETE FROM tblOysterCardAreas "
            + "WHERE cardNumber=? and cardPurchaseDate = ? and zoneNumber <= ? and cardLength = ?";

    /**
     * @no: 49
     * @purpose: returns all the zones a new oyster card should have
     * @usage: Card form
     */
    public static final String OYSTER_PROGRAM_ZONES_TO_ADD = "SELECT * FROM tblZone "
            + "WHERE number <= ? and number not in (SELECT zoneNumber FROM "
            + "tblOysterCardAreas WHERE cardNumber = ? and cardPurchaseDate = ?)";

    /**
     * @no: 50
     * @purpose: deletes all the oyster program of a card given its number
     * @usage: Card form
     */
    public static final String DELETE_ALL_OYSTER_PROGRAMS_OF_CARD = "DELETE FROM "
            + "tblOysterCardAreas WHERE cardNumber = ?";

    //------------------------------- SITE ---------------------------------------
    /**
     * @no: 51
     * @purpose: returns a site given is name
     * @usage: Site form
     */
    public static final String SELECT_SITEID_BY_NAME = "Select * From tblSite As S WHERE S.NAME = ?";

    /**
     * @no: 52
     * @purpose: returns all sites
     * @usage: Site form
     */
    public static final String SELECT_ALL_SITES = "Select * From tblSite";

    /**
     * @no: 53
     * @purpose: returns the line names of a station given its name
     * @usage: Site form
     */
    public static final String SELECT_LINES_OR_STATION__BY_NAME = "SELECT SIL.lineName FROM tblStationInLine SIL "
            + "JOIN tblStation s on sil.stationID = s.ID WHERE s.name = ?";

    /**
     * @no: 54
     * @purpose: return stations that have lines
     * @usage: Site form
     */
    public static final String SELECT_ONLY_STATION_WITH_LINES = "SELECT distinct [ID],[name] "
            + "FROM tblStation s join tblStationInLine sil "
            + "on s.ID = sil.stationID";

    /**
     * @no: 55
     * @purpose: inserts site
     * @usage: Site form
     */
    public static final String INSERT_SITE = "INSERT INTO tblSite VALUES (?,?,?,?)";

    /**
     * @no: 56
     * @purpose: deletes site
     * @usage: Site form
     */
    public static final String DELETE_SITE = "DELETE FROM tblSite WHERE ID = ?";

    /**
     * @no: 57
     * @purpose: updates site given its PK
     * @usage: Site form
     */
    public static final String UPDATE_SITE = "UPDATE tblSite SET name = ? "
            + ", siteDescription = ?  ,foundedYear = ?  ,siteType = ? WHERE ID = ?";

    /**
     * @no: 58
     * @purpose: return a site given its PK
     * @usage: Site form
     */
    public static final String SELECT_SITE_BY_SITEID = "Select * From tblSite As S WHERE S.ID = ?";


    //----------------------------- SITE FROM EXIT -----------------------------
    /**
     * @no: 59
     * @purpose: inserts siteFromExit
     * @usage: Site form
     */
    public static final String INSERT_SFE = "INSERT INTO tblSiteFromExit VALUES (?,?,?,?)";

    /**
     * @no: 60
     * @purpose: deletes siteFromExit given its PK
     * @usage: Site form
     */
    public static final String DELETE_SFE = "DELETE FROM tblSiteFromExit WHERE "
            + "siteID = ? and stationID = ? and lineName = ?";
    /**
     * @no: 61
     * @purpose: returns all sites nearby to an exit of a station given its PK
     * @usage: Site form
     */
    public static final String SELECT_SFE_BY_SITEID = "SELECT * FROM tblSiteFromExit "
            + "As SFE join tblStation As S on SFE.stationID = S.ID WHERE SFE.siteID = ? ";

    //----------------------------- SITE FROM SITE -----------------------------
    /**
     * @no: 62
     * @purpose: returns all nearby sites for a site given its PK
     * @usage: Site form
     */
    public static final String SELECT_SFS_BY_SITEID = "SELECT * FROM tblSiteFromSite "
            + "As SFS join tblSite As S on SFS.siteID2 = S.ID and s.ID in(SELECT "
            + "SFS.siteID2 FROM tblSiteFromSite As SFS) WHERE SFS.siteID1 = ?";

    /**
     * @no: 63
     * @purpose: inserts a SiteFromSite
     * @usage: Site form
     */
    public static final String INSERT_SFS = "INSERT INTO tblSiteFromSite VALUES (?,?,?)";

    /**
     * @no: 64
     * @purpose: deletes a SiteFromSite given its PK
     * @usage: Site form
     */
    public static final String DELETE_SFS = "DELETE FROM tblSiteFromSite WHERE "
            + "siteID1 = ? and siteID2 = ?";

    //------------------------------ SITE TYPES --------------------------------
    /**
     * @no: 65
     * @purpose: returns all site types
     * @usage: SiteTypes form
     */
    public static final String SELECT_ALL_SITE_TYPES = "SELECT * FROM tblSiteType";

    /**
     * @no: 66
     * @purpose: inserts s site type
     * @usage: SiteTypes form
     */
    public static final String INSERT_SYTE_TYPE = "INSERT INTO tblSiteType VALUES (?)";

    /**
     * @no: 67
     * @purpose: deletes a site type given its PK
     * @usage: SiteTypes form
     */
    public static final String DELETE_SITE_TYPE = "DELETE FROM tblSiteType WHERE "
            + "siteType = ?";

    //----------------------------- CARD LENGTHS -------------------------------
    /**
     * @no: 68
     * @purpose: returns all card lengths
     * @usage: CardLengths and Card forms
     */
    public static final String SELECT_ALL_LENGTHS = "SELECT * FROM tblCardLengths";

    /**
     * @no: 69
     * @purpose: inserts a card length
     * @usage: CardLengths form
     */
    public static final String INSERT_LENGTH = "INSERT INTO tblCardLengths VALUES (?,?)";

    /**
     * @no: 70
     * @purpose: deletes a card length given its PK
     * @usage: CardLengths form
     */
    public static final String DELETE_LENGTH = "DELETE FROM tblCardLengths WHERE "
            + "cardLength = ?";

    /**
     * @no: 71
     * @purpose: updates a card length given it PK
     * @usage: CardLengths form
     */
    public static final String UPDATE_LENGTH = "UPDATE tblCardLengths SET "
            + "lengthDescription = ? WHERE cardLength = ?";

    //-------------------------- GENERAL PARAMETERS ----------------------------
    /**
     * @no: 72
     * @purpose: returns all deposit fees
     * @usage: Deposits form
     */
    public static final String SELECT_ALL_DEPOSITS = "SELECT * FROM tblGeneralParameters";

    /**
     * @no: 73
     * @purpose: returns the year which the next deposit fee should start from
     * @usage: CardLengths form
     */
    public static final String NEXT_DEPOSIT_YEAR = "SELECT MAX(depositEndYear)+1 "
            + "as 'next year' FROM tblGeneralParameters";

    /**
     * @no: 74
     * @purpose: inserts a new deposit fee
     * @usage: CardLengths form
     */
    public static final String INSERT_DEPOSIT = "INSERT INTO tblGeneralParameters VALUES (?,?,?)";

    /**
     * @no: 75
     * @purpose: deletes a deposit fee given its PK
     * @usage: CardLengths form
     */
    public static final String DELETE_DEPOSIT = "DELETE FROM tblGeneralParameters WHERE "
            + "depositStartYear = ? and depositEndYear = ?";

    /**
     * @no: 76
     * @purpose: updates a deposit fee given its PK
     * @usage: CardLengths form
     */
    public static final String UPDATE_DEPOSIT = "UPDATE tblGeneralParameters "
            + "SET price = ? WHERE depositStartYear = ? and depositEndYear = ?";

    //-------------------------------- ROLE ------------------------------------
    /**
     * @no: 77
     * @purpose: return the role ID of a role by its name 
     * @usage: Roles form
     */
    public static final String SELECT_ROLE_ID_OF_ROLE_BY_NAME = "SELECT * FROM "
            + "tblRole WHERE RoleName = ?";

    /**
     * @no: 78
     * @purpose: returns all the roles
     * @usage: Roles form
     */
    public static final String SELECT_ALL_ROLES = "SELECT * FROM tblRole";

    /**
     * @no: 79
     * @purpose: inserts a role
     * @usage: Roles form
     */
    public static final String INSERT_ROLE = "INSERT INTO tblRole VALUES (?,?)";

    /**
     * @no: 80
     * @purpose: updates a role given its PK
     * @usage: Roles form
     */
    public static final String UPDATE_ROLE = "UPDATE tblRole SET RoleName = ? "
            + "WHERE RoleID = ?";

    /**
     * @no: 81
     * @purpose: deletes a role given its PK
     * @usage: Roles form
     */
    public static final String DELETE_ROLE = "DELETE FROM tblRole WHERE RoleID = ?";

    //-------------------------------- USERS -----------------------------------
    /**
     * @no: 82
     * @purpose: 
     * @usage: Users form
     */
    public static final String SELECT_USER = "SELECT username FROM tblUser u WHERE u.username=?";

    /**
     * @no: 83
     * @purpose:
     * @usage: Users form
     */
    public static final String SELECT_ALL_USERS_ANDTHEIR_ROLES = "SELECT * FROM "
            + "tblUser u join tblRole r on u.RoleID = r.RoleID";

    /**
     * @no: 84
     * @purpose:
     * @usage: Users form
     */
    public static final String INSERT_USER = "INSERT INTO tblUser VALUES (?,?,?)";

    //TODO: NOT IN USE
    /**
     * @no: 85
     * @purpose:
     * @usage: Users form
     */
    public static final String UPDATE_USER = "UPDATE tblUser SET RoleID = ? "
            + "WHERE username = ? and password = ?";

    /**
     * @no: 86
     * @purpose:
     * @usage: Users form
     */
    public static final String DELETE_USER = "DELETE FROM tblUser WHERE "
            + "username = ? and password = ?";

    //---------------------------- OTHER QUERIES -------------------------------
    /**
     * @no: 87
     * @purpose:
     * @usage: QueryForm form
     */
    public static final String QUERY1 = "select S.siteType, AVG(S.foundedYear) as 'average founded year',\n"
            + "COUNT(S.ID) as 'num of sites'\n"
            + "from tblSite S left outer join tblSiteFromExit SFE on S.ID=SFE.siteID\n"
            + "left outer join tblSiteFromSite SFS on (S.ID=SFS.siteID1 or\n"
            + "S.ID=SFS.siteID2)\n"
            + "where SFE.siteID is null and SFS.siteID1 is null and (S.siteType like\n"
            + "'%s%m%')\n"
            + "group by S.siteType\n"
            + "order by 'average founded year' desc";

    /**
     * @no: 88
     * @purpose:
     * @usage: QueryForm form
     */
    public static final String QUERY2 = "select PC.number, PC.purchaseDate, PC.isTourist,MAX(PCA.zoneNumber)\n"
            + "as 'maxZone'\n"
            + "from dbo.tblPaperCard as PC inner join dbo.tblPaperCardAreas as PCA\n"
            + "on PC.number = PCA.cardNumber\n"
            + "where DATEDIFF(day, PC.purchaseDate, getdate())<60 and PC.number not\n"
            + "in(select A.cardNumber\n"
            + " from dbo.tblActivity as A\n"
            + " where DATEDIFF(day, A.activityDate, getdate())<30)\n"
            + "group by PC.number, PC.purchaseDate, PC.isTourist";
    /**
     * @no: 89
     * @purpose:
     * @usage: QueryForm form
     */
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
    /**
     * @no: 90
     * @purpose:
     * @usage: QueryForm form
     */
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
    /**
     * @no: 91
     * @purpose:
     * @usage: QueryForm form
     */
    public static final String USE_LONDON2 = "USE [LondonU2]";
    /**
     * @no: 92
     * @purpose:
     * @usage:
     */
    public static final String IF_EXISTS_endDates = "IF EXISTS (SELECT * "
            + "FROM sys.views WHERE object_id = OBJECT_ID(N'[dbo].[endDates]'))\n"
            + "DROP VIEW [dbo].[endDates]";
    /**
     * @no: 93
     * @purpose:
     * @usage: QueryForm form
     */
    public static final String IF_EXISTS_numOfCardsForPrice = "IF  EXISTS (SELECT * "
            + "FROM sys.views WHERE object_id = OBJECT_ID(N'[dbo].[numOfCardsForPrice]'))\n"
            + "DROP VIEW [dbo].[numOfCardsForPrice]";
    /**
     * @no: 94
     * @purpose:
     * @usage: QueryForm form
     */
    public static final String IF_EXISTS_NumOfOysterRides = "IF  EXISTS (SELECT * "
            + "FROM sys.views WHERE object_id = OBJECT_ID(N'[dbo].[NumOfOysterRides]'))\n"
            + "DROP VIEW [dbo].[NumOfOysterRides]";
    /**
     * @no: 95
     * @purpose:
     * @usage: QueryForm form
     */
    public static final String IF_EXISTS_yearsWithMoreThan5000 = "IF  EXISTS (SELECT * "
            + "FROM sys.views WHERE object_id = OBJECT_ID(N'[dbo].[yearsWithMoreThan5000]'))\n"
            + "DROP VIEW [dbo].[yearsWithMoreThan5000]";
    /**
     * @no: 96
     * @purpose:
     * @usage: QueryForm form
     */
    public static final String SET_ANSI_NULLS_ON = "SET ANSI_NULLS ON";
    /**
     * @no: 97
     * @purpose:
     * @usage: QueryForm form
     */
    public static final String SET_QUOTED_IDENTIFIER_ON = "SET QUOTED_IDENTIFIER ON";
    /**
     * @no: 98
     * @purpose:
     * @usage: QueryForm form
     */
    public static final String CREATE_VIEW_END_DATES = "create view [dbo].[endDates] as\n"
            + "select OCA.*, case\n"
            + "when OCA.cardLength='1' then DATEADD(dd,1,OCA.cardPurchaseDate)\n"
            + "when OCA.cardLength='2' then DATEADD(dd,3,OCA.cardPurchaseDate)\n"
            + "when OCA.cardLength='3' then DATEADD(dd,7,OCA.cardPurchaseDate)\n"
            + "when OCA.cardLength='4' then DATEADD(mm,1,OCA.cardPurchaseDate)\n"
            + "when OCA.cardLength='5' then DATEADD(mm,3,OCA.cardPurchaseDate)\n"
            + "when OCA.cardLength='6' then DATEADD(yyyy,1,OCA.cardPurchaseDate)\n"
            + "end as 'date'\n"
            + "from tblOysterCardAreas OCA";

    /**
     * @no: 99
     * @purpose:
     * @usage: QueryForm form
     */
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

    /**
     * @no: 100
     * @purpose:
     * @usage: QueryForm form
     */
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
    /**
     * @no: 101
     * @purpose:
     * @usage: QueryForm form
     */
    public static final String CREATE_VIEW_numOfCardsForPrice
            = "create view [dbo].[numOfCardsForPrice] as\n"
            + "select GP.depositStartYear,GP.depositEndYear,GP.price,COUNT(*) as\n"
            + "'num of cards'\n"
            + "from tblGeneralParameters GP, tblOysterCard OC\n"
            + "where (GP.depositStartYear <= year(OC.purchaseDate))\n"
            + "and (GP.depositEndYear >= year(OC.purchaseDate))\n"
            + "group by GP.depositStartYear,GP.depositEndYear,GP.price\n"
            + "having (COUNT(*)/(GP.depositEndYear-GP.depositStartYear+1))>= 300";

    /**
     * @no: 102
     * @purpose:
     * @usage: QueryForm form
     */
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

    /**
     * @no: 103
     * @purpose:
     * @usage: QueryForm form
     */
    public static final String CREATE_VIEW_NumOfOysterRides = "create view [dbo].[NumOfOysterRides] as\n"
            + "select YEAR(A.activityDate) as theYear,COUNT(*) as 'num of rides for Oyster'\n"
            + "from tblOysterCard OC inner join tblActivity A\n"
            + "on OC.number=A.cardNumber and OC.purchaseDate=A.cardPurchaseDate,\n"
            + "yearsWithMoreThan5000 MT5\n"
            + "where (year(A.activityDate) >= MT5.depositStartYear)\n"
            + "and (year(A.activityDate) <= MT5.depositEndYear)\n"
            + "group by YEAR(A.activityDate)";

    /**
     * @no: 104
     * @purpose:
     * @usage: QueryForm form
     */
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
