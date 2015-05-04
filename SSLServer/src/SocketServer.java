import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManagerFactory;

public class SocketServer {
	public static void main(String args[]) throws Exception {
	/*	System.setProperty("javax.net.ssl.keyStore",
				"D:\\exp\\SSLServer\\keys\\trust.jks");
	
		System.setProperty("javax.net.ssl.keyStorePassword", "password");	
		
		System.setProperty("javax.net.debug", "all");
		
		SSLServerSocketFactory sslfact = (SSLServerSocketFactory) SSLServerSocketFactory
				.getDefault();
		SSLServerSocket setverSocket = (SSLServerSocket) sslfact
				.createServerSocket(5432);
		 while (true) {
		      Socket client = setverSocket.accept();
		      sendWelcomeMessage(client);
		    }*/

		String STORETYPE = "JKS";
		String KEYSTORE = "D:\\exp\\SSLServer\\keys\\identity.jks";
		String STOREPASSWORD = "password";
		String KEYPASSWORD = "privatepassword";

		KeyStore ks;
		try {
			ks = KeyStore.getInstance( STORETYPE );
		
		File kf = new File( KEYSTORE );
		ks.load( new FileInputStream( kf ), STOREPASSWORD.toCharArray() );

		KeyManagerFactory kmf = KeyManagerFactory.getInstance( "SunX509" );
		kmf.init( ks, KEYPASSWORD.toCharArray() );
		TrustManagerFactory tmf = TrustManagerFactory.getInstance( "SunX509" );
		tmf.init( ks );

		SSLContext sslContext = null;
		sslContext = SSLContext.getInstance( "TLS" );
		sslContext.init( kmf.getKeyManagers(), tmf.getTrustManagers(), null );
		SSLServerSocketFactory sslfact = (SSLServerSocketFactory) SSLServerSocketFactory
				.getDefault();
		SSLServerSocket setverSocket = (SSLServerSocket) sslfact
				.createServerSocket(5432);
		SSLSocket sslsocket = (SSLSocket) setverSocket.accept();
		 // read response
        BufferedReader rd = new BufferedReader(new InputStreamReader(sslsocket.getInputStream()));           
        String string = null;

        while ((string = rd.readLine()) != null) {
            System.out.println(string);
            System.out.flush();
        }
       
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 private static void sendWelcomeMessage(Socket s) throws IOException {
		 OutputStream out = s.getOutputStream();
		    out.write("\nConnection established.\n\n".getBytes());
		    out.flush();
		    int theCharacter = 0;
		    theCharacter = System.in.read();
		    while (theCharacter != '~') // The '~' is an escape character to exit
		    {
		      out.write(theCharacter);
		      out.flush();
		      theCharacter = System.in.read();
		    }

		    out.close();
		    s.close();
	       
	    }
}