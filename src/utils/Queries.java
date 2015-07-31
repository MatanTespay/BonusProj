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
public final class Queries {

    //---------------------------- ACTIVITY ------------------------------------
    /**
     * @no: 1
     * @purpose: return an activity given its PK
     * @usage: Activity form
     */
    public static final String SELECT_ACTIVITY = "SELECT A.*, S.name FROM "
            + "tblActivity as A join tblStation as S on A.stationID = S.ID "
            + "WHERE A.cardNumber = ? and A.cardPurchaseDate = ? and "
            + "A.activityDate = ?";

    /**
     * @no: 2
     * @purpose: checks if a card already have 1 activity or more given its PK
     * @usage: Activity form
     */
    public static final String IS_CARD_1ST_ACTIVITY = "SELECT TOP 1 * FROM "
            + "tblActivity as A WHERE A.cardNumber = ? and A.cardPurchaseDate = ?";

    /**
     * @no: 3
     * @purpose: returns the activities of card given its PK
     * @usage: Activity form
     */
    public static final String SELECT_ACTIVITIES_OF_CARD = "SELECT activityDate "
            + "FROM tblActivity WHERE cardNumber = ? and cardPurchaseDate = ?";

    /**
     * @no: 4
     * @purpose: returns all the number of card that are have 1 activity or more
     * given its PK
     * @usage: ActivityDialog form
     */
    public static final String SELECT_CARD_NUMBERS_WITH_ACTIVITIES = "SELECT "
            + "distinct C.number FROM tblCard as C join tblActivity t on "
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
    
    /**
     * @no: 7.5
     * @purpose: deletes an activity given its PK
     * @usage: Activity form
     */
    public static final String CHECK_PROGRAMS_FOR_CARD = "SELECT x.cardNumber, "
            + "x.cardPurchaseDate,x.cardLength FROM (SELECT * FROM "
            + "tblPaperCardAreas UNION SELECT * FROM tblOysterCardAreas) as x "
            + "INNER JOIN tblCardLengths as LENG ON (x.cardLength=LENG.cardLength) "
            + "WHERE cardNumber = ? AND cardPurchaseDate = ? AND zoneNumber = ? "
            + "AND((LENG.cardLength = '1' AND DATEDIFF(day,x.cardPurchaseDate,GETDATE()) <= 1 ) "
            + "OR(LENG.cardLength = '2' AND DATEDIFF(day,x.cardPurchaseDate,GETDATE()) <= 3 ) "
            + "OR(LENG.cardLength = '3' AND DATEDIFF(day,x.cardPurchaseDate,GETDATE()) <= 7 ) "
            + "OR(LENG.cardLength = '4' AND DATEDIFF(month,x.cardPurchaseDate,GETDATE()) <= 1 ) "
            + "OR(LENG.cardLength = '5' AND DATEDIFF(month,x.cardPurchaseDate,GETDATE()) <= 3 ) "
            + "OR(LENG.cardLength = '6' AND DATEDIFF(year,x.cardPurchaseDate,GETDATE()) <= 1 ))";

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
    public static final String SELECT_LINE_AND_COLOR = "SELECT * FROM tblLine as L "
            + "join tblLineColor as LC on L.name = LC.lineName WHERE L.name = ?";

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
     * @purpose: updates a line given its PK
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
    public static final String SELECT_STATION_ID_BY_NAME = "SELECT ID FROM "
            + "tblStation as S WHERE S.name = ?";

    /**
     * @no: 17
     * @purpose: returns all station IDs and their names
     * @usage: station comboboxes in Line form and Station dialog
     */
    public static final String SELECT_ALL_STATION_IDS_AND_NAMES = "SELECT ID, "
            + "name FROM tblStation";

    /**
     * @no: 18
     * @purpose: returns a station given its PK
     * @usage: Activity and Station forms
     */
    public static final String SELECT_STATION = "SELECT * FROM tblStation WHERE "
            + "ID = ?";

    /**
     * @no: 19
     * @purpose: inserts a station
     * @usage: Station form
     */
    public static final String INSERT_STATION = "INSERT INTO tblStation VALUES "
            + "(?,?,?,?)";

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
    public static final String DELETE_STATION = "DELETE FROM tblStation WHERE "
            + "ID = ?";
    
