/* 
 * 定义参数名称为数组的下标，实现多语言实时切换
 * 
 */
var StringRes_en = new Array();  //英文数组
var StringRes_zh_cn = new Array();  //简体中文数组
var StringRes_zh_tw = new Array();  //繁体中文数组

var ERR_NO_ORGID = 0;  //单位账号不能为空
var ERR_ORGID_ERROR = 1;  //请输入正确的单位账号
var ERR_NO_USERID = 2;  //用户账号不能为空
var NO_DIFINE_ERROR_MSG = 3;  //未定义的错误信息
var ERROR_CODE = 4;  //错误代码
var ERR_NO_IMAGE = 5;  //请选择要上传logo图片
var ERR_IMAGE_ERROR = 6;  //请重新选择图片上传.上传文件类型必须为jpg、png、gif或者bmp
var LOGO_MOD_SUSCESS = 7;  //logo修改成功
var LOGO_IS_DEFAULT = 8;  //当前已经为默认logo
var LOGO_MOD_FAILURE = 9;  //logo修改失败
var ERR_ORGID_IS_NUM = 10;  //单位账号必须为数字
var REG_SUSCESS = 11;  //企业注册成功
var REG_FAILURE = 12;  //企业注册失败
var REG_GET_TOKEN_FAILURE = 13;  //获取token失败，请检查企业ID和管理密码
var REG_ADD_ADMIN_FAILURE = 14;  //添加超级管理员失败
var REG_CREATE_TABLE_FAILURE = 15;  //创建orgid_user表失败
var REG_TABLE_EXIST = 16;  //企业已经存在
var REG_NO_COMPANY_INFORMATION = 17;  //中心服务器中获取不到该企业信息
var ERR_NO_EMAIL_HOST = 18;  //邮箱认证服务器不能为空
var ERR_NO_SENDER_ALIAS = 19;  //发件人昵称不能为空
var ERR_NO_HOST_PORT = 20;  //认证服务器端口不能为空
var ERR_NO_EMAIL_ACCOUNT = 21;  //邮箱登录账户不能为空
var ERR_NO_PASSWORD = 22;  //密码不能为空
var REG_PASSWORD_UNPAIR = 23;  //两次输入密码不一致
var ERR_PORT_ERROR = 24;  //请输入正确的端口号
var ERR_EMAIL_ADDRESS_ERROR = 25;  //请输入正确的邮箱地址
var ERR_CURRENT_USER_UNEXIST = 26;  //当前用户不存在,请重新登录
var OPERATION_SUCESS = 27;  //操作成功
var ERR_EMAIL_CONFIG_ERROR = 28;  //邮件配置不正确，登录测试失败
var EMAIL_CONFIG_SAVE_FAILURE = 29;  //邮件配置未保存成功
var ERR_NO_PERMISSION = 30;  //无此操作权限
var OPERATION_FAILURE = 31;  //操作失败
var ERR_OLD_PASS_ERROR = 32;  //旧密码输入错误
var ERR_NEW_PASS_UNPAIR = 33;  //两次输入的新密码不一致，请重新输入
var ERR_PASS_SAME = 34;  //输入的新密码和旧密码相同，请重新输入
var ERR_PASS_LENGTH = 35;  //新密码不得超过12个字
var MOD_SUCESS = 36;  //修改成功
var MOD_FAILURE = 37;  //修改失败
var ADDRESSEE_EXAMPLE = 38;  //收件人邮箱以逗号隔开，如：zhang@seegle.com,gong@seegle.com
var CONF_URL = 39;  //会议链接：
var SELECT_ADDRESSEE = 40;  //选择收件人
var ERR_EMAIL_ADDRESS_ERROR = 41;  //请输入正确的邮箱地址
var ERR_NO_ADDRESSEE = 42;  //请填写收件人
var ERR_NO_EMAIL_TITLE = 43;  //邮件标题不能为空
var ERR_EMAIL_TITLE_ERROR = 44;  //邮件标题不能有空格
var ERR_NO_EMAIL_CONTENT = 45;  //内容不能为空
var EMAIL_SEND_SUCESS = 46;  //邮件已发出
var EMAIL_SEND_FAILURE = 47;  //邮件发送出错
var ERR_NO_COMPANY_NAME = 48;  //请输入新名称
var ERR_COMPANY_NAME_LENGTH = 49;  //企业名称不能超过32位
var ERR_NO_CONFNAME = 50;  //会议名称不允许为空
var ERR_NON_STANDARD_CHARACTERS = 51;  //会议名称由字母a～z(不区分大小写)、汉字、数字0～9、减号或下划线组成,只能以数字、汉字、或字母开头和结尾
var ERR_CONFPASS_UNPAIR = 52;  //会议密码两次输入不一致
var ERR_MANAGEPASS_UNPAIR = 53;  //会议管理密码两次输入不一致
var ERR_NO_MAXCONFUSER = 54;  //请输入最大与会人数
var ERR_MAXCONFUSER_ERROR = 55;  //与会人数必须为正整数
var ERR_MAXCONFUSER_RENGE_ERROR = 56;  //最大与会人数必须在1--1000以内
var ERR_NO_SPOKESMAN = 57;  //请输入主席人数
var ERR_SPOKESMAN_ERROR = 58;  //主席人数必须为正整数
var ERR_SPOKESMAN_RENGE_ERROR = 59;  //最大主席人数必须在1--20以内
var ERR_SPOKESMAN_BIGER = 60;  //主席人数不能大于最大与会者人数
var ERR_NO_TOURIST = 61;  //请输入游客人数
var ERR_TOURIST_ERROR = 62;  //游客人数必须为正整数
var ERR_TOURIST_RENGE_ERROR = 63;  //最大游客人数必须在0--1000以内
var ERR_TOURIST_BIGER = 64;  //游客人数不能多于与会人数
var ERR_DESCRIPTION_LENGTH = 65;  //会议描述不得超过100个字
var ERR_NO_CONFGROUP = 66;  //请选择会议集群类型
var ERR_CONFIRM_ADD = 67;  //当前时段会议室允许最大用户数已超过最大并发数，确定要增加吗
var ERR_CONFADD_REFUSED = 68;  //当前时段会议室允许最大用户数已超过最大并发数,不允许增加
var ADD_SUCESS = 69;  //添加成功
var ADD_FAILURE = 70;  //添加失败
var CANCEL_CONFADMIN_SUCESS = 71;  //取消会议室管理员成功
var CANCEL_CONFADMIN_FAILURE = 72;  //取消会议室管理员失败
var ERR_NO_USER_SELECTED = 73;  //未选择用户
var CONFIRM_DELETE = 74;  //确认删除吗
var ERR_NO_CONFADMIN_SELECTED = 75;  //请选择视频会议管理员
var CANCEL_CONFCOMMON_SUCESS = 76;  //取消默认与会者成功
var CANCEL_CONFCOMMON_FAILURE = 77;  //取消默认与会者失败
var ERR_NO_CONFCOMMON_SELECTED = 78;  //请选择默认与会者
var DELETE_SUCESS = 79;  //删除成功
var DELETE_FAILURE = 80;  //删除失败
var CANCEL_TOP_SUCESS = 81;  //取消置顶成功
var CANCEL_TOP_FAILURE = 82;  //取消置顶失败
var CONFIRM_CANCEL_TOP = 83;  //确认取消置顶吗
var SET_TOP_SUCESS = 84;  //置顶成功
var SET_TOP_FAILURE = 85;  //置顶失败
var CONFIRM_SET_TOP = 86;  //确认置顶吗
var ERR_NO_CONF_SELECTED = 87;  //未选择会议
var DELETE_ALL_SUCESS = 88;  //批量删除成功
var DELETE_ALL_FAILURE = 89;  //批量删除失败
var SAVE_SUCESS = 90;  //保存成功
var SAVE_FAILURE = 91;  //保存失败
var YEAR = 92;  //年
var MONTH = 93;  //月
var DATE = 94;  //日
var CANCEL_ADMIN_SUCCESS = 95;  //取消会议管理员成功
var CANCEL_ADMIN_FAILURE = 96;  //取消会议管理员失败
var ERR_NO_ADMIN_SELECTED = 97;  //请选择会议管理员
var ERR_NO_USERALIAS = 98;  //请输入昵称
var ERR_USERALIAS_LENGTH_ERROR = 99;  //昵称不能超过32位
var ERR_USERALIAS_STANDARD = 100;  //昵称由中文、字母、数字、减号或下划线组成,只能以中文、字母、或数字开头和结尾!
var ERR_EMAIL_LENGTH = 101;  //邮箱名称长度过长
var ERR_EMAIL_ERROR = 102;  //请输入正确的邮箱地址
var ERR_PHONENUMBER_ERROR = 103;  //请输入正确的电话号码
var SET_ADMIN_SUCCESS = 104;  //设置管理员成功
var SET_ADMIN_FAILURE = 105;  //设置管理员失败
var ERR_USERACCOUNT_STANDARD = 106;  //登录账号由字母a～z(不区分大小写)、数字0～9、减号或下划线组成,只能以数字、或字母开头和结尾
var ERR_USERNAME_STANDARD = 107;  //昵称由字母a～z(不区分大小写)、汉字、数字0～9、减号或下划线组成,只能以数字、汉字、或字母开头和结尾
var ERR_NO_PROXY_ADDRESS = 108;  //请输入代理服务器地址
var ERR_PROXY_ADDRESS_ERROR = 109;  //请输入正确的代理服务器地址
var ERR_N0_PROXY_PORT = 110;  //请输入代理服务器端口号
var ERR_USERACCOUNT_EXIST = 111;  //用户名已存在
var CLOSE = 112;  //关闭
var UPDATE_ALL_SUCESS = 113;  //批量更新成功
var UPDATE_ALL_FAILURE = 114;  //批量更新失败
var ERR_NO_USER_NUMBER = 115;  //请输入需要添加用户的数量
var ERR_USER_NUMBER_ERROR = 116;  //请输入1~999区间的正确数字
var CANCEL_USERADMIN_SUCCESS = 117;  //取消用户管理员成功
var CANCEL_USERADMIN_FAILURE = 118;  //取消用户管理员失败
var ERR_NO_USERADMIN_SELECTED = 119;  //请选择用户管理员
var VIDEO_FILE = 120;  //视频文件
var SELECT_VIDEO_FILE = 121;  //选择录像文件
var ERR_NO_VIDEO_FILE = 122;  //没有准备上传的文件
var ERR_NO_SGCONF = 123;  //检测到您尚未安装客户端软件,现在安装吗?<br>(或从网页进入会议室所需的组件未安装或未启动，若已安装请刷新后再试！
var PROMOTION = 124;  //提示
var SETUP_ONLINE = 125;  //Web在线安装(只支持IE内核浏览器)
var DOWNLOAD_INSTALLATION_PACKAGE = 126;  //下载安装包
var CANCEL = 127;  //取消
var COPY_SUCCESS = 128;  //复制成功
var ERR_WRONG_BROWSER = 129;  //浏览器不支持该操作
var ERR_NO_WMVPLAY = 130;  //wmplayer播放器未安装,是否打开下载页面
var ERR_NO_REALPLAY = 131;  //realplay播放器未安装,是否打开下载页面
var ERR_NO_CONFID = 132;  //会议室ID不能为空
var ERR_ACCOUNT_OR_PASS_ERROR = 133;  //用户名或密码错误
var IMAGE_FILE = 134;  //图像文件
var DAY = 135; //天
var HOUR = 136; //小时
var MINUTE = 137; //分
var SECOND = 138; //秒
var INVITE_BY_INTERNET = 139; //网络邀请
var INVITE_BY_PHONE = 140; //电话加入
var OTHER = 141; //其他
var NORMAL = 142; //正常
var MUTE = 143; //静音
var NO_CONNECTED = 144; //未连接
var CONNECTED = 145; //已连接
var CALLING = 146; //呼叫中
var NO_RECORD_SELECTED = 147; //没有记录选中
var ADD_PHONEUSER = 148; //添加电话会议联系人
var EDIT_PHONEUSER = 149; //编辑电话会议联系人
var NO_USER_SELECTED = 150; //没有用户选中
var NO_PHONE_NUMBER = 151;  //电话号码不能为空
var NO_AVAILABLE_VCODE = 152; //请先生成可用验证码
var SEEGLE_PHONE_CONF = 153; //欢迎使用视高免费电话会议系统
var DAIL_PHONE = 154; //请您拨打电话
var INPUT_VCODE = 155; //并输入验证码
var LOGIN_CONF = 156; //参加会议
var INVITE_BY_MSG = 157; //短信邀请
var REPEAT_SUBMIT = 158; //上次请求仍在进行中，不可重复提交
var ADD_VCODE = 159; //添加验证码
var USER_IMPORT = 160; //用户导入
var USER_IMPORTING = 161; //用户导入中
var EDIT_PHONE_CONF = 162; //修改电话会议
var UPDATE = 163; //更新
var NO_VCODE = 164; //请输入验证码
var ERR_MAX_LENGTH = 165; //验证码最大长度为12位
var CAN_NOT_EDIT_AUTO = 166; //自动创建的验证码无法修改
var EDIT_VCODE = 167; //修改验证码
var ERROR_NOTFIND = 168;//错误信息没有找到
var ERR_REBUILD_FAILURE = 169;//重新生成验证码失败，请刷新后重试！
var ERR_OPERUSER_ACCOUNT = 170;//操作员帐号错误
var ERR_OPERUSER_ALIAS = 171;//操作员昵称错误
var ERR_OPERUSER_PHONE = 172;//操作员昵称错误
var OPERUSER_TITLE = 173;//操作员昵称错误
var CONFIRM_CANCEL = 174;//确认取消吗！
var PHONE_CHARGE_COUNTDATA = 175;//扣费数据条数！
var PHONE_CHARGE_COUNTTIME = 176;//计费时长！
var PHONE_CHARGE_BEGINTIME = 177;//扣费时间段开始！
var PHONE_CHARGE_ENDTIME = 178;//扣费时间段结束！
var PHONE_CHARGE_NOEXCESS = 179;//超额计费未启用
var PHONE_CHARGE_STARTEXCESS = 180;//超额计费已启用，超额单价为
var PHONE_CHARGE_PRICEUNIT = 181;//元/分钟
var PHONE_CHARGE_PAYMONEYFORMATERROR = 182;//充值金额格式错误
var PHONE_CHARGE_LESSMONEYFORMATERROR = 183;//优惠金额格式错误
var PHONE_CHARGE_PRICEFORMATERROR = 184;//单价格式错误
var PHONE_CHARGE_EXCESSFORMATERROR = 185;//超额计费单价格式错误
var PHONE_CHARGE_REMINDMONEYFORMATERROR = 186;//欠费提醒金额格式错误
var PHONE_CHARGE_EMAILFORMATERROR = 187;//邮箱格式错误
var BACKUP_BACKUPNAMEERROR = 188;//邮箱格式错误

