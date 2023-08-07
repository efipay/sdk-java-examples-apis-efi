package br.com.efi.charges.payment_link.map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;
public class CreateOneStepLink {
    public static void main(String[] args) {
        /* *********  Set credentials parameters ******** */
        Credentials credentials = new Credentials();
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("client_id", credentials.getClientId());
        options.put("client_secret", credentials.getClientSecret());
        options.put("sandbox", credentials.isSandbox());
        /* ************************************************* */
		
        List<Object> items = new ArrayList<Object>();
        Map<String, Object> item1 = new HashMap<String, Object>();
        item1.put("name", "Item 1");
        item1.put("amount", 1);
        item1.put("value", 1000);
        Map<String, Object> item2 = new HashMap<String, Object>();
        item2.put("name", "Item 2");
        item2.put("amount", 1);
        item2.put("value", 2000);
        items.add(item1);
        items.add(item2);
        
        Map<String, Object> settings = new HashMap<String, Object>();
		settings.put("payment_method", "all");
		settings.put("billet_discount", 0);
		settings.put("card_discount", 0);
		settings.put("expire_at", "2020-12-12");
		settings.put("request_delivery_address", false);
        
        Map<String, Object> metadata = new HashMap<String, Object>();
        metadata.put("notification_url", "http://domain.com/notification");   

        Map<String, Object> body = new HashMap<String, Object>();
        body.put("items", items);        
        body.put("settings", settings);
		body.put("metadata", metadata);
        
        try {
            EfiPay efi = new EfiPay(options);
            Map<String, Object> response = efi.call("createOneStepLink", new HashMap<String,String>(), body);
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