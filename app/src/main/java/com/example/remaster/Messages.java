package com.example.remaster;

/**
 * Class for Message object.
 */
public class Messages
{
    private int messageId;
    private String contactName;
    private String message;

    /**
     * Constructor
     */
    public Messages(){}

    /**
     * Constructor
     * @param id
     * @param contact
     * @param sendMessage
     */
    public Messages(int id, String contact, String sendMessage)
    {
        this.messageId = id;
        this.contactName = contact;
        this.message = sendMessage;
    }

    /**
     * Sets the id for the current object.
     * Used for storing in the database
     * @param id
     */
    public void setID(int id)
    {
        this.messageId = id;
    }

    /**
     * When called returns the ID of the current object.
     * @return
     */
    public int getID()
    {
        return this.messageId;
    }

    /**
     * Sets the contact for the current object.
     * @param contact
     */
    public void setContactName(String contact)
    {
        this.contactName = contact;
    }

    /**
     * When called returns the Contact name of the current object.
     * @return
     */
    public String getContactName()
    {
        return this.contactName;
    }

    /**
     * Sets the message for the current object.
     * @param sendMessage
     */
    public void setMessage(String sendMessage)
    {
        this.message = sendMessage;
    }

    /**
     * When called returns the message of the current object.
     * @return
     */
    public String getMessage()
    {
        return this.message;
    }
}
