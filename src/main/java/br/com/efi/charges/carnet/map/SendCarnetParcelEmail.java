package br.com.efi.charges.carnet.map;

import java.util.HashMap;
import java.util.Map;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class SendCarnetParcelEmail {

	public static void main(String[] args) {
		/* *********  Set credentials parameters ******** */

		Credentials credentials = new Credentials();

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("sandbox", credentials.isSandbox());

		/* ************************************************* */ 

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", "0");
		params.put("parcel", "1");

		Map<String, Object> body = new HashMap<String, Object>();
		body.put("email", "client_email@server.com.br");

		try {
			EfiPay efi = new EfiPay(options);
			Map<String, Object> response = efi.call("sendCarnetParcelEmail", params, body);
			System.out.println(response);
		}catch (EfiPayException e){
			System.out.println(e.getCode());
			System.out.println(e.getError());
			System.out.println(e.getErrorDescription());
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