    /**
     * @no: 21.5
     * @purpose: returns the zone number of a station given its PK
     * @usage: Station form
     */
    public static final String SELECT_ZONEID_BY_STATIONID = "SELECT zoneNumber "
            + "FROM tblStation WHERE ID = ?";

    //------------------------ STATION IN LINE ---------------------------------
    /**
     * @no: 22
     * @purpose: returns station of a line given its PK
     * @usage: Line form
     */
    public static final String SELECT_STATIONS_OF_LINE = "SELECT * FROM "
            + "tblStationInLine as SIL join tblStation as S on SIL.stationID = "
            + "S.ID WHERE SIL.lineName = ?";

    /**
     * @no: 23
     * @purpose: returns lines of a station (and their colors) given its PK
     * @usage: Station form
     */
    public static final String SELECT_LINES_OF_STATION = "SELECT L.*, LC.name "
            + "as 'colorName' FROM tblStationInLine as SIL join tblLine as L on "
            + "SIL.lineName = L.name join tblLineColor  as LC on L.name = "
            + "LC.lineName WHERE SIL.stationID = ? ";

    /**
     * @no: 24
     * @purpose: returns station IDs and names that have one line or more
     * @usage: Activity form
     */
    public static final String SELECT_STATIONS_WITH_LINES = "SELECT distinct "
            + "sil.stationID ,s.name FROM  tblStation s  inner join "
            + "tblStationInLine sil on s.ID = sil.stationID";

    /**
     * @no: 25
     * @purpose: insert a StationInLine
     * @usage: Line and Station forms
     */
    public static final String INSERT_STATION_IN_LINE = "INSERT INTO "
            + "tblStationInLine VALUES (?,?)";

    /**
     * @no: 26
     * @purpose: deletes a stationInLine
     * @usage: Line and Station forms
     */
    public static final String DELETE_STATION_IN_LINE = "DELETE FROM "
            + "tblStationInLine WHERE stationID = ? AND  lineName = ?";

    //----------------------------- CARD ---------------------------------------
    /**
     * @no: 27
     * @purpose: returns a new (unused) card number
     * @usage: Card form
     */
    public static final String SELECT_NEW_CARD = "SELECT TOP 1 number+1 as "
            + "'number' FROM tblCard ORDER BY number DESC";

    /**
     * @no: 28
     * @purpose: returns a card given its PK
     * @usage: Card form
     */
    public static final String SELECT_CARD = "SELECT * FROM tblCard WHERE number "
            + "= ? and purchaseDate = ?";

    /**
     * @no: 29
     * @purpose: return all the card numbers
     * @usage: card comboboxes in Card dialog and Activity form
     */
    public static final String SELECT_ALL_CARD_NUMBERS = "SELECT distinct "
            + "number FROM  tblCard";

    /**
     * @no: 30
     * @purpose: returns the purchase dates of a card given its number
     * @usage: purchase date comboboxes in Activity form and Activity dialog
     */
    public static final String SELECT_PURCHASE_DATES_OF_CARD = "SELECT "
            + "C.purchaseDate FROM tblCard as C WHERE C.number = ?";

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
     * @purpose: return true of false whether the card has an activity (bounded)
     * given its number
     * @usage: Card form
     */
    public static final String IS_CARD_BOUNDED = "SELECT CASE WHEN EXISTS "
            + "(SELECT * FROM tblActivity WHERE cardNumber = ?) THEN CAST "
            + "(1 AS BIT) ELSE CAST(0 AS BIT) END";
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
    public static final String INSERT_PAPER_CARD = "INSERT INTO tblPaperCard "
            + "VALUES (?,?,?)";

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
    public static final String SELECT_ALL_OYSTER_CARDS = "SELECT * FROM "
            + "tblOysterCard";

    /**
     * @no: 38
     * @purpose: returns the picture of an oyster card given is number
     * @usage: Card form
     */
    public static final String SELECT_OYSTER_PICTURE = "SELECT picture FROM "
            + "tblOysterCard WHERE number = ?";

