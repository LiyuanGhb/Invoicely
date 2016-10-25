package com.dzfp.dzfp.model.User;

/**
 * Created by Administrator on 2016/8/12.
 */

public class UserMessage {
    private Data userBean;
    private static UserMessage mUserMessage = null;

    public static UserMessage newInstance() {
        if (mUserMessage == null) {
            mUserMessage = new UserMessage();
        }
        return mUserMessage;
    }


    public Data getUserBean() {
        return userBean;
    }

    public void setUserBean(Data mUserBean) {
        userBean = mUserBean;
    }
}
