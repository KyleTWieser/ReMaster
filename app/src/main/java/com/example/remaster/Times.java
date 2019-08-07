package com.example.remaster;

/**
 * Class for the Time object.
 */
public class Times
{
    private int timeId;
    private int hour;
    private int minute;

    /**
     * Constructor for the object
     */
    public Times(){}

    /**
     * Constructor
     * @param timeIdIn
     * @param hourIn
     * @param minuteIn
     */
    public Times(int timeIdIn, int hourIn, int minuteIn)
    {
        this.timeId = timeIdIn;
        this.hour = hourIn;
        this.minute = minuteIn;
    }

    /**
     * Sets the Id for the Time object.
     * @param timeIdIn
     */
    public void setTimeId (int timeIdIn)
    {
        this.timeId = timeIdIn;
    }

    /**
     * Sets hour for the Time object.
     * @param hourIn
     */
    public void setHour (int hourIn)
    {
        this.hour = hourIn;
    }

    /**
     * Sets minute for the Time object.
     * @param minuteIn
     */
    public void setMinute (int minuteIn)
    {
        this.minute = minuteIn;
    }

    /**
     * When called returns the ID of the current object.
     * @return
     */
    public int getTimeId ()
    {
        return this.timeId;
    }

    /**
     * When called returns the Hour of the current object.
     * @return
     */
    public int getHour()
    {
        return this.hour;
    }

    /**
     * When called returns the Minute of the current object.
     * @return
     */
    public int getMinute()
    {
        return this.minute;
    }
}
