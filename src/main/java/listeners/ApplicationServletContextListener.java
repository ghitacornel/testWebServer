package listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationServletContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        System.out.println("application servlet context listener : servlet context initialised " + context);
        System.out.println("application servlet context listener : servlet context name " + context.getServletContextName());
        System.out.println("application servlet context listener : servlet context path " + context.getContextPath());
        System.out.println("application servlet context listener : server info " + context.getServerInfo());
        System.out.println("application servlet context listener : virtual server name " + context.getVirtualServerName());
        System.out.println("application servlet context listener : Servlet API " + context.getMajorVersion() + "." + context.getMinorVersion());
        System.out.println("application servlet context listener : effective Servlet API " + context.getEffectiveMajorVersion() + "." + context.getEffectiveMinorVersion());
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        System.out.println("servlet context destroyed " + context);
    }

}
