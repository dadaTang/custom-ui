package com.xtan.component.dialog.extensions

import android.content.DialogInterface
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.ViewGroup

import com.xtan.component.dialog.other.DialogOptions
import com.xtan.component.dialog.CustomDialog
import com.xtan.component.dialog.listener.DataConvertListener
import com.xtan.component.dialog.listener.DialogShowOrDismissListener
import com.xtan.component.dialog.listener.OnKeyListener
import com.xtan.component.dialog.listener.ViewConvertListener
import com.xtan.component.dialog.other.ViewHolder
import kotlin.reflect.KClass

/**
 * 创建一个dialog
 * 你也可以继承CustomDialog，实现更多功能，并通过扩展函数来简化创建过程
 */
inline fun newCustomDialog(options: DialogOptions.(dialog: CustomDialog) -> Unit): CustomDialog {
    val customDialog = CustomDialog()
    customDialog.getDialogOptions().options(customDialog)
    return customDialog
}

/**
 * 当需要通过继承CustomDialog来分离Activity与dialog的代码时，
 * 可通过该扩展方法在子类中创建DialogOptions，可见AAA
 */
inline fun CustomDialog.dialogOptionsFun(dialogOp: DialogOptions.() -> Unit): DialogOptions {
    val options = DialogOptions()
    options.dialogOp()
    setDialogOptions(options)
    return options
}

/**
 * 设置convertListener的扩展方法
 */
inline fun DialogOptions.convertListenerFun(crossinline listener: (holder: ViewHolder, dialog: CustomDialog) -> Unit) {
    val viewConvertListener = object : ViewConvertListener() {
        override fun convertView(holder: ViewHolder, dialog: CustomDialog) {
            listener.invoke(holder, dialog)
        }
    }
    convertListener = viewConvertListener
}
/*

*/
/**
 * 设置dataListener的扩展方法
 *//*

inline fun< VB : ViewDataBinding> DialogOptions.dataConvertListenerFun(bindingClass: KClass<VB>, crossinline listener: (dialogBinding: VB, dialog: CustomDialog) -> Unit) {
    val dataBindingConvertListener = object : DataConvertListener() {
        override fun convertView(dialogBinding: Any, dialog: CustomDialog) {
            listener.invoke(dialogBinding as VB, dialog)
        }
    }
    dataConvertListener = dataBindingConvertListener
}

*/
/**
 * 设置dataBindingListener的扩展方法
 *//*

inline fun <T : Any, VB : ViewDataBinding> DialogOptions.bindingListenerFun(inflater: LayoutInflater, data: T,
                                                                            bindingClass: KClass<VB>, crossinline listener: (dialogBinding: VB, dialog: CustomDialog) -> Unit) {
    val newBindingListener = { container: ViewGroup?, dialog: CustomDialog ->
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, layoutId, container, false) as VB
        binding.setVariable(BR.data, data)
        binding.lifecycleOwner = dialog
        listener.invoke(binding, dialog)
        dialog.dialogBinding = binding
        binding.root
    }
    bindingListener = newBindingListener
}

*/

/**
 * 添加DialogShowOrDismissListener的扩展方法
 */
inline fun DialogOptions.addShowDismissListener(key: String, dialogInterface: DialogShowOrDismissListener.() -> Unit): DialogOptions {
    val dialogShowOrDismissListener = DialogShowOrDismissListener()
    dialogShowOrDismissListener.dialogInterface()
    showDismissMap[key] = dialogShowOrDismissListener
    return this
}

/**
 * 设置OnKeyListener的扩展方法
 */
inline fun DialogOptions.onKeyListenerForOptions(crossinline listener: (keyCode: Int, event: KeyEvent) -> Boolean) {
    val onKey = object : OnKeyListener() {
        override fun onKey(dialog: DialogInterface, keyCode: Int, event: KeyEvent): Boolean {
            return listener.invoke(keyCode, event)
        }
    }
    onKeyListener = onKey
}

/**
 * 针对特殊动画需要调用Customdialog.dismiss()的方法
 */
inline fun CustomDialog.onKeyListenerForDialog(crossinline listener: (Customdialog: CustomDialog, dialogInterFace: DialogInterface, keyCode: Int, event: KeyEvent) -> Boolean): CustomDialog {
    val onKey = object : OnKeyListener() {
        override fun onKey(dialog: DialogInterface, keyCode: Int, event: KeyEvent): Boolean {
            return listener.invoke(this@onKeyListenerForDialog, dialog, keyCode, event)
        }
    }
    getDialogOptions().onKeyListener = onKey
    return this
}

