/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BE.Text;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author a.tamas
 */
public class TextDBManager
{

    private final DBConnectionManager cm;

    private static TextDBManager instance = null;

    private TextDBManager() throws IOException
    {
        cm = DBConnectionManager.getInstance();

    }

    /**
     *
     * @return instance
     * @throws IOException
     */
    public static TextDBManager getInstance() throws IOException
    {
        if (instance == null) {
            instance = new TextDBManager();
        }
        return instance;
    }

    /**
     *
     * @return txtList
     * @throws SQLException
     */
    public ArrayList<Text> readAll() throws SQLException
    {
        try (Connection con = cm.getConnection()) {
            ArrayList<Text> txtList = new ArrayList<>();
            String sql = "Select Presentation.* , Text.Text, Text.Font, Text.FontSize, Text.FontStyle, Text.FontColor from Presentation, Text"
                    + " where Presentation.ID = Text.PresentationId";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Text txt = getOneText(rs);
                txtList.add(txt);
            }
            return txtList;
        }
    }

    /**
     *
     * @param id
     * @return txtList
     * @throws SQLException
     */
    public ArrayList<Text> readAllDisp(int id) throws SQLException
    {
        try (Connection con = cm.getConnection()) {
            ArrayList<Text> txtList = new ArrayList<>();
            String sql = " Select Presentation.*, Text.Text, Text.Font, Text.FontSize, Text.FontStyle, Text.FontColor from Presentation, Display, DisplayCtrl, Text\n"
                    + " where DisplayCtrl.PresentationId = Presentation.ID and DisplayCtrl.DisplayId = Display.ID \n"
                    + " and Presentation.ID = Text.PresentationId and DisplayCtrl.[Disable] = 'false'\n"
                    + " and Display.ID = ? ";
           
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            
       
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Text txt = getOneText(rs);
                
                txtList.add(txt);
            }
            return txtList;
        }
    }

    private Text getOneText(ResultSet rs) throws SQLException
    {
        int id = rs.getInt("ID");
        int presTypeId = rs.getInt("PresTypeId");
        String title = rs.getString("Title");

        Date startDate = rs.getDate("StartDate");
        Date endDate = rs.getDate("EndDate");
        Double timer = rs.getDouble("Timer");

        boolean notSafe = rs.getBoolean("NotSafe");

        String text = rs.getString("Text");
        String font = rs.getString("Font");
        int fontSize = rs.getInt("FontSize");
        int fontStyle = rs.getInt("FontStyle");
        int fontColor = rs.getInt("FontColor");


        return new Text(id, presTypeId, title, startDate, endDate, timer, notSafe, text, font, fontSize, fontStyle, fontColor);
    }

    /**
     *
     * @param title
     * @return null or getOneText
     * @throws SQLException
     */
    public Text readByTitle(String title) throws SQLException
    {
        try (Connection con = cm.getConnection()) {
            String sql = "SELECT Presentation.* , Text.Text FROM Presentation, Text "
                    + "WHERE Title = ? and Presentation.ID = Text.PresentationId";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, title);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return getOneText(rs);
            }

        }
        return null;
    }

    /**
     *
     * @param id
     * @return null or getOneText
     * @throws SQLException
     */
    public Text readById(int id) throws SQLException
    {
        try (Connection con = cm.getConnection()) {
            String sql = "SELECT Presentation.* , Text.Text FROM Presentation, Text "
                    + "WHERE Presentation.ID = ? and Presentation.ID = Text.PresentationId";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return getOneText(rs);
            }
        }
        return null;
    }

    /**
     *
     * @param safe
     * @return txtList
     * @throws SQLException
     */
    public ArrayList<Text> readByNotSafe(boolean safe) throws SQLException
    {
        try (Connection con = cm.getConnection()) {
            ArrayList<Text> txtList = new ArrayList<>();
            String sql = "SELECT Presentation.* , Text.Text FROM Presentation, Text "
                    + "WHERE Presentation.NotSafe = ? and Presentation.ID = Text.PresentationId";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setBoolean(1, safe);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Text txt = getOneText(rs);
                txtList.add(txt);
            }

            return txtList;

        }
    }
    
}
