package netpractice;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyStore.PrivateKeyEntry;

public class InterativeEchoServer {

	private static final int BUF_SIZE = 32;
	
	public static void main(String[] args) throws Exception{
		if(args.length != 1) throw new IllegalArgumentException("Parameter: port num");
		
		int servPort = Integer.parseInt(args[0]);
		System.out.println("portnumber:" + servPort);
		ServerSocket serverSocket= new ServerSocket(servPort);
		
		int recvMessageSize;
		byte byteBuffer[] = new byte[BUF_SIZE];
		
		while(true){
			Socket clntSock = serverSocket.accept();
			System.out.println("接続先のクライアント： " +
	                clntSock.getInetAddress().getHostAddress() + 
	                " 利用ポート番号 " + clntSock.getPort());
			
			InputStream in = clntSock.getInputStream();
			OutputStream out = clntSock.getOutputStream();
			out.write("Connected to Echo server...\r\n".getBytes("latin1"));
			
			 while ((recvMessageSize = in.read(byteBuffer)) != -1) {
	                out.write(byteBuffer, 0, recvMessageSize);
	                System.out.println(byteBuffer);
	            }
	            System.out.println("Closing..");
	            clntSock.close();
		}
	}
}
