package io.hedwig.concurrence.executeService;

import io.hedwig.concurrence.executeService.executor.CountDownLatchDemo;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;


/**
 * @author patrick
 */
public class CountDownLatchDemoTest {

    @Test
    /**
     * we’ve demonstrated how we can use a CountDownLatch in order to block a thread until other threads have finished some processing.
     */
    public void testCountDownLatch() throws InterruptedException {
        List<String> output = Collections.synchronizedList(new ArrayList<>());
        CountDownLatch latch = new CountDownLatch(5);
        List<Thread> workers = Stream.generate(() ->
                new Thread(new CountDownLatchDemo.Worker(output,latch))).limit(10).collect(Collectors.toList());

        workers.forEach(Thread::start);
        latch.await(3L,TimeUnit.SECONDS);  //java.lang.IllegalMonitorStateException, todo: await,wait difference
        output.add("Latch Released!!");
        Assert.assertThat(output.size(),is(11));  //very interesting for learning countdownlatch, the size is not granted
    }

}