package com.projects.pitjarus_tracking.features;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * Created by Sohibun Nawawi on 12/03/2021.
 */
public abstract class BaseActivity <Binding extends ViewDataBinding> extends AppCompatActivity {
    abstract protected int attachLayout();

    protected Binding binding;

    protected void initData(){

    }

    protected void initLayout(){

    }

    protected void initAction() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,attachLayout());
        initData();
        initLayout();
        initAction();
    }
}
