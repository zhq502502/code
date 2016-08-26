package greendog.util.test;

public class Test1 {

	/**
	 * @author zhangqing
	 * @date 2013-12-2 下午04:21:05
	 * @param args
	 */
	public static void main(String[] args) {
		String name1 = "name1";
		String name2 = "name2";
		String name3 = "name3";
		new Th(name1).start();
		new Th(name2).start();
		new Th(name3).start();
	}

}
class Th extends Thread{
	String name = "";
	public Th(String name){
		this.name = name;
	}
	public void run(){
		TestOperation.getInstance().sysout(name);
	}
}
