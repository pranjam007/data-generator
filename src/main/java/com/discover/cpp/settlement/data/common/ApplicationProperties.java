package com.discover.cpp.settlement.data.common;

public class ApplicationProperties {

    private int noOfFiles;
    
    private int noOfBatches;
    
    private int noOfTxnPerBatch;
    
    private String delimiter;
    
    private String outFileLocation;
    
    private int noOfDuplicateBatch;
    
    private int noOfDuplicateTxnsInBatch;

    public int getNoOfFiles() {
        return noOfFiles;
    }

    public void setNoOfFiles(int noOfFiles) {
        this.noOfFiles = noOfFiles;
    }

    public int getNoOfBatches() {
        return noOfBatches;
    }

    public void setNoOfBatches(int noOfBatches) {
        this.noOfBatches = noOfBatches;
    }

    public int getNoOfTxnPerBatch() {
        return noOfTxnPerBatch;
    }

    public void setNoOfTxnPerBatch(int noOfTxnPerBatch) {
        this.noOfTxnPerBatch = noOfTxnPerBatch;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getOutFileLocation() {
        return outFileLocation;
    }

    public void setOutFileLocation(String outFileLocation) {
        this.outFileLocation = outFileLocation;
    }

    public int getNoOfDuplicateBatch() {
        return noOfDuplicateBatch;
    }

    public void setNoOfDuplicateBatch(int noOfDuplicateBatch) {
        this.noOfDuplicateBatch = noOfDuplicateBatch;
    }

    public int getNoOfDuplicateTxnsInBatch() {
        return noOfDuplicateTxnsInBatch;
    }

    public void setNoOfDuplicateTxnsInBatch(int noOfDuplicateTxnsInBatch) {
        this.noOfDuplicateTxnsInBatch = noOfDuplicateTxnsInBatch;
    }

   
    
}
