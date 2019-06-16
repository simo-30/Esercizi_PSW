package _framework;

//Nota e' importantissimo che perform sia synchronized in Executor!
//Serve per avere un accesso controllato agli oggetti del diagramma delle classi
public final class TaskExecutor {
	public static TaskExecutor instance = new TaskExecutor();
	private TaskExecutor(){}
	public static TaskExecutor getInstance() {
		return instance;
	}
	public synchronized void perform(Task t) {
		t.esegui();
	}
}
