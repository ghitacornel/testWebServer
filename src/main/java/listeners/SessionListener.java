package listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * see {@link javax.servlet.http.HttpSessionAttributeListener}
 * see {@link javax.servlet.http.HttpSessionActivationListener}
 * see {@link javax.servlet.http.HttpSessionBindingListener}
 */
@WebListener
public class SessionListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent sessionEvent) {
        HttpSession session = sessionEvent.getSession();
        System.out.println(session + "created");
    }

    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        HttpSession session = sessionEvent.getSession();
        System.out.println(session + "destroyed");
    }

}