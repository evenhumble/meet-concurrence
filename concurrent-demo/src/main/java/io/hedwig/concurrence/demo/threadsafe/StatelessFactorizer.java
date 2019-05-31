package io.hedwig.concurrence.demo.threadsafe;

import javax.annotation.concurrent.ThreadSafe;
import javax.servlet.*;
import java.math.BigInteger;

/**
 * stateless as it is all functions
 * Servlet that counts requests without the necessary synchronization.
 */
@ThreadSafe
public class StatelessFactorizer extends GenericServlet implements Servlet {

    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        encodeIntoResponse(resp, factors);
    }

    void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {
    }

    BigInteger extractFromRequest(ServletRequest req) {
        return new BigInteger("7");
    }

    BigInteger[] factor(BigInteger i) {
        // Doesn't really factor
        return new BigInteger[] { i };
    }
}