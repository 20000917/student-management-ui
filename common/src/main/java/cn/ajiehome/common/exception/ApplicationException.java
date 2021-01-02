package cn.ajiehome.common.exception;

import cn.ajiehome.common.enums.CodeType;


public class ApplicationException extends RuntimeException {
    private int code;
    private String msg;

    public ApplicationException(CodeType codeType) {
        this.code = codeType.getCode();
        this.msg = codeType.getMsg();
    }
    public ApplicationException(CodeType codeType,String msg) {
        this.code = codeType.getCode();
        this.msg = msg;
    }


    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
