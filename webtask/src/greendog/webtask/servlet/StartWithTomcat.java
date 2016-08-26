package greendog.webtask.servlet;

import greendog.webtask.beans.TaskHost;
import greendog.webtask.beans.TaskUrl;
import greendog.webtask.beans.TaskUser;
import greendog.webtask.business.Business;
import greendog.webtask.business.thread.UserTaskThread;
import greendog.webtask.dao.Dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StartWithTomcat extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8890552215348705903L;

	private Business buss;
	private Dao dao;
	
	/**
	 * Constructor of the object.
	 */
	public StartWithTomcat() {
		super();
		buss = new Business();
		dao = new Dao();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		List<TaskHost> hostList = dao.getTaskHost();
		for(TaskHost host:hostList){
			List<TaskUrl> urlList = dao.getTaskUrlByHostID(host.getId());
			List<TaskUser> userList = dao.getUsersByHost(host, urlList);
			for(TaskUser user:userList){
				new UserTaskThread(user).start();
			}
		}
	}
	
	
	public static void main(String [] srg) throws ServletException{
		new StartWithTomcat().init();
	}
}
