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
    private String font;
    private int fontSize;
    private int fontStyle;
    private int fontColor;

    /**
     *
     * @param id auto generated id for a specific text text presentation.
     * @param presTypeId this id determents the type of the text.
     * @param title title for a specific text presentation
     * @param startDate startDate for a specific text presentation
     * @param endDate endDate for a specific presentation
     * @param timer timer for a specific text presentation
     * @param notSafe mark for not safe(not costumerFriendly) for a specific
     * text presentation
     * @param text text for a specific text presentation
     */
    public Text(int id, int presTypeId, String title, Date startDate, Date endDate, double timer, boolean notSafe, String text)
    {
        super(id, presTypeId, title, startDate, endDate, timer, notSafe);
        this.text = text;
    }

    /**
     *
     * @param id
     * @param presTypeId
     * @param title
     * @param startDate
     * @param endDate
     * @param timer
     * @param notSafe
     * @param text
     * @param font
     * @param fontSize
     */
    public Text(int id, int presTypeId, String title, Date startDate, Date endDate, double timer, boolean notSafe, String text, String font, int fontSize)
    {
        super(id, presTypeId, title, startDate, endDate, timer, notSafe);
        this.text = text;
        this.font = font;
        this.fontSize = fontSize;
    }

    /**
     *
     * @param id
     * @param presTypeId
     * @param title
     * @param startDate
     * @param endDate
     * @param timer
     * @param notSafe
     */
    public Text(int id, int presTypeId, String title, Date startDate, Date endDate, double timer, boolean notSafe)
    {
        super(id, presTypeId, title, startDate, endDate, timer, notSafe);
    }

    /**
     * @param id
     * @param txt
     */
    public Text(int id, Text txt)
    {
        this(id, txt.getPresTypeId(), txt.getTitle(), txt.getStartDate(), txt.getEndDate(), txt.getTimer(), txt.isNotSafe(), txt.getText());
    }

    /**
     *
     * @param presTypeId this id determents the type of the presentation.
     * @param title title for a specific presentation
     * @param startDate startDate for a specific presentation
     * @param endDate endDate for a specific presentation
     * @param timer timer for a specific presentation
     * @param notSafe mark for not safe(not costumerFriendly) for a specific
     * presentation
     * @param text text for a specific presentation
     */
    public Text(int presTypeId, String title, Date startDate, Date endDate, double timer, boolean notSafe, String text)
    {
        super(presTypeId, title, startDate, endDate, timer, notSafe);
        this.text = text;
    }

    /**
     *
     * @param presTypeId
     * @param title
     * @param startDate
     * @param endDate
     * @param timer
     * @param notSafe
     * @param text
     * @param font
     * @param fontSize
     */
    public Text(int presTypeId, String title, Date startDate, Date endDate, double timer, boolean notSafe, String text, String font, int fontSize)
    {
        super(presTypeId, title, startDate, endDate, timer, notSafe);
        this.text = text;
        this.font = font;
        this.fontSize = fontSize;
    }

    /**
     *
     * @param id
     * @param presTypeId
     * @param title
     * @param startDate
     * @param endDate
     * @param timer
     * @param notSafe
     * @param text
     * @param font
     * @param fontSize
     * @param fontStyle
     * @param fontColor
     */
    public Text(int id, int presTypeId, String title, Date startDate, Date endDate, double timer, boolean notSafe, String text, String font, int fontSize, int fontStyle, int fontColor)
    {
        super(id, presTypeId, title, startDate, endDate, timer, notSafe);
        this.text = text;
        this.font = font;
        this.fontSize = fontSize;
        this.fontStyle = fontStyle;
        this.fontColor = fontColor;
    }

    /**
     *
     * @param presTypeId
     * @param title
     * @param startDate
     * @param endDate
     * @param timer
     * @param notSafe
     * @param text
     * @param font
     * @param fontSize
     * @param fontStyle
     * @param fontColor
     */
    public Text(int presTypeId, String title, Date startDate, Date endDate, double timer, boolean notSafe, String text, String font, int fontSize, int fontStyle, int fontColor)
    {
        super(presTypeId, title, startDate, endDate, timer, notSafe);
        this.text = text;
        this.font = font;
        this.fontSize = fontSize;
        this.fontStyle = fontStyle;
        this.fontColor = fontColor;
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
     * @return the font
     */
    public String getFont()
    {
        return font;
    }

    /**
     * @param font the font to set
     */
    public void setFont(String font)
    {
        this.font = font;
    }

    /**
     * @return the fontSize
     */
    public int getFontSize()
    {
        return fontSize;
    }

    /**
     * @param fontSize the fontSize to set
     */
    public void setFontSize(int fontSize)
    {
        this.fontSize = fontSize;
    }

    /**
     * @return the fontStyle
     */
    public int getFontStyle()
    {
        return fontStyle;
    }

    /**
     * @param fontStyle the fontStyle to set
     */
    public void setFontStyle(int fontStyle)
    {
        this.fontStyle = fontStyle;
    }

    /**
     * @return the fontColor
     */
    public int getFontColor()
    {
        return fontColor;
    }

    /**
     * @param fontColor the fontColor to set
     */
    public void setFontColor(int fontColor)
    {
        this.fontColor = fontColor;
    }

}
