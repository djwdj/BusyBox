package l;

import android.app.*;
import android.content.*;
import android.content.pm.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.net.*;
import android.os.*;
import android.provider.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.widget.*;
import android.widget.LinearLayout.*;
import java.io.*;
import android.*;

public class l extends Activity implements OnClickListener
{
	LinearLayout l,l1;
	LayoutParams lp=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT,1);
	Animation a1,aa;
	Button b;
	TextView t;
	float d,z;
	int w,h,p,m;
	String ver,
	s1="请ROOT后再试！",
	bb="busybox",
	bi="busybox --install -s %s",
	bp="/system/xbin",
	f="/sdcard/busybox",
	ff="%s/busybox",
	rw="mount -o rw,remount /system",
	ro="mount -o ro,remount /system",
	cp="cp -f %s %s",
	rm="rm -f %s",
	chmod="chmod 0755 %s"
	;
	String[] s={"安装","卸载"};
	
	String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		ff=String.format(ff,bp);
		bi=String.format(bi,bp);
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) 
            if (checkSelfPermission(permissions[0]) != 0)
                requestPermissions(permissions, 321);
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			getWindow().setNavigationBarColor(0);
			getWindow().setStatusBarColor(0);
		}else if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT)
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS |
								 WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

		DisplayMetrics dm = getResources().getDisplayMetrics();
		d = dm.density;
		if(dm.widthPixels<dm.heightPixels)
		{
			w = dm.widthPixels;
			h = dm.heightPixels;
		}else{
			h = dm.widthPixels;
			w = dm.heightPixels;
		}
		p=w/36;
		m=w/60;
		z=w/d/20;

		lp.setMargins(m,m,m,m);

		l=new LinearLayout(this);
		l.setPadding(p,p,p,p);
		l.setGravity(Gravity.CENTER);
		l.setOnClickListener(this);

		a1=new TranslateAnimation(0, 0, h, 0);
		a1.setDuration(500);
		l.startAnimation(a1);

		l1=new LinearLayout(this);
		l1.setPadding(p,0,p,p);
		l1.setOrientation(LinearLayout.VERTICAL);
		l1.setBackgroundDrawable(d(w/10,0xbbcccccc));
		l1.setLayoutParams(lp);
		l.addView(l1);
		
		try
		{
			ver = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
		}
		catch (PackageManager.NameNotFoundException e)
		{}
		
		t(sb("BusyBox",ver));
		
		t(Build.MODEL);
		
		t(Build.VERSION.RELEASE+" ("+Build.VERSION.SDK+")");
		
		for(int i=0;i<s.length;i++)b(i);
		
		addContentView(l, new WindowManager.LayoutParams());
		
		
		
	}
	
	@Override
	public void onClick(View v)
	{
		aa=new AlphaAnimation(1, 0);
		aa.setDuration(1000);
		v.startAnimation(aa);
		
		switch(v.getId())
		{
			case 0:
				if (!new File(f).exists())
					try
					{
						c("busybox", f);
					}
					catch (IOException e)
					{
						T("没有储存权限，请授权后再试！");
						if (Build.VERSION.SDK_INT >= 19)
							startActivity(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", getPackageName(), null)));
					}
					
				try
				{
					s(rw);
					s(String.format(cp,f,ff));
					s(String.format(chmod,ff));
					s(bi);
					s(ro);
					
					if (!r("echo 基哥科技").equals("基哥科技"))
						((Button)v).setText(s1);
				}
				catch (IOException e)
				{
					((Button)v).setText(s1);
				}
				if (new File(ff).exists())T("安装成功！");
				break;
			case 1:
				try
				{
					s(rw);
					s(String.format(rm,ff));
					s(ro);
					if (!r("echo 基哥科技").equals("基哥科技"))
						((Button)v).setText(s1);
				}
				catch (IOException e)
				{
					((Button)v).setText(s1);
				}
				if (!new File(ff).exists())T("已卸载！");
				break;

			default:finish();
		}
		
	}

	SpannableStringBuilder sb(String b,String s)
	{
		SpannableStringBuilder sb = new SpannableStringBuilder();
		sb.append(b);
		sb.append("\n");
		sb.append(s);
		sb.setSpan(new RelativeSizeSpan(2),0,b.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		sb.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), 0, b.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		sb.setSpan(new RelativeSizeSpan(0.8f),b.length()+1,b.length()+1+s.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		sb.setSpan(new StyleSpan(Typeface.ITALIC), b.length()+1,b.length()+1+s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		return sb;
	}
	
	void t(CharSequence s)
	{
		t = new TextView(this);
		t.setText(s); 
		t.setTextSize(z);
		t.setTextColor(0xffff5555);
		t.setGravity(Gravity.CENTER);
		l1.addView(t);
	}
	void b(int i)
	{
		b=new Button(this);
		b.setId(i);
		b.setText(s[i]);
		b.setTextColor(0xffffffff);
		b.setTextSize(z);
		b.setBackgroundDrawable(d(w/16,0xffff5556));
		b.setPadding(p,p,p,p);
		b.setLayoutParams(lp);
		b.setOnClickListener(this);
		l1.addView(b);
	}
	
	void T(String s)
	{
		Toast.makeText(this,s,50).show();
	}

	Drawable d(int r,int c)
	{
		GradientDrawable d=new GradientDrawable();
		d.setColor(c);
		d.setCornerRadius(r);
		d.setStroke(2, 0xffeeeeee);
		return d;
	}
	
	void c(String assetsFileName, String OutFileName) throws IOException 
	{
        File f = new File(OutFileName);
        if (f.exists())
            f.delete();
        f = new File(OutFileName);
        f.createNewFile();
        InputStream I = getAssets().open(assetsFileName);
        OutputStream O = new FileOutputStream(OutFileName);
        byte[] b = new byte[1024];
        int l = I.read(b);
        while (l > 0) 
		{
            O.write(b, 0, l);
            l = I.read(b);
        }
        O.flush();
        I.close();
        O.close();
    }
	
	java.lang.Process su;
	void s(String s) throws IOException
	{
		if (su == null)su = Runtime.getRuntime().exec("su");
		OutputStream o=su.getOutputStream();
		o.write((String.format("%s\n",s)).getBytes());
		o.flush();
	}
	String r(String s) throws IOException
	{
		s(s);
		byte[] b = new byte[4096];
		s = new String(b, 0, su.getInputStream().read(b));
		return s.trim();
	}
	

	@Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions,  int[] grantResults) 
	{
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 321)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                if (grantResults[0] != 0)
                    if (!shouldShowRequestPermissionRationale(permissions[0])) 
					{
						Toast.makeText(this, "存储权限不可用！\n请在-应用设置-权限-中，允许使用存储权限来保存数据", Toast.LENGTH_SHORT).show();
						startActivityForResult(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,Uri.fromParts("package", getPackageName(), null)), 123);
					}
                    else
                        x();
                else 
                    Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
    }
	
	void x()
	{
		Toast.makeText(this, "权限获取失败!\n是否卸载本软件？", Toast.LENGTH_SHORT).show();
		startActivity(new Intent(Intent.ACTION_DELETE)
					  .setData(Uri.fromParts("package", getPackageName(), null)));
		finish();
	}
	
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                if (checkSelfPermission(permissions[0]) != 0)
					x();
                else 
                   	Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
    }
}
