package br.com.efi.open_finance.payments.schedule.json;

import java.util.HashMap;

import org.json.JSONObject;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay; 
import br.com.efi.efisdk.exceptions.EfiPayException;

public class OfDevolutionSchedulePix {
    public static void main(String[] args) {
		Credentials credentials = new Credentials(); 

		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("identificadorPagamento", "urn:gerencianet:a61e5e1f-66d2-4b1d-92d9-2246a967f5ca");
		
		JSONObject body = new JSONObject();
        body.put("valor", "0.01");
	
		try {
			EfiPay efi = new EfiPay(options);
			JSONObject response = efi.call("ofDevolutionSchedulePix", params, body);
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
