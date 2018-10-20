import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.String;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

public class hell{
	public static String gp,typ,dest_name;
	public static ServerSocket listener;
	public static Socket sock;
	public static BufferedReader input;
	public static PrintWriter output;
	public static PrintWriter fout;
	public static int mode,key;

	public static String encrypt(String cl_in_str,int m, int k)
	{//function for some kind of encryption
		byte[] ch = cl_in_str.getBytes();
		if(m==1)
		for(int i=0;i<cl_in_str.length();i++)
		{
			ch[i]=(byte)(ch[i]+(i)+k);
		}
		else
		for(int i=0;i<cl_in_str.length();i++)
		{
			ch[i]=(byte)(ch[i]+(i*i)-i+k);
		}
		

		cl_in_str=new String(ch);
		return cl_in_str;
	}
	
	public static void upload() throws IOException
	{
		
		fout = new PrintWriter(new File(dest_name));
		String msg;
	
		key=Integer.parseInt(input.readLine());
		mode=Integer.parseInt(input.readLine());
		try{
			while((msg=input.readLine())!=null)
			{
				msg =new String(encrypt(msg,mode,key));
				fout.println(msg);
				//todo : store in database
			
			}	//while end
		}		//try of while(true) ends
		finally{
			fout.close();
			sock.close();
		}//finally of while(true) ends
	}

	public static void main(String[] args) throws IOException {
		/*todo:
		create table Chatlogs
		(p_key int, from ip,to ip,date date, time time, messege varchar(1000))	
		*/
		System.out.println("Server Booted Successfully");
		listener = new ServerSocket(9090);
		try{
			while(true)
			{
				sock = listener.accept();
				try{
					input = new BufferedReader(new InputStreamReader(sock.getInputStream()));
					output = new PrintWriter(sock.getOutputStream(),true);
					
					/*
					output.println(sock.getRemoteSocketAddress().toString());
					*/					
					gp = input.readLine();
					dest_name = input.readLine();
					if(gp.equals("put"))
						upload();

				}
				finally{
					System.out.println("client Ended");
					sock.close();
					continue;	
				}
			}
		}
		finally{
			listener.close();		
		}
	}
}

