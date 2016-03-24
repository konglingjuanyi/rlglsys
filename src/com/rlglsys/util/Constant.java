package com.rlglsys.util;

public class Constant {

    // ----------------- SESSION_KEY ---------------
    // 验证码
    public final static String SESSION_KEY_RANDOMCODE = "randomCode";

    // Login用户信息
    public final static String SESSION_KEY_LOGINUSER = "session_key_loginuser";

    // 权限信息
    public final static String SESSION_KEY_USER_AUTHORITY = "session_key_user_authority";

    // 权限
    public final static String ROLE_READ = "1";
    public final static String ROLE_WRITE = "2";
    public final static String ROLE_COFIRM_LV1 = "3";
    public final static String ROLE_COFIRM_LV2 = "4";
    public final static String ROLE_COFIRM_LV3 = "5";
    public final static String ROLE_SALEMAN = "99999997"; 
    public final static String ROLE_MANAGE = "99999998";
    public final static String ROLE_DIRECTOR = "99999999";
    
    // 文件、图片上传路径设置文件
    public final static String CONFIG_FILE = "conf/filePath";
    
    // 邮件发送设置文件
    public final static String CONFIG_MAIL = "conf/mail";

    // GID中允许输入的文字
    public final static String ID_CHK = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // 语种
    public final static String LAN_CN = "cn";
    public final static String LAN_JP = "jp";
    public final static String LAN_KO = "ko";
    public final static String LAN_US = "us";
    // 济南卫生局,主要用来标识课件是属于济南卫生局
    public static String JNWSJ = "jnwsj";
    // 用户批量导入功能中的提示信息
    public static String ALERT_ONR ="导入的用户信息中含有非本单位的人员！";
    // 银联的查询地址
    public static String CHAXUNDIZHI = "http://i.chinaecpay.com/querying.html";
     
    
}
