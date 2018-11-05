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
		System.out.println("Enter file path");
		String path = scan.next();
		System.out.println("Enter key and Encryption type");
		int p_key=scan.nextInt();
		int m=scan.nextInt();
		System.out.println("Destination name : ");
		Scanner fin = new Scanner(new File(path));
		dest_name = scan.next(); 
		connect_to_Server(gp,dest_name,p_key,m);		
		
		//sending whole file linewise, to the server
		while(fin.hasNext())
		{
			output.println(local_encrypt(fin.nextLine(),p_key));
		}

	}
	public static void download() throws IOException
	{
		System.out.println("Enter filename(exact) : ");
		String dest=scan.next();
		System.out.println("Entered key, encrypted type : ");
		int p_key=scan.nextInt();
		int m=scan.nextInt();
		connect_to_Server(gp,dest,p_key,m);
		//todo get msg from server if his stuff are authenticated
		System.out.println("Enter path where the decrypted file is to be stored : ");
		String path = scan.next();
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
	
	public static void main(String[] args) throws IOException
	{
		scan = new Scanner(System.in);
		
		//todo GUI
		System.out.println ( "get or put");
		gp = scan.next();

		if(gp.equals("put"))
			upload();
		else if(gp.equals("get"))
			download();
		//get
	}
}