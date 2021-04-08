package com.guitarshack;

import java.util.Date;

public class TimeInterval {
    private final Date endDate;
    private final Date startDate;

    public TimeInterval(Date endDate, Date startDate) {
        this.endDate = endDate;
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getStartDate() {
        return startDate;
    }
}
