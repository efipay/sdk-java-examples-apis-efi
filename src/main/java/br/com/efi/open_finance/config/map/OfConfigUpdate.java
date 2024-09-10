package br.com.efi.open_finance.config.map;

import java.util.HashMap;
import java.util.Map;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class OfConfigUpdate {
    public static void main(String[] args) {
        Credentials credentials = new Credentials();

        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("client_id", credentials.getClientId());
        options.put("client_secret", credentials.getClientSecret());
        options.put("certificate", credentials.getCertificate());
        options.put("sandbox", credentials.isSandbox());

        Map<String, Object> body = new HashMap<String, Object>();
		    body.put("redirectURL", "https://client.com/redirect/here");
        body.put("webhookURL", "https://client.com/callback/here");

        Map<String, Object> webhookSecurity = new HashMap<String, Object>();
        webhookSecurity.put("type", "mtls");
        body.put("webhookSecurity", webhookSecurity);
        body.put("processPayment", "async");

        try {
            EfiPay efi = new EfiPay(options);
            Map<String, Object> response = efi.call("ofConfigUpdate", new HashMap<String,String>(), body);
            System.out.println(response);
          }catch (EfiPayException e){
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
          }
          catch (Exception e) {
            System.out.println(e.getMessage());
          }
    }
}
