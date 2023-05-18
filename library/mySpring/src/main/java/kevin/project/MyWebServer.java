package kevin.project;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.boot.web.server.WebServer;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
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
        public void init(javax.servlet.ServletConfig config) throws ServletException {
        }

        @Override
        public javax.servlet.ServletConfig getServletConfig() {
            return null;
        }

        @Override
        public void service(javax.servlet.ServletRequest req, javax.servlet.ServletResponse res) throws ServletException, java.io.IOException {
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
