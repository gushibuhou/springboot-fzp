package com.jyp.springboot.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpURL;
import org.apache.tomcat.util.http.MimeHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA
 * User: jyp
 * Date: 2019/10/8
 * Time: 16:10
 */
@Controller
@Slf4j
public class HelloController {

    @RequestMapping("/hello1")
    @ResponseBody
    public String index() {
        return "Greetings from Spring Boot!";
    }

    // 访问 hello.jsp 参考路径: http://localhost:8082/springboot/hello
    @RequestMapping("/hello")
    public String hello(Model m) {
        m.addAttribute("now", DateFormat.getDateTimeInstance().format(new Date()));
        return "hello";
    }

    @GetMapping("/rpt")
    public Object rpt(HttpServletRequest request, HttpServletResponse response)
    {
        //试验结果,重定向 sendRedirect 会把 request 的参数清掉,所以在 request 里面新增 header 的参数 是不行的
        String url = "http://10.1.7.37:8089/sbrpt/decision/view/report?viewlet=ems%252Fmonthrpt%252FSB_O_N_AR_MONTH.cpt&op=view";
        //String url = "http://127.0.0.1:8082/springboot/rpt1";
        try {
            //1.
//            ModelAndView view = new ModelAndView("redirect:http://10.1.7.37:8089/sbrpt/decision/view/report?viewlet=ems%252Fmonthrpt%252FSB_O_N_AR_MONTH.cpt&op=view");
//            return view;
            //2.

            HeaderMapRequestWrapper request1 = new HeaderMapRequestWrapper(request);
            request1.addHeader("referer", "http://ulev.fj-sanbao.com");
            log.info("请求连接中的 referer1={}",request1.getHeader("referer"));
            request = request1;
            log.info("请求连接中的 referer={}",request.getHeader("referer"));
            response.sendRedirect(url);
            return null;
            //3.
//        	return callhttp(url);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("打开报表失败：" + e.getMessage(),e);
            return e.getMessage();
        }

    }

    @RequestMapping("/rpt1")
    public String rpt1(HttpServletRequest request, HttpServletResponse response) {
        log.info("request.getHeader(\"host\"):" + request.getHeader("host"));
        log.info("request.getHeader(\"referer\"):" + request.getHeader("referer"));
        log.info("response.getHeader(\"referer\"):" + response.getHeader("referer"));
        return "hello1";
    }


    public class HeaderMapRequestWrapper extends HttpServletRequestWrapper {
        /**
         * construct a wrapper for this request
         *
         * @param request
         */
        public HeaderMapRequestWrapper(HttpServletRequest request) {
            super(request);
        }

        private Map<String, String> headerMap = new HashMap<>();

        /**
         * add a header with given name and value
         *
         * @param name
         * @param value
         */
        public void addHeader(String name, String value) {
            headerMap.put(name, value);
        }

        @Override
        public String getHeader(String name) {
            log.info("getHeader --->{}",name);
            String headerValue = super.getHeader(name);
            if (headerMap.containsKey(name)) {
                headerValue = headerMap.get(name);
            }
            return headerValue;
        }

        /**
         * get the Header names
         */
        @Override
        public Enumeration<String> getHeaderNames() {
            List<String> names = Collections.list(super.getHeaderNames());
            for (String name : headerMap.keySet()) {
                names.add(name);
            }
            return Collections.enumeration(names);
        }

        @Override
        public Enumeration<String> getHeaders(String name) {
            log.info("getHeaders --->>>>>>{}",name);
            List<String> values = Collections.list(super.getHeaders(name));
            log.info("getHeaders --->>>>>>{}",values);
            if (headerMap.containsKey(name)) {
                log.info("getHeaders --->{}",headerMap.get(name));
                values = Arrays.asList(headerMap.get(name));
            }
            return Collections.enumeration(values);
        }
    }

    public static void main(String[] args) {
        System.out.println();
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if (arg != null) {

            }

            if (arg == null) {
                
            }
        }
        for (int i = args.length - 1; i >= 0; i--) {
            
        }
    }

}
