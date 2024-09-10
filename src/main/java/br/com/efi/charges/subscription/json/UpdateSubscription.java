package br.com.efi.charges.subscription.json;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class UpdateSubscription {
	public static void main(String[] args) {
		/* *********  Set credential parameters ******** */

		Credentials credentials = new Credentials();

		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("sandbox", credentials.isSandbox());

		/* ************************************************* */

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", "1223802");

		// items
		JSONArray items = new JSONArray();

		JSONObject item1 = new JSONObject();
		item1.put("name", "Item 5");
		item1.put("amount", 1);
		item1.put("value", 6000);

		items.put(item1);

		// customer
		JSONObject customer = new JSONObject();
		customer.put("phone_number", "5744916523");

		JSONObject body = new JSONObject();
		body.put("plan_id", 116587);
		body.put("items", items);
		body.put("customer", customer);

		
		try {
            EfiPay efi = new EfiPay(options);
            JSONObject response = efi.call("updateSubscription", params, body);
            System.out.println(response);
        }catch (EfiPayException e){
            System.out.println(e.getCode());
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

	}
}
