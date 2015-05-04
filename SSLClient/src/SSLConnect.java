import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.security.KeyStoreException;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

public class SSLConnect {
	public void connect(String str) {

				String STORETYPE = "JKS";
				String KEYSTORE = "D:\\exp\\SSLClient\\keys\\identity.jks";
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
				// sslContext.init( null, null, null ); // will use java's default key and trust store which is sufficient unless you deal with self-signed certificates

				SSLSocketFactory factory = sslContext.getSocketFactory();// (SSLSocketFactory) SSLSocketFactory.getDefault();


				SSLSocket sslsocket = (SSLSocket) factory.createSocket(
						"localhost", 5432);
				System.out.println("CONNECTION ESTABLISHED");

				
	            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(sslsocket.getOutputStream()));            
	            wr.write(str);
	          
	             
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
}

