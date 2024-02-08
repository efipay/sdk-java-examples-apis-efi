package br.com.efi.charges.carnet.json;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class UpdateCarnetParcels {

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
		
		JSONArray parcels = new JSONArray();

		JSONObject parcel1 = new JSONObject(); 
		parcel1.put("parcel", 1);
		parcel1.put("expire_at", "2024-03-15");

		JSONObject parcel2 = new JSONObject(); 
		parcel2.put("parcel", 2);
		parcel2.put("expire_at", "2024-04-15");

		JSONObject parcel3 = new JSONObject(); 
		parcel3.put("parcel", 3);
		parcel3.put("expire_at", "2024-05-15");

		parcels.put(parcel1);
		parcels.put(parcel2);
		parcels.put(parcel3);
		
		JSONObject body = new JSONObject();
		body.put("parcels", parcels);
		
		try {
			EfiPay efi = new EfiPay(options);
			JSONObject response = efi.call("updateCarnetParcels", params, body);
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
