package com.common.system.config;

import java.io.File;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "custom.tomcat.https")
public class TomcatSslConnectorProperties {
    private Integer port;
    private Boolean ssl = true;
    private Boolean secure = true;
    private String scheme = "https";
    private File keystore;
    private String keystorePassword;

    public void configureConnector(Connector connector) {
        if (port != null) {
            connector.setPort(port);
        }
        if (secure != null) {
            connector.setSecure(secure);
        }
        if (scheme != null) {
            connector.setScheme(scheme);
        }
        if (ssl != null) {
            connector.setProperty("SSLEnabled", ssl.toString());
        }
        if (keystore != null && keystore.exists()) {
            connector.setProperty("keystoreFile", keystore.getAbsolutePath());
            connector.setProperty("keystorePassword", keystorePassword);
        }
        
    }

	/**
	 * @return the port
	 */
	public Integer getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(Integer port) {
		this.port = port;
	}

	/**
	 * @return the ssl
	 */
	public Boolean getSsl() {
		return ssl;
	}

	/**
	 * @param ssl the ssl to set
	 */
	public void setSsl(Boolean ssl) {
		this.ssl = ssl;
	}

	/**
	 * @return the secure
	 */
	public Boolean getSecure() {
		return secure;
	}

	/**
	 * @param secure the secure to set
	 */
	public void setSecure(Boolean secure) {
		this.secure = secure;
	}

	/**
	 * @return the scheme
	 */
	public String getScheme() {
		return scheme;
	}

	/**
	 * @param scheme the scheme to set
	 */
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	/**
	 * @return the keystore
	 */
	public File getKeystore() {
		return keystore;
	}

	/**
	 * @param keystore the keystore to set
	 */
	public void setKeystore(File keystore) {
		this.keystore = keystore;
	}

	/**
	 * @return the keystorePassword
	 */
	public String getKeystorePassword() {
		return keystorePassword;
	}

	/**
	 * @param keystorePassword the keystorePassword to set
	 */
	public void setKeystorePassword(String keystorePassword) {
		this.keystorePassword = keystorePassword;
	}
    
}
