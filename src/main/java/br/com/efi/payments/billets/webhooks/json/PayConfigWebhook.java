package br.com.efi.payments.billets.webhooks.json;

import java.util.HashMap;

import org.json.JSONObject;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class PayConfigWebhook {
    public static void main(String[] args) {

	  Credentials credentials = new Credentials();

      JSONObject options = new JSONObject();
      options.put("client_id", credentials.getClientId());
      options.put("client_secret", credentials.getClientSecret());
      options.put("certificate", credentials.getCertificate());
      options.put("sandbox", credentials.isSandbox());

      options.put("x-skip-mtls-checking", "true");

        JSONObject body = new JSONObject();
        body.put("url", "https://seudominio.com.br/webhook");

        try {
          EfiPay efi = new EfiPay(options);
          JSONObject response = efi.call("payConfigWebhook", new HashMap<String,String>(), body);
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
