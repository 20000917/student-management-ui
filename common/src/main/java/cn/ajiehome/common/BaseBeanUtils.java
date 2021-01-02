package cn.ajiehome.common;

import cn.ajiehome.common.advice.GlobalExceptionHandler;
import cn.ajiehome.common.aspects.TokenAspect;
import cn.ajiehome.common.config.GoWebMvcConfigurerAdapter;
import cn.ajiehome.common.emails.NetEaseEmailCode;
import cn.ajiehome.common.jwt.JwtUtils;
import cn.ajiehome.common.swagger.SpringFoxConfig;
import cn.ajiehome.common.utils.BodyUtils;
import cn.ajiehome.common.utils.SnowFlake;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public abstract class BaseBeanUtils extends SpringBootServletInitializer {

    @Bean
    public JwtUtils getJwtUtils(){
        return new JwtUtils();
    }

    @Bean
    public GlobalExceptionHandler getGlobalExceptionHandler(){
        return new GlobalExceptionHandler();
    }

    @Bean
    public TokenAspect getTokenAspect(){
        return new TokenAspect();
    }

    @Bean
    public SpringFoxConfig getSpringFoxConfig(){
        return new SpringFoxConfig();
    }

    @Bean
    public NetEaseEmailCode getNetEaseEmailCode(){
        return new NetEaseEmailCode();
    }

    @Bean
    public BodyUtils getGetBodyUtils(){
        return new BodyUtils();
    }

    @Bean
    public SnowFlake getShowFlake(){
        return new SnowFlake();
    }

    @Bean
    public GoWebMvcConfigurerAdapter getGoWebMvcConfigurerAdapter(){ return new GoWebMvcConfigurerAdapter(); }

    /**
     * mybatis-plus分页插件
     * @return 分页配置结果
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        // paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        paginationInterceptor.setLimit(5);
        // 开启 count 的 join 优化,只针对部分 left join
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }
}
