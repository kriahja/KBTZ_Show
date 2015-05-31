/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BLL.Exceptions.BivExceptions;
import DAL.TextDBManager;
import BE.Text;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author notandi
 */
public class TextManager
{

    private static TextManager instance = null;

    private static TextDBManager db;

    private TextManager()
    {

        try {
            db = TextDBManager.getInstance();
        } catch (IOException ex) {
            throw new BivExceptions("Unable to connect to Text database");
        }
    }

    /**
     *
     * @return instance
     */
    public static TextManager getInstance()
    {
        if (instance == null) {
            instance = new TextManager();
        }
        return instance;
    }

    /**
     *
     * @return readAll from TextDBManager
     */
    public ArrayList<Text> readAll()
    {
        try {
            return db.readAll();
        } catch (SQLException ex) {
            throw new BivExceptions("Unable to readAll Text data");
        }
    }

    /**
     *
     * @param id
     * @return readById from TextDBManager
     */
    public Text getById(int id)
    {
        try {
            return db.readById(id);
        } catch (SQLException ex) {
            throw new BivExceptions("Unable to getById from textDBManager");
        }
    }

    /**
     *
     * @return readByNotSafe from TextDBManager
     */
    public ArrayList<BE.Text> getBySafe()
    {
        try {
            return db.readByNotSafe(false);
        } catch (SQLException ex) {
            throw new BivExceptions("Unable to getBySafe from textDBManager");
        }
    }

    /**
     *
     * @param title
     * @return readByTitle from TextDBManager
     */
    public BE.Text getByTitle(String title)
    {
        try {
            return db.readByTitle(title);
        } catch (SQLException ex) {
            throw new BivExceptions("Unable to ReadByTitle from textDBManager");
        }
    }
    
    /**
     *
     * @param id
     * @return current
     */
    public ArrayList<Text> readCurrent(int id)
    {
        try {
            ArrayList<Text> all = new ArrayList<>();
            ArrayList<Text> current = new ArrayList<>();
            all = db.readAllDisp(id);
            Date now = new Date(System.currentTimeMillis());
            Calendar c = Calendar.getInstance();

            for (int i = 0; i < all.size(); ++i) {
                if ((all.get(i).getStartDate().before(now) || all.get(i).getStartDate() == now)
                        && (all.get(i).getEndDate().after(now) || all.get(i).getEndDate() == now)) {
                    current.add(all.get(i));
                }
            }
            return current;

    }   catch (SQLException ex) {
            throw new BivExceptions("Unable to read current from TextDBManager");
        }


}
}
