package cn.ajiehome.common.jwt.bo;

import lombok.Data;


@Data
public class JwtBeanBO {
    private Long id;
    private String userName;
    private String passWord;
    private String userPhone;
    private String userAddress;
}