StringRes_en[IMAGE_FILE] = 'IMAGE FILE';
StringRes_zh_cn[IMAGE_FILE] = '图像文件';
StringRes_zh_tw[IMAGE_FILE] = '圖像文件';

StringRes_en[ERR_NO_ORGID] = 'Units of account can not be empty!';
StringRes_zh_cn[ERR_NO_ORGID] = '单位账号不能为空！';
StringRes_zh_tw[ERR_NO_ORGID] = '單位賬號不能為空！';

StringRes_en[ERR_ORGID_ERROR] = 'Please input the correct unit account!';
StringRes_zh_cn[ERR_ORGID_ERROR] = '请输入正确的单位账号！';
StringRes_zh_tw[ERR_ORGID_ERROR] = '請輸入正確的單位賬號！';

StringRes_en[ERR_NO_USERID] = 'User accounts can not be empty！';
StringRes_zh_cn[ERR_NO_USERID] = '用户账号不能为空！';
StringRes_zh_tw[ERR_NO_USERID] = '用戶賬號不能為空！';

StringRes_en[NO_DIFINE_ERROR_MSG] = 'Undefined error message';
StringRes_zh_cn[NO_DIFINE_ERROR_MSG] = '未定义的错误信息';
StringRes_zh_tw[NO_DIFINE_ERROR_MSG] = '未定義的錯誤信息';

StringRes_en[ERROR_CODE] = 'Error Codes';
StringRes_zh_cn[ERROR_CODE] = '错误代码';
StringRes_zh_tw[ERROR_CODE] = '錯誤代碼';

StringRes_en[ERR_NO_IMAGE] = 'Please select the pictures you want to upload logo!';
StringRes_zh_cn[ERR_NO_IMAGE] = '请选择要上传logo图片！';
StringRes_zh_tw[ERR_NO_IMAGE] = '請選擇要上傳logo圖片！';

StringRes_en[ERR_IMAGE_ERROR] = 'Please re-select image upload. Upload file type must be jpg, png, gif or bmp!';
StringRes_zh_cn[ERR_IMAGE_ERROR] = '请重新选择图片上传.上传文件类型必须为jpg、png、gif或者bmp！';
StringRes_zh_tw[ERR_IMAGE_ERROR] = '請重新選擇圖片上傳.上​​傳文件類型必須為jpg、png、gif或者bmp！ ';

StringRes_en[LOGO_MOD_SUSCESS] = 'logo changed successfully!';
StringRes_zh_cn[LOGO_MOD_SUSCESS] = 'logo修改成功！';
StringRes_zh_tw[LOGO_MOD_SUSCESS] = 'logo修改成功！';

StringRes_en[LOGO_IS_DEFAULT] = 'This is already the default logo!';
StringRes_zh_cn[LOGO_IS_DEFAULT] = '当前已经为默认logo！';
StringRes_zh_tw[LOGO_IS_DEFAULT] = '當前已經為默認logo！';

StringRes_en[LOGO_MOD_FAILURE] = 'logo modification fails!';
StringRes_zh_cn[LOGO_MOD_FAILURE] = 'logo修改失败！';
StringRes_zh_tw[LOGO_MOD_FAILURE] = 'logo修改失敗！';

