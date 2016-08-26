package greendog.weixin.beans.bus;

public class Line {
	private int direction;//上行或下行
	private String startStopName;//起始站名
	private String endStopname;//末尾站名
	private String firstTime;//开班时间
	private String lastTime;//收班时间
	private String lineName;//线路名称
	private String lineNo;//线路车号
	private String stopNum;//站总数
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public String getStartStopName() {
		return startStopName;
	}
	public void setStartStopName(String startStopName) {
		this.startStopName = startStopName;
	}
	public String getEndStopname() {
		return endStopname;
	}
	public void setEndStopname(String endStopname) {
		this.endStopname = endStopname;
	}
	public String getFirstTime() {
		return firstTime;
	}
	public void setFirstTime(String firstTime) {
		this.firstTime = firstTime;
	}
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public String getLineNo() {
		return lineNo;
	}
	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}
	public String getStopNum() {
		return stopNum;
	}
	public void setStopNum(String stopNum) {
		this.stopNum = stopNum;
	}
	
}