    /**
     * @no: 39
     * @purpose: inserts an oyster card
     * @usage: Card form
     */
    public static final String INSERT_OYSTER_CARD = "INSERT INTO tblOysterCard "
            + "VALUES (?,?,?)";

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
     * @purpose: returns a paper card program (and expiration date) given its
     * card number
     * @usage: Card form
     */
    public static final String SELECT_PAPER_PROGRAMS = "SELECT TOP 1 *, case "
            + "when PCA.cardLength='1' then DATEADD(dd,1,PCA.cardPurchaseDate) "
            + "when PCA.cardLength='2' then DATEADD(dd,3,PCA.cardPurchaseDate) "
            + "when PCA.cardLength='3' then DATEADD(dd,7,PCA.cardPurchaseDate) "
            + "when PCA.cardLength='4' then DATEADD(mm,1,PCA.cardPurchaseDate) "
            + "when PCA.cardLength='5' then DATEADD(mm,3,PCA.cardPurchaseDate) "
            + "when PCA.cardLength='6' then DATEADD(yyyy,1,PCA.cardPurchaseDate) "
            + "end as 'expirationDate' FROM tblPaperCardAreas PCA WHERE "
            + "cardNumber = ? ORDER BY zoneNumber DESC";

    /**
     * @no: 42
     * @purpose: inserts paper program
     * @usage: Card form
     */
    public static final String INSERT_PAPER_PROGRAM = "INSERT INTO "
            + "tblPaperCardAreas VALUES (?,?,?,?)";

    /**
     * @no: 43
     * @purpose: deletes a paper program given its PK
     * @usage: Card form
     */
    public static final String DELETE_PAPER_PROGRAM = "DELETE FROM "
            + "tblPaperCardAreas WHERE cardNumber=? and cardPurchaseDate = ? "
            + "and zoneNumber <= ? and cardLength = ?";

    /**
     * @no: 44
     * @purpose: returns all the zones a new paper card should have
     * @usage: Card form
     */
    public static final String PAPER_PROGRAM_ZONES_TO_ADD = "SELECT * FROM "
            + "tblZone WHERE number <= ? and number not in (SELECT zoneNumber FROM "
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
     * @purpose: returns all oyster card programs (and their expiration date)
     * given their card number
     * @usage: Card form
     */
    public static final String SELECT_OYSTER_PROGRAMS = "SELECT TOP 1 *, case "
            + "when OCA.cardLength='1' then DATEADD(dd,1,OCA.cardPurchaseDate) "
            + "when OCA.cardLength='2' then DATEADD(dd,3,OCA.cardPurchaseDate) "
            + "when OCA.cardLength='3' then DATEADD(dd,7,OCA.cardPurchaseDate) "
            + "when OCA.cardLength='4' then DATEADD(mm,1,OCA.cardPurchaseDate) "
            + "when OCA.cardLength='5' then DATEADD(mm,3,OCA.cardPurchaseDate) "
            + "when OCA.cardLength='6' then DATEADD(yyyy,1,OCA.cardPurchaseDate) "
            + "end as 'expirationDate' FROM tblOysterCardAreas OCA WHERE "
            + "cardNumber = ? ORDER BY zoneNumber DESC";

    /**
     * @no: 47
     * @purpose: inserts a paper program
     * @usage: Card form
     */
    public static final String INSERT_OYSTER_PROGRAM = "INSERT INTO "
            + "tblOysterCardAreas VALUES (?,?,?,?)";

    /**
     * @no: 48
     * @purpose: deletes an oyster program
     * @usage: Card form
     */
    public static final String DELETE_OYSTER_PROGRAM = "DELETE FROM "
            + "tblOysterCardAreas WHERE cardNumber=? and cardPurchaseDate = ? "
            + "and zoneNumber <= ? and cardLength = ?";

    /**
     * @no: 49
     * @purpose: returns all the zones a new oyster card should have
     * @usage: Card form
     */
    public static final String OYSTER_PROGRAM_ZONES_TO_ADD = "SELECT * FROM "
            + "tblZone WHERE number <= ? and number not in (SELECT zoneNumber FROM "
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
    public static final String SELECT_SITEID_BY_NAME = "Select * From tblSite "
            + "as S WHERE S.NAME = ?";

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
    public static final String SELECT_LINES_OR_STATION__BY_NAME = "SELECT "
            + "SIL.lineName FROM tblStationInLine SIL JOIN tblStation s on "
            + "sil.stationID = s.ID WHERE s.name = ?";

