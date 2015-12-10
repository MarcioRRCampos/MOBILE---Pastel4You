package com.br.marcio.campos.pastel4you.prefs;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.br.marcio.campos.pastel4you.MyApplicativo;
import com.br.marcio.campos.pastel4you.R;
import com.br.marcio.campos.pastel4you.RegisterActivity;

/**
 * Created by Marcio on 08/11/2015.
 */
public class BaseActivity extends livroandroid.lib.activity.BaseActivity{

    @Override
    protected boolean isLogLifecycle() {
        return super.isLogLifecycle();
    }

    @Override
    protected boolean isLogOn() {
        return false;
    }

    protected void setUpToolbar() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyApplicativo app = MyApplicativo.getInstance();
    }





}
