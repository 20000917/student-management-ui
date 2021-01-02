package cn.ajiehome.common.aspects;

import cn.ajiehome.common.annotations.AllowToken;
import cn.ajiehome.common.enums.CodeType;
import cn.ajiehome.common.exception.ApplicationException;
import cn.ajiehome.common.jwt.JwtUtils;
import cn.ajiehome.common.jwt.bo.JwtBeanBO;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


@Aspect
@Component
public class TokenAspect {
    @Autowired
    private JwtUtils jwtUtils;

    @Pointcut(value = "@annotation(cn.ajiehome.common.annotations.AllowToken)")
    private void pointCut(){}

    @Before(value = "pointCut()&&@annotation(allowToken)")
    private void before(AllowToken allowToken){
        //AOP前置通知 advice
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert servletRequestAttributes != null;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String token = request.getHeader("token");
        if (token==null){
            throw new ApplicationException(CodeType.TOKEN_NULL);
        }
        //解析token
        DecodedJWT decode = JWT.decode(token);
        //Jwt的头部，由于头部被Base64编码过了，所以需要base64逆解编码
        String header = new String(Base64.getUrlDecoder().decode(decode.getHeader()), StandardCharsets.UTF_8);
        //Jwt的中间部分，理由同上头部
        String payload = new String(Base64.getUrlDecoder().decode(decode.getPayload()),StandardCharsets.UTF_8);
        //解析头部
        JSONObject userJson = JSONObject.parseObject(header);
        String id = userJson.getString("id");
        String userName = userJson.getString("userName");
        //逆解匹配整个Token的编码以及密钥key
        Algorithm algorithm = Algorithm.HMAC256(id);
        try {
            //设置验证条件，这里设置了验证的密钥和编码以及验证的签发人
            JWTVerifier jwtVerifier = JWT.require(algorithm).withIssuer(userName).build();
            //验证是否匹配，如果Token不匹配或者超时，此处将抛出异常
            DecodedJWT verify = jwtVerifier.verify(token);
            //需要的信息获取从头部份获取和中间部分获取
            JwtBeanBO jwtBeanBO = new JwtBeanBO();
            jwtBeanBO.setId(Long.parseLong(id));
            jwtBeanBO.setUserName(userName);
            jwtUtils.setJwtBeanBO(jwtBeanBO);
        }catch (Exception e){

            throw  new ApplicationException(CodeType.TOKEN_TIME_OUT);
        }
    }
    @After(value = "pointCut()&&@annotation(allowToken)")
    private void after(AllowToken allowToken){
        //AOP后置通知 advice
    }

    @AfterReturning(value = "pointCut()&& @annotation(allowToken)",returning = "o")
    private void alterReturn(AllowToken allowToken,Object o){
        //AOP返回通知 advice
    }

    @AfterThrowing(value = "pointCut()&&@annotation(allowToken)")
    private void alterError(AllowToken allowToken){
        //AOP异常通知 advice
    }
}
