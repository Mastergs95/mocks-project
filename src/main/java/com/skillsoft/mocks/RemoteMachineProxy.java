package com.skillsoft.mocks;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//NOTE: Assume that this is a proxy, that uses a URL to connect to another machine
// and allows us to perform operations on the other machine
public class RemoteMachineProxy {

    private final String remoteUrl;
    private Iterator<String> remoteFileIterator;

    public RemoteMachineProxy(String remoteUrl){
        this.remoteUrl=remoteUrl;
    }

    private void connect(){
        // NOTE: Assume this connects to the remote machine and initializes
        // an iterator to get the list of records stored in the remote machine
    }

    void setTestIterator(Iterator<String> testIterator){
        this.remoteFileIterator=testIterator;
    }

    public String getNextFile(){
        if(remoteFileIterator.hasNext()){

            String record = remoteFileIterator.next();

            Matcher m = Pattern
                    .compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+")
                    .matcher(record);

            if(m.find()){
                return m.group();
            }
            return remoteFileIterator.next();
        }
        return null;
    }
}
