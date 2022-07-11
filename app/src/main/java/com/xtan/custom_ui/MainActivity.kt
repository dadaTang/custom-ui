package com.xtan.custom_ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.xtan.component.dialog.extensions.UtilsExtension.Companion.dp2px
import com.xtan.component.dialog.extensions.newCustomDialog
import com.xtan.component.loading.LoadingUtil
import com.xtan.custom_ui.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(mBinding.root);
        mBinding.openDialog.setOnClickListener {
            LoadingUtil.show(this)
        }
        mBinding.closeDialog.setOnClickListener {
            LoadingUtil.dismiss()
        }

        mBinding.dialog2.setOnClickListener {
            newCustomDialog {
                unLeak = true
                width = dp2px(resources, 100f)
                height = dp2px(resources, 100f)
            }.showOnWindow(supportFragmentManager)
        }
    }
}