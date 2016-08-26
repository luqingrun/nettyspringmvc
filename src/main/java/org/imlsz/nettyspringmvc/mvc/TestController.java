package org.imlsz.nettyspringmvc.mvc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
public class TestController {

    @RequestMapping("/echo")
    public String echo(HttpServletRequest request) {
        return request.getParameter("echo");
    }

    @RequestMapping("/download")
    public void handleFoo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        File file = new File("/Users/luqingrun/Downloads/4.rar");
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[1024];
        while (true) {
            int length =inputStream.read(bytes);
            if(length ==-1){
                break;
            }
            response.getOutputStream().write(bytes, 0, length);
        }
    }

    @RequestMapping(value="/foo2",method= RequestMethod.DELETE)
    public String handleFoo2() {
        return "Hello world";
    }

}
