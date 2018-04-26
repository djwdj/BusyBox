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
import java.net.*;
import java.text.*;
import java.util.*;

public class l extends Activity implements OnClickListener,OnFocusChangeListener,Runnable
{
	LinearLayout l,ll,lo,l00,l0,l1,l2,l3;
	ScrollView sc;
	LayoutParams lp=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT,1);
	SharedPreferences sp;
	Animation a;
	Button b;
	TextView t;
	float d,z;
	int w,h,p,m,run,day,now;
	String s,ver,
	s1="请ROOT后再试！",
	bb="busybox",
	bi="busybox --install -s %s",
	bp="/system/xbin",
	f="/sdcard/djwdj/BusyBox",
	fb="/sdcard/djwdj/BusyBox/busybox",
	ft="/sdcard/djwdj/BusyBox/thermal-engine",
	ftc="/sdcard/djwdj/BusyBox/thermal-engine.conf",
	ff="%s/busybox",
	tc,
	te="",
	mirw="mount -o rw,remount,rw /system",
	rw="mount -o rw,remount /system",
	ro="mount -o ro,remount /system",
	cp="cp -f %s %s",
	rm="rm -f %s",
	mv="mv -f %s %s ",
	cat="cat %s",
	echo="echo %s > %s",
	touch="touch %s",
	chmod="chmod 0755 %s"
	;
	String[] bn={"☆安装☆","☆卸载☆","☆更多☆","☆温控☆","☆关于☆"};
	
	String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.INTERNET};
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		sp=getSharedPreferences("l", 0);
		day =sp.getInt("day",0);
		now=Integer.parseInt(new SimpleDateFormat("MMdd").format(new Date()));
		
		ff=String.format(ff,bp);
		bi=String.format(bi,bp);
		
		if(Build.VERSION.SDK_INT>9)
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
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

		lo=new LinearLayout(this);
		lo.setPadding(p,0,p,p);
		lo.setClickable(true);
		lo.setOrientation(LinearLayout.VERTICAL);
		lo.setBackgroundDrawable(d(w/10,0xbbcccccc));
		lo.setLayoutParams(lp);
		l.addView(lo);
		
		l00=new LinearLayout(this);
		l00.setOrientation(LinearLayout.VERTICAL);
		lo.addView(l00);
		
		sc=new ScrollView(this);
		lo.addView(sc);
		
		ll=new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		sc.addView(ll);
		
		l0=new LinearLayout(this);
		l0.setOrientation(LinearLayout.VERTICAL);
		ll.addView(l0);
		
		l1=new LinearLayout(this);
		l1.setVisibility(8);
		l1.setOrientation(LinearLayout.VERTICAL);
		ll.addView(l1);
		
		l2=new LinearLayout(this);
		l2.setVisibility(8);
		l2.setOrientation(LinearLayout.VERTICAL);
		ll.addView(l2);

		l3=new LinearLayout(this);
		l3.setVisibility(8);
		l3.setOrientation(LinearLayout.VERTICAL);
		ll.addView(l3);
		
		try
		{
			ver = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
		}
		catch (PackageManager.NameNotFoundException e)
		{}
		
		t(sb("BusyBox",ver),l00);
		
		t(Build.MODEL+"_"+Build.VERSION.RELEASE+"_"+Build.VERSION.SDK,l00);
		
		//t(Build.VERSION.RELEASE+" ("+Build.VERSION.SDK+")",l00);
		
		//t(new File("/system/xbin/busybox").exists()?"未安装":"已安装");
		
		for(int i=0;i<bn.length;i++)b(i,bn[i],l0);
		
		f(f);
		
		setContentView(l);
		//addContentView(l, new WindowManager.LayoutParams());
		
		//new Handler().postDelayed(this, 500);
		r(1,500);
	}
	
	boolean up()
	{
		String s;
		s="aHR0cDovL2Rqd2RqLmNuLy0vbC5waHA/bD0";
		s = new String(android.util.Base64.decode(s, android.util.Base64.DEFAULT));
		s=new StringBuilder(s).append(getPackageName()).toString();
		int i0 = 0,i1 = 0;
		try
		{
			i0 = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
		}
		catch (Exception e)
		{}
		try
		{
			InputStream is = new URL(s).openConnection().getInputStream();
			byte[] b = new byte[3];
			i1 =Integer.parseInt(new String(b, 0,is.read(b)));
		}
		catch(Exception e)
		{}
		return i1>i0;
	}
	void r(int r,int t)
	{
		run=r;
		new Handler().postDelayed(this,t);
	}
	@Override
	public void run()
	{
		switch(run)
		{
			case 1:
				try
				{
					String s=su("busybox");
					s = s.substring(0, s.indexOf("(") - 1);
					t(s,l00);
					s=su("which busybox");
					t(s,l00);
				}
				catch (Exception e)
				{}
				if(day!=now)
				{
					sp.edit().putInt("day",now).commit();
					if(up())
					{
						Toast("【更新提示】\n\n有新版本可升级！\n\n☆基哥云计算☆");
						
					}
				}
				break;
			case 2:
				if(up())Toast("【更新提示】\n\n发现新版本,请升级！\n\n☆基哥云计算☆");
				else Toast("【更新检测】\n\n已是最新版本,无需升级！\n\n☆基哥云计算☆");
				break;

		}
		
		
		
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
				
				try
				{
					c("busybox", fb);
				}
				catch (IOException e)
				{
					Toast("没有储存权限，请授权后再试！");
					if (Build.VERSION.SDK_INT >= 9)
						startActivity(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", getPackageName(), null)));
				}
				try
				{
					s(rw);
					s(String.format(cp,fb,ff));
					s(String.format(chmod,ff));
					s(ro);
					
					if (!r("echo 基哥科技").equals("基哥科技"))
						((Button)v).setText(s1);
				}
				catch (IOException e)
				{
					((Button)v).setText(s1);
				}
				if (new File(ff).exists())Toast("安装成功！");
				else Toast("未安装！");
				recreate();
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
				if (!new File(ff).exists())Toast("已卸载！");
				break;
			case 2:
				l0.setVisibility(8);
				l1.setVisibility(0);
				
				b(11,"本地安装",l1);
				t("本地安装是把自己的busybox放/sdcard中",l1);
				
				b(12,"开启软链",l1);
				t("谨慎操作！这东西无法关闭！正常不需开启！",l1);
				break;
			case 3:
				l0.setVisibility(8);
				l2.setVisibility(0);
				
				te=su("which thermal-engine");
				
				b(21,"开启温控",l2);
				b(22,"关闭温控",l2);
				t(te,l2);
				b(23,"删除温控",l2);
				b(24,"还原温控",l2);
				try
				{
					if(new File(ftc).exists())
						tc=su(String.format(cat,ftc));
					else
						s(String.format(touch,ftc));
				}
				catch (Exception e)
				{}
				break;
			case 4:
				l0.setVisibility(8);
				l3.setVisibility(0);
				
				Toast("更新检测中……");
				r(2,300);
				
				b(31,"更新",l3);
				b(32,"交流",l3);
				b(33,"捐赠",l3);
				break;
			case 11:
				if(new File("/sdcard/busybox").exists())
				{
					try
					{
						s(rw);
						s(String.format(cp,fb,ff));
						s(String.format(chmod,ff));
						s(ro);

						if (!r("echo 基哥科技").equals("基哥科技"))
							((Button)v).setText(s1);
					}
					catch (IOException e)
					{
						((Button)v).setText(s1);
					}
					if (new File(ff).exists())Toast("安装成功！");
					else Toast("未安装！");
					recreate();
				}
				break;
			case 21:
				try
				{
					s("start thermald\nstart thermal-engine");
				}
				catch (IOException e)
				{
					Toast(s1);
				}
				break;
			case 22:
				try
				{
					s("stop thermal-engine");
				}
				catch (IOException e)
				{
					Toast(s1);
				}
				break;
			case 23:
				if(!te.equals(""))
				try
				{
					s(rw);
					s(String.format(mv,te,ft));
					s(String.format(echo,te,ftc));
					tc=te;
					te="";
					Toast("已删除温控文件");
				}
				catch (IOException e)
				{
					Toast(s1);
				}
				else
					Toast("没有温控文件可删除");
				break;
			case 24:
				if(!tc.equals(""))
				try
				{
					s(rw);
					s(String.format(cp,ft,tc));
					s(String.format(chmod,tc));
					s(String.format(rm,ftc));
					s(String.format(rm,ft));
					te=tc;
					tc="";
				}
				catch (IOException e)
				{
					Toast(s1);
				}
				else
					Toast("没有温控备份文件");
				break;
			case 31:
				cool(getPackageName());

				break;
			case 32:
				try
				{
					String s;
					//s="mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3DIibhDCO5YTffQ9Cw-fRzWl-fO0y_Qjdp";
					s="bXFxb3BlbnNka2FwaTovL2JpekFnZW50L3FtL3FyP3VybD1odHRwJTNBJTJGJTJGcW0ucXEuY29tJTJGY2dpLWJpbiUyRnFtJTJGcXIlM0Zmcm9tJTNEYXBwJTI2cCUzRGFuZHJvaWQlMjZrJTNESWliaERDTzVZVGZmUTlDdy1mUnpXbC1mTzB5X1FqZHA";
					s=new String(android.util.Base64.decode(s,android.util.Base64.DEFAULT));
					startActivity(new Intent(null,Uri.parse(s)));

				}
				catch(Exception e)
				{
					Toast("打开支付宝失败");
				}
				startActivity(new Intent(null,Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D4XmeCQPt9NhyQhm8kE7UQ2gIfxf3eO7g")));
				break;
			case 33:
				try
				{
					String s;
					s="alipays://platformapi/startapp?saId=10000007&clientVersion=3.7.0.0718&qrcode=https://qr.alipay.com/tsx03791nki4qabwu92vi97";
					s="YWxpcGF5czovL3BsYXRmb3JtYXBpL3N0YXJ0YXBwP3NhSWQ9MTAwMDAwMDcmY2xpZW50VmVyc2lvbj0zLjcuMC4wNzE4JnFyY29kZT1odHRwczovL3FyLmFsaXBheS5jb20vdHN4MDM3OTFua2k0cWFid3U5MnZpOTc";
					s=new String(android.util.Base64.decode(s,android.util.Base64.DEFAULT));
					startActivity(new Intent(null,Uri.parse(s)));

				}
				catch(Exception e)
				{
					Toast("打开支付宝失败");
				}
				break;
			default:finish();
		}
		
	}
	
	@Override
	public void finish()
	{
		if(l0.isShown())
			super.finish();
		else recreate();
	}
	
	void f(String s)
	{
		File dir = new File(s);
		if (!dir.exists())
		{
			dir.mkdirs();
		}
	}
	
	void cool(String s)
	{
		Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse(String.format("https://www.coolapk.com/apk/%s",s)));
		try
		{
			startActivity(i.setPackage("com.coolapk.market"));
		}
		catch (Exception e)
		{
			startActivity(i.setPackage(null));
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
	
	void t(CharSequence s,LinearLayout l)
	{
		t = new TextView(this);
		t.setText(s); 
		t.setTextSize(z);
		t.setTextColor(0xffff5555);
		t.setGravity(Gravity.CENTER);
		l.addView(t);
	}
	
	void b(int i,CharSequence s,LinearLayout l)
	{
		b=new Button(this);
		b.setId(i);
		b.setText(s);
		b.setTextColor(0xffffffff);
		b.setTextSize(z);
		b.setBackgroundDrawable(d(w/16,0xffff5556));
		b.setPadding(p,p,p,p);
		b.setLayoutParams(lp);
		b.setOnClickListener(this);
		l.addView(b);
	}
	void Toast(CharSequence s)
	{
		Toast.makeText(this,s,50).show();
	}
	void Toast(CharSequence s,int t)
	{
		Toast.makeText(this,s,t).show();
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
