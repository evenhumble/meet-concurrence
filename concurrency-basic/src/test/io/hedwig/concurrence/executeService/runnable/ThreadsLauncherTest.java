package io.hedwig.concurrence.executeService.runnable;


import org.junit.Test;

public class ThreadsLauncherTest {

	@Test
	public void testLaunch() throws InterruptedException {
		
		ThreadsLauncher launcher = new ThreadsLauncher();
		launcher.launch();
	}

}
