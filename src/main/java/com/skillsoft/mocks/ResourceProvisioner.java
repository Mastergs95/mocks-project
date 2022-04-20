package com.skillsoft.mocks;



public class ResourceProvisioner {

    private String cloudPlatform;
    private String authenticationKey;

    public ResourceProvisioner(String cloudPlatform, String authenticationKey){
        this.cloudPlatform=cloudPlatform;
        this.authenticationKey=authenticationKey;
    }

    public void initialize(){

    }

    public boolean setupSingleMachine(String operatingSystem,
                                      String framework){

        return true;
    }

    public boolean setupCluster(int numMachines, String operatingSystem,
                                String framework){

        return true;
    }
}
