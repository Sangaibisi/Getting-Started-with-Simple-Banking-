package com.eteration.simplebanking;

public final class GeneralEnumerationDefinitions {
    private GeneralEnumerationDefinitions() {
    }

    public enum TransactionStatus{
        OK("OK"),
        ERROR("ERROR");

        TransactionStatus(String shortCode) {
            this.shortCode = shortCode;
        }
        private String shortCode;

        public String getShortCode() {
            return shortCode;
        }
    }
}
