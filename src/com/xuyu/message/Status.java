package com.xuyu.message;

public interface Status {
    /**
     * 未经过oauth认证的链接状态
     */
    String FLAG_UN_OAUTH_CERTIFICATE="0000";

    /**
     * 已经经过oauth认证的链接状态
     */
    String FLAG_OAUTH_CERTIFICATE ="1111";

    /**
     * 未设置上课时间标志
     */
     int FLAG_UNSET = 0 ;

    /**
     * 已设置上课时间标志
     */
    int FLAG_SETTED = 1;
}