    /**
     * @no: 54
     * @purpose: return stations that have lines
     * @usage: Site form
     */
    public static final String SELECT_ONLY_STATION_WITH_LINES = "SELECT distinct "
            + "ID,name FROM tblStation s join tblStationInLine sil on s.ID = "
            + "sil.stationID";

    /**
     * @no: 55
     * @purpose: inserts site
     * @usage: Site form
     */
    public static final String INSERT_SITE = "INSERT INTO tblSite VALUES "
            + "(?,?,?,?)";

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
    public static final String UPDATE_SITE = "UPDATE tblSite SET name = ?, "
            + "siteDescription = ? ,foundedYear = ? ,siteType = ? WHERE ID = ?";

    /**
     * @no: 58
     * @purpose: return a site given its PK
     * @usage: Site form
     */
    public static final String SELECT_SITE_BY_SITEID = "Select * From tblSite as "
            + "S WHERE S.ID = ?";

    //----------------------------- SITE FROM EXIT -----------------------------
    /**
     * @no: 59
     * @purpose: inserts siteFromExit
     * @usage: Site form
     */
    public static final String INSERT_SFE = "INSERT INTO tblSiteFromExit VALUES "
            + "(?,?,?,?)";

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
    public static final String SELECT_SFE_BY_SITEID = "SELECT * FROM "
            + "tblSiteFromExit as SFE join tblStation as S on SFE.stationID = "
            + "S.ID WHERE SFE.siteID = ? ";

    //----------------------------- SITE FROM SITE -----------------------------
    /**
     * @no: 62
     * @purpose: returns all nearby sites for a site given its PK
     * @usage: Site form
     */
    public static final String SELECT_SFS_BY_SITEID = "SELECT * FROM tblSiteFromSite "
            + "as SFS join tblSite as S on SFS.siteID2 = S.ID and s.ID in(SELECT "
            + "SFS.siteID2 FROM tblSiteFromSite as SFS) WHERE SFS.siteID1 = ?";

    /**
     * @no: 63
     * @purpose: inserts a SiteFromSite
     * @usage: Site form
     */
    public static final String INSERT_SFS = "INSERT INTO tblSiteFromSite VALUES "
            + "(?,?,?)";

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
    public static final String INSERT_SYTE_TYPE = "INSERT INTO tblSiteType "
            + "VALUES (?)";

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
    public static final String INSERT_LENGTH = "INSERT INTO tblCardLengths "
            + "VALUES (?,?)";

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
    public static final String SELECT_ALL_DEPOSITS = "SELECT * FROM "
            + "tblGeneralParameters";

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
    public static final String INSERT_DEPOSIT = "INSERT INTO tblGeneralParameters "
            + "VALUES (?,?,?)";

    /**
     * @no: 75
     * @purpose: deletes a deposit fee given its PK
     * @usage: CardLengths form
     */
    public static final String DELETE_DEPOSIT = "DELETE FROM tblGeneralParameters "
            + "WHERE depositStartYear = ? and depositEndYear = ?";

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
    public static final String SELECT_USER = "SELECT username FROM tblUser u "
            + "WHERE u.username=?";

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

    /**
     * @no: 85
     * @purpose: updates a user given its PK
     * @usage: Users form
     */
    public static final String UPDATE_USER = "UPDATE tblUser SET username = ? "
            + ",password = ?, RoleID = ? WHERE username=? ";

    /**
     * @no: 86
     * @purpose: deletes a user given its PK
     * @usage: Users form
     */
    public static final String DELETE_USER = "DELETE FROM tblUser WHERE username = ? ";

