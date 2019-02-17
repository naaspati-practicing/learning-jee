package org.glassfish.jersey.examples.httpsclientservergrizzly;

import org.glassfish.grizzly.ssl.SSLContextConfigurator;
import org.glassfish.grizzly.ssl.SSLEngineConfigurator;

public class SSLFactory {
	
	private static final String KEYSTORE_SERVER_FILE = "./creds/keystore_server";
    private static final String KEYSTORE_SERVER_PWD = "asdfgh";
    private static final String TRUSTORE_SERVER_FILE = "./creds/truststore_server";
    private static final String TRUSTORE_SERVER_PWD = "asdfgh";

	public static SSLEngineConfigurator createSSLEngine() {
		SSLContextConfigurator ssl_context = new SSLContextConfigurator();
		
		  // set up security context
      ssl_context.setKeyStoreFile(KEYSTORE_SERVER_FILE); // contains server keypair
      ssl_context.setKeyStorePass(KEYSTORE_SERVER_PWD);
      ssl_context.setTrustStoreFile(TRUSTORE_SERVER_FILE); // contains client certificate
      ssl_context.setTrustStorePass(TRUSTORE_SERVER_PWD);

      SSLEngineConfigurator ssl_engine = new SSLEngineConfigurator(ssl_context)
      .setClientMode(false)
      .setNeedClientAuth(true);
      
      return ssl_engine;
	} 
}
