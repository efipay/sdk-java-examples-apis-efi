package br.com.efi.charges.subscription.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class UpdateSubscription {
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

        List<Object> items = new ArrayList<Object>();
        Map<String, Object> item1 = new HashMap<String, Object>();
        item1.put("name", "Item 1");
        item1.put("amount", 1);
        item1.put("value", 1000);
        
        Map<String, Object> customer = new HashMap<String, Object>();
        customer.put("email", "gorbadoc.oldbuck@gmail.com");
        customer.put("phone_number", "5144916523");
        
        Map<String, Object> bankingBillet = new HashMap<String, Object>();
		bankingBillet.put("expire_at", "2022-12-12");
		bankingBillet.put("customer", customer);

		Map<String, Object> body = new HashMap<String, Object>();
		body.put("plan_id", 116580);
        body.put("items", items);
		body.put("customer", customer);
        
        try {
            EfiPay efi = new EfiPay(options);
            Map<String, Object> response = efi.call("updateSubscription", params, body);
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
