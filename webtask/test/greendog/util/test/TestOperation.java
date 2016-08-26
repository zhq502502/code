package greendog.util.test;

public class TestOperation {

	public static TestOperation oper;
	private TestOperation(){
		
	}
	public static TestOperation getInstance(){
		if(oper==null){
			oper = new TestOperation();
		}
		return oper;
	}
	
	public void sysout(String name){
		String n = name;
		for(int i=0;i<10;i++){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(n+"==="+i);
		}
	}
}
