package me.yjh;

//import org.apache.catalina.Context;
//import org.apache.catalina.LifecycleException;
//import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@SpringBootApplication
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
        //자동설정 만들기 예제
//        SpringApplication application = new SpringApplication(Application.class);
//        application.setWebApplicationType(WebApplicationType.NONE);
//        application.run(args);


         //내장 톰켓 예시 (사실상 안쓴다)
//       Tomcat tomcat = new Tomcat();
//        tomcat.setPort(8080);
//        final Context context = tomcat.addContext("/","/");
//
//        HttpServlet servlet = new HttpServlet() {
//            @Override
//            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//                PrintWriter writer = resp.getWriter();
//                writer.println("<html><head><title>");
//                writer.println("Hey, tomcat");
//                writer.println("</title></head>");
//                writer.println("<body><h1>Hello Tomcat</h1></body>");
//                writer.println("</html>");
//            }
//        };
//        String servletname = "helloServlet";
//        tomcat.addServlet("/",servletname, servlet);
//        context.addServletMappingDecoded("hello",servletname);
//
//
//        tomcat.start();
//        tomcat.getServer().await();

    }

//    @Bean
//    public Holoman holoman(){
//        Holoman holoman = new Holoman();
//        holoman.setName("newumji");
//        holoman.setHowlong(60);
//        return holoman;
//    }

}
