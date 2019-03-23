package com.springboot.demo.base.filter;

import com.springboot.demo.constants.Constant;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
public class SimpleCORSFilter implements Filter {
    private static final Set<String> originSet = new HashSet<>();
    private static final String ORIGINAL_URL = "http://localhost:8080";

    static {
        originSet.add(ORIGINAL_URL);
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        if (request.getHeader("Origin") != null && !request.getHeader("Origin").equals("")) {
            String[] origins = request.getHeader("Origin").split("/");
            if (origins.length >= 3) {
                String origin = origins[0] + "//" + origins[2];
                if (originSet.contains(origin) || origin.endsWith(Constant.DOMAIN)) {
                    response.setHeader("Access-Control-Allow-Origin", origin);
                } else {
                    response.setHeader("Access-Control-Allow-Origin", ORIGINAL_URL);
                }
            } else {
                response.setHeader("Access-Control-Allow-Origin", ORIGINAL_URL);
            }
        } else {
            response.setHeader("Access-Control-Allow-Origin", ORIGINAL_URL);
        }


//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS");
        response.addHeader("Access-Control-Allow-Methods", "HEAD,GET,POST,PUT,DELETE,OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type,Origin,Accept");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("P3p", "CP=\"CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR\"");
        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }
}
