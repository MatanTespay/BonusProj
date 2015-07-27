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

    //----------------------------LINE & COLOR----------------------------------  
    public static final String SELECT_LINE_AND_COLOR = "SELECT * FROM tblLine As L "
            + "join tblLineColor As LC on L.name = LC.lineName WHERE L.name = ?";

    //INSERT LINE transaction
    public static final String INSERT_LINE = "INSERT INTO tblLine VALUES (?,?,?,?)";
    public static final String INSERT_COLOR = "INSERT INTO tblLineColor VALUES (?,?)";

    //UPDATE LINE transaction
    public static final String UPDATE_LINE = "UPDATE tblLine SET foundedYear = ?, "
            + "lineType = ?, lineLength = ? WHERE name = ?";
    public static final String UPDATE_COLOR = "UPDATE tblLineColor SET name = ? "
            + "WHERE lineName = ?";

    // note: deleting a line automatically deletes the color
    public static final String DELETE_LINE = "DELETE FROM tblLine WHERE name = ?";

    //------------------------------STATION-------------------------------------
    public static final String SELECT_STATION_ID_BY_NAME = "SELECT ID FROM tblStation As S WHERE S.name = ?";

    public static final String SELECT_STATION = "SELECT * FROM tblStation WHERE ID = ?";

    public static final String INSERT_STATION = "INSERT INTO tblStation VALUES (?,?,?,?)";

    public static final String UPDATE_STATION = "UPDATE tblStation SET name = ?, "
            + "platformNum = ?, kiosk = ?, zoneNumber = ? WHERE ID = ?";

    public final static String DELETE_STATION = "DELETE FROM tblStation WHERE ID = ?";

    //-------------------------STATION IN LINE----------------------------------
    public static final String SELECT_STATIONS_OF_LINE = "SELECT * FROM tblStationInLine "
            + "As SIL join tblStation As S on SIL.stationID = S.ID WHERE SIL.lineName = ?";

    public static final String SELECT_LINES_OF_STATION = "SELECT L.*, LC.name "
            + "As 'colorName' FROM tblStationInLine As SIL join tblLine As L on "
            + "SIL.lineName = L.name join tblLineColor  As LC on L.name = "
            + "LC.lineName WHERE SIL.stationID = ? ";

    public static final String INSERT_STATION_IN_LINE = "INSERT INTO tblStationInLine VALUES (?,?)";

    public static final String DELETE_STATION_IN_LINE = "DELETE FROM tblStationInLine "
            + "WHERE stationID = ? AND  lineName = ?";

    //------------------------------CARD----------------------------------------
    public static final String SELECT_NEW_CARD = "SELECT TOP 1 number+1 As 'number' "
            + "FROM tblCard ORDER BY number DESC";

    public static final String SELECT_CARD = "SELECT * FROM tblCard "
            + "WHERE number = ? and purchaseDate = ?";

    public static final String INSERT_CARD = "INSERT INTO tblCard VALUES(?,?,?)";

    public static final String DELETE_CARD = "DELETE FROM tblCard WHERE number = ? "
            + "and purchaseDate = ?";
    //---------------------------PAPER CARD-------------------------------------
    public static final String SELECT_ALL_PAPER_CARDS = "SELECT * FROM tblPaperCard";

    public static final String SELECT_PAPER_ISTOURIST = "SELECT isTourist "
            + "FROM tblPaperCard WHERE number = ?";

    public static final String INSERT_PAPER_CARD = "INSERT INTO tblPaperCard VALUES (?,?,?)";

    public static final String UPDATE_PAPER_CARD = "UPDATE tblPaperCard "
            + "SET isTourist = ? WHERE number = ? and purchaseDate = ?";

    public static final String DELETE_PAPER_CARD = "DELETE FROM tblPaperCard WHERE cardNumber = ? "
            + "and cardPurchaseDate = ? and zoneNumber = ? and cardLength = ?";

    //---------------------------OYSTER CARD------------------------------------
    public static final String SELECT_ALL_OYSTER_CARDS = "SELECT * FROM tblOysterCard";

    public static final String SELECT_OYSTER_PICTURE = "SELECT picture FROM tblOysterCard "
            + "WHERE number = ?";

    public static final String INSERT_OYSTER_CARD = "INSERT INTO tblOysterCard VALUES (?,?,?)";

    public static final String UPDATE_OYSTER_CARD = "UPDATE tblOysterCard "
            + "SET picture = ? WHERE number = ? and purchaseDate = ?";

    public static final String DELETE_OYSTER_CARD = "DELETE FROM tblPaperCard WHERE cardNumber = ? "
            + "and cardPurchaseDate = ? and zoneNumber = ? and cardLength = ?";

    //-------------------------PAPER CARD AREAS---------------------------------
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
            + "WHERE cardNumber=? and cardPurchaseDate = ? and zoneNumber = ?";

    //-------------------------OYSTER CARD AREAS--------------------------------
    public static final String SELECT_OYSTER_PROGRAMS = "SELECT TOP 1 *, case "
            + "when OCA.cardLength='1' then DATEADD(dd,1,OCA.cardPurchaseDate) "
            + "when OCA.cardLength='2' then DATEADD(dd,3,OCA.cardPurchaseDate) "
            + "when OCA.cardLength='3' then DATEADD(dd,7,OCA.cardPurchaseDate) "
            + "when OCA.cardLength='4' then DATEADD(mm,1,OCA.cardPurchaseDate) "
            + "when OCA.cardLength='5' then DATEADD(mm,3,OCA.cardPurchaseDate) "
            + "when OCA.cardLength='6' then DATEADD(yyyy,1,OCA.cardPurchaseDate) "
            + "end as 'expirationDate' "
            + "FROM tblPaperCardAreas OCA "
            + "WHERE cardNumber = ? "
            + "ORDER BY zoneNumber DESC";

    public static final String INSERT_OYSTER_PROGRAM = "INSERT INTO tblOysterCardAreas "
            + "VALUES (?,?,?,?)";

    public static final String DELETE_OYSTER_PROGRAM = "DELETE FROM tblOysterCardAreas "
            + "WHERE cardNumber=? and cardPurchaseDate = ? and zoneNumber = ?";
    
    //-------------------------------ZONE---------------------------------------
    
    public static final String SELECT_ALL_ZONES = "SELECT * FROM tblZone";
}
