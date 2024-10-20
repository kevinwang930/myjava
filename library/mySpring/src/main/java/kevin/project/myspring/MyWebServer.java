package kevin.project.myspring;

import jakarta.servlet.*;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.boot.web.server.WebServer;


import java.io.File;

public class MyWebServer {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        Context context = tomcat.addContext("/", new File(".").getAbsolutePath());
        Tomcat.addServlet(context, "myServlet", new MyServlet());
        context.addServletMappingDecoded("/myservlet", "myServlet");

        tomcat.start();

        WebServer webServer = new TomcatWebServer(tomcat);
        webServer.start();
    }

    private static class MyServlet implements Servlet {
        @Override
        public void init(ServletConfig config) throws ServletException {
        }

        @Override
        public ServletConfig getServletConfig() {
            return null;
        }

        @Override
        public void service(ServletRequest req, ServletResponse res) throws ServletException, java.io.IOException {
            res.getWriter().println("Hello, World!");
        }

        @Override
        public String getServletInfo() {
            return null;
        }

        @Override
        public void destroy() {
        }
    }
}
