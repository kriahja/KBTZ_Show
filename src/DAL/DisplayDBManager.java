/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BE.Display;
import BE.Display;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
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

    public static DisplayDBManager getInstance() throws IOException
    {
        if (instance == null) {
            instance = new DisplayDBManager();
        }
        return instance;
    }

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
}
