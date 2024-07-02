package br.com.efi.payments.billets.webhooks.map;

import java.util.HashMap;
import java.util.Map;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class PayDeleteWebhook {
    public static void main(String[] args) {
    	Credentials credentials = new Credentials();

		
		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());

    	Map<String, Object> body = new HashMap<String, Object>();
        body.put("url", "https://seudominio.com.br/webhook");

		try {
			EfiPay efi= new EfiPay(options);
			Map<String, Object> response = efi.call("payDeleteWebhook", new HashMap<String,String>(), body);
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