StringRes_en[ERR_ORGID_IS_NUM] = 'Units of account must be a number!';
StringRes_zh_cn[ERR_ORGID_IS_NUM] = '单位账号必须为数字!';
StringRes_zh_tw[ERR_ORGID_IS_NUM] = '單位賬號必須為數字!';

StringRes_en[REG_SUSCESS] = 'Registered success!';
StringRes_zh_cn[REG_SUSCESS] = '企业注册成功！';
StringRes_zh_tw[REG_SUSCESS] = '企業註冊成功！';

StringRes_en[REG_FAILURE] = 'Corporate registration failed!';
StringRes_zh_cn[REG_FAILURE] = '企业注册失败！';
StringRes_zh_tw[REG_FAILURE] = '企業註冊失敗！';

StringRes_en[REG_GET_TOKEN_FAILURE] = 'Get token fails, check the corporate ID and password management!';
StringRes_zh_cn[REG_GET_TOKEN_FAILURE] = '获取token失败，请检查企业ID和管理密码！';
StringRes_zh_tw[REG_GET_TOKEN_FAILURE] = '獲取token失敗，請檢查企業ID和管理密碼！';

StringRes_en[REG_ADD_ADMIN_FAILURE] = 'Add super administrator failed!';
StringRes_zh_cn[REG_ADD_ADMIN_FAILURE] = '添加超级管理员失败！';
StringRes_zh_tw[REG_ADD_ADMIN_FAILURE] = '添加超級管理員失敗！';

StringRes_en[REG_CREATE_TABLE_FAILURE] = 'Creating orgid_user table failed!';
StringRes_zh_cn[REG_CREATE_TABLE_FAILURE] = '创建orgid_user表失败！';
StringRes_zh_tw[REG_CREATE_TABLE_FAILURE] = '創建orgid_user表失敗！';

StringRes_en[REG_TABLE_EXIST] = 'Enterprises already exists!';
StringRes_zh_cn[REG_TABLE_EXIST] = '企业已经存在！';
StringRes_zh_tw[REG_TABLE_EXIST] = '企業已經存在！';

StringRes_en[REG_NO_COMPANY_INFORMATION] = 'Central server within the enterprise to obtain information!';
StringRes_zh_cn[REG_NO_COMPANY_INFORMATION] = '中心服务器中获取不到该企业信息！';
StringRes_zh_tw[REG_NO_COMPANY_INFORMATION] = '中心服務器中獲取不到該企業信息！';

StringRes_en[ERR_NO_EMAIL_HOST] = 'E-mail authentication server can not be empty!';
StringRes_zh_cn[ERR_NO_EMAIL_HOST] = '邮箱认证服务器不能为空！';
StringRes_zh_tw[ERR_NO_EMAIL_HOST] = '郵箱認證服務器不能為空！';

StringRes_en[ERR_NO_SENDER_ALIAS] = 'From nickname can not be empty!';
StringRes_zh_cn[ERR_NO_SENDER_ALIAS] = '发件人昵称不能为空！';
StringRes_zh_tw[ERR_NO_SENDER_ALIAS] = '發件人暱稱不能為空！';

StringRes_en[ERR_NO_HOST_PORT] = 'Authentication server port can not be empty!';
StringRes_zh_cn[ERR_NO_HOST_PORT] = '认证服务器端口不能为空！';
StringRes_zh_tw[ERR_NO_HOST_PORT] = '認證服務器端口不能為空！';

StringRes_en[ERR_NO_EMAIL_ACCOUNT] = 'E-mail login accounts can not be empty!';
StringRes_zh_cn[ERR_NO_EMAIL_ACCOUNT] = '邮箱登录账户不能为空！';
StringRes_zh_tw[ERR_NO_EMAIL_ACCOUNT] = '郵箱登錄賬戶不能為空！';

StringRes_en[ERR_NO_PASSWORD] = 'Password can not be empty！';
StringRes_zh_cn[ERR_NO_PASSWORD] = '密码不能为空！';
StringRes_zh_tw[ERR_NO_PASSWORD] = '密碼不能為空！';

StringRes_en[REG_PASSWORD_UNPAIR] = 'Enter the password twice inconsistent!';
StringRes_zh_cn[REG_PASSWORD_UNPAIR] = '两次输入密码不一致！';
StringRes_zh_tw[REG_PASSWORD_UNPAIR] = '兩次輸入密碼不一致！';

StringRes_en[ERR_PORT_ERROR] = 'Please enter the correct port number!';
StringRes_zh_cn[ERR_PORT_ERROR] = '请输入正确的端口号！';
StringRes_zh_tw[ERR_PORT_ERROR] = '請輸入正確的端口號！';

StringRes_en[ERR_EMAIL_ADDRESS_ERROR] = 'Please enter a valid email address!';
StringRes_zh_cn[ERR_EMAIL_ADDRESS_ERROR] = '请输入正确的邮箱地址！';
StringRes_zh_tw[ERR_EMAIL_ADDRESS_ERROR] = '請輸入正確的郵箱地址！';

StringRes_en[ERR_CURRENT_USER_UNEXIST] = 'The current user does not exist, please re-login!';
StringRes_zh_cn[ERR_CURRENT_USER_UNEXIST] = '当前用户不存在,请重新登录！';
StringRes_zh_tw[ERR_CURRENT_USER_UNEXIST] = '當前用戶不存在,請重新登錄！';

StringRes_en[OPERATION_SUCESS] = 'Successful operation!';
StringRes_zh_cn[OPERATION_SUCESS] = '操作成功！';
StringRes_zh_tw[OPERATION_SUCESS] = '操作成功！';

StringRes_en[ERR_EMAIL_CONFIG_ERROR] = 'Mail is configured incorrectly, the test failed login!';
StringRes_zh_cn[ERR_EMAIL_CONFIG_ERROR] = '邮件配置不正确，登录测试失败！';
StringRes_zh_tw[ERR_EMAIL_CONFIG_ERROR] = '郵件配置不正確，登錄測試失敗！';

StringRes_en[EMAIL_CONFIG_SAVE_FAILURE] = 'Mail configuration is not saved successfully!';
StringRes_zh_cn[EMAIL_CONFIG_SAVE_FAILURE] = '邮件配置未保存成功！';
StringRes_zh_tw[EMAIL_CONFIG_SAVE_FAILURE] = '郵件配置未保存成功！';

StringRes_en[ERR_NO_PERMISSION] = 'No such authority to operate！';
StringRes_zh_cn[ERR_NO_PERMISSION] = '无此操作权限！';
StringRes_zh_tw[ERR_NO_PERMISSION] = '無此操作權限！';

StringRes_en[OPERATION_FAILURE] = 'Operation failed!';
StringRes_zh_cn[OPERATION_FAILURE] = '操作失败！';
StringRes_zh_tw[OPERATION_FAILURE] = '操作失敗！';

StringRes_en[ERR_OLD_PASS_ERROR] = 'Enter the old password wrong!';
StringRes_zh_cn[ERR_OLD_PASS_ERROR] = '旧密码输入错误！';
StringRes_zh_tw[ERR_OLD_PASS_ERROR] = '舊密碼輸入錯誤！';

StringRes_en[ERR_NEW_PASS_UNPAIR] = 'Enter the new password twice inconsistencies, please re-enter!';
StringRes_zh_cn[ERR_NEW_PASS_UNPAIR] = '两次输入的新密码不一致，请重新输入！';
StringRes_zh_tw[ERR_NEW_PASS_UNPAIR] = '兩次輸入的新密碼不一致，請重新輸入！';

StringRes_en[ERR_PASS_SAME] = 'Enter the new password and the old password, please re-enter!';
StringRes_zh_cn[ERR_PASS_SAME] = '输入的新密码和旧密码相同，请重新输入！';
StringRes_zh_tw[ERR_PASS_SAME] = '輸入的新密碼和舊密碼相同，請重新輸入！';

StringRes_en[ERR_PASS_LENGTH] = 'The new password must not exceed 12 characters!';
StringRes_zh_cn[ERR_PASS_LENGTH] = '新密码不得超过12个字！';
StringRes_zh_tw[ERR_PASS_LENGTH] = '新密碼不得超過12個字！';

StringRes_en[MOD_SUCESS] = 'Successfully modified!';
StringRes_zh_cn[MOD_SUCESS] = '修改成功！';
StringRes_zh_tw[MOD_SUCESS] = '修改成功！';

StringRes_en[MOD_FAILURE] = 'Modify failed!';
StringRes_zh_cn[MOD_FAILURE] = '修改失败！';
StringRes_zh_tw[MOD_FAILURE] = '修改失敗！';

StringRes_en[ADDRESSEE_EXAMPLE] = "Recipient's mailbox separated by commas, such as:zhang@seegle.com,gong@seegle.com";
StringRes_zh_cn[ADDRESSEE_EXAMPLE] = '收件人邮箱以逗号隔开，如：zhang@seegle.com,gong@seegle.com';
StringRes_zh_tw[ADDRESSEE_EXAMPLE] = '收件人郵箱以逗號隔開，如：zhang@seegle.com,gong@seegle.com';

