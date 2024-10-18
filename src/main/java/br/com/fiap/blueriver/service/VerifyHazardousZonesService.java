package br.com.fiap.blueriver.service;

import br.com.fiap.blueriver.configuration.UnsafeTrustManager;
import br.com.fiap.blueriver.model.alertablue.Response;
import br.com.fiap.blueriver.notification.NotificationService;
import br.com.fiap.blueriver.repository.RegionRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service
public class VerifyHazardousZonesService {

    @Autowired
    NotificationService notificationService;

    @Autowired
    RegionRepository repository;

    public void verifyHazardousZones(){
        try{
            var response = doRequest();

            for(var dado : response.dados){
                var tipoNome = dado.tipoNome;

                for(var sitregiao : dado.sitregioes){
                    var regiao = sitregiao.regiao.nome;
                    var condicao = sitregiao.condicao.condicao;

                    if(!condicao.equals("Normalidade")){
                        var users = repository.findAllByRegion(regiao);
                        for(var user : users){
                            var notificationMsg = "Atenção " + user.getUser().getUsername() + " a região " + regiao + " da sua cidade está em " + condicao + " quanto ao " + tipoNome;
                            System.out.println(notificationMsg);
                            notificationService.sendNotification(user.getDeviceId(), "Alerta BluRiver", notificationMsg);
                        }
                    }
                }
            }


        }catch (Exception e ){
            System.out.println(e.getMessage());
        }
    }

    public Response doRequest() throws NoSuchAlgorithmException, KeyManagementException, IOException, InterruptedException {
        var sslContext = SSLContext.getInstance("TLS");
        var trustManager = new UnsafeTrustManager();
        sslContext.init(null, new TrustManager[]{trustManager}, new SecureRandom());

        var client = HttpClient.newBuilder()
                .sslContext(sslContext)
                .build();

        var request = HttpRequest.newBuilder()
                .uri(URI.create("https://alertablu.blumenau.sc.gov.br/static/data/situacao_atual.json"))
                .build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        var json = response.body();

        var gson = new Gson();
        return gson.fromJson(json, Response.class);

    }
}
