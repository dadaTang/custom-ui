package com.xtan.custom_ui

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xtan.component.loading.LoadingUtil
import com.xtan.custom_ui.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var createLoadingDialog: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(mBinding.root);
        mBinding.openDialog.setOnClickListener {
              createLoadingDialog = LoadingUtil.createLoadingDialog(this)

        }
        mBinding.closeDialog.setOnClickListener {
            LoadingUtil.closeDialog(createLoadingDialog)

        }
    }
}