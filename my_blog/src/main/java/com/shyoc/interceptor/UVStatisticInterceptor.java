package com.shyoc.interceptor;

import com.shyoc.utils.IpAddressUtils;
import com.shyoc.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;


public class UVStatisticInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RedisUtil redisUtil;

    private static final String PREFIX = "uniqueViews::";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ipAddress = IpAddressUtils.getIpAddress(request);
        String referer = request.getHeader("Referer");
        String requestURI = request.getRequestURL().toString();
        String date = getDate();
        String declareDate = getDeclareDate();
        String key = PREFIX + date;
        redisUtil.pfadd(key, ipAddress);
       // logger.info("{}：访客ip：{}，Referer：{}, Request: {}", declareDate, ipAddress, referer, requestURI);
        return true;
    }

    private String getDate() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formatDate = sdf.format(date);
        return formatDate;
    }

    private String getDeclareDate() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatDate = sdf.format(date);
        return formatDate;
    }
}
