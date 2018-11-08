package p;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import java.io.File;

public class clientA
{
	public static Socket soc;
	public static BufferedReader input;
	public static PrintWriter output;
	public static PrintWriter fout;
	public static String ip = "127.0.0.1";
	public static int po = 9090;
	public static Scanner scan;
	public static String gp,typ,dest_name;
	public static int mode,key;

	public static void connect_to_Server(String g,String d,int p,int m) throws IOException
	{
		try{
		soc = new Socket(ip,po);
		input = new BufferedReader(new InputStreamReader(soc.getInputStream()));
		output = new PrintWriter(soc.getOutputStream(),true);
		output.println(g);
		output.println(d);
		output.println(p);
		output.println(m);
		}
		catch(IOException e)
		{System.out.println(""+e);}
	}

	public static String local_encrypt(String s,int k)
	{
		byte[] ch = s.getBytes();
		for(int i=0;i<s.length();i++)
		{
			ch[i]=(byte)(ch[i]+(i)+k);
		}

		return s;
	}
	public static void upload() throws IOException
	{
		//TODO GUI
		String path =frup.textField1.getText() ;
	
		int p_key=Integer.parseInt(frup.textField2.getText() );
		int m=frup.sleenc;
		gp="put";
		dest_name = frup.textField3.getText();
		Scanner fin = new Scanner(new File(path));
		
		connect_to_Server(gp,dest_name,p_key,m);		
		//check if all is ok
		int ack =Integer.parseInt(input.readLine());
		if(ack>0)
		//sending whole file linewise, to the server
		{
		while(fin.hasNext())
		{
			output.println(local_encrypt(fin.nextLine(),p_key));
		}
		}
		else
			//todo GUI
			System.out.println("All not OK! lol");

	}
	public static void download() throws IOException
	{
		
		String dest=frdw.textField4.getText();
		
		int p_key=Integer.parseInt(frdw.textField5.getText());
		int m=1;
		gp="get";
		connect_to_Server(gp,dest,p_key,m);
		//todo get msg from server if his stuff are authenticated
		int ack = Integer.parseInt(input.readLine());
		if(ack==0)
			//todo GUI like a dialog box and log the client out.
			System.out.println("Input mismatch in database");
		else if(ack>1)
			System.out.println("Multiple matches");//shouldnt be allowed

		else
		{
		String path = frdw.textField6.getText();
		String msg;
		fout = new PrintWriter(new File(path));
		try{
		while((msg=input.readLine())!=null)
		{
			fout.println(msg);
		}
		}
		finally{
			fout.close();
		}
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		
	}
}