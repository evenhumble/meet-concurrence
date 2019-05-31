package io.hedwig.concurrence.demo.threadsafe;

import java.math.BigInteger;
import java.util.concurrent.atomic.*;
import javax.annotation.concurrent.ThreadSafe;
import javax.servlet.*;



/**
 * CountingFactorizer
 *
 * Servlet that counts requests using AtomicLong
 *
d */
@ThreadSafe
public class CountingFactorizer extends GenericServlet implements Servlet {
    private final AtomicLong count = new AtomicLong(0);  //safe

    public long getCount() { return count.get(); }

    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        count.incrementAndGet();
        encodeIntoResponse(resp, factors);
    }

    void encodeIntoResponse(ServletResponse res, BigInteger[] factors) {}
    BigInteger extractFromRequest(ServletRequest req) {return null; }
    BigInteger[] factor(BigInteger i) { return null; }
}