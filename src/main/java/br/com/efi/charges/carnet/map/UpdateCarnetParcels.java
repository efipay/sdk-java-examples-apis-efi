package br.com.efi.charges.carnet.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class UpdateCarnetParcels {

	public static void main(String[] args) {		
		/* *********  Set credentials parameters ******** */

		Credentials credentials = new Credentials();

		Map<String, Object> options = new HashMap<String, Object>();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("sandbox", credentials.isSandbox());

		/* ************************************************* */ 

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", "0");

		List<Object> parcels = new ArrayList<Object>();

		Map<String, Object> parcel1 = new HashMap<String, Object>();
		parcel1.put("parcel", 1);
		parcel1.put("expire_at", "2024-03-15");

		Map<String, Object> parcel2 = new HashMap<String, Object>();
		parcel2.put("parcel", 2);
		parcel2.put("expire_at", "2024-04-15");

		parcels.add(parcel1);
		parcels.add(parcel2); 
		
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("items", parcels);
		try {
			EfiPay efi = new EfiPay(options);
			Map<String, Object> response = efi.call("updateCarnetParcels", params, body);
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
