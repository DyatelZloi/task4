package servlet;

/**
 * Created by Malkov Nikifor on 25.01.2016.
 */
public class ExceptionServlet extends RuntimeException {

        public ExceptionServlet() {super();}

        public ExceptionServlet(String message) {
            super(message);
        }

        public ExceptionServlet(String message, Throwable cause) {
            super(message, cause);
        }

        public ExceptionServlet(Throwable cause) {
            super(cause);
        }
    }
