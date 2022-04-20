package com.skillsoft.mocks;

import java.util.HashMap;
import java.util.Map;

public class WeeklyReportsBatchJob {

    private EmailSender emailSender;
    private ResourceProvisioner resourceProvisioner;
    private DataPreparer dataPreparer;

    private final Map<String, String> reportGenerateConfig=new HashMap<>();
    private final Map<String, String> reportSendingConfig=new HashMap<>();

    public WeeklyReportsBatchJob(){
    }

    public void initialize(){
        resourceProvisioner.initialize();
        dataPreparer.initialize();
    }

    public void initializeReportGenerationConfig(int numMachines,
                                                 String operatingSystem,
                                                 String framework){

        reportGenerateConfig.put("numMachines", String.valueOf(numMachines));
        reportGenerateConfig.put("operatingSystem", operatingSystem);
        reportGenerateConfig.put("framework", framework);
    }

    public void initializeReportSendingConfig(String operatingSystem,
                                                 String framework){

        reportGenerateConfig.put("operatingSystem", operatingSystem);
        reportGenerateConfig.put("framework", framework);
    }

    public boolean generateWeeklyReport(String reportType, String emailRecipient){

        int numMachines=Integer.parseInt(reportGenerateConfig.get("numMachines"));
        String operatingSystem=reportGenerateConfig.get("operatingSystem");
        String framework=reportGenerateConfig.get("framework");




        if(resourceProvisioner.setupCluster(3,"Linux","Spark")&&
        dataPreparer.prepareRawData()) {

            return emailSender.sendEmail(
                    emailRecipient,
                    String.format("The %s weekly report has been generated", reportType));
        }
        return false;
        }

    public boolean generateWeeklyReport(String reportType, String[] emailRecipient){

        return emailSender.sendEmailToMultipleRecipients(
                emailRecipient,
                String.format("The %s weekly report has been generated", reportType));
    }

    public boolean sendWeeklyReport(String reportType, String emailRecipient){

        if(resourceProvisioner.setupSingleMachine("Linux","Java")&&
        dataPreparer.prepareRawData()) {

            String reportCopy = "Sales have been going up!";

            return emailSender.sendEmailWithAttachments(
                    emailRecipient,
                    String.format("The %s weekly report has been generated", reportType),
                    reportCopy.getBytes());
        }
        return false;
        }
}
