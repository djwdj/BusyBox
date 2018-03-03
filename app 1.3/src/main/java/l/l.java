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
import java.nio.*;
import java.security.*;
import java.nio.channels.*;

public class l extends Activity implements OnClickListener,OnFocusChangeListener,Runnable
{
	LinearLayout l,ll,l0,l00,l1,l2;
	ScrollView sc;
	LayoutParams lp=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT,1);
	Animation a;
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
	mirw="mount -o rw,remount,rw /system",
	rw="mount -o rw,remount /system",
	ro="mount -o ro,remount /system",
	cp="cp -f %s %s",
	rm="rm -f %s",
	chmod="chmod 0755 %s"
	;
	String[] s={"简单安装","链式安装","一键卸载","刷新文件","简单测试"};
	
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

		a=new AlphaAnimation(0,1);
		a.setDuration(500);
		l.startAnimation(a);

		l0=new LinearLayout(this);
		l0.setPadding(p,0,p,p);
		l0.setClickable(true);
		l0.setOrientation(LinearLayout.VERTICAL);
		l0.setBackgroundDrawable(d(w/10,0xbbcccccc));
		l0.setLayoutParams(lp);
		l.addView(l0);
		
		l00=new LinearLayout(this);
		l00.setOrientation(LinearLayout.VERTICAL);
		l0.addView(l00);
		
		sc=new ScrollView(this);
		l0.addView(sc);
		
		ll=new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		sc.addView(ll);
		
		l1=new LinearLayout(this);
		l1.setOrientation(LinearLayout.VERTICAL);
		ll.addView(l1);
		
		try
		{
			ver = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
		}
		catch (PackageManager.NameNotFoundException e)
		{}
		
		t(sb("BusyBox",ver));
		
		t(Build.MODEL);
		
		t(Build.VERSION.RELEASE+" ("+Build.VERSION.SDK+")");
		
		t(new File("/system/xbin/busybox").exists()?"文件存在":"文件不存在");
		
		for(int i=0;i<s.length;i++)b(i);
		
		addContentView(l, new WindowManager.LayoutParams());
		
		new Handler().postDelayed(this, 500);
		
	}

	@Override
	public void run()
	{
		try
		{
			String s=su("busybox");
			s = s.substring(0, s.indexOf("(") - 1);
			t(s);
		}
		catch (Exception e)
		{}
		
		
	}

	@Override
	public void onFocusChange(View v, boolean b)
	{
		if(b)v.setBackgroundDrawable(d(w/16,0xff00ff00));
		else v.setBackgroundDrawable(d(w/16,0xffff5556));
		
	}
	
	
	
	@Override
	public void onClick(View v)
	{
		a=new AlphaAnimation(1, 0);
		a.setDuration(1000);
		v.startAnimation(a);
		
		switch(v.getId())
		{
			case 0:
				f();
					
				try
				{
					s(rw);
					s(String.format(cp,f,ff));
					s(String.format(chmod,ff));
					s(ro);
					
					if (!r("echo 基哥科技").equals("基哥科技"))
						((Button)v).setText(s1);
				}
				catch (IOException e)
				{
					((Button)v).setText(s1);
				}
				if (new File(ff).exists())T("安装成功！");
				else T("未安装！");
				recreate();
				break;
			case 1:
				f();

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
				else T("未安装！");
				recreate();
				break;
			case 2:
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
			case 3:
				try
				{
					c("busybox", f);
				}
				catch (IOException e)
				{
					T("没有储存权限，请授权后再试！");
					if (Build.VERSION.SDK_INT >= 9)
						startActivity(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", getPackageName(), null)));
				}
				break;
			case 4:
				try
				{
					s("busybox pkill com.android.systemui");
				}
				catch (IOException e)
				{}
				break;
			default:finish();
		}
		
	}

	void f()
	{
		if (!new File(f).exists())
			try
			{
				c("busybox", f);
			}
			catch (IOException e)
			{
				T("没有储存权限，请授权后再试！");
				if (Build.VERSION.SDK_INT >= 9)
					startActivity(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", getPackageName(), null)));
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
		l00.addView(t);
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
	String su(String su){
		String s=;
		try{
			java.lang.Process p = Runtime.getRuntime().exec("su");
			BufferedWriter w = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
			w.write(su+"\nexit\n");
			w.flush();
			BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
			StringBuilder i = new StringBuilder();
			while (( s= r.readLine()) != null)
			{
				i.append(s+"\n");
			}
			r.close();
			w.close();
			p.destroy();

			s=i.toString().trim();

		}catch (IOException e){}

		return s;
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
