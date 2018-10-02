import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.Integer;

public class hell{
	/*public static String encrypt(String cl_in_str)
	{//function for some kind of encryption
		for( int i =0;i<cl_in_str.length();i++)
			cl_in_str.charAt(i)=Integer.toString(cl_in_str.charAt(i)+1);//todo-->convert toChar()
		return cl_in_str;
	}*/
	public static void main(String[] args) throws IOException {
		/*todo:
		create table Chatlogs
		(p_key int, from ip,to ip,date date, time time, messege varchar(1000))	
		*/
		System.out.println("Server Booted Successfully");
		ServerSocket listener = new ServerSocket(9090);
		try{
			while(true)
			{
				Socket sock = listener.accept();
				try{
					BufferedReader input = new BufferedReader(new InputStreamReader(sock.getInputStream()));
					String s = input.readLine();
					PrintWriter output = new PrintWriter(sock.getOutputStream(),true);
					output.println(s.toUpperCase());
					output.println(new Date().toString());
					output.println(sock.getRemoteSocketAddress().toString());
					output.println(""+sock);
					String msg;
					try{
					while(true){
					if((msg=input.readLine())!=null)
					{
						//todo : encrypt and store in database
						//System.out.println(""+encrypt(msg));
						//to simply print on terminal uncomment this
						System.out.println(""+msg);
					}	
							}//while end
						}//try of while(true) ends
					finally{
						System.out.println("Client Ended");
						sock.close();
						continue;	
					}//finally of while(true) ends
				}
				finally{
					sock.close();
				}
			}
		}
		finally{
			listener.close();		
		}
	}
}