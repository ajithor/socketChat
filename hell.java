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
		char[] ch = cl_in_str.toCharArray();
		if(m==1)
		for(int i=0;i<str.length();i++)
		{
			ch[i]=(char)(ch[i]+(i)+k);
		}
		else
		for(int i=0;i<str.length();i++)
		{
			ch[i]=(char)(ch[i]+(i*i)-i+k);
		}
		

		return cl_in_str;
	}
	public static void upload() throws IOException
	{
		if(typ.equals("txt"))
			{up_txt();}
		else if (typ.equals("img"));
			up_img();
	}

	public static void up_img()
	{
		System.out.println("image_upload part");
	}

	public static void up_txt() throws IOException
	{
		
		fout = new PrintWriter(new File(dest_name));
		String msg;
		try{
			while((msg=input.readLine())!=null)
			{
				msg = encrypt(msg,mode);
				fout.println(msg);
				//todo : encrypt and store in database
			
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
					//tryto char[] msg;
					
					gp = input.readLine();
					typ = input.readLine();
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

