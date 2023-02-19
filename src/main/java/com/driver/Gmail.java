package com.driver;

import java.util.ArrayList;
import java.util.Date;
class msginbox
{
    Date date;
    String sender;

    String message;
    msginbox(Date date,String sender,String message)
    {
        this.date = date;
        this.sender = sender;
        this.message = message;
    }
}

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    ArrayList<msginbox> inbox = new ArrayList<>();
    ArrayList<msginbox> trash = new ArrayList<>();
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;

    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        if(inbox.size() < inboxCapacity)
        {
            inbox.add(new msginbox(date,sender,message));
        }
        else if(inbox.size() == inboxCapacity)
        {
            trash.add(inbox.get(0));
            inbox.remove(0);
            inbox.add(new msginbox(date,sender,message));
        }

    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        int i = 0;
        while (i<inbox.size())
        {
            if(inbox.get(i).message.equals(message))
            {
                trash.add(inbox.get(i));
                inbox.remove(i);
                break;
            }
            i++;

        }


    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(inbox.size() == 0) return null;
        int lstindex = inbox.size()-1;
        return inbox.get(lstindex).message;

    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(inbox.size() == 0) return null;

        return inbox.get(0).message;


    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int count = 0;
        for(int i = 0;i<inbox.size();i++)
        {
            if(inbox.get(i).date.after(start) && inbox.get(i).date.before(end))
            {
                count++;
            }
            else if(inbox.get(i).date.equals(start))
            {
                count++;
            }
            else if(inbox.get(i).date.equals(end))
            {
                count++;
            }

        }

        return count;

    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return inbox.size();

    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trash.size();

    }

    public void emptyTrash(){
        // clear all mails in the trash
        trash.clear();

    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}
