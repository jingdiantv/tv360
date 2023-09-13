package com.example.myapplication.Presenter;

import com.example.myapplication.Interface.LoginInterface;
import com.example.myapplication.Model.DataObjectLogin;

public class LoginPresenter {
    private LoginInterface mloginInterface;

    public LoginPresenter(LoginInterface mloginInterface) {
        this.mloginInterface = mloginInterface;
    }

    public  void  login(DataObjectLogin userModel)
    {
        if(userModel.getErrorCode() == 200)
        {
            mloginInterface.loginSuccess(userModel.getData().getAccessToken(),userModel.getData().getRefreshToken(),userModel.getData().getUserId(),userModel.getData().getProfileId());
        }
        else
        {
            mloginInterface.loginError();
        }
    }
}
