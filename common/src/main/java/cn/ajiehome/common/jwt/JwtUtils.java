package cn.ajiehome.common.jwt;

import cn.ajiehome.common.jwt.bo.JwtBeanBO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;


@Component
public class JwtUtils {

    private JwtBeanBO jwtBeanBO;

    /**
     *  获取Token，签名由ID代替，形成动态签名
     * @param autoDuration 超时的时间长度
     * @param jwtBeanBO 用户数据
     * @return token
     */
    public String getToken(long autoDuration, JwtBeanBO jwtBeanBO){

        long outLong = System.currentTimeMillis() + autoDuration;
        Date outDate = new Date(outLong);
        Algorithm algorithm = Algorithm.HMAC256(jwtBeanBO.getId().toString());
        HashMap<String, Object> hashMap = new HashMap<>(5);
        hashMap.put("id",jwtBeanBO.getId());
        hashMap.put("userName",jwtBeanBO.getUserName());
        hashMap.put("passWord",jwtBeanBO.getPassWord());
        hashMap.put("userPhone",jwtBeanBO.getUserPhone());
        hashMap.put("userAddress",jwtBeanBO.getUserAddress());

        return JWT.create()
                .withHeader(hashMap)
                .withIssuer(jwtBeanBO.getUserName())
                .withExpiresAt(outDate)
                .sign(algorithm);
    }

    public  void setJwtBeanBO(JwtBeanBO jwtBeanBO){
        this.jwtBeanBO = jwtBeanBO;
    }
    public  JwtBeanBO getJwtBeanBO(){
        return this.jwtBeanBO;
    }
}
