package br.com.fiap.blueriver.configuration;

import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

public class UnsafeTrustManager implements X509TrustManager {
    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[]{};
    }

    @Override
    public void checkClientTrusted(X509Certificate[] certs, String authType) {
    }

    @Override
    public void checkServerTrusted(X509Certificate[] certs, String authType) {
    }
}
