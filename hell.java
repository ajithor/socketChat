import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.String;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.sql.*;

public class hell{
	public static String gp,typ,dest_name;
	public static ServerSocket listener;
	public static Socket sock;
	public static BufferedReader input;
	public static PrintWriter output;
	public static PrintWriter fout;
	public static Scanner fin;
	public static int mode,key;
	public static Statement stm;
	public static Connection con;
	public static int count = 1;
	public static ResultSet rs;
	
	public static void db_connect() throws SQLException
	{
		try{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost/jdb","root","root123");
		try{
		stm = con.createStatement();
		}
		catch(SQLException e)
		{System.out.println("Execp "+ e);}		
		//PreparedStatement pstm = con.prepareStatement();
		}
		catch(SQLException e)
		{System.out.println("Excep : "+e);}
		catch(ClassNotFoundException e )
		{System.out.println("Excep : "+e);}
	}

	public static String encrypt(String cl_in_str,int m, int k)
	{//function for some kind of encryption
		byte[] ch = cl_in_str.getBytes();
		if(m==1)
		for(int i=0;i<cl_in_str.length();i++)
		{
			ch[i]=(byte)(ch[i]+(i%5)+k);
		}
		else
		for(int i=0;i<cl_in_str.length();i++)
		{
			ch[i]=(byte)(ch[i]+(i*i)-i+k);
		}
		

		cl_in_str=new String(ch);
		return cl_in_str;
	}
	public static String decrypt(String cl_in_str,int m, int k)
	{
		byte[] ch = cl_in_str.getBytes();
		if(m==1)
		for(int i =0;i<cl_in_str.length();i++)
		{
			ch[i]=(byte)(ch[i]-(i%5)-k);
		}
		else
		for(int i=0;i<cl_in_str.length();i++)
		{
			ch[i]=(byte)(ch[i]-(i*i)-i-k);
		}
		cl_in_str=new String(ch);
		return cl_in_str;
	}
	
	public static void upload() throws IOException
	{
		
		fout = new PrintWriter(new File(dest_name));
		
		key=Integer.parseInt(input.readLine());
		mode=Integer.parseInt(input.readLine());
		
		//todo first, check if already exists.

		try{
		count ++;
		String db_upload = "insert into Rot values("+count+",'"+dest_name+"');";
		stm.executeUpdate(db_upload);

		db_upload = "insert into Gen values ("+key+","+mode+","+count+");";
		stm.executeUpdate(db_upload);

		//send back file id to client
		output.println(count);
		}
		catch(SQLException e)
		{System.out.println("Excep : "+e);}
		

		String msg;
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
	public static void download() throws IOException
	{
		fin = new Scanner(new File(dest_name));
		String msg;
		key=Integer.parseInt(input.readLine());
		mode=Integer.parseInt(input.readLine());
		//todo check in database for the matching of key and mode and filename
		int cn=-1;
		try{
		String db_download = "Select g.file_id from Gen g,Rot r where g.file_id=r.file_id and g.secret_key = "+key+" and g.mode = "+mode+" and r.file_name='"+dest_name+"';";
		rs = stm.executeQuery(db_download);

		//find number of matching rows
		rs.last();
		cn=rs.getRow();
		}
		catch(SQLException e)
		{System.out.println("Excep :"+e);}
		if(cn==0)				//no matching files
		{
			output.println(-1);
		}
		else if(cn>1)			//more than 1 matching filename-key-mod(should be not allowed during upload)
		{
			output.println(0);
		}
		else 					//perfect match 
			output.println(1);
		{
		try{
		while((msg=fin.nextLine())!=null)
		{
			msg=new String(decrypt(msg,mode,key));
			output.println(msg);
		}
		}
		finally{
			fin.close();
			sock.close();
		}
		}
	}
	public static void main(String[] args) throws IOException {
		/*todo:
		create table Chatlogs
		(p_key int, from ip,to ip,date date, time time, messege varchar(1000))	
		*/
		System.out.println("Server Booted Successfully");
		listener = new ServerSocket(9090);
		try{
		db_connect();
		rs=stm.executeQuery("select max(file_id) from Rot");
		rs.next();
		count=rs.getInt(1);
		}
		catch(SQLException e)
		{System.out.println("Execp "+ e);}
	
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
					if(gp.equals("get"))
						download();

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

