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
	public static String ip = "127.0.0.1";
	public static int po = 9090;
	public static Scanner scan;
	public static String gp,typ,dest_name;
	public static int mode,key;

	public static void connect_to_Server(String g,String t,String d) throws IOException
	{
		try{
		soc = new Socket(ip,po);
		input = new BufferedReader(new InputStreamReader(soc.getInputStream()));
		output = new PrintWriter(soc.getOutputStream(),true);
		output.println(g);
		output.println(t);
		output.println(d);
		}
		catch(IOException e)
		{System.out.println(""+e);}
		
	}
	public static void upload() throws IOException
	{
		//TODO GUI
		System.out.println("img or txt");
		typ = scan.next();
	
		if(typ.equals("txt"))
			up_txt();
		if(typ.equals("img"))
			up_img();
	
	
	}
	public static void up_txt() throws IOException
	{
		//todo GUI
		System.out.println("Enter file path");
		String path = scan.next();
		System.out.println("Enter key and Encryption type");
		scan.
		Scanner fin = new Scanner(new File(path));
		System.out.println("Destination name : ");
		dest_name = scan.next(); 
		connect_to_Server(gp,typ,dest_name);		
		
		//sending whole file linewise, to the server
		
		while(fin.hasNext())
		{
			output.println(fin.nextLine());
		}

	}
	public static void up_img()
	{

	}
	public static void main(String[] args) throws IOException
	{
		scan = new Scanner(System.in);
		
		//todo GUI
		System.out.println ( "get or put");
		gp = scan.next();

		if(gp.equals("put"))
			upload();

		//get

	}
}