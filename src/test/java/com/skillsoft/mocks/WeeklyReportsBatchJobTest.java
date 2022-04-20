package com.skillsoft.mocks;

import org.junit.jupiter.api.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
public class WeeklyReportsBatchJobTest {

    @Mock
    private EmailSender emailSenderMock;

    @Mock
    private ResourceProvisioner resourceProvisioner;

    @Mock
    private DataPreparer dataPreparer;
    @InjectMocks
    WeeklyReportsBatchJob batchJob;

    @Test
    public void testInitialize(){
        batchJob.initialize();

        verify(resourceProvisioner).initialize();
        verify(dataPreparer).initialize();

        verifyNoInteractions(emailSenderMock);
    }



    @Test
    public void testGenerateWeeklyReport_singleRecipient(){

        when(resourceProvisioner.setupCluster(
                3,"Linux","Spark")).thenReturn(true);

        when(dataPreparer.prepareRawData()).thenReturn(true);

        when(emailSenderMock.sendEmail("john@infomoto.com",
                "The Sales weekly report has been generated")).thenReturn(true);

        batchJob.initialize();
        batchJob.initializeReportGenerationConfig(3,"Linux","Spark");
        batchJob.generateWeeklyReport("Sales","john@infomoto.com");

        InOrder inOrderResourceProvisionerMock = inOrder(resourceProvisioner);

        inOrderResourceProvisionerMock.verify(resourceProvisioner).initialize();
        inOrderResourceProvisionerMock.verify(resourceProvisioner).setupCluster(anyInt(),anyString(),anyString());

        verifyNoMoreInteractions(resourceProvisioner);
    }

    @Test
    public void testGenerateWeeklyReport_multipleRecipients(){

        when(emailSenderMock.sendEmailToMultipleRecipients(
                new String[]{"john@infomoto.com","julia@infomoto.com"},
                "The Revenues weekly report has been generated")).thenReturn(true);

        assertTrue(batchJob.generateWeeklyReport(
                "Revenues",new String[]{"john@infomoto.com","julia@infomoto.com"}));
    }

    @Test
    public void sendWeeklyReport_withAttachment() {

        when(resourceProvisioner.setupSingleMachine(
                "Linux", "Java")).thenReturn(true);

        when(dataPreparer.prepareRawData()).thenReturn(true);

        when(emailSenderMock.sendEmailWithAttachments(
                "john@infomoto.com",
                "The Profits weekly report has been generated",
                "Sales have been going up!".getBytes())).thenReturn(true);

        batchJob.initialize();
        batchJob.initializeReportSendingConfig("Linux", "Java");
        batchJob.sendWeeklyReport("Profits", "john@infomoto.com");

        InOrder inOrderResourceProvisionerMock = inOrder(resourceProvisioner);

        inOrderResourceProvisionerMock.verify(resourceProvisioner).initialize();

        inOrderResourceProvisionerMock.verify(resourceProvisioner).setupSingleMachine(
                "Linux","Java");

        InOrder inOrderDataPreparerMock=inOrder(dataPreparer);
        inOrderDataPreparerMock.verify(dataPreparer).initialize();
        inOrderDataPreparerMock.verify(dataPreparer).prepareRawData();


    }

}
