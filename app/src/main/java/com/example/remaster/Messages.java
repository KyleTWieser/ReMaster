package com.example.remaster;

public class Messages
{
    private int messageId;
    private String contactName;
    private String message;

    public Messages(){}
    public Messages(int id, String contact, String sendMessage)
    {
        this.messageId = id;
        this.contactName = contact;
        this.message = sendMessage;
    }

    public void setID(int id)
    {
        this.messageId = id;
    }
    public int getID()
    {
        return this.messageId;
    }

    public void setContactName(String contact)
    {
        this.contactName = contact;
    }

    public String getContactName()
    {
        return this.contactName;
    }

    public void setMessage(String sendMessage)
    {
        this.message = sendMessage;
    }

    public String getMessage()
    {
        return this.message;
    }
}
