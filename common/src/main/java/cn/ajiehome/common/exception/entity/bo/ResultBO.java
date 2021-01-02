package cn.ajiehome.common.exception.entity.bo;

import cn.ajiehome.common.enums.CodeType;
import cn.ajiehome.common.exception.ApplicationException;
import lombok.Data;


@Data
public class ResultBO<T> {
    private Integer code;
    private T msg;

    public ResultBO(Integer code, T msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResultBO newResultBO(CodeType codeType){
        return new ResultBO<String>(codeType.getCode(), codeType.getMsg());
    }
    public static ResultBO newResultBO(CodeType codeType,Object message){
        return new ResultBO<Object>(codeType.getCode(), message);
    }
    public static ResultBO newResultBO(ApplicationException e){
        return new ResultBO(e.getCode(), e.getMsg());
    }

}