StringRes_en[CONF_URL] = 'Conference link:';
StringRes_zh_cn[CONF_URL] = '会议链接：';
StringRes_zh_tw[CONF_URL] = '會議鏈接：';

StringRes_en[SELECT_ADDRESSEE] = 'Select Recipients';
StringRes_zh_cn[SELECT_ADDRESSEE] = '选择收件人';
StringRes_zh_tw[SELECT_ADDRESSEE] = '選擇收件人';

StringRes_en[ERR_EMAIL_ADDRESS_ERROR] = 'Please enter a valid email address!';
StringRes_zh_cn[ERR_EMAIL_ADDRESS_ERROR] = '请输入正确的邮箱地址！';
StringRes_zh_tw[ERR_EMAIL_ADDRESS_ERROR] = '請輸入正確的郵箱地址！';

StringRes_en[ERR_NO_ADDRESSEE] = 'Please fill in the recipient!';
StringRes_zh_cn[ERR_NO_ADDRESSEE] = '请填写收件人！';
StringRes_zh_tw[ERR_NO_ADDRESSEE] = '請填寫收件人！';

StringRes_en[ERR_NO_EMAIL_TITLE] = 'Message headers can not be empty!';
StringRes_zh_cn[ERR_NO_EMAIL_TITLE] = '邮件标题不能为空！';
StringRes_zh_tw[ERR_NO_EMAIL_TITLE] = '郵件標題不能為空！';

StringRes_en[ERR_EMAIL_TITLE_ERROR] = 'Message headers can not have spaces!';
StringRes_zh_cn[ERR_EMAIL_TITLE_ERROR] = '邮件标题不能有空格！';
StringRes_zh_tw[ERR_EMAIL_TITLE_ERROR] = '郵件標題不能有空格！';

StringRes_en[ERR_NO_EMAIL_CONTENT] = 'Content can not be empty!';
StringRes_zh_cn[ERR_NO_EMAIL_CONTENT] = '内容不能为空！';
StringRes_zh_tw[ERR_NO_EMAIL_CONTENT] = '內容不能為空！';

StringRes_en[EMAIL_SEND_SUCESS] = 'Message has been sent!';
StringRes_zh_cn[EMAIL_SEND_SUCESS] = '邮件已发出！';
StringRes_zh_tw[EMAIL_SEND_SUCESS] = '郵件已發出！';

StringRes_en[EMAIL_SEND_FAILURE] = 'Mail sent wrong!';
StringRes_zh_cn[EMAIL_SEND_FAILURE] = '邮件发送出错！';
StringRes_zh_tw[EMAIL_SEND_FAILURE] = '郵件發送出錯！';

StringRes_en[ERR_NO_COMPANY_NAME] = 'Please enter a new name!';
StringRes_zh_cn[ERR_NO_COMPANY_NAME] = '请输入新名称！';
StringRes_zh_tw[ERR_NO_COMPANY_NAME] = '請輸入新名稱！';

StringRes_en[ERR_COMPANY_NAME_LENGTH] = 'Company name not more than 32!';
StringRes_zh_cn[ERR_COMPANY_NAME_LENGTH] = '企业名称不能超过32位！';
StringRes_zh_tw[ERR_COMPANY_NAME_LENGTH] = '企業名稱不能超過32位！';

StringRes_en[ERR_NO_CONFNAME] = 'Conference names are not allowed to empty!';
StringRes_zh_cn[ERR_NO_CONFNAME] = '会议名称不允许为空！';
StringRes_zh_tw[ERR_NO_CONFNAME] = '會議名稱不允許為空！';

StringRes_en[ERR_NON_STANDARD_CHARACTERS] = 'Conference Name letters a ~ z (case insensitive), Chinese characters, numbers 0 to 9, the minus sign or underscore, only to numbers, characters, or letters at the beginning and end!';
StringRes_zh_cn[ERR_NON_STANDARD_CHARACTERS] = '会议名称由字母a～z(不区分大小写)、汉字、数字0～9、减号或下划线组成,只能以数字、汉字、或字母开头和结尾！';
StringRes_zh_tw[ERR_NON_STANDARD_CHARACTERS] = '會議名稱由字母a～z(不區分大小寫)、漢字、數字0～9、減號或下劃線組成,只能以數字、漢字、或字母開頭和結尾！';

StringRes_en[ERR_CONFPASS_UNPAIR] = 'Enter the meeting password twice inconsistent!';
StringRes_zh_cn[ERR_CONFPASS_UNPAIR] = '会议密码两次输入不一致！';
StringRes_zh_tw[ERR_CONFPASS_UNPAIR] = '會議密碼兩次輸入不一致！';

StringRes_en[ERR_MANAGEPASS_UNPAIR] = 'Conference Management typed two different passwords!';
StringRes_zh_cn[ERR_MANAGEPASS_UNPAIR] = '会议管理密码两次输入不一致！';
StringRes_zh_tw[ERR_MANAGEPASS_UNPAIR] = '會議管理密碼兩次輸入不一致！';

StringRes_en[ERR_NO_MAXCONFUSER] = 'Please enter the maximum number of participants!';
StringRes_zh_cn[ERR_NO_MAXCONFUSER] = '请输入最大与会人数！';
StringRes_zh_tw[ERR_NO_MAXCONFUSER] = '請輸入最大與會人數！';

StringRes_en[ERR_MAXCONFUSER_ERROR] = 'Number of participants must be a positive integer!';
StringRes_zh_cn[ERR_MAXCONFUSER_ERROR] = '与会人数必须为正整数！';
StringRes_zh_tw[ERR_MAXCONFUSER_ERROR] = '與會人數必須為正整數！';

StringRes_en[ERR_MAXCONFUSER_RENGE_ERROR] = 'Maximum number of participants must be between 1 - 1000 within!';
StringRes_zh_cn[ERR_MAXCONFUSER_RENGE_ERROR] = '最大与会人数必须在1--1000以内！';
StringRes_zh_tw[ERR_MAXCONFUSER_RENGE_ERROR] = '最大與會人數必須在1--1000以內！';

StringRes_en[ERR_NO_SPOKESMAN] = 'Please enter the number of the Chairman!';
StringRes_zh_cn[ERR_NO_SPOKESMAN] = '请输入主席人数！';
StringRes_zh_tw[ERR_NO_SPOKESMAN] = '請輸入主席人數！';

StringRes_en[ERR_SPOKESMAN_ERROR] = 'The President must be a positive integer number!';
StringRes_zh_cn[ERR_SPOKESMAN_ERROR] = '主席人数必须为正整数！';
StringRes_zh_tw[ERR_SPOKESMAN_ERROR] = '主席人數必須為正整數！';

StringRes_en[ERR_SPOKESMAN_RENGE_ERROR] = 'The maximum number of the Chairman must be 1 - 20 or less!';
StringRes_zh_cn[ERR_SPOKESMAN_RENGE_ERROR] = '最大主席人数必须在1--20以内！';
StringRes_zh_tw[ERR_SPOKESMAN_RENGE_ERROR] = '最大主席人數必須在1--20以內！';

StringRes_en[ERR_SPOKESMAN_BIGER] = 'The number can not be greater than the maximum number of participants chairman!';
StringRes_zh_cn[ERR_SPOKESMAN_BIGER] = '主席人数不能大于最大与会者人数！';
StringRes_zh_tw[ERR_SPOKESMAN_BIGER] = '主席人數不能大於最大與會者人數！';

StringRes_en[ERR_NO_TOURIST] = 'Please enter the number of visitors!';
StringRes_zh_cn[ERR_NO_TOURIST] = '请输入游客人数！';
StringRes_zh_tw[ERR_NO_TOURIST] = '請輸入遊客人數！';

StringRes_en[ERR_TOURIST_ERROR] = 'Visitors must be a positive integer!';
StringRes_zh_cn[ERR_TOURIST_ERROR] = '游客人数必须为正整数！';
StringRes_zh_tw[ERR_TOURIST_ERROR] = '遊客人數必須為正整數！';

StringRes_en[ERR_TOURIST_RENGE_ERROR] = 'The maximum number of visitors must be between 0 - 1000 within!';
StringRes_zh_cn[ERR_TOURIST_RENGE_ERROR] = '最大游客人数必须在0--1000以内！';
StringRes_zh_tw[ERR_TOURIST_RENGE_ERROR] = '最大遊客人數必須在0--1000以內！';

StringRes_en[ERR_TOURIST_BIGER] = 'Visitors can not exceed the number of participants!';
StringRes_zh_cn[ERR_TOURIST_BIGER] = '游客人数不能多于与会人数！';
StringRes_zh_tw[ERR_TOURIST_BIGER] = '遊客人數不能多於與會人數！';

StringRes_en[ERR_DESCRIPTION_LENGTH] = 'Conference description should not exceed 100 words!';
StringRes_zh_cn[ERR_DESCRIPTION_LENGTH] = '会议描述不得超过100个字！';
StringRes_zh_tw[ERR_DESCRIPTION_LENGTH] = '會議描述不得超過100個字！';

