
package netpractice;

import java.io.BufferedReader;
import java.io.*;
import java.net.Socket;

public class EchoClient {
	public static void main(String[] args) throws Exception{
		String port ="55556";
		String  ip = "192.168.64.24";
		
		int servPort = Integer.parseInt(port);
		
		PrintWriter networkOut = null;
		BufferedReader networkIn = null;
		
		try{
			Socket soc = new Socket(ip,servPort);
			networkIn = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			networkOut = new PrintWriter(soc.getOutputStream());
			
			BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("connecting to echo server");
			while(true){
				String line = userIn.readLine();
				if(line.equals("."))
					break;
				networkOut.println(line);
				networkOut.flush();
				System.out.println("key=> " + line);
				System.out.println("echo=> " + networkIn.readLine());
				
			}
		}catch(Exception e){
			System.err.println(e);
		}finally{
			try{
				if(networkIn != null) networkIn.close();
				if(networkOut != null) networkOut.close();
				
			}catch(Exception e){
				System.err.println(e);
				
			}
		}
		
	}
}
