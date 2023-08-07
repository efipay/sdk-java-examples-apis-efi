package br.com.efi.charges.carnet.json;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class CreateCarnet {

	public static void main(String[] args) {		
		/* *********  Set credentials parameters ******** */
		
		Credentials credentials = new Credentials();
		
		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("sandbox", credentials.isSandbox());
		
		/* ************************************************* */ 
		
		JSONArray items = new JSONArray();
		
		JSONObject item1 = new JSONObject();
		item1.put("name", "Item 1");
		item1.put("amount", 1);
		item1.put("value", 2000);
		
		JSONObject item2 = new JSONObject("{\"name\":\"Item 1\", \"amount\":1, \"value\":1000}");
		
		items.put(item1);
		items.put(item2);
		
		JSONObject customer = new JSONObject();
		customer.put("name", "Gorbadoc Oldbuck");
		customer.put("cpf", "94271564656");
		customer.put("phone_number", "5144916523");
		
		JSONObject body = new JSONObject();
		body.put("items", items);
		body.put("customer", customer);
		body.put("expire_at", "2022-12-30");
		body.put("repeats", 4);
		body.put("message", "Pague pelo código de barras ou pelo QR Code");
		body.put("split_items", false);
		
		try {
			EfiPay efi = new EfiPay(options);
			JSONObject response = efi.call("createCarnet", new HashMap<String,String>(), body);
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
