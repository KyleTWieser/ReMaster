package com.example.remaster;

public class Times
{
    private int timeId;
    private int hour;
    private int minute;

    public Times(){}
    public Times(int timeIdIn, int hourIn, int minuteIn)
    {
        this.timeId = timeIdIn;
        this.hour = hourIn;
        this.minute = minuteIn;
    }

    public void setTimeId (int timeIdIn)
    {
        this.timeId = timeIdIn;
    }
    public void setHour (int hourIn)
    {
        this.hour = hourIn;
    }

    public void setMinute (int minuteIn)
    {
        this.minute = minuteIn;
    }

    public int getTimeId ()
    {
        return this.timeId;
    }

    public int getHour()
    {
        return this.hour;
    }

    public int getMinute()
    {
        return this.minute;
    }
}
