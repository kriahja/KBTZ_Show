/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BLL.Exceptions.BivExceptions;
import DAL.TextDBManager;
import Entities.Text;
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

    public static TextManager getInstance()
    {
        if (instance == null) {
            instance = new TextManager();
        }
        return instance;
    }

    public ArrayList<Text> readAll()
    {
        try {
            return db.readAll();
        } catch (SQLException ex) {
            throw new BivExceptions("Unable to readAll Text data");
        }
    }

    public Text getById(int id)
    {
        return db.readById(id);
    }

    public ArrayList<Text> getByPriorityId(int priId)
    {
        return db.readByPriorityId(priId);
    }

    public ArrayList<Text> getByDisplayId(int dispId)
    {
        return db.readByDisplayId(dispId);
    }

    public ArrayList<Text> getBySafe()
    {
        return db.readByNotSafe(false);
    }

    public Text getByTitle(String title)
    {
        return db.readByTitle(title);
    }

    public void createText(Text text)
    {
        try {
            db.createText(text);
        } catch (SQLException ex) {
            Logger.getLogger(TextManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteText(int id)
    {
        db.delete(id);
    }

    public void updateText(Text txt)
    {
        try {
            db.update(txt);
        } catch (SQLException ex) {
            Logger.getLogger(TextManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<Text> readCurrent()
    {
        try {
            ArrayList<Text> all = new ArrayList<>();
            ArrayList<Text> current = new ArrayList<>();
            all = db.readAll();
            Date now = new Date(System.currentTimeMillis());
            Calendar c = Calendar.getInstance();

            for (int i = 0; i < all.size(); ++i) {
                if ((all.get(i).getStartDate().before(now) || all.get(i).getStartDate() == now)
                        && (all.get(i).getEndDate().after(now) || all.get(i).getEndDate() == now)) 
                {
                    current.add(all.get(i));
                }
            }
            return current;
        } catch (SQLException ex) {
            throw new BivExceptions("unable to load texts");
        }
    }

//    public void guiCreateText(Text text)
//    {
//        String text = textModel.getText(textTable.getSelectedRow());
//
//    }
}
