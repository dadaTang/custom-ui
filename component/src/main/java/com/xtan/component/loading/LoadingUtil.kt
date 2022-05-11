package com.xtan.component.loading

import android.app.Dialog
import android.content.Context
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import com.xtan.component.R
import com.xtan.component.databinding.DialogLoadingBinding
import java.security.AccessController.getContext


/**
 * user: dadaloser
 * date: 2022/4/30
 * desc:
 */
object LoadingUtil {

    private var dialog: Dialog? = null

    fun show(context: Context, msg: String = "加载中...") {
        //防止重复添加层级
        dismiss()

        val binding: DialogLoadingBinding =
            DialogLoadingBinding.inflate(LayoutInflater.from(context))
        binding.tvTips.text = msg // 设置加载信息
        val loadingDialog = Dialog(context, R.style.LoadingDialogStyle) // 创建自定义样式dialog
        loadingDialog.setCancelable(true) // 是否可以按“返回键”消失
        loadingDialog.setCanceledOnTouchOutside(false) // 点击加载框以外的区域
        loadingDialog.setContentView(
            binding.root, LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
        ) // 设置布局
        /**
         * 将显示Dialog的方法封装在这里面
         */
        val window = loadingDialog.window
        window?.apply {
            val lp: WindowManager.LayoutParams = attributes
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT
            setGravity(Gravity.CENTER)
            attributes = lp
            setWindowAnimations(R.style.PopWindowAnimStyle)

            loadingDialog.show()
        }

        dialog = loadingDialog
    }

    /**
     * 关闭dialog
     *
     * http://blog.csdn.net/qq_21376985
     *
     * @param mDialogUtils
     */
    fun dismiss() {
        dialog?.let {
            if (it.isShowing) {
                it.dismiss()
            }
        }

    }
}