    //---------------------------- OTHER QUERIES -------------------------------
    /**
     * @no: 87
     * @purpose: returns remote sites
     * @usage: QueryForm form
     */
    public static final String QUERY1 = "SELECT S.siteType, AVG(S.foundedYear) "
            + "as 'average founded year', COUNT(S.ID) as 'num of sites' FROM "
            + "tblSite S left outer join tblSiteFromExit SFE on S.ID=SFE.siteID "
            + "left outer join tblSiteFromSite SFS on (S.ID = SFS.siteID1 or "
            + "S.ID = SFS.siteID2) WHERE SFE.siteID is null and SFS.siteID1 "
            + "is null and S.siteType like '%s%m%' GROUP BY S.siteType ORDER BY "
            + "'average founded year' DESC";

    /**
     * @no: 88
     * @purpose: returns unused Paper Cards
     * @usage: QueryForm form
     */
    public static final String QUERY2 = "SELECT PC.number, PC.purchaseDate, "
            + "PC.isTourist,MAX(PCA.zoneNumber) as 'maxZone' FROM tblPaperCard "
            + "as PC inner join tblPaperCardAreas as PCA on PC.number = "
            + "PCA.cardNumber WHERE DATEDIFF(day, PC.purchaseDate, getdate())<60 "
            + "and PC.number not in(SELECT A.cardNumber FROM tblActivity as A "
            + "WHERE DATEDIFF(day, A.activityDate, getdate())<30) GROUP BY "
            + "PC.number, PC.purchaseDate, PC.isTourist";
    /**
     * @no: 89
     * @purpose: returns main stations
     * @usage: QueryForm form
     */
    public static final String QUERY3 = "SELECT SE.lineName,SE.stationID, "
            + "COUNT(SE.siteID) as numOfSitesForLine FROM tblSiteFromExit SE "
            + "WHERE SE.distance<0.5 and not exists (SELECT SE1.siteID FROM "
            + "tblSiteFromExit SE1 WHERE SE1.stationID!=SE.stationID and "
            + "SE1.distance<0.5 and SE1.siteID=SE.siteID) GROUP BY SE.lineName, "
            + "SE.stationID HAVING COUNT(SE.siteID)>=3 ORDER BY SE.lineName, "
            + "numOfSitesForLine desc";
    /**
     * @no: 90
     * @purpose:returns most active stations
     * @usage: QueryForm form
     */
    public static final String QUERY4 = "SELECT S.ID, S.name, MIN(SFE.distance)"
            + "as 'minDistance' FROM tblStation as S inner join tblSiteFromExit "
            + "SFE on S.ID = SFE.stationID join tblSite as SI1 on SI1.ID = "
            + "SFE.siteID WHERE S.platformNum > 4 and S.ID in (SELECT "
            + "SIL1.stationID FROM tblStationInLine as SIL1 WHERE S.ID = "
            + "SIL1.stationID GROUP BY SIL1.stationID HAVING COUNT (SIL1.lineName)"
            + " > 2 intersect (SELECT SFX.stationID FROM tblSiteFromExit as "
            + "SFX WHERE S.ID = SFX.stationID and SFX.distance <= 1.5 GROUP BY "
            + "SFX.stationID HAVING COUNT (SFX.siteID) >= 5) GROUP BY S.ID, S.name";
    /**
     * @no: 91
     * @purpose: executes "USE LONDON U2" (the db name)
     * @usage: QueryForm form
     */
    public static final String USE_LONDON2 = "USE LondonU2";
    /**
     * @no: 92
     * @purpose: checks if the view 'endDates' already exists, if so - drops it
     * @usage: QueryForm form
     */
    public static final String IF_EXISTS_endDates = "IF EXISTS (SELECT *  FROM "
            + "sys.views WHERE object_id = OBJECT_ID(N'[dbo].[endDates]'))"
            + "DROP VIEW [dbo].[endDates]";
    /**
     * @no: 93
     * @purpose: checks if the view 'numOfCardsForPrice' already exists, if so -
     * drops it
     * @usage: QueryForm form
     */
    public static final String IF_EXISTS_numOfCardsForPrice = "IF  EXISTS (SELECT * "
            + "FROM sys.views WHERE object_id = OBJECT_ID(N'[dbo].[numOfCardsForPrice]'))"
            + "DROP VIEW [dbo].[numOfCardsForPrice]";
    /**
     * @no: 94
     * @purpose: checks if the view 'NumOfOysterRides' already exists, if so -
     * drops it
     * @usage: QueryForm form
     */
    public static final String IF_EXISTS_NumOfOysterRides = "IF  EXISTS (SELECT * "
            + "FROM sys.views WHERE object_id = OBJECT_ID(N'[dbo].[NumOfOysterRides]'))"
            + "DROP VIEW [dbo].[NumOfOysterRides]";
    /**
     * @no: 95
     * @purpose: checks if the view 'yearsWithMoreThan5000' already exists, if
     * so - drops it
     * @usage: QueryForm form
     */
    public static final String IF_EXISTS_yearsWithMoreThan5000 = "IF  EXISTS (SELECT * "
            + "FROM sys.views WHERE object_id = OBJECT_ID(N'[dbo].[yearsWithMoreThan5000]'))"
            + "DROP VIEW [dbo].[yearsWithMoreThan5000]";
    /**
     * @no: 96
     * @purpose: executes "SET_ANSI_NULLS ON"
     * @usage: QueryForm form
     */
    public static final String SET_ANSI_NULLS_ON = "SET ANSI_NULLS ON";
    /**
     * @no: 97
     * @purpose: executes "SET QUOTED_IDENTIFIER ON"
     * @usage: QueryForm form
     */
    public static final String SET_QUOTED_IDENTIFIER_ON = "SET QUOTED_IDENTIFIER ON";
    /**
     * @no: 98
     * @purpose: creates the view 'endDates' (expiration date of all oyster
     * cards)
     * @usage: QueryForm form
     */
    public static final String CREATE_VIEW_END_DATES = "create view endDates as "
            + "SELECT OCA.*, case "
            + "when OCA.cardLength='1' then DATEADD(dd,1,OCA.cardPurchaseDate)"
            + "when OCA.cardLength='2' then DATEADD(dd,3,OCA.cardPurchaseDate)"
            + "when OCA.cardLength='3' then DATEADD(dd,7,OCA.cardPurchaseDate)"
            + "when OCA.cardLength='4' then DATEADD(mm,1,OCA.cardPurchaseDate)"
            + "when OCA.cardLength='5' then DATEADD(mm,3,OCA.cardPurchaseDate)"
            + "when OCA.cardLength='6' then DATEADD(yyyy,1,OCA.cardPurchaseDate)"
            + "end as 'date' FROM tblOysterCardAreas OCA";

