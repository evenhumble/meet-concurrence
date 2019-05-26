
import io.hedwig.concurrence.demo.intro.NoThreadSafeSeqGenerator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SeqGeneratorIntro {

    public static void notThreadSafeGenerator(){
        NoThreadSafeSeqGenerator generator = new NoThreadSafeSeqGenerator();
        ExecutorService es = Executors.newFixedThreadPool(64);
        for (int i = 0; i < 120; i++) {
            es.submit(() -> {
                int count = 0;
                while(count<100){
                    count++;
                    System.out.println("thread-"+Thread.currentThread().getName()+"-"+generator.getNext());
                }
            });
        }

        es.shutdown();
    }

    public static void main(String[] args) {
        notThreadSafeGenerator();
    }
}
