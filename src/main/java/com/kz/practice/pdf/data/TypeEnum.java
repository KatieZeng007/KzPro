package com.kz.practice.pdf.data;

public enum TypeEnum {

    STR(1,"字符串"),
    BARCODE(2,"条形码");

    private int code;
    private String msg;

    TypeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
