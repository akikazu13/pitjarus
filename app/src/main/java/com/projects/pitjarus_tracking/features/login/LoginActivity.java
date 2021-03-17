package com.projects.pitjarus_tracking.features.login;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import com.projects.pitjarus_tracking.R;
import com.projects.pitjarus_tracking.connections.requests.PostLoginRequest;
import com.projects.pitjarus_tracking.databinding.ActivityLoginBinding;
import com.projects.pitjarus_tracking.features.BaseActivity;
import com.projects.pitjarus_tracking.features.mainMenu.MainMenuActivity;

public class LoginActivity extends BaseActivity<ActivityLoginBinding> {

    private LoginViewModel loginViewModel;
    private String username;
    private String password;

    @Override
    protected int attachLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initAction() {
        super.initAction();

        binding.btnLogin.setOnClickListener(onClick -> {
            username = binding.etUsername.getText().toString();
            password = binding.etPassword.getText().toString();
            login();
        });
    }

    @Override
    protected void initData() {
        super.initData();

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
    }

    private void login(){
        PostLoginRequest request = PostLoginRequest.builder()
                .username(username)
                .password(password)
                .build();


        loginViewModel.postLogin(request);
        loginViewModel.getLoginLiveData().observe(this, response->{
            if (!response.isEmpty()){
                Log.v("responseObserve", response.toString());
                loginViewModel.insertResponseToDatabase(response);
                binding.setIsTrue(false);
                showMainmenuActivity();
            }
            else {
                binding.setIsTrue(true);
            }
        });
    }

    private void showMainmenuActivity(){
        Intent intent = new Intent(LoginActivity.this, MainMenuActivity.class);
        startActivity(intent);
    }
}