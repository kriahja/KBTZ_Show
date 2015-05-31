/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.Exceptions.BivExceptions;
import BE.Image;
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
public class ImageDBManager
{

    private final DBConnectionManager cm;

    private static ImageDBManager instance = null;

    private ImageDBManager() throws IOException
    {
        cm = DBConnectionManager.getInstance();

    }

    /**
     *
     * @return instance
     * @throws IOException
     */
    public static ImageDBManager getInstance() throws IOException
    {
        if (instance == null)
        {
            instance = new ImageDBManager();
        }
        return instance;
    }

    /**
     *
     * @return imgList
     * @throws SQLException
     */
    public ArrayList<Image> readAll() throws SQLException
    {
        try (Connection con = cm.getConnection())
        {
            ArrayList<Image> imgList = new ArrayList<>();
            String sql = "Select Presentation.* , Image.Path from Presentation, Image"
                    + " where Presentation.ID = Image.PresentationId";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next())
            {
                Image img = getOneImage(rs);
                imgList.add(img);
            }
            return imgList;
        }
    }
    
    /**
     *
     * @param id
     * @return imgList
     * @throws SQLException
     */
    public ArrayList<Image> readAllDisp(int id) throws SQLException
    {
        try (Connection con = cm.getConnection()) {
            ArrayList<Image> imgList = new ArrayList<>();
            String sql = " Select Presentation.*, Image.Path from Presentation, Display, DisplayCtrl, Image\n"
                    + " where DisplayCtrl.PresentationId = Presentation.ID and DisplayCtrl.DisplayId = Display.ID \n"
                    + " and Presentation.ID = Image.PresentationId and DisplayCtrl.[Disable] = 'false'\n"
                    + " and Display.ID = ? ";
           
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);


            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Image img = getOneImage(rs);
                
                imgList.add(img);
            }
            return imgList;
        }
    }

    private Image getOneImage(ResultSet rs) throws SQLException
    {
        int id = rs.getInt("ID");
        int presTypeId = rs.getInt("PresTypeId");
        String title = rs.getString("Title");

        Date startDate = rs.getDate("StartDate");
        Date endDate = rs.getDate("EndDate");
        Double timer = rs.getDouble("Timer");
        boolean notSafe = rs.getBoolean("NotSafe");
        String path = rs.getString("Path");

//        String depName = rs.getString("Name");
        return new Image(id, presTypeId, title, startDate, endDate, timer, notSafe, path);
    }

    /**
     *
     * @param title
     * @return null or getOneImage
     * @throws SQLException
     */
    public Image readByTitle(String title) throws SQLException
    {
        try (Connection con = cm.getConnection())
        {
            String sql = "SELECT Presentation.* , Image.Path FROM Presentation, Image "
                    + "WHERE Title = ? and Presentation.ID = Image.PresentationId";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, title);

            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                return getOneImage(rs);
            }
        }

        return null;
    }

    /**
     *
     * @param id
     * @return getOneImage or null
     * @throws SQLException
     */
    public Image readById(int id) throws SQLException
    {
        try (Connection con = cm.getConnection())
        {
            String sql = "SELECT Presentation.* , Image.Path FROM Presentation, Image "
                    + "WHERE Presentation.ID = ? and Presentation.ID = Image.PresentationId";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                return getOneImage(rs);
            }
        }

        return null;
    }

    /**
     *
     * @param safe
     * @return imgList
     * @throws SQLException
     */
    public ArrayList<Image> readByNotSafe(boolean safe) throws SQLException
    {
        try (Connection con = cm.getConnection())
        {
            ArrayList<Image> imgList = new ArrayList<>();
            String sql = "SELECT Presentation.* , Image.Path FROM Presentation, Image "
                    + "WHERE Presentation.NotSafe = ? and Presentation.ID = Image.PresentationId";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setBoolean(1, safe);

            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Image img = getOneImage(rs);
                imgList.add(img);
            }

            return imgList;
        }

    }

}
