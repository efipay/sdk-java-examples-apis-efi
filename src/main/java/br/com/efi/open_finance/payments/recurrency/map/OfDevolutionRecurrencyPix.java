package br.com.efi.open_finance.payments.recurrency.map;

import java.util.HashMap;
import java.util.Map;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class OfDevolutionRecurrencyPix {
    public static void main(String[] args) {
		Credentials credentials = new Credentials();

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("endToEndId", "E090893562024072215009e91a097af2");

    	Map<String, Object> body = new HashMap<String, Object>();	
        body.put("valor", "0.01");

		try {
			EfiPay efi = new EfiPay(options);
			
			Map<String, Object> response = efi.call("ofDevolutionRecurrencyPix", params, body);
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