StringRes_en[ERR_NO_CONFGROUP] = 'Please select the type of cluster meeting!';
StringRes_zh_cn[ERR_NO_CONFGROUP] = '请选择会议集群类型！';
StringRes_zh_tw[ERR_NO_CONFGROUP] = '請選擇會議集群類型！';

StringRes_en[ERR_CONFIRM_ADD] = 'The current meeting room to allow the maximum number of hours has exceeded the maximum number of concurrent users, you sure you want to increase it?';
StringRes_zh_cn[ERR_CONFIRM_ADD] = '当前时段会议室允许最大用户数已超过最大并发数，确定要增加吗？';
StringRes_zh_tw[ERR_CONFIRM_ADD] = '當前時段會議室允許最大用戶數已超過最大並發數，確定要增加嗎？';

StringRes_en[ERR_CONFADD_REFUSED] = 'The current meeting room to allow the maximum number of hours has exceeded the maximum number of concurrent users allowed to increase!';
StringRes_zh_cn[ERR_CONFADD_REFUSED] = '当前时段会议室允许最大用户数已超过最大并发数,不允许增加！';
StringRes_zh_tw[ERR_CONFADD_REFUSED] = '當前時段會議室允許最大用戶數已超過最大並發數,不允許增加！';

StringRes_en[ADD_SUCESS] = 'Add a success!';
StringRes_zh_cn[ADD_SUCESS] = '添加成功！';
StringRes_zh_tw[ADD_SUCESS] = '添加成功！';

StringRes_en[ADD_FAILURE] = 'Add to fail!';
StringRes_zh_cn[ADD_FAILURE] = '添加失败！';
StringRes_zh_tw[ADD_FAILURE] = '添加失敗！';

StringRes_en[CANCEL_CONFADMIN_SUCESS] = 'Cancel Meeting Room Manager successfully!';
StringRes_zh_cn[CANCEL_CONFADMIN_SUCESS] = '取消会议室管理员成功！';
StringRes_zh_tw[CANCEL_CONFADMIN_SUCESS] = '取消會議室管理員成功！';

StringRes_en[CANCEL_CONFADMIN_FAILURE] = 'Cancel the meeting room administrators failed!';
StringRes_zh_cn[CANCEL_CONFADMIN_FAILURE] = '取消会议室管理员失败！';
StringRes_zh_tw[CANCEL_CONFADMIN_FAILURE] = '取消會議室管理員失敗！';

StringRes_en[ERR_NO_USER_SELECTED] = 'Not selected users!';
StringRes_zh_cn[ERR_NO_USER_SELECTED] = '未选择用户！';
StringRes_zh_tw[ERR_NO_USER_SELECTED] = '未選擇用戶！';

StringRes_en[CONFIRM_DELETE] = 'Confirm delete?';
StringRes_zh_cn[CONFIRM_DELETE] = '确认删除吗？';
StringRes_zh_tw[CONFIRM_DELETE] = '確認刪除嗎？';

StringRes_en[ERR_NO_CONFADMIN_SELECTED] = 'Please select a video conference administrator';
StringRes_zh_cn[ERR_NO_CONFADMIN_SELECTED] = '请选择视频会议管理员';
StringRes_zh_tw[ERR_NO_CONFADMIN_SELECTED] = '請選擇視頻會議管理員';

StringRes_en[CANCEL_CONFCOMMON_SUCESS] = 'Cancel Default successful participants！';
StringRes_zh_cn[CANCEL_CONFCOMMON_SUCESS] = '取消默认与会者成功！';
StringRes_zh_tw[CANCEL_CONFCOMMON_SUCESS] = '取消默認與會者成功！';

StringRes_en[CANCEL_CONFCOMMON_FAILURE] = 'Cancel Default participants failed!';
StringRes_zh_cn[CANCEL_CONFCOMMON_FAILURE] = '取消默认与会者失败！';
StringRes_zh_tw[CANCEL_CONFCOMMON_FAILURE] = '取消默認與會者失敗！';

StringRes_en[ERR_NO_CONFCOMMON_SELECTED] = 'Please select the default participants';
StringRes_zh_cn[ERR_NO_CONFCOMMON_SELECTED] = '请选择默认与会者';
StringRes_zh_tw[ERR_NO_CONFCOMMON_SELECTED] = '請選擇默認與會者';

StringRes_en[DELETE_SUCESS] = 'Deleted successfully!';
StringRes_zh_cn[DELETE_SUCESS] = '删除成功！';
StringRes_zh_tw[DELETE_SUCESS] = '刪除成功！';

StringRes_en[DELETE_FAILURE] = 'Delete failed!';
StringRes_zh_cn[DELETE_FAILURE] = '删除失败！';
StringRes_zh_tw[DELETE_FAILURE] = '刪除失敗！';

StringRes_en[CANCEL_TOP_SUCESS] = 'Unstuck success!';
StringRes_zh_cn[CANCEL_TOP_SUCESS] = '取消置顶成功！';
StringRes_zh_tw[CANCEL_TOP_SUCESS] = '取消置頂成功！';

StringRes_en[CANCEL_TOP_FAILURE] = 'Unstuck failed!';
StringRes_zh_cn[CANCEL_TOP_FAILURE] = '取消置顶失败！';
StringRes_zh_tw[CANCEL_TOP_FAILURE] = '取消置頂失敗！';

StringRes_en[CONFIRM_CANCEL_TOP] = 'Confirm Unstuck it?';
StringRes_zh_cn[CONFIRM_CANCEL_TOP] = '确认取消置顶吗？';
StringRes_zh_tw[CONFIRM_CANCEL_TOP] = '確認取消置頂嗎？';

StringRes_en[SET_TOP_SUCESS] = 'Top success!';
StringRes_zh_cn[SET_TOP_SUCESS] = '置顶成功！';
StringRes_zh_tw[SET_TOP_SUCESS] = '置頂成功！';

StringRes_en[SET_TOP_FAILURE] = 'Sticky failed!';
StringRes_zh_cn[SET_TOP_FAILURE] = '置顶失败！';
StringRes_zh_tw[SET_TOP_FAILURE] = '置頂失敗！';

StringRes_en[CONFIRM_SET_TOP] = 'Top confirm it?';
StringRes_zh_cn[CONFIRM_SET_TOP] = '确认置顶吗？';
StringRes_zh_tw[CONFIRM_SET_TOP] = '確認置頂嗎？';

StringRes_en[ERR_NO_CONF_SELECTED] = 'Not selected conference!';
StringRes_zh_cn[ERR_NO_CONF_SELECTED] = '未选择会议！';
StringRes_zh_tw[ERR_NO_CONF_SELECTED] = '未選擇會議！';

StringRes_en[DELETE_ALL_SUCESS] = 'Batch deleted successfully!';
StringRes_zh_cn[DELETE_ALL_SUCESS] = '批量删除成功！';
StringRes_zh_tw[DELETE_ALL_SUCESS] = '批量刪除成功！';

StringRes_en[DELETE_ALL_FAILURE] = 'Batch Delete failed!';
StringRes_zh_cn[DELETE_ALL_FAILURE] = '批量删除失败！';
StringRes_zh_tw[DELETE_ALL_FAILURE] = '批量刪除失敗！';

StringRes_en[SAVE_SUCESS] = 'Successfully saved!';
StringRes_zh_cn[SAVE_SUCESS] = '保存成功！';
StringRes_zh_tw[SAVE_SUCESS] = '保存成功！';

StringRes_en[SAVE_FAILURE] = 'Save failed!';
StringRes_zh_cn[SAVE_FAILURE] = '保存失败！';
StringRes_zh_tw[SAVE_FAILURE] = '保存失敗！';

StringRes_en[YEAR] = '/';
StringRes_zh_cn[YEAR] = '年';
StringRes_zh_tw[YEAR] = '年';

StringRes_en[MONTH] = '/';
StringRes_zh_cn[MONTH] = '月';
StringRes_zh_tw[MONTH] = '月';

StringRes_en[DATE] = '';
StringRes_zh_cn[DATE] = '日';
StringRes_zh_tw[DATE] = '日';

StringRes_en[CANCEL_ADMIN_SUCCESS] = 'Cancel Meeting administrator success!';
StringRes_zh_cn[CANCEL_ADMIN_SUCCESS] = '取消会议管理员成功！';
StringRes_zh_tw[CANCEL_ADMIN_SUCCESS] = '取消會議管理員成功！';

StringRes_en[CANCEL_ADMIN_FAILURE] = 'Cancel Meeting administrators failed!';
StringRes_zh_cn[CANCEL_ADMIN_FAILURE] = '取消会议管理员失败！';
StringRes_zh_tw[CANCEL_ADMIN_FAILURE] = '取消會議管理員失敗！';

StringRes_en[ERR_NO_ADMIN_SELECTED] = 'Please select the conference administrator';
StringRes_zh_cn[ERR_NO_ADMIN_SELECTED] = '请选择会议管理员';
StringRes_zh_tw[ERR_NO_ADMIN_SELECTED] = '請選擇會議管理員';

