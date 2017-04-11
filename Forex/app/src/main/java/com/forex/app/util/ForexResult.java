package com.forex.app.util;

public class ForexResult {
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Double getFactor() {
        return factor;
    }

    public void setFactor(Double factor) {
        this.factor = factor;
    }

    public String toString(){
        return "From - " + getFrom() + "\\n" +
                "To - " + getTo() + "\\n" +
                "Factor - " + getFactor() + "\\n" +
                "Result Code - " + getResultCode();
    }

    private String from;
    private String to;
    private Double factor;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    private int resultCode;

}