package greendog.dwr.test;
/**
 * 用户筛选类
 * @author zhangqing
 * @date 2013-10-18 上午11:33:36
 */
public class UserDwrParam extends DwrParam {

	private String userid;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	@Override
	public boolean validate() {
		Object userId = session.getAttribute("userid");
		return (userId!=null&&userId.toString().equals(this.userid));
	}

}
