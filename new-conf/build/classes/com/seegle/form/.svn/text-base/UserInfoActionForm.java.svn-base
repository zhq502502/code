package com.seegle.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * 用户信息
 */
public class UserInfoActionForm  implements Serializable {

    private int userId;//用户Id
    private String userName; //用户注册名，唯一
    private String alias;//用户昵称
    private String name;//真实姓名
    private String accountEmail = "";//邮件地址，唯一
    private String accountMobile = "";  //移动电话
    private String accountPhone = "";  //固定电话
    private String passwordMd5;  //用户密码(Md5)
    private int depId = 0;  //用户部门ID
    private String lastlogintime;  //用户最后登录时间
    private int logintag = 0;  //登录状态标记, 0 未在线， 1 在线
    private int flag = 0;  //删除标记，0 未删除，1 已删除
    private int loadBalance;  //负载均衡标记，0 未启用 1 启用
    private String confServerIp = "";  //指定会议服务器IP
    private String rolenote;//用户权限
    private boolean state = false;//用户登陆状态
    private int pageNumber = 0;
    private int roleId = 3;
    private String password;  //用户密码（未加密）
    private int proxytype = 0;  //代理设置  0 未设置代理 1 socks4 代理 2 socks5代理 3 http代理
    private String proxyaddr;  //代理服务器地址
    private String proxyport;  //代理服务器端口
    private String proxyuser;  //代理服务器用户名
    private String proxypass;  //代理服务器用户密码
    private int serverport = 2810;  //服务端TCP端口
    private int udpport = 2810;  //服务端UDP端口
    private boolean selected =false;
    private boolean selected1 =false;
//    private int load_balance ;  //负载均衡标记，0 未启用 1 启用
    //set方法
    public void setProxyaddr(String proxyaddr) {
        this.proxyaddr = proxyaddr;
    }

    public void setProxypass(String proxypass) {
        this.proxypass = proxypass;
    }

    public void setProxyport(String proxyport) {
        this.proxyport = proxyport;
    }

    public void setProxytype(int proxytype) {
        this.proxytype = proxytype;
    }

    public void setProxyuser(String proxyuser) {
        this.proxyuser = proxyuser;
    }

    public void setRoleId(int role) {
        this.roleId = role;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setRolenote(String rolenote) {
//        if (null == rolenote) {
//            rolenote = "";
//        }
        this.rolenote = rolenote;
    }

    public void setAccount_email(String accountEmail) {
//        if (null == accountEmail) {
//            accountEmail = "";
//        }
        this.accountEmail = accountEmail;
    }

    public void setAccount_mobile(String accountMobile) {
//        if (null == accountMobile) {
//            accountMobile = "";
//        }
        this.accountMobile = accountMobile;
    }

    public void setAccount_phone(String accountPhone) {
//        if (null == accountPhone) {
//            accountPhone = "";
//        }
        this.accountPhone = accountPhone;
    }

    public void setAlias(String alias) {
//        if (null == alias) {
//            alias = "";
//        }
        this.alias = alias;
    }

    public void setConf_server_ip(String confServerIp) {
//        if (null == confServerIp) {
//        }
        this.confServerIp = confServerIp;
    }

    public void setDep_id(int depId) {

        this.depId = depId;
    }

    public void setFlag(int flag) {

        this.flag = flag;
    }

    public void setLoad_balance(int loadBalance) {
        this.loadBalance = loadBalance;
    }
//        public void setLoad_balance(int loadBalance) {
//        this.loadBalance = loadBalance;
//    }
//    public void setLoadBalance(int loadBalance) {
//        this.loadBalance = loadBalance;
//    }

    public void setLogintag(int logintag) {
        this.logintag = logintag;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword_md5(String passwordMd5) {
        this.passwordMd5 = passwordMd5;
    }

    public void setUser_name(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //get方法
    public String getProxyaddr() {
        return proxyaddr;
    }

    public String getProxypass() {
        return proxypass;
    }

    public String getProxyport() {
        return proxyport;
    }

    public int getProxytype() {
        return proxytype;
    }

    public String getProxyuser() {
        return proxyuser;
    }

    public int getUserId() {
        return userId;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public String getAccountMobile() {
        return accountMobile;
    }

    public String getAccountPhone() {
        return accountPhone;
    }

    public String getAlias() {
        return alias;
    }

    public String getConfServerIp() {
        return confServerIp;
    }

    public int getDepId() {
        return depId;
    }

    public int getFlag() {
        return flag;
    }

    //返回当前系统时间
    public String getLastlogintime() {
        Date DT = new Date();
        SimpleDateFormat SimDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String StrDA = SimDF.format(DT);
        return StrDA;
    }

    public int getLoadBalance() {
        return loadBalance;
    }

    public int getLogintag() {
        return logintag;
    }

    public String getName() {
        return name;
    }

    public String getPasswordMd5() {
        return passwordMd5;
    }

    public String getUserName() {
        return userName;
    }

    public String getRolenote() {
        return rolenote;
    }

    public boolean getState() {
        return state;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getRoleId() {
        return roleId;
    }

    public String getPassword() {
        return password;
    }

    public int getServerport() {
        return serverport;
    }

    public void setServerport(int serverport) {
        this.serverport = serverport;
    }

    public int getUdpport() {
        return udpport;
    }

    public void setUdpport(int udpport) {
        this.udpport = udpport;
    }
    
    public boolean getSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    public boolean getSelected1() {
        return selected1;
    }

    public void setSelected1(boolean selected1) {
        this.selected1 = selected1;
    }
}
