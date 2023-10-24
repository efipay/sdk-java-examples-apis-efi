package br.com.efi.open_finance.config.json;
import java.util.HashMap;

import org.json.JSONObject;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class OfConfigUpdate {
    public static void main(String[] args) {
        Credentials credentials = new Credentials();

        JSONObject options = new JSONObject();
        options.put("client_id", credentials.getClientId());
        options.put("client_secret", credentials.getClientSecret());
        options.put("certificate", credentials.getCertificate());
        options.put("sandbox", credentials.isSandbox());

        JSONObject body = new JSONObject();
		    body.put("redirectURL", "https://client.com/redirect/here");
        body.put("webhookURL", "https://client.com/callback/here");
        body.put("webhookSecurity", new JSONObject().put("type", "mtls"));
        body.put("processPayment", "async");

        try {
            EfiPay efi = new EfiPay(options);
            JSONObject response = efi.call("ofConfigUpdate", new HashMap<String,String>(), body);
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
