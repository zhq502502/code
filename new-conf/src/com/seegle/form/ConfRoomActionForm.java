package com.seegle.form;

/**
 *
 * 会议信息，对应conf_room表和real_conf表
 */
public class ConfRoomActionForm extends org.apache.struts.action.ActionForm {

    private String id;  //会议ID
    private String confName;  //会议室名
    private String confPasswordMd5;  //会议密码
    private String managePasswordMd5;  //会议管理密码
    private String description;  //会议描述
    private String confBeginTime;  //会议开始时间
    private String confEndTime;  //会议结束时间
    private int maxConfUser;  //最大与会人数
    private int maxConfTourist;  //最大游客人数
    private int maxConfSpokesman;  //最大讲话人数
    private String confScreenMode;  //分屏模式
    private int lockFlag;  //锁定标记
    private int autoClearFlag;  //是否自动清除数据
    
    private int all_can_visible;  //是否所有人可见
    private int download_mode;  //白板下载方式
    private int open_audit;  //是否开启旁听；
    private int aaa;  //是否开启直播
    private int live_max_num;  //直播最大人数；
    private String live_conf_pass = "";  //直播会议室密码
    private String is_video;  //功能模块组合
    private long recordMode; //是否同步录制及录制模块
	private int loadBalance; //登录方式
    private int video_quality; //会议室视频质量和同步性参数
    private int VRenderer; //视频
    private String conf_server_ip= "";  //指定会议服务器IP
    
    private int openFlag;  //开放标记
    private int joinFlag;  //加入标记
    private String creator;  //创建者
    private String createTime;  //创建时间
    private String modificator;  //最后修改者
    private String modifyTime;  //最后修改时间
    private int hideflag;  //隐藏标记
    private int flag;  //隐藏标记
    private int confOnlineNumber;  //会议在线人数（real_conf表）
    private int realConfId;  //会议真实ID（real_conf表）
    private int pageNumber = 0;  //分页数（分多少页显示）
    private String confExtra = "";

    public String getConfExtra() {
		return confExtra;
	}

	public void setConfExtra(String confExtra) {
		this.confExtra = confExtra;
	}

	//set方法
    public void setId(String id) {
        this.id = id;
    }

    public void setAuto_clear_flag(int autoClearFlag) {
        this.autoClearFlag = autoClearFlag;
    }

    public void setConf_begin_time(String confBeginTime) {
        this.confBeginTime = confBeginTime;
    }

    public void setConf_end_time(String confEndTime) {
        this.confEndTime = confEndTime;
    }

    public void setConf_name(String confName) {
        this.confName = confName;
    }

    public void setConf_password_md5(String confPasswordMd5) {
        this.confPasswordMd5 = confPasswordMd5;
    }

    public void setConf_screen_mode(String confScreenMode) {
        this.confScreenMode = confScreenMode;
    }

    public void setCreate_time(String createTime) {
        this.createTime = createTime;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void setHideflag(int hideflag) {
        this.hideflag = hideflag;
    }

    public void setJoin_flag(int joinFlag) {
        this.joinFlag = joinFlag;
    }

    public void setLock_flag(int lockFlag) {
        this.lockFlag = lockFlag;
    }

    public void setManage_password_md5(String managePasswordMd5) {
        this.managePasswordMd5 = managePasswordMd5;
    }

    public void setMax_conf_spokesman(int maxConfSpokesman) {
        this.maxConfSpokesman = maxConfSpokesman;
    }

    public void setMax_conf_tourist(int maxConfTourist) {
        this.maxConfTourist = maxConfTourist;
    }

    public void setMax_conf_user(int maxConfUser) {
        this.maxConfUser = maxConfUser;
    }

    public void setModificator(String modificator) {
        this.modificator = modificator;
    }

    public void setModify_time(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public void setOpen_flag(int openFlag) {
        this.openFlag = openFlag;
    }

    public void setConf_online_number(int confOnlineNumber) {
        this.confOnlineNumber = confOnlineNumber;
    }

    public void setRealConfId(int realConfId) {
        this.realConfId = realConfId;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

  

	public int getAll_can_visible() {
		return all_can_visible;
	}
 
	public void setAll_can_visible(int allCanVisible) {
		all_can_visible = allCanVisible;
	}

	public int getDownload_mode() {
		return download_mode;
	}

	public void setDownload_mode(int downloadMode) {
		download_mode = downloadMode;
	}

	public int getOpen_audit() {
		return open_audit;
	}

	public void setOpen_audit(int openAudit) {
		open_audit = openAudit;
	}

	public int getAaa() {
		return aaa;
	}

	public void setAaa(int aaa) {
		this.aaa = aaa;
	}

	public int getLive_max_num() {
		return live_max_num;
	}

	public void setLive_max_num(int liveMaxNum) {
		live_max_num = liveMaxNum;
	}

	public String getLive_conf_pass() {
		return live_conf_pass;
	}

	public void setLive_conf_pass(String liveConfPass) {
		live_conf_pass = liveConfPass;
	}

	public String getIs_video() {
		
		return is_video;
	}

	public void setIs_video(String isVideo) {
		is_video = isVideo;
	}

	public int getLoadBalance() {
		return loadBalance;
	}

	public void setLoadBalance(int loadBalance) {
		this.loadBalance = loadBalance;
	}

	public int getVideo_quality() {
		return video_quality;
	}

	public void setVideo_quality(int videoQuality) {
		video_quality = videoQuality;
	}

	public int getVRenderer() {
		return VRenderer;
	}

	public void setVRenderer(int vRenderer) {
		VRenderer = vRenderer;
	}

	public String getConf_server_ip() {
		return conf_server_ip;
	}

	public void setConf_server_ip(String confServerIp) {
		conf_server_ip = confServerIp;
	}

	//get方法
    public String getId() {
        return id;
    }

    public int getAutoClearFlag() {
        return autoClearFlag;
    }

    public String getConfBeginTime() {
        return confBeginTime;
    }

    public String getConfEndTime() {
        return confEndTime;
    }

    public String getConfName() {
        return confName;
    }

    public String getConfPasswordMd5() {
        return confPasswordMd5;
    }

    public String getConfScreenMode() {
        return confScreenMode;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getCreator() {
        return creator;
    }

    public String getDescription() {
        return description;
    }

    public int getFlag() {
        return flag;
    }

    public int getHideflag() {
        return hideflag;
    }

    public int getJoinFlag() {
        return joinFlag;
    }

    public int getLockFlag() {
        return lockFlag;
    }

    public String getManagePasswordMd5() {
        return managePasswordMd5;
    }

    public int getMaxConfSpokesman() {
        return maxConfSpokesman;
    }

    public int getMaxConfTourist() {
        return maxConfTourist;
    }

    public int getMaxConfUser() {
        return maxConfUser;
    }

    public String getModificator() {
        return modificator;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public int getOpenFlag() {
        return openFlag;
    }

    public int getConfOnlineNumber() {
        return confOnlineNumber;
    }

    public int getRealConfId() {
        return realConfId;
    }

    public int getPageNumber() {
        return pageNumber;
    }

	public long getRecordMode() {
		return recordMode;
	}

	public void setRecordMode(long recordMode) {
		this.recordMode = recordMode;
	}
}
