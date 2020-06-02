package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

// web filter to all URLs
@WebFilter("/*")
public class SimpleFilter implements javax.servlet.Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println(this + " initialised");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println(this + " triggered before request");
        chain.doFilter(request, response);
        System.out.println(this + " triggered after request");
    }

}
