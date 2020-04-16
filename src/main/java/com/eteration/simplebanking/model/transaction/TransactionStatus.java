package com.eteration.simplebanking.model.transaction;

import com.eteration.simplebanking.GeneralEnumerationDefinitions;

import javax.persistence.*;

@Entity
@Table(name="TransactionStatus")
public class TransactionStatus {

    @Id
    @GeneratedValue
    private int id;

    @Column(name="transactionStatus")
    private String transactionStatus;

    @Column(name="approvalCode")
    private String approvalCode;

    public TransactionStatus(GeneralEnumerationDefinitions.TransactionStatus transactionStatus, String approvalCode) {
        this.transactionStatus = transactionStatus.getShortCode();
        this.approvalCode = approvalCode;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(GeneralEnumerationDefinitions.TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus.getShortCode();
    }

    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }
}
