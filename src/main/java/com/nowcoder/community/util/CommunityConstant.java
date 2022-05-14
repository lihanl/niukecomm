package com.nowcoder.community.util;

public interface CommunityConstant {

    //success
    int ACTIVATION_SUCCESS = 0;

    int ACTIVATION_REPEAT = 1;

    int ACTIVATION_FAILURE = 2;

    //默认登陆超时时间
    int DEFAULT_EXPIRED_SECONDS = 3600 * 12;


    //记住状态下的登录超时时间
    int REMEMBER_EXPIRED_SECONDS = 3600 * 24 * 100;
}
