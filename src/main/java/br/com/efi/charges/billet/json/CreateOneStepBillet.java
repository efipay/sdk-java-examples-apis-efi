package br.com.efi.charges.billet.json;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class CreateOneStepBillet {
	public static void main(String[] args) {
		/* *********  Set credentials parameters ******** */

		Credentials credentials = new Credentials();

		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("sandbox", credentials.isSandbox());

		/* ************************************************* */ 		
		
		// items
		JSONArray items = new JSONArray();

		JSONObject item1 = new JSONObject();
		item1.put("name", "Item 4");
		item1.put("amount", 1);
		item1.put("value", 5000);

		JSONObject item2 = new JSONObject("{\"name\":\"Item 2\", \"amount\":1, \"value\":1000}");

		items.put(item1);
		items.put(item2);
		
		//customer
		JSONObject customer = new JSONObject();
		customer.put("name", "Gorbadoc Oldbuck");
		customer.put("cpf", "94271564656");
		customer.put("phone_number", "5144916523");
		
		//notification url
		JSONObject metadata = new JSONObject();
		metadata.put("notification_url", "https://seudominio.com.br");
		
		//discount		
		JSONObject discount = new JSONObject();
		discount.put("type","currency");
		discount.put("value",599);
		
		//configurations
		JSONObject configurations = new JSONObject();
		configurations.put("fine", 200);
		configurations.put("interest", 100);
		
		//conditional discount
		JSONObject conditional_discount = new JSONObject();
		conditional_discount.put("type","percentage");
		conditional_discount.put("value", 500);
		conditional_discount.put("until_date", "2023-01-29");
	
		
		JSONObject bankingBillet = new JSONObject();
		bankingBillet.put("expire_at", "2023-01-30");
		bankingBillet.put("customer", customer);
		bankingBillet.put("discount", discount);
		bankingBillet.put("configurations", configurations);
		bankingBillet.put("conditional_discount", conditional_discount);
		bankingBillet.put("message", "Pague pelo código de barras ou pelo QR Code");
		

		JSONObject payment = new JSONObject();
		payment.put("banking_billet", bankingBillet);

		JSONObject body = new JSONObject();
		body.put("payment", payment);
		body.put("items", items);
		body.put("metadata", metadata);
		
		try {
		    EfiPay efi = new EfiPay(options);
		    JSONObject response = efi.call("createOneStepCharge", new HashMap<String,String>(), body);
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