    /**
     * @no: 99
     * @purpose: returns card that have activity issues (zone or date is not
     * right)
     * @usage: QueryForm form
     */
    public static final String QUERY5 = "SELECT A.cardNumber,A.cardPurchaseDate "
            + "FROM tblActivity A inner join tblStation S on A.stationID=S.ID "
            + "WHERE S.zoneNumber>(SELECT MAX(OCA.zoneNumber) FROM tblOysterCardAreas "
            + "OCA WHERE A.cardNumber = OCA.cardNumber and A.cardPurchaseDate = "
            + "OCA.cardPurchaseDate) UNION SELECT A.cardNumber, A.cardPurchaseDate "
            + "FROM tblActivity A inner join endDates ED on A.cardNumber = "
            + "ED.cardNumber and A.cardPurchaseDate=ED.cardPurchaseDate WHERE "
            + "A.activityDate>ED.date or A.activityDate<A.cardPurchaseDate "
            + "GROUP BY A.cardNumber,A.cardPurchaseDate";

    /**
     * @no: 100
     * @purpose: returns oyster cards that have been very active lately
     * @usage: QueryForm form
     */
    public static final String QUERY6 = "SELECT A.cardNumber, "
            + "COUNT(A.cardNumber)/2 as 'NumOfTrip' FROM tblActivity A "
            + "WHERE A.cardNumber in (SELECT A1.cardNumber FROM tblActivity as "
            + "A1 WHERE DATEDIFF(month, A1.activityDate, getdate()) = 0 and "
            + "A1.activityType = 'I' and exists (SELECT A2.cardNumber FROM "
            + "tblActivity as A2 WHERE (convert(varchar(10), A2.activityDate, "
            + "120) = convert(varchar(10), A1.activityDate, 120) and "
            + "(activityType = 'O') and A1.cardNumber = A2.cardNumber)) GROUP BY "
            + "A1.cardNumber HAVING COUNT (A1.activityDate) > 19) and not exists "
            + "(SELECT ACA.cardNumber FROM tblOysterCardAreas as ACA WHERE "
            + "((ACA.cardNumber = A.cardNumber) and (ACA.cardLength "
            + "IN('1', '3', 'W')))) GROUP BY A.cardNumber ORDER BY NumOfTrip desc";
    /**
     * @no: 101
     * @purpose: creates the view 'numOfCardsForPrice'
     * @usage: QueryForm form
     */
    public static final String CREATE_VIEW_numOfCardsForPrice
            = "create view [dbo].[numOfCardsForPrice] as SELECT "
            + "GP.depositStartYear,GP.depositEndYear,GP.price,COUNT(*) as "
            + "'num of cards' FROM tblGeneralParameters GP, tblOysterCard OC "
            + "WHERE (GP.depositStartYear <= year(OC.purchaseDate)) and "
            + "(GP.depositEndYear >= year(OC.purchaseDate)) GROUP BY "
            + "GP.depositStartYear,GP.depositEndYear,GP.price HAVING "
            + "(COUNT(*)/(GP.depositEndYear-GP.depositStartYear+1))>= 300";

