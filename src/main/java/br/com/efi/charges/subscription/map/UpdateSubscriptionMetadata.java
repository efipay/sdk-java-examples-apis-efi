package br.com.efi.charges.subscription.map;

import java.util.HashMap;
import java.util.Map;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class UpdateSubscriptionMetadata {
	public static void main(String[] args) {
		/* *********  Set credential parameters ******** */

		Credentials credentials = new Credentials();

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("sandbox", credentials.isSandbox());

		/* ************************************************* */

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", "0");

		Map<String, Object> body = new HashMap<String, Object>();
		body.put("notification_url", "http://domain.com/notification");
		body.put("custom_id", "Custom Subscription 0001");
		
		try {
            EfiPay efi = new EfiPay(options);
            Map<String, Object> response = efi.call("updateSubscriptionMetadata", params, body);
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
