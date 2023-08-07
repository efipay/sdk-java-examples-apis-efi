package br.com.efi.charges.subscription.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;
public class CreateOneStepCardSubscription {
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
        Map<String, Object> item2 = new HashMap<String, Object>();
        item2.put("name", "Item 2");
        item2.put("amount", 1);
        item2.put("value", 2000);
        items.add(item1);
        items.add(item2);
        
        String paymentToken = "Insira_aqui_o_payment_token";
        
        Map<String, Object> customer = new HashMap<String, Object>();
        customer.put("name", "Gorbadoc Oldbuck");
        customer.put("cpf", "94271564656");
        customer.put("phone_number", "5144916523");
        customer.put("email", "client_email@server.com.br");
        customer.put("birth", "1977-01-15");
        
        Map<String, Object> billingAddress = new HashMap<String, Object>();
        billingAddress.put("street", "Av. JK");
        billingAddress.put("number", 909);
        billingAddress.put("neighborhood", "Bauxita");
        billingAddress.put("zipcode", "35400000");
        billingAddress.put("city", "Ouro Preto");
        billingAddress.put("state", "MG");
        
        Map<String, Object> creditCard = new HashMap<String, Object>();
        creditCard.put("installments", 1);
        creditCard.put("billing_address", billingAddress);
        creditCard.put("payment_token", paymentToken);
        creditCard.put("customer", customer);
        Map<String, Object> payment = new HashMap<String, Object>();
        payment.put("credit_card", creditCard);
        Map<String, Object> body = new HashMap<String, Object>();
        body.put("items", items);        
        body.put("payment", payment);
        
        try {
            EfiPay efi = new EfiPay(options);
            Map<String, Object> response = efi.call("createOneStepSubscription", params, body);
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