package com.heima.utils.common;

import com.heima.model.wemedia.pojos.WmUser;

public class WmThreadLocalUtils {
    public static ThreadLocal<WmUser> WM_USER_THREAD_LOCAL = new ThreadLocal<>();

    public static WmUser getUser(){
        return WM_USER_THREAD_LOCAL.get();
    }
    public static void  setUser(WmUser wmUser){
        WM_USER_THREAD_LOCAL.set(wmUser);
    }
    public static void clear(){
        WM_USER_THREAD_LOCAL.remove();
    }
}
