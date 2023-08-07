package br.com.efi.charges.carnet.json;

import java.util.HashMap;

import org.json.JSONObject;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class UpdateCarnetParcel {

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
		params.put("parcel", "1");
		
		JSONObject body = new JSONObject();
		body.put("expire_at", "2018-01-01");
		
		try {
			EfiPay efi = new EfiPay(options);
			JSONObject response = efi.call("updateCarnetParcel", params, body);
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
