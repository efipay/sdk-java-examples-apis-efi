package br.com.efi.pix.webhooks.json;

import java.util.HashMap;

import org.json.JSONObject;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class PixConfigWebhook {
    public static void main(String[] args) {

	  Credentials credentials = new Credentials();

      JSONObject options = new JSONObject();
      options.put("client_id", credentials.getClientId());
      options.put("client_secret", credentials.getClientSecret());
      options.put("certificate", credentials.getCertificate());
      options.put("sandbox", credentials.isSandbox());

      options.put("x-skip-mtls-checking", "true");

        HashMap<String, String> params = new HashMap<String, String>();
		    params.put("chave", "Insira_aqui_sua_chave");

        JSONObject body = new JSONObject();
        body.put("webhookUrl", "https://seudominio.com.br/webhook/");

        try {
          EfiPay efi = new EfiPay(options);
          JSONObject response = efi.call("pixConfigWebhook", params, body);
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
