/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BE;

import java.util.Date;

/**
 *
 * @author Zalan
 */
public class DisplayShow {
    private int textId;
    private Date startDate;
    private Date endDate;
    private int priorityId;
    private int displayId;

    public DisplayShow(int textId, Date startDate, Date endDate, int priorityId, int displayId) {
        this.textId = textId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priorityId = priorityId;
        this.displayId = displayId;
    }
}
