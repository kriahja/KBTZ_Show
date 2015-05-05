/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BE;

import java.sql.Date;

/**
 *
 * @author notandi
 */
public class Text extends Presentation
{
    private String text;
    
    
    public Text(int id, int presTypeId, String title, Date startDate, Date endDate, double timer, boolean notSafe, String text)
    {
        super(id, presTypeId, title, startDate, endDate, timer, notSafe);
        this.text = text;
    }

    @Override
    protected void doShow()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the text
     */
    public String getText()
    {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text)
    {
        this.text = text;
    }
    
    
}