StringRes_en[ERR_NO_USERALIAS] = 'Please enter a nickname!';
StringRes_zh_cn[ERR_NO_USERALIAS] = '请输入昵称！';
StringRes_zh_tw[ERR_NO_USERALIAS] = '請輸入暱稱！';

StringRes_en[ERR_USERALIAS_LENGTH_ERROR] = 'Nickname not over 32!';
StringRes_zh_cn[ERR_USERALIAS_LENGTH_ERROR] = '昵称不能超过32位！';
StringRes_zh_tw[ERR_USERALIAS_LENGTH_ERROR] = '暱稱不能超過32位！';

StringRes_en[ERR_USERALIAS_STANDARD] = 'Nickname from the Chinese, letters, numbers, or underscores minus sign, only to Chinese, letters, or numbers at the beginning and end!';
StringRes_zh_cn[ERR_USERALIAS_STANDARD] = '昵称由中文、字母、数字、减号或下划线组成,只能以中文、字母、或数字开头和结尾！';
StringRes_zh_tw[ERR_USERALIAS_STANDARD] = '暱稱由中文、字母、數字、減號或下劃線組成,只能以中文、字母、或數字開頭和結尾！';

StringRes_en[ERR_EMAIL_LENGTH] = 'Mailbox name is too long!';
StringRes_zh_cn[ERR_EMAIL_LENGTH] = '邮箱名称长度过长！';
StringRes_zh_tw[ERR_EMAIL_LENGTH] = '郵箱名稱長度過長！';

StringRes_en[ERR_EMAIL_ERROR] = 'Please enter a valid email address!';
StringRes_zh_cn[ERR_EMAIL_ERROR] = '请输入正确的邮箱地址！';
StringRes_zh_tw[ERR_EMAIL_ERROR] = '請輸入正確的郵箱地址！';

StringRes_en[ERR_PHONENUMBER_ERROR] = 'Please enter the correct phone number!';
StringRes_zh_cn[ERR_PHONENUMBER_ERROR] = '请输入正确的电话号码！';
StringRes_zh_tw[ERR_PHONENUMBER_ERROR] = '請輸入正確的電話號碼！';

StringRes_en[SET_ADMIN_SUCCESS] = 'Set administrator success!';
StringRes_zh_cn[SET_ADMIN_SUCCESS] = '设置管理员成功！';
StringRes_zh_tw[SET_ADMIN_SUCCESS] = '設置管理員成功！';

StringRes_en[SET_ADMIN_FAILURE] = 'Set the Administrator failed!';
StringRes_zh_cn[SET_ADMIN_FAILURE] = '设置管理员失败！';
StringRes_zh_tw[SET_ADMIN_FAILURE] = '設置管理員失敗！';

StringRes_en[ERR_USERACCOUNT_STANDARD] = 'Login account by the letter a ~ z (case insensitive), numbers 0 to 9, the minus sign or underscore, only to numbers or letters at the beginning and end!';
StringRes_zh_cn[ERR_USERACCOUNT_STANDARD] = '登录账号由字母a～z(不区分大小写)、数字0～9、减号或下划线组成,只能以数字、或字母开头和结尾！';
StringRes_zh_tw[ERR_USERACCOUNT_STANDARD] = '登錄賬號由字母a～z(不區分大小寫)、數字0～9、減號或下劃線組成,只能以數字、或字母開頭和結尾！';

StringRes_en[ERR_USERNAME_STANDARD] = 'Nickname letters a ~ z (case insensitive), Chinese characters, numbers 0 to 9, the minus sign or underscore, only to numbers, characters, or letters at the beginning and end!';
StringRes_zh_cn[ERR_USERNAME_STANDARD] = '昵称由字母a～z(不区分大小写)、汉字、数字0～9、减号或下划线组成,只能以数字、汉字、或字母开头和结尾！';
StringRes_zh_tw[ERR_USERNAME_STANDARD] = '暱稱由字母a～z(不區分大小寫)、漢字、數字0～9、減號或下劃線組成,只能以數字、漢字、或字母開頭和結尾！';

StringRes_en[ERR_NO_PROXY_ADDRESS] = 'Please enter the proxy server address!';
StringRes_zh_cn[ERR_NO_PROXY_ADDRESS] = '请输入代理服务器地址！';
StringRes_zh_tw[ERR_NO_PROXY_ADDRESS] = '請輸入代理服務器地址！';

StringRes_en[ERR_PROXY_ADDRESS_ERROR] = 'Please enter the correct proxy server address!';
StringRes_zh_cn[ERR_PROXY_ADDRESS_ERROR] = '请输入正确的代理服务器地址！';
StringRes_zh_tw[ERR_PROXY_ADDRESS_ERROR] = '請輸入正確的代理服務器地址！';

StringRes_en[ERR_N0_PROXY_PORT] = 'Enter the proxy server port number!';
StringRes_zh_cn[ERR_N0_PROXY_PORT] = '请输入代理服务器端口号！';
StringRes_zh_tw[ERR_N0_PROXY_PORT] = '請輸入代理服務器端口號！';

StringRes_en[ERR_USERACCOUNT_EXIST] = 'User name already exists!';
StringRes_zh_cn[ERR_USERACCOUNT_EXIST] = '用户名已存在！';
StringRes_zh_tw[ERR_USERACCOUNT_EXIST] = '用戶名已存在！';

StringRes_en[CLOSE] = 'Close';
StringRes_zh_cn[CLOSE] = '关闭';
StringRes_zh_tw[CLOSE] = '關閉';

StringRes_en[UPDATE_ALL_SUCESS] = 'Batch Update successful！';
StringRes_zh_cn[UPDATE_ALL_SUCESS] = '批量更新成功！';
StringRes_zh_tw[UPDATE_ALL_SUCESS] = '批量更新成功！';

StringRes_en[UPDATE_ALL_FAILURE] = 'Batch update failed!';
StringRes_zh_cn[UPDATE_ALL_FAILURE] = '批量更新失败！';
StringRes_zh_tw[UPDATE_ALL_FAILURE] = '批量更新失敗！';

StringRes_en[ERR_NO_USER_NUMBER] = 'Please enter the number of users needs to be added!';
StringRes_zh_cn[ERR_NO_USER_NUMBER] = '请输入需要添加用户的数量！';
StringRes_zh_tw[ERR_NO_USER_NUMBER] = '請輸入需要添加用戶的數量！';

StringRes_en[ERR_USER_NUMBER_ERROR] = 'Please enter the correct number from 1 to 999 range!';
StringRes_zh_cn[ERR_USER_NUMBER_ERROR] = '请输入1~999区间的正确数字！';
StringRes_zh_tw[ERR_USER_NUMBER_ERROR] = '請輸入1~999區間的正確數字！';

StringRes_en[CANCEL_USERADMIN_SUCCESS] = 'Cancellation user administrator success!';
StringRes_zh_cn[CANCEL_USERADMIN_SUCCESS] = '取消用户管理员成功！';
StringRes_zh_tw[CANCEL_USERADMIN_SUCCESS] = '取消用戶管理員成功！';

StringRes_en[CANCEL_USERADMIN_FAILURE] = 'Cancellation user administrator failed!';
StringRes_zh_cn[CANCEL_USERADMIN_FAILURE] = '取消用户管理员失败！';
StringRes_zh_tw[CANCEL_USERADMIN_FAILURE] = '取消用戶管理員失敗！';

StringRes_en[ERR_NO_USERADMIN_SELECTED] = 'Please select the User Administrator';
StringRes_zh_cn[ERR_NO_USERADMIN_SELECTED] = '请选择用户管理员';
StringRes_zh_tw[ERR_NO_USERADMIN_SELECTED] = '請選擇用戶管理員';

StringRes_en[VIDEO_FILE] = 'Video files';
StringRes_zh_cn[VIDEO_FILE] = '视频文件';
StringRes_zh_tw[VIDEO_FILE] = '視頻文件';

StringRes_en[SELECT_VIDEO_FILE] = 'Select the video file';
StringRes_zh_cn[SELECT_VIDEO_FILE] = '选择录像文件';
StringRes_zh_tw[SELECT_VIDEO_FILE] = '選擇錄像文件';

StringRes_en[ERR_NO_VIDEO_FILE] = 'Not ready to upload files!';
StringRes_zh_cn[ERR_NO_VIDEO_FILE] = '没有准备上传的文件！';
StringRes_zh_tw[ERR_NO_VIDEO_FILE] = '沒有準備上傳的文件！';

StringRes_en[ERR_NO_SGCONF] = 'Detects that you have not yet installed the client software, install it now? <br> (Or enter from the web meeting room required component is not installed or started, if installed, please refresh and try again!';
StringRes_zh_cn[ERR_NO_SGCONF] = '检测到您尚未安装客户端软件,现在安装吗?<br>(或从网页进入会议室所需的组件未安装或未启动，若已安装请刷新后再试！';
StringRes_zh_tw[ERR_NO_SGCONF] = '檢測到您尚未安裝客戶端軟件,現在安裝嗎?<br>(或從網頁進入會議室所需的組件未安裝或未啟動，若已安裝請刷新後再試！';

