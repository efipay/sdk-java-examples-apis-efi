package br.com.efi.charges.subscription.json;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class CreateOneStepSubscriptionLink {
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
		
		// items
		JSONArray items = new JSONArray();

		JSONObject item1 = new JSONObject();
		item1.put("name", "Item 4");
		item1.put("amount", 1);
		item1.put("value", 500);

		JSONObject item2 = new JSONObject("{\"name\":\"Item 2\", \"amount\":1, \"value\":1000}");

		items.put(item1);
		items.put(item2);
		
		JSONObject seetings = new JSONObject();
		seetings.put("billet_discount", 10);
		seetings.put("card_discount", 10);
		seetings.put("message", "link test");
		seetings.put("expire_at", "2022-10-18");
		seetings.put("request_delivery_address", false);
		seetings.put("payment_method", "all");

		//notification url
		JSONObject metadata = new JSONObject();
		metadata.put("notification_url", "http://domain.com/notification");

		JSONObject body = new JSONObject();
		body.put("items", items);
		body.put("settings", seetings);
		body.put("metadata", metadata);
		
		try {
		    EfiPay efi = new EfiPay(options);
		    JSONObject response = efi.call("createOneStepSubscriptionLink", params, body);
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
