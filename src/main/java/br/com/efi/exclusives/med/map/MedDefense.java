package br.com.efi.exclusives.med.map;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class MedDefense {
    public static void main(String[] args) throws IOException {
        Credentials credentials = new Credentials();

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("idInfracao", "id_infracao");

		Map<String, Object> body = new HashMap<String, Object>();	
        body.put("analise", "aceito");
        body.put("justificativa", "Justificativa");
		
		try {
			EfiPay efi = new EfiPay(options);
			Map<String, Object> response = efi.call("medDefense", params, body);
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