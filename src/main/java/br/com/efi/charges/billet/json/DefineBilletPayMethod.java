package br.com.efi.charges.billet.json;

import java.util.HashMap;

import org.json.JSONObject;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class DefineBilletPayMethod {
	public static void main(String[] args) {
		/* *********  Set credentials parameters ******** */
		
		Credentials credentials = new Credentials();
		
		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("sandbox", credentials.isSandbox());

		/* ************************************************* */ 
		

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", "0");

		JSONObject customer = new JSONObject();
		customer.put("name", "Gorbadoc Oldbuck");
		customer.put("cpf", "94271564656");
		customer.put("phone_number", "5144916523");

		JSONObject bankingBillet = new JSONObject();
		bankingBillet.put("expire_at", "2023-12-12");
		bankingBillet.put("customer", customer);
 
		JSONObject payment = new JSONObject();
		payment.put("banking_billet", bankingBillet);

		JSONObject body = new JSONObject();
		body.put("payment", payment);

		try {
		    EfiPay efi = new EfiPay(options);
		    JSONObject response = efi.call("definePayMethod", params, body);
		    System.out.println(response);
		}catch (EfiPayException e){
		    System.out.println(e.getCode());
		    System.out.println(e.getError());
		    System.out.println(e.getErrorDescription());
		}
		catch (Exception e) {
		    System.out.println(e.getMessage());
		}
	}
}