StringRes_en[PROMOTION] = 'Prompt';
StringRes_zh_cn[PROMOTION] = '提示';
StringRes_zh_tw[PROMOTION] = '提示';

StringRes_en[SETUP_ONLINE] = 'Web-line installation (only support IE ​​browser kernel)';
StringRes_zh_cn[SETUP_ONLINE] = 'Web在线安装(只支持IE内核浏览器)';
StringRes_zh_tw[SETUP_ONLINE] = 'Web在線安裝(只支持IE內核瀏覽器)';

StringRes_en[DOWNLOAD_INSTALLATION_PACKAGE] = 'Download the installation package';
StringRes_zh_cn[DOWNLOAD_INSTALLATION_PACKAGE] = '下载安装包';
StringRes_zh_tw[DOWNLOAD_INSTALLATION_PACKAGE] = '下載安裝包';

StringRes_en[CANCEL] = 'Cancel';
StringRes_zh_cn[CANCEL] = '取消';
StringRes_zh_tw[CANCEL] = '取消';

StringRes_en[COPY_SUCCESS] = 'Copy success!';
StringRes_zh_cn[COPY_SUCCESS] = '复制成功！';
StringRes_zh_tw[COPY_SUCCESS] = '複製成功！';

StringRes_en[ERR_WRONG_BROWSER] = 'Browser does not support this operation!';
StringRes_zh_cn[ERR_WRONG_BROWSER] = '浏览器不支持该操作！';
StringRes_zh_tw[ERR_WRONG_BROWSER] = '瀏覽器不支持該操作！';

StringRes_en[ERR_NO_WMVPLAY] = 'wmplayer player is not installed, whether to open the download page?';
StringRes_zh_cn[ERR_NO_WMVPLAY] = 'wmplayer播放器未安装,是否打开下载页面？';
StringRes_zh_tw[ERR_NO_WMVPLAY] = 'wmplayer播放器未安裝,是否打開下載頁面？';

StringRes_en[ERR_NO_REALPLAY] = 'realplay player is not installed, whether to open the download page?';
StringRes_zh_cn[ERR_NO_REALPLAY] = 'realplay播放器未安装,是否打开下载页面？';
StringRes_zh_tw[ERR_NO_REALPLAY] = 'realplay播放器未安裝,是否打開下載頁面？';

StringRes_en[ERR_NO_CONFID] = 'Meeting Room ID can not be empty!';
StringRes_zh_cn[ERR_NO_CONFID] = '会议室ID不能为空！';
StringRes_zh_tw[ERR_NO_CONFID] = '會議室ID不能為空！';

StringRes_en[ERR_ACCOUNT_OR_PASS_ERROR] = 'User name or password is incorrect!';
StringRes_zh_cn[ERR_ACCOUNT_OR_PASS_ERROR] = '用户名或密码错误！';
StringRes_zh_tw[ERR_ACCOUNT_OR_PASS_ERROR] = '用戶名或密碼錯誤！';

StringRes_en[DAY] = '/';
StringRes_zh_cn[DAY] = '天';
StringRes_zh_tw[DAY] = '天';

StringRes_en[HOUR] = '/';
StringRes_zh_cn[HOUR] = '小时';
StringRes_zh_tw[HOUR] = '小時';

StringRes_en[MINUTE] = '/';
StringRes_zh_cn[MINUTE] = '分';
StringRes_zh_tw[MINUTE] = '';

StringRes_en[SECOND] = 's';
StringRes_zh_cn[SECOND] = '秒';
StringRes_zh_tw[SECOND] = '秒';

StringRes_en[INVITE_BY_INTERNET] = 'Invite by internet';
StringRes_zh_cn[INVITE_BY_INTERNET] = '网络邀请';
StringRes_zh_tw[INVITE_BY_INTERNET] = '網絡邀請';

StringRes_en[INVITE_BY_PHONE] = 'Join by phone';
StringRes_zh_cn[INVITE_BY_PHONE] = '电话加入';
StringRes_zh_tw[INVITE_BY_PHONE] = '電話加入';

StringRes_en[OTHER] = 'Other ';
StringRes_zh_cn[OTHER] = '其他';
StringRes_zh_tw[OTHER] = '其他';

StringRes_en[NORMAL] = 'Normal';
StringRes_zh_cn[NORMAL] = '正常';
StringRes_zh_tw[NORMAL] = '正常';

StringRes_en[MUTE] = 'Mute';
StringRes_zh_cn[MUTE] = '静音';
StringRes_zh_tw[MUTE] = '靜音';

StringRes_en[NO_CONNECTED] = 'Disconnected';
StringRes_zh_cn[NO_CONNECTED] = '未连接';
StringRes_zh_tw[NO_CONNECTED] = '未連接';

StringRes_en[CONNECTED] = 'Connected';
StringRes_zh_cn[CONNECTED] = '已连接';
StringRes_zh_tw[CONNECTED] = '已連接';

StringRes_en[CALLING] = 'Calling';
StringRes_zh_cn[CALLING] = '呼叫中';
StringRes_zh_tw[CALLING] = '呼叫中';

StringRes_en[NO_RECORD_SELECTED] = 'No data selected';
StringRes_zh_cn[NO_RECORD_SELECTED] = '没有记录选中';
StringRes_zh_tw[NO_RECORD_SELECTED] = '沒有記錄選中';

StringRes_en[ADD_PHONEUSER] = 'Add phone user';
StringRes_zh_cn[ADD_PHONEUSER] = '添加电话会议联系人';
StringRes_zh_tw[ADD_PHONEUSER] = '添加電話會議聯系人';

StringRes_en[EDIT_PHONEUSER] = 'Edit phone user';
StringRes_zh_cn[EDIT_PHONEUSER] = '编辑电话会议联系人';
StringRes_zh_tw[EDIT_PHONEUSER] = '編輯電話會議聯系人';

StringRes_en[NO_USER_SELECTED] = 'No user selected';
StringRes_zh_cn[NO_USER_SELECTED] = '没有用户选中';
StringRes_zh_tw[NO_USER_SELECTED] = '沒有用戶選中';

StringRes_en[NO_PHONE_NUMBER] = 'Phone number can not be empty ';
StringRes_zh_cn[NO_PHONE_NUMBER] = '电话号码不能为空';
StringRes_zh_tw[NO_PHONE_NUMBER] = '電話號碼不能為空';

StringRes_en[NO_AVAILABLE_VCODE] = 'Please Mr Into available authentication code!';
StringRes_zh_cn[NO_AVAILABLE_VCODE] = '请先生成可用验证码!';
StringRes_zh_tw[NO_AVAILABLE_VCODE] = '請先生成可用驗證碼';

StringRes_en[SEEGLE_PHONE_CONF] = '1.Welcome to use seegle conference system.';
StringRes_zh_cn[SEEGLE_PHONE_CONF] = '1、欢迎使用视高免费电话会议系统。';
StringRes_zh_tw[SEEGLE_PHONE_CONF] = '1、歡迎使用視高免費電話會議系統。';

StringRes_en[DAIL_PHONE] = '2.Please make a phone call. ';
StringRes_zh_cn[DAIL_PHONE] = '2、请您拨打电话.';
StringRes_zh_tw[DAIL_PHONE] = '2、請您撥打電話。';

StringRes_en[INPUT_VCODE] = '3.Enter the authentication code';
StringRes_zh_cn[INPUT_VCODE] = '3、并输入验证码';
StringRes_zh_tw[INPUT_VCODE] = '3、並輸入驗證碼';

StringRes_en[LOGIN_CONF] = 'Attend meeting';
StringRes_zh_cn[LOGIN_CONF] = '参加会议.';
StringRes_zh_tw[LOGIN_CONF] = '參加會議.';

StringRes_en[INVITE_BY_MSG] = 'Invite by message';
StringRes_zh_cn[INVITE_BY_MSG] = '短信邀请';
StringRes_zh_tw[INVITE_BY_MSG] = '短信邀請';

StringRes_en[REPEAT_SUBMIT] = 'The last request is still in progress, and do not repeat submitted';
StringRes_zh_cn[REPEAT_SUBMIT] = '上次请求仍在进行中，不可重复提交';
StringRes_zh_tw[REPEAT_SUBMIT] = '上次請求仍在進行中，不可重復提交';

StringRes_en[ADD_VCODE] = 'Add authentication code';
StringRes_zh_cn[ADD_VCODE] = '添加验证码';
StringRes_zh_tw[ADD_VCODE] = '添加驗證碼';

StringRes_en[USER_IMPORT] = 'Import user';
StringRes_zh_cn[USER_IMPORT] = '用户导入';
StringRes_zh_tw[USER_IMPORT] = '用戶導入';

StringRes_en[USER_IMPORTING] = 'Importing';
StringRes_zh_cn[USER_IMPORTING] = '用户导入中';
StringRes_zh_tw[USER_IMPORTING] = '用戶導入中';

