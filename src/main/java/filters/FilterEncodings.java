package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Malkov Nikifor on 03.12.2015.
 */
@WebFilter(filterName = "FilterEncodings", urlPatterns = "/*")
public class FilterEncodings implements Filter {

    private static final Logger log = LoggerFactory.getLogger(FilterEncodings.class);

    /**
     *
     */
    @Override
    public void destroy() {
        log.info("Encodings filter destroyed");
    }

    /**
     *
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {
        log.info("Encodings filter started");
    }

    /**
     *
     * @param req
     * @param resp
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        doFilter((HttpServletRequest) req, (HttpServletResponse) resp, chain);
    }

    /**
     *
     * @param req
     * @param resp
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        chain.doFilter(req, resp);
    }
}
