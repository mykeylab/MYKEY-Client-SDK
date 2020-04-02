package com.mykey.sdk.common.constants;

/**
 * Created by zero on 2019/5/27.
 */

public interface ErrorCons {
    // #######################defined by SimpleWallet
    int ERROR_CODE_CANCEL = 0;
    int ERROR_CODE_OK = 1;
    int ERROR_CODE_FAILURE = 2;

    // #######################defined by MYKEY
    int ERROR_CODE_CAN_NOT_WAKE_UP = 10001;
    int ERROR_CODE_MYKEY_NOT_INSTALL = 10002;
    int ERROR_CODE_FREEZED = 10003;
    int ERROR_CODE_NO_INIT = 10004;
    int ERROR_CODE_TIME_OUT = 10005;
    int BINDED = 10006;
    int UNBIND = 10007;
    int JUST_DAPP_BIND = 10008;
    int JUST_MYKEY_BINDED = 10009;
    int BIND_NOT_MATCH = 10010;
    int MYKEY_UNREGISTERED = 10011;

    // ##############################defined by Self
    int SERVICE_FREE_PROMPT_INVALID = 20001;
    int SERVICE_PASSWORD_EXPIRED = 20002;
    int SERVICE_FAILURE = 20003;
}
