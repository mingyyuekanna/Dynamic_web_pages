package com.test.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;
@WebFilter(
        urlPatterns = "/*",
        initParams = {
                @WebInitParam(name = "charset",value = "utf-8")
        }
        /*filterName ="encoding",
           urlPatterns = "/*",
           initParams = {
                   @WebInitParam(name="charset",value = "utf-8")
           }*/

)

public class EncodingFilter implements Filter {
    //保存字符集名字
    private String encoding="";


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//        初始化时调用 读取web.xml中名字为charset的私有信息的值 获得字符集的名字
        this.encoding=filterConfig.getInitParameter("charset");


    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(this.encoding);
        servletResponse.setCharacterEncoding(this.encoding);
        servletResponse.setContentType("text/html;charset="+this.encoding);

        filterChain.doFilter(servletRequest,servletResponse);

    }

    @Override
    public void destroy() {
//        Filter.super.destroy();
//        销毁过滤器前调用
//        System.out.println("destroy filter..");
        this.encoding=null;
    }
}