StringRes_en[EDIT_PHONE_CONF] = 'Edit phone conf';
StringRes_zh_cn[EDIT_PHONE_CONF] = '修改电话会议';
StringRes_zh_tw[EDIT_PHONE_CONF] = '修改電話會議';

StringRes_en[UPDATE] = 'Update';
StringRes_zh_cn[UPDATE] = '更新';
StringRes_zh_tw[UPDATE] = '更新';

StringRes_en[NO_VCODE] = 'Please enter the authentication code！';
StringRes_zh_cn[NO_VCODE] = '请输入验证码！';
StringRes_zh_tw[NO_VCODE] = '請輸入驗證碼！';

StringRes_en[ERR_MAX_LENGTH] = 'Maximum length of the authentication code is 12！ ';
StringRes_zh_cn[ERR_MAX_LENGTH] = '验证码最大长度为12位！';
StringRes_zh_tw[ERR_MAX_LENGTH] = '驗證碼最大長度為12位！';

StringRes_en[CAN_NOT_EDIT_AUTO] = 'The verification code automatically create  cannot be modified！ ';
StringRes_zh_cn[CAN_NOT_EDIT_AUTO] = '自动创建的验证码无法修改！';
StringRes_zh_tw[CAN_NOT_EDIT_AUTO] = '動創建的驗證碼無法修改！';

StringRes_en[EDIT_VCODE] = 'Edit authentication code';
StringRes_zh_cn[EDIT_VCODE] = '修改验证码';
StringRes_zh_tw[EDIT_VCODE] = '修改驗證碼';

StringRes_en[ERROR_NOTFIND] = 'An unknown error message, the error code is:';
StringRes_zh_cn[ERROR_NOTFIND] = '未知的错误信息，错误代码：';
StringRes_zh_tw[ERROR_NOTFIND] = '未知的錯誤信息，錯誤代碼：';

StringRes_en[ERR_REBUILD_FAILURE] = 'To generate verification failed, please refresh retry!';
StringRes_zh_cn[ERR_REBUILD_FAILURE] = '重新生成验证码失败，请刷新后重试！';
StringRes_zh_tw[ERR_REBUILD_FAILURE] = '重新生成驗證碼失敗，請刷新後重試！';

StringRes_en[ERR_OPERUSER_ACCOUNT] = 'Account input is wrong!';
StringRes_zh_cn[ERR_OPERUSER_ACCOUNT] = '帐号输入有误！';
StringRes_zh_tw[ERR_OPERUSER_ACCOUNT] = '帳號輸入有誤！';

StringRes_en[ERR_OPERUSER_ALIAS] = 'Nickname input is wrong!';
StringRes_zh_cn[ERR_OPERUSER_ALIAS] = '昵称输入有误！';
StringRes_zh_tw[ERR_OPERUSER_ALIAS] = '暱稱輸入有誤！';

StringRes_en[ERR_OPERUSER_PHONE] = 'Phone input is wrong!';
StringRes_zh_cn[ERR_OPERUSER_PHONE] = '电话输入有误！';
StringRes_zh_tw[ERR_OPERUSER_PHONE] = '電話入有誤！';

StringRes_en[OPERUSER_TITLE] = 'The telephone operator set';
StringRes_zh_cn[OPERUSER_TITLE] = '电话操作员设置';
StringRes_zh_tw[OPERUSER_TITLE] = '電話操作員設置';

StringRes_en[CONFIRM_CANCEL] = 'Confirm cancel?';
StringRes_zh_cn[CONFIRM_CANCEL] = '确认取消吗?';
StringRes_zh_tw[CONFIRM_CANCEL] = '確認取消嗎？';

StringRes_en[PHONE_CHARGE_COUNTDATA] = 'Number of deduction data：';
StringRes_zh_cn[PHONE_CHARGE_COUNTDATA] = '扣费数据条数：';
StringRes_zh_tw[PHONE_CHARGE_COUNTDATA] = '扣費數據條數：';

StringRes_en[PHONE_CHARGE_COUNTTIME] = 'The billing time：';
StringRes_zh_cn[PHONE_CHARGE_COUNTTIME] = '计费时长：';
StringRes_zh_tw[PHONE_CHARGE_COUNTTIME] = '計費時長：';

StringRes_en[PHONE_CHARGE_BEGINTIME] = 'Deduction period begins：';
StringRes_zh_cn[PHONE_CHARGE_BEGINTIME] = '扣费时间段开始：';
StringRes_zh_tw[PHONE_CHARGE_BEGINTIME] = '扣費時間段開始：';

StringRes_en[PHONE_CHARGE_ENDTIME] = 'Deduction period is over：';
StringRes_zh_cn[PHONE_CHARGE_ENDTIME] = '扣费时间段结束：';
StringRes_zh_tw[PHONE_CHARGE_ENDTIME] = '扣費時間段結束：';

StringRes_en[PHONE_CHARGE_NOEXCESS] = 'Excess charge is not enabled';
StringRes_zh_cn[PHONE_CHARGE_NOEXCESS] = '超额计费未启用';
StringRes_zh_tw[PHONE_CHARGE_NOEXCESS] = '超額計費未啟用';

StringRes_en[PHONE_CHARGE_STARTEXCESS] = 'Excess charge enabled, excess sum is：';
StringRes_zh_cn[PHONE_CHARGE_STARTEXCESS] = '超额计费已启用，超额总额为：';
StringRes_zh_tw[PHONE_CHARGE_STARTEXCESS] = '超額計費已啟用，超額總額為：';

StringRes_en[PHONE_CHARGE_PRICEUNIT] = 'yuan/min';
StringRes_zh_cn[PHONE_CHARGE_PRICEUNIT] = '元/分钟';
StringRes_zh_tw[PHONE_CHARGE_PRICEUNIT] = '元/分鐘';

StringRes_en[PHONE_CHARGE_PAYMONEYFORMATERROR] = 'PayMoney format error';
StringRes_zh_cn[PHONE_CHARGE_PAYMONEYFORMATERROR] = '充值金额格式错误';
StringRes_zh_tw[PHONE_CHARGE_PAYMONEYFORMATERROR] = '充值金額格式錯誤';

StringRes_en[PHONE_CHARGE_LESSMONEYFORMATERROR] = 'Discount format error';
StringRes_zh_cn[PHONE_CHARGE_LESSMONEYFORMATERROR] = '优惠金额格式错误';
StringRes_zh_tw[PHONE_CHARGE_LESSMONEYFORMATERROR] = '優惠金額格式錯誤';

StringRes_en[PHONE_CHARGE_PRICEFORMATERROR] = 'Price format error';
StringRes_zh_cn[PHONE_CHARGE_PRICEFORMATERROR] = '单价格式错误';
StringRes_zh_tw[PHONE_CHARGE_PRICEFORMATERROR] = '單價格式錯誤';

StringRes_en[PHONE_CHARGE_EXCESSFORMATERROR] = 'ExcessPrice format error';
StringRes_zh_cn[PHONE_CHARGE_EXCESSFORMATERROR] = '超额计费单价格式错误';
StringRes_zh_tw[PHONE_CHARGE_EXCESSFORMATERROR] = '超額計費單價格式錯誤';

StringRes_en[PHONE_CHARGE_REMINDMONEYFORMATERROR] = 'Arrears Remind Amount format error';
StringRes_zh_cn[PHONE_CHARGE_REMINDMONEYFORMATERROR] = '欠费提醒金额格式错误';
StringRes_zh_tw[PHONE_CHARGE_REMINDMONEYFORMATERROR] = '欠費提醒金額格式錯誤';

StringRes_en[PHONE_CHARGE_EMAILFORMATERROR] = 'Email format error';
StringRes_zh_cn[PHONE_CHARGE_EMAILFORMATERROR] = '邮箱格式错误';
StringRes_zh_tw[PHONE_CHARGE_EMAILFORMATERROR] = '郵箱格式錯誤';
StringRes_en[BACKUP_BACKUPNAMEERROR] = 'backup name error';
StringRes_zh_cn[BACKUP_BACKUPNAMEERROR] = '备份名称错误';
StringRes_zh_tw[BACKUP_BACKUPNAMEERROR] = '備份名稱錯誤';

//根据当前语种选择相应文字
function getMsg(message){
    var language = getCookie("SGlanguage");  //从cookie中获得当前语种
    if (language == "zh_tw"){  // 繁体中文
        return StringRes_zh_tw[message];
    }else if (language == "en"){  // English
        return StringRes_en[message];
    }else{  // 简体中文
        return StringRes_zh_cn[message];
    }
}

function getCookie(c_name)
{
	c_name=getRootPath()+"/"+c_name;
    if (document.cookie.length > 0)
    {
        c_start = document.cookie.indexOf(c_name + "=")
        if (c_start != -1)
        {
            c_start = c_start + c_name.length + 1;
            c_end   = document.cookie.indexOf(";",c_start);
            if (c_end == -1)
            {
                c_end = document.cookie.length;
            }
            return unescape(document.cookie.substring(c_start,c_end));
        }
    }
    return null
}








