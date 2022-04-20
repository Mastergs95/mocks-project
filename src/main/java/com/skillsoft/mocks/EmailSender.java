package com.skillsoft.mocks;

public class EmailSender {

    private String username;
    private String password;
    private String emailServer;

    public EmailSender(String username, String password, String emailServer){
        this.username=username;
        this.password=password;
        this.emailServer=emailServer;
    }

    public boolean sendEmail(String toAddress, String emailText){

        // NOTE: Assume that you connect tho the email server and send your email here

        return true;
    }

    public boolean sendEmailToMultipleRecipients(String[]Address, String emailText){

        // NOTE: Assume that you connect to the email server and send your email to multiple
        // recipients here

        return true;
    }

    public boolean sendEmailWithAttachments(String toAddress, String emailText, byte[]attachmentsBytes){

        // NOTE: Assume that you connect to the email server and send you email alonh with
        // the attachment here

        return true;
    }
}
