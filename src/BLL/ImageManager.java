/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BLL.Exceptions.BivExceptions;
import DAL.ImageDBManager;
import BE.Image;
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
public class ImageManager
{

    private static ImageManager instance = null;

    private static ImageDBManager db;

    private ImageManager()
    {

        try {
            db = ImageDBManager.getInstance();
        } catch (IOException ex) {
            throw new BivExceptions("Unable to connect to Image database");
        }
    }

    public static ImageManager getInstance()
    {
        if (instance == null) {
            instance = new ImageManager();
        }
        return instance;
    }

    public ArrayList<Image> readAll()
    {
        try {
            return db.readAll();
        } catch (SQLException ex) {
            throw new BivExceptions("Unable to readAll Image data");
        }
    }

    public Image getById(int id)
    {
        try
        {
            return db.readById(id);
        }
        catch (SQLException ex)
        {
            throw new BivExceptions("");
        }
    }


    public ArrayList<Image> getBySafe()
    {
        try
        {
            return db.readByNotSafe(false);
        }
        catch (SQLException ex)
        {
            throw new BivExceptions("");
        }
    }

    public Image getByTitle(String title)
    {
        try
        {
            return db.readByTitle(title);
        }
        catch (SQLException ex)
        {
            throw new BivExceptions("");
        }
    }
    
//    public Image getByPath(String path)
//    {
//        return db.readByPath(path);
//    }

//    public void createImage(Image text)
//    {
//        try {
//            db.createImage(text);
//        } catch (SQLException ex) {
//            Logger.getLogger(ImageManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public void deleteImage(int id)
//    {
//        db.delete(id);
//    }
//    
//    
//
//    public void updateImage(Image txt)
//    {
//        try {
//            db.update(txt);
//        } catch (SQLException ex) {
//            Logger.getLogger(ImageManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
    
    
     public ArrayList<Image> readCurrent(int dispId)
    {
        try {
            ArrayList<Image> all = new ArrayList<>();
            ArrayList<Image> current = new ArrayList<>();
            all = db.readAllDisp(dispId);
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
    


//    public void guiCreateImage(Image text)
//    {
//        String text = textModel.getImage(textTable.getSelectedRow());
//
//    }
}
