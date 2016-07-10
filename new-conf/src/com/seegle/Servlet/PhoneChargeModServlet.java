package com.seegle.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.seegle.data.GetConfigFile;
import com.seegle.data.HttpClient;
import com.seegle.data.PhoneChargeOperation;
import com.seegle.data.PhoneOperuserOper;
import com.seegle.form.AccountSumForm;
import com.seegle.form.PriceinfoForm;

public class PhoneChargeModServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GetConfigFile gcf = new GetConfigFile();//读取配置
	private String url = gcf.getConfig("apiUrl");  //API服务器的地址 ，例如："http://www.seegle.cn"
	
    public PhoneChargeModServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String webSiteUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		if(request.getSession().getAttribute("token")==null){
			response.sendRedirect(webSiteUrl+"/Login.jsp");
		}
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
	  	HttpSession session = request.getSession();
	  	String token = session.getAttribute("token").toString();
	  	String orgid = session.getAttribute("orgid").toString();
	  	String userid = session.getAttribute("userid").toString();
	  	HttpClient hc = new HttpClient(url,orgid);
	  	PhoneChargeOperation pco = new PhoneChargeOperation(orgid.toString());
	  	String flag = "-1";

	  	//获取传入的参数
	  	String corgid = request.getParameter("corgid");  //计费的企业ID
	  	String realmoney = "0";
	  	if(request.getParameter("realmoney")!=null&&request.getParameter("realmoney")!=""){
	  		realmoney = request.getParameter("realmoney"); //实际充值金额，元 	
	  	}
	  	String lessenmoney = "0";
	  	if(request.getParameter("lessenmoney")!=null&&request.getParameter("lessenmoney")!=""){
	  		lessenmoney = request.getParameter("lessenmoney"); //优惠金额，元
	  	}
	  	String price = "0";
	  	if(request.getParameter("price")!=null&&request.getParameter("price")!=""){
	  		price = request.getParameter("price"); //单价，元/分钟
	  	}
	  	String allowexcess = request.getParameter("allowexcess"); //是否允许超额计费。是为1，否为0
	  	String excessprice = "0";
	  	if(request.getParameter("excessprice")!=null&&request.getParameter("excessprice")!=""){
	  		excessprice = request.getParameter("excessprice"); //超额计费单价，元/分钟
	  	}
		String param = request.getParameter("param"); //备用字段
		String remindsum = "0";
		if(request.getParameter("remindsum")!=null&&request.getParameter("remindsum")!=""){
			remindsum = request.getParameter("remindsum"); //提醒金额，元
	  	}
		
		try {
			String payresult = pco.pay(token, corgid, "0", param, pco.yuan2fen(realmoney), pco.yuan2fen(lessenmoney), pco.yuan2fen(price), allowexcess, pco.yuan2fen(price));
			if(payresult!=null&&payresult.equals("0")){
				flag = pco.updateOverfulfilsum(token, corgid, pco.yuan2fen(excessprice));
			}else{
				flag = payresult;
			}
			if(payresult!=null&&payresult.equals("0")){
				flag = pco.updateRemind(token, corgid, pco.yuan2fen(remindsum));
			}else{
				flag = payresult;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//其他参数，参数格式单位转换，realmoney，lessenmoney，price，excessprice，remindsum
		/*SimpleDateFormat SimDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String operatetime = SimDF.format(new Date()); //操作时间
		String begintime = operatetime; //单价生效时间
		String beforpay = ""; //充值前金额
		String fen_realmoney = "";
		String fen_lessenmoney = "";
		String fen_price = "";
		String fen_excessprice = "";
		String fen_remindsum = "";
		String fen_beforpay = "";
		String paymoneysum = ""; //总充值金额，元
	 	String realmoneysum = ""; //总实际充值金额，元
	 	String lessenmoneysum = ""; //总优惠金额，元
	 	String fen_paymoneysum = "";
	 	String fen_realmoneysum = "";
	 	String fen_lessenmoneysum = "";
	  	AccountSumForm asf = new AccountSumForm();
	  	try {
			asf = pco.getAccountinfo(token, corgid); 
			if(asf.getOrgid()!=null){
				fen_beforpay = pco.yuan2fen(asf.getRemainingsum()); //获取当前余额，为充值前金额，单位分
			}
			//提醒金额为空时，读取账户中现有的提醒金额
			if(remindsum==null||"".equals(remindsum)){
				remindsum = asf.getRemindsum();
			}
			fen_realmoney = pco.yuan2fen(realmoney); //元转为分
	  		fen_lessenmoney = pco.yuan2fen(lessenmoney);
	  		fen_price = pco.yuan2fen(price);
	  		fen_excessprice = pco.yuan2fen(excessprice);
	  		fen_remindsum = pco.yuan2fen(remindsum);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		  	
	  	int i_beforpay = Integer.parseInt(fen_beforpay); //转为int类型，分
	  	int i_paymoney = Integer.parseInt(fen_realmoney)+Integer.parseInt(fen_lessenmoney); //充值金额=实际充值金额+优惠金额（负数）
	  	String fen_paymoney = i_paymoney+""; //此次充值金额，分
	  	int i_afterpay = i_beforpay+i_paymoney; //充值后金额，分
	  	String fen_afterpay = i_afterpay+"";

	 	if(asf.getOrgid()!=null){ //如果账户不为空
	 		try {
				fen_paymoneysum = pco.yuan2fen(asf.getPaymoney());
				fen_realmoneysum = pco.yuan2fen(asf.getRealmoney());
				fen_lessenmoneysum = pco.yuan2fen(asf.getLessenmoney());
			} catch (ParseException e) {
				e.printStackTrace();
			}
	 	}
	 	
	 	int i_paymoneysum = Integer.parseInt(fen_paymoneysum)+Integer.parseInt(fen_paymoney);
	  	int i_realmoneysum = Integer.parseInt(fen_realmoneysum)+Integer.parseInt(fen_realmoney);
	  	int i_lessenmoneysum = Integer.parseInt(fen_lessenmoneysum)+Integer.parseInt(fen_lessenmoney);
	  	fen_paymoneysum = i_paymoneysum+"";
	  	fen_realmoneysum = i_realmoneysum+"";
	  	fen_lessenmoneysum = i_lessenmoneysum+"";
	  	String reminded = asf.getReminded();
		
		//获取当前的priceinfo信息，比较传入的priceinfo，如有更改，就添加新的priceinfo
    	ArrayList<PriceinfoForm> piList = new ArrayList<PriceinfoForm>();	
    	try {
			piList = pco.getPriceinfo(token.toString(), corgid);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	PriceinfoForm pif = new PriceinfoForm();
    	if(piList.size()>0){
    		pif = piList.get(0);
    	}
    	if(price.equals(pif.getPrice())&&allowexcess.equals(pif.getAllowoverfulfil())&&excessprice.equals(pif.getOverfulfilprice())){
    		//当前的priceinfo信息和传入的priceinfo一致，无需addPriceinfo
    		if(realmoney.equals("0")){
    			//未充值，无需addPayRecord
    		}else{
    			try {
					flag = pco.addPayRecord(token, corgid, fen_paymoney, fen_realmoney, fen_lessenmoney, fen_price, allowexcess, fen_excessprice, operatetime, orgid+userid, param, fen_beforpay, fen_afterpay);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}else{
    		try {
    			//添加新的priceinfo
				flag = pco.addPriceinfo(token, corgid, fen_price, allowexcess, fen_excessprice, begintime, orgid+userid, param);
				//添加充值记录
				flag = pco.addPayRecord(token, corgid, fen_paymoney, fen_realmoney, fen_lessenmoney, fen_price, allowexcess, fen_excessprice, operatetime, orgid+userid, param, fen_beforpay, fen_afterpay);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
	 	
	 	//查询账户是否存在，如果不存在添加账户信息，如果存在修改余额信息
	  	try {
	  		asf = pco.getAccountinfo(token, corgid);
	  		if(asf.getOrgid()==null||"0".equals(asf.getOrgid())){
	  			flag = pco.addAccountinfo(token, corgid, fen_paymoneysum, fen_realmoneysum, fen_lessenmoneysum, fen_afterpay, fen_remindsum, "0", param);
	  		}else{
	  			flag = pco.updateAccountInfo(token, corgid, fen_paymoneysum, fen_realmoneysum, fen_lessenmoneysum, fen_afterpay, fen_remindsum, "0", param);
	  		}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//修改企业提醒人信息
		String remindaccount1 = request.getParameter("remindaccount1");
		String remindaccount2 = request.getParameter("remindaccount2");
		String remindaccount3 = request.getParameter("remindaccount3");
		String remindname1 = request.getParameter("remindname1");
		String remindname2 = request.getParameter("remindname2");
		String remindname3 = request.getParameter("remindname3");
		String remindmail1 = request.getParameter("remindmail1");
		String remindmail2 = request.getParameter("remindmail2");
		String remindmail3 = request.getParameter("remindmail3");
		PhoneOperuserOper oper = new PhoneOperuserOper(orgid);
		if((remindaccount1==null||remindaccount1.equals(""))&&(remindmail1!=null&&!remindmail1.equals(""))){
			oper.save(token, "1", corgid, "", remindmail1, remindname1, "1");
		}else if((remindaccount1!=null&&!remindaccount1.equals(""))&&(remindname1==null||remindname1.equals("")&&(remindmail1==null||remindmail1.equals("")))){
			oper.del(token, remindaccount1, corgid);
		}else if((remindaccount1!=null&&!remindaccount1.equals(""))&&((remindname1!=null&&!remindname1.equals("")||(remindmail1!=null&&!remindmail1.equals(""))))){
			oper.update(token, remindaccount1, corgid, "", remindmail1, remindname1, "1");
		}
		if((remindaccount2==null||remindaccount2.equals(""))&&(remindmail2!=null&&!remindmail2.equals(""))){
			oper.save(token, "2", corgid, "", remindmail2, remindname2, "1");
		}else if((remindaccount2!=null&&!remindaccount2.equals(""))&&(remindname2==null||remindname2.equals("")&&(remindmail2==null||remindmail2.equals("")))){
			oper.del(token, remindaccount2, corgid);
		}else if((remindaccount2!=null&&!remindaccount2.equals(""))&&((remindname2!=null&&!remindname2.equals("")||(remindmail2!=null&&!remindmail2.equals(""))))){
			oper.update(token, remindaccount2, corgid, "", remindmail2, remindname2, "1");
		}
		if((remindaccount3==null||remindaccount3.equals(""))&&(remindmail3!=null&&!remindmail3.equals(""))){
			oper.save(token, "3", corgid, "", remindmail3, remindname3, "1");
		}else if((remindaccount3!=null&&!remindaccount3.equals(""))&&(remindname3==null||remindname3.equals("")&&(remindmail3==null||remindmail3.equals("")))){
			oper.del(token, remindaccount3, corgid);
		}else if((remindaccount3!=null&&!remindaccount3.equals(""))&&((remindname3!=null&&!remindname3.equals("")||(remindmail3!=null&&!remindmail3.equals(""))))){
			oper.update(token, remindaccount3, corgid, "", remindmail3, remindname3, "1");
		}
		out.print(flag);	 	
	}

}
