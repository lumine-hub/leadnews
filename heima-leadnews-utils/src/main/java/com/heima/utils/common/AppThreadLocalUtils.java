package com.heima.utils.common;

import com.heima.model.user.pojos.ApUser;
import com.heima.model.wemedia.pojos.WmUser;

public class AppThreadLocalUtils {
    public static ThreadLocal<ApUser> WM_USER_THREAD_LOCAL = new ThreadLocal<>();

    public static ApUser getUser(){
        return WM_USER_THREAD_LOCAL.get();
    }
    public static void  setUser(ApUser wmUser){
        WM_USER_THREAD_LOCAL.set(wmUser);
    }
    public static void clear(){
        WM_USER_THREAD_LOCAL.remove();
    }
}