    /**
     * @no: 102
     * @purpose: creates the view 'yearsWithMoreThan5000'
     * @usage: QueryForm form
     */
    public static final String CREATE_VIEW_yearsWithMoreThan5000
            = "create view yearsWithMoreThan5000 as SELECT NOC.depositStartYear, "
            + "NOC.depositEndYear,COUNT(*) as 'num of rides' FROM tblOysterCard "
            + "OC inner join tblActivity A on OC.number=A.cardNumber and "
            + "OC.purchaseDate=A.cardPurchaseDate, numOfCardsForPrice NOC "
            + "WHERE (year(A.activityDate) >= NOC.depositStartYear) and "
            + "(year(A.activityDate) <= NOC.depositEndYear) GROUP BY "
            + "NOC.depositStartYear,NOC.depositEndYear HAVING COUNT(*)>5000";

    /**
     * @no: 103
     * @purpose: creates the view 'numOfOysterRides'
     * @usage: QueryForm form
     */
    public static final String CREATE_VIEW_numOfOysterRides = "create view "
            + "NumOfOysterRides as SELECT YEAR(A.activityDate) as theYear,COUNT(*) "
            + "as 'num of rides for Oyster' FROM tblOysterCard OC inner join "
            + "tblActivity A on OC.number=A.cardNumber and OC.purchaseDate = "
            + "A.cardPurchaseDate, yearsWithMoreThan5000 MT5 WHERE "
            + "(year(A.activityDate) >= MT5.depositStartYear) and "
            + "(year(A.activityDate) <= MT5.depositEndYear) GROUP BY "
            + "YEAR(A.activityDate)";

    /**
     * @no: 104
     * @purpose: returns profitable Deposit Years
     * @usage: QueryForm form
     */
    public static final String QUERY7 = "SELECT year(A.activityDate) as 'Year', "
            + "case when NOR.[num of rides for Oyster] is null then 'Low' when "
            + "NOR.[num of rides for Oyster] = COUNT(*) then 'High' when "
            + "(cast(NOR.[num of rides for Oyster] as float)/COUNT(*))<0.2 then "
            + "'Low' when (cast(NOR.[num of rides for Oyster] as float)/"
            + "COUNT(*))<0.6 then 'Medium' when (cast(NOR.[num of rides for Oyster] "
            + "as float)/COUNT(*))>=0.6 then 'High' end as 'how many oysters' "
            + "FROM tblActivity A left outer join NumOfOysterRides NOR on "
            + "year(A.activityDate) = NOR.theYear, yearsWithMoreThan5000 MT5 "
            + "WHERE (year(A.activityDate)>=MT5.depositStartYear and "
            + "year(A.activityDate)<=MT5.depositEndYear) GROUP BY "
            + "year(A.activityDate), NOR.[num of rides for Oyster]";
}
