package io.github.kevvy.chat_java.entity.constants;

/**
 * 用来放置常量的类
 */
public class Constants {
    public static final long REDIS_TIME_1MIN = 60L;
    public static final long REDIS_TIME_1HOUR = 60L * 60L ;
    public static final long REDIS_TIME_1DAY = 60L * 60L * 24L;
    /**
     * 验证码邮箱
     */
    public static final String REDIS_KEY_CHECK_CODE = "code:mail:";//
    /**
     * 设置发送锁。验证码发送后设置多少秒时间才能再次发送
     */
    public static final String REDIS_KEY_CHECK_LOCK = "code:lock:";
    /**
     * 客户端心跳
     */
    public static final String REDIS_KEY_WS_USER_HEART_BEAT = "ws:user:heart:beat:";

    public static final String REDIS_KEY_WS_TOKEN = "ws:token:";
    public static final String REDIS_KEY_WS_TOKEN_REFRESH = "ws:token:refresh:";
    public static final String REDIS_KEY_WS_TOKEN_USERID = "ws:token:userid:";
    /**
     * 预留 ai机器人
     */
    public static final String ROBOT_ID ="";

    public static final String REDIS_SYS_SETTING ="NoriChat:sysSetting";
    /**
     * 图像文件后续可以考虑放在云服务器上，如阿里云文件存储专用服务器，价格便宜
     */
    public static final String IMG_FILE_PATH ="imgFile/";
    public static final String AVATAR_FILE_PATH ="imgFile/avatar/";
    public static final String IMG_SUFFIX =".png";
    public static final String COVER_IMG_SUFFIX ="_cover.png";//缩略图




}
