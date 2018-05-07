package com.jgcj.library.http;

/**
 * Created by HH
 * Date: 2017/11/21
 */

public class ApiStores {

    static final String urlVersion = "index/User/";
    static final String indexVersion = "index/Index/";
    static final String noticeVersion = "index/notice/";
    static final String teacherVersion = "index/Teacher/";
    static final String chatVersion = "Chat/Chat/";
    /** 意见反馈 */
    public static final String User_Feedback = noticeVersion + "User_Feedback";
    /** 登录 */
    public static final String user_send_login = urlVersion + "Login";
    /** 忘记密码 */
    public static final String chengePwdOne = urlVersion + "chengePwdOne";
    public static final String chengePwdTwo = urlVersion + "chengePwdTwo";

    /** 发送验证码 */
    public static final String user_send_yzm = urlVersion + "sendYzm";

    /** 修改密码 */
    public static final String changePwd = urlVersion + "changePwd2";

    /** 修改密码 */
    public static final String changePhoto = urlVersion + "changePhoto";

    /** 修改昵称 */
    public static final String changeName = urlVersion + "changeName";

    /** 我的消息 */
    public static final String my_Notice = noticeVersion + "my_Notice";

    /** 导师简介 */
    public static final String info_Teach = teacherVersion + "info_Teach";

    /** 关注 取消关注 */
    public static final String add_attension = teacherVersion + "add_attension";


    /**  获取聊天记录 */
    public static final String seacher_record = chatVersion + "seacher_record";

    /**  聊天室列表 */
    public static final String room_info = indexVersion + "room_info";
    /** 注册 */
    public static final String user_register = urlVersion + "register";

    /** banner  主页列表 */
    public static final String banner = indexVersion + "banner";
    /** 老师详情 */
    public static final String getTeacherDetail = indexVersion + "getTeacherDetail";
    /** 视频列表 */
    public static final String getClass = indexVersion + "getClass";
    /** 名师 */
    public static final String getTeacher = indexVersion + "getTeacher";
    /** 查询关注状态 */
    public static final String Search_attension = teacherVersion + "Search_attension";

    /** 用户的发言 */
    public static final String student_say = chatVersion + "student_say";

    /** banner  主页列表 */
    public static final String more = indexVersion + "more";

    /** banner  主页列表 */
    public static final String getAllTeacher = indexVersion + "getAllTeacher";

    /** 视频回放 */
    public static final String getafterClass = indexVersion + "getafterClass";
}
