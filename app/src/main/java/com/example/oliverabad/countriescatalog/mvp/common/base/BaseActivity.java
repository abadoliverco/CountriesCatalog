package com.example.oliverabad.countriescatalog.mvp.common.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.example.oliverabad.countriescatalog.BuildConfig;
import com.example.oliverabad.countriescatalog.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by oliverabad on 7/7/18.
 */

public abstract class BaseActivity extends AppCompatActivity implements MvpView {

    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    ProgressDialog progressDialog;

    Unbinder unbinder;
    int onStartCount = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        unbinder = ButterKnife.bind(this);

        progressDialog = new ProgressDialog(BaseActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading countries...");

        if (toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(setHeaderTitle());

            if (hasBackButton()) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });
            }
        }

        if (!BuildConfig.DEBUG) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        }

        onStartCount = 1;
        if (savedInstanceState == null){
            this.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        } else {
            onStartCount = 2;
        }
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    protected abstract int getContentView();
    protected abstract boolean hasBackButton();
    protected abstract String setHeaderTitle();

    @Override
    protected void onPause() {
        super.onPause();
        hideLoader();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else{
            super.onBackPressed();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (onStartCount > 1) {
            this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
        } else if (onStartCount == 1) {
            onStartCount++;
        }
    }

    @Override
    public void showLoader() {
        if (progressDialog != null && !progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void hideLoader() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}