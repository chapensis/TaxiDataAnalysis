package pers.yangchang.TaxiDataAnalysis.bean;

import pers.yangchang.TaxiDataAnalysis.bean.vo.UserVO;

/**
 * 当前用户
 */
public class CurrentUser {

    private static final ThreadLocal<UserVO> currentUser = new ThreadLocal<>();

    public static void put(UserVO userVO) {
        currentUser.set(userVO);
    }

    public static UserVO get() {
        return currentUser.get();
    }

}
