package cn.ajiehome.common.enums;


public enum CodeType {
    /**
     * 枚举对象
     */
    SERVICE_ERROR(5001,"服务逻辑操作发生错误"),
    SYSTEM_EXCEPTION(5002,"系统发生未正常处理异常"),
    TOKEN_NULL(4003,"Token为空"),
    TOKEN_TIME_OUT(4002,"Token过时"),
    SERVICE_IO_ERROR(5003,"服务IO操作异常"),
    SERVICE_EMAIL_SEND_ERROR(5004,"邮件验证码发送异常"),
    SERVICE_EMAIL_NOT_FOUND(5005,"邮箱号码为空"),
    OK(200,"数据成功"),
    TIME_POINTER_BACK(5006,"时间指针被回移动");

    private int code;
    private String msg;


    CodeType(int code, String msg) {
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
