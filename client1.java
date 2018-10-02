import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
public class client1
{
	public static void main(String[] args) throws IOException
	{
		Scanner scan = new Scanner(System.in);
		/*System.out.println("Enter the ip of Machine : ");
		String ip=scan.next();
		System.out.println("Enter port addr : ");
		int po = scan.nextInt();
		System.out.println("Enter a messege");
		String str=scan.next();
		*/
		//Comment the foll 2 lines if non-default ip/port and uncomment the upper stuff(user input) 
		String ip = "127.0.0.1";
		int po = 9090;


		Socket soc = new Socket(ip,po);
		BufferedReader input = new BufferedReader(new InputStreamReader(soc.getInputStream()));
		PrintWriter output = new PrintWriter(soc.getOutputStream(),true);
		output.println("Working");
		String ans = input.readLine();//WORKING
		System.out.println(ans);
		ans=input.readLine();//getDATE
		System.out.println("Date : "+ans);
		String reip=input.readLine();
		System.out.println(reip);
		

		String inp;
		try{
		while(true){
		if((inp=scan.nextLine())!=null)
		{
			output.println(inp);
		}
					}
		}
			
		finally
		{	System.out.println("Bye");
			System.exit(0);}
		

		//delay	
		//System.out.println(ans);
		/*try{
		*TimeUnit.SECONDS.sleep(2);
		*}
		*catch(InterruptedException ex) {
		*		
		*}*/
	}
}