/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BE.Display;
import BE.Display;
import BLL.Exceptions.BivExceptions;
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
public class DisplayDBManager
{

    private final DBConnectionManager cm;

    private static DisplayDBManager instance = null;

    private DisplayDBManager() throws IOException
    {
        cm = DBConnectionManager.getInstance();

    }

    /**
     *
     * @return instance
     * @throws IOException
     */
    public static DisplayDBManager getInstance() throws IOException
    {
        if (instance == null) {
            instance = new DisplayDBManager();
        }
        return instance;
    }

    /**
     *
     * @return dispList from DisplayDBManager
     * @throws SQLException
     */
    public ArrayList<Display> readAll() throws SQLException
    {
        try (Connection con = cm.getConnection()) {
            ArrayList<Display> dispList = new ArrayList<>();
            String sql = "Select * From Display";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Display disp = getOneDisplay(rs);
                dispList.add(disp);
            }
            return dispList;
        }
    }

    private Display getOneDisplay(ResultSet rs) throws SQLException
    {
        int id = rs.getInt("ID");
        String name = rs.getString("ScreenName");

        return new Display(id, name);
    }

    /**
     *
     * @param reload
     * @throws SQLException
     */
    public void reloadText(boolean reload) throws SQLException
    {
        try (Connection con = cm.getConnection()) {
            String sql = "update Reload set ReloadText = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setBoolean(1, reload);

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new BivExceptions("Unable to Reload Text");
            }
        }
    }

    /**
     *
     * @param reload
     * @throws SQLException
     */
    public void reloadImage(boolean reload) throws SQLException
    {
        try (Connection con = cm.getConnection()) {
            String sql = "update Reload set ReloadImage = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setBoolean(1, reload);

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new BivExceptions("Unable to Reload Text");
            }
        }
    }

    /**
     *
     * @return reload from DisplayDBManager
     * @throws SQLException
     */
    public boolean toBeRelodedText() throws SQLException
    {
        try (Connection con = cm.getConnection()) {
            boolean reload = false;
            String sql = "Select ReloadText From Reload";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                reload = getOneReloadText(rs);

            }
            return reload;
        }
    }

    private boolean getOneReloadText(ResultSet rs) throws SQLException
    {
        boolean reload = rs.getBoolean("ReloadText");

        return reload;

    }

    /**
     *
     * @return reload from DisplayDBManager
     * @throws SQLException
     */
    public boolean toBeRelodedImage() throws SQLException
    {
        try (Connection con = cm.getConnection()) {
            boolean reload = false;
            String sql = "Select ReloadImage From Reload";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                reload = getOneReloadImage(rs);

            }
            return reload;
        }
    }

    private boolean getOneReloadImage(ResultSet rs) throws SQLException
    {
        boolean reload = rs.getBoolean("ReloadImage");

        return reload;

    }

}
