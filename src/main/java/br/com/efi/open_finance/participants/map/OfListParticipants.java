package br.com.efi.open_finance.participants.map;

import java.util.HashMap;
import java.util.Map;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class OfListParticipants {
    public static void main(String[] args) {
        Credentials credentials = new Credentials();

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());

		// HashMap<String, String> params = new HashMap<String, String>();
		// params.put("organizacao", "false");
		// params.put("nome", "Efí"); 

		try {
			EfiPay efi = new EfiPay(options);
			Map<String, Object> response = efi.call("ofListParticipants", new HashMap<String,String>(), new HashMap<String, Object>());
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
