package com.example.ldk.mvpdemo.common.utils;

/**
 * Author: ldk
 * Date: 2017/7/26 16:59
 * <p>
 * Description: 路由表
 */
@SuppressWarnings("unused")
public class RouterUrl {
    private static final String URI_SCHEME = "mvp:/";

    /**
     * 获得请求URI
     */
    public static String getRouterUrl(String host) {
        return URI_SCHEME + host;
    }

    /****************************************************************************************************************/
    /******************************************* appCommon router url ***********************************************/
    /****************************************************************************************************************/

    // 主界面 - 声明
    public static final String AppCommon_IMain = "appCommon/main";

    public static final String AppCommon_IEvaluation = "appCommon/evaluation";

    public static final String AppCommon_Evaluation = AppCommon_IEvaluation + "?state=%1$s";

    public static final String AppCommon_IGuide= "appCommon/guide";
}