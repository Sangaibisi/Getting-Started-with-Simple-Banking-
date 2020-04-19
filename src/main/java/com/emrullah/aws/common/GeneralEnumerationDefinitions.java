package com.emrullah.aws.common;

public final class GeneralEnumerationDefinitions {
    private GeneralEnumerationDefinitions() {
    }

    public enum TransactionStatus {
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

    public enum USER_ROLE{
        ADMIN("SUPERVISOR"),
        USER("USER");

        private String shortCode;
        USER_ROLE(String shortCode){this.shortCode=shortCode;};

        public String getShortCode(){return shortCode;}
    }
}
