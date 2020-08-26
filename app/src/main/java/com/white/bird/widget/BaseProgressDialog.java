package com.white.bird.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.white.bird.R;


/**
 * createTime 2020/8/8 09:20
 * 类名：BaseProgressDialog
 * 实现的主要功能:
 */
public class BaseProgressDialog extends ProgressDialog {

	private String tips;
	TextView tv_title;
	public BaseProgressDialog(Context context) {
		super(context, R.style.baseProgressDialog);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.progress_circle);
		tv_title = (TextView) findViewById(R.id.tv_title);
		tv_title.setText(getTips());
	}

	@Override
	public void dismiss() {
		super.dismiss();
		dailog = null;
	}

	private static BaseProgressDialog dailog;

	public static BaseProgressDialog getDialogInstance(Context context)
	{
//		if(dailog==null)
		dailog = new BaseProgressDialog(context);
		return dailog;
	}
	public String getTips() {
		return tips;
	}
	public void setTips(String tips) {

		this.tips = tips;
		if(tv_title!=null) {
			tv_title.setText(tips);
		}
	}

}
