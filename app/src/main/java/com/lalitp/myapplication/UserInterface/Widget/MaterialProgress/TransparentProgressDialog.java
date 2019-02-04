package com.lalitp.myapplication.UserInterface.Widget.MaterialProgress;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.WindowManager;

import com.lalitp.myapplication.R;


public class TransparentProgressDialog extends Dialog {
    MaterialProgressBar progress1;

    Context mContext;
	public TransparentProgressDialog(Context context) {
		super(context, R.style.ProgressDialog);
        this.mContext=context;

		setContentView(R.layout.custom_material_progress);

		progress1 = (MaterialProgressBar) findViewById (R.id.progress1);


		progress1.setColorSchemeResources(R.color.colorAccent,R.color.colorAccent,R.color.colorAccent,R.color.colorAccent);

		setTitle(null);
		setCancelable(false);
		setOnCancelListener(null);
		//dialog.setOnCancelListener(cancelListener);
		getWindow().getAttributes().gravity= Gravity.CENTER;
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.dimAmount=0.2f;
		getWindow().setAttributes(lp);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
	}

}
