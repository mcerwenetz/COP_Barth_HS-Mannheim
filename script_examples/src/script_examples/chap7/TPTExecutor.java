package script_examples.chap7;

import java.util.concurrent.Executor;

public class TPTExecutor implements Executor {

	@Override
	public void execute(Runnable command) {
		new Thread(command).start();
	}

}
