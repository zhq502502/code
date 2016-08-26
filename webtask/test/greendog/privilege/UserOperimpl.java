package greendog.privilege;

public class UserOperimpl implements UserOper{

	public void save(String username,int age) {
		System.out.println("---user saved!---"+username);		
	}

}
