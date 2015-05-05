/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BE;

/**
 *
 * @author notandi
 */
public class DisplayText
{ 
    private int id;
    private int displayId;
    private int priorityId;
    private double timer;
    private java.sql.Date startDate;
    private java.sql.Date endDate;
    private boolean notSafe;
    private String text;
    private String title;

    public DisplayText(int id, int displayId, int priorityId, double timer, java.sql.Date startDate, java.sql.Date endDate, boolean notSafe, String text, String title)
    {
        this.id = id;
        this.displayId = displayId;
        this.priorityId = priorityId;
        this.timer = timer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.notSafe = notSafe;
        this.text = text;
        this.title = title;
    }

    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**
     * @return the displayId
     */
    public int getDisplayId()
    {
        return displayId;
    }

    /**
     * @return the priorityId
     */
    public int getPriorityId()
    {
        return priorityId;
    }

    /**
     * @return the timer
     */
    public double getTimer()
    {
        return timer;
    }

    /**
     * @param timer the timer to set
     */
    public void setTimer(double timer)
    {
        this.timer = timer;
    }

    /**
     * @return the startDate
     */
    public java.sql.Date getStartDate()
    {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(java.sql.Date startDate)
    {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public java.sql.Date getEndDate()
    {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(java.sql.Date endDate)
    {
        this.endDate = endDate;
    }

    /**
     * @return the notSafe
     */
    public boolean isNotSafe()
    {
        return notSafe;
    }

    /**
     * @param notSafe the notSafe to set
     */
    public void setNotSafe(boolean notSafe)
    {
        this.notSafe = notSafe;
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

    /**
     * @return the title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    
    
    
    
    
    
    
}
