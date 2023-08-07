package br.com.efi.charges.card.json;

import java.util.HashMap;

import org.json.JSONObject;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class DefineCardPayMethod {
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
		
		String paymentToken = "Insira_aqui_o_payment_token";
		
		JSONObject customer = new JSONObject();
		customer.put("name", "Gorbadoc Oldbuck");
		customer.put("cpf", "94271564656");
		customer.put("phone_number", "5144916523");
		customer.put("email", "client_email@server.com.br");
		customer.put("birth", "1977-01-15");
		
		JSONObject billingAddress = new JSONObject();
		billingAddress.put("street", "Av. JK");
		billingAddress.put("number", 909);
		billingAddress.put("neighborhood", "Bauxita");
		billingAddress.put("zipcode", "5400000");
		billingAddress.put("city", "Ouro Preto");
		billingAddress.put("state", "MG");
		
		JSONObject creditCard = new JSONObject();
		creditCard.put("installments", 1);
		creditCard.put("billing_address", billingAddress);
		creditCard.put("payment_token", paymentToken);
		creditCard.put("customer", customer);

		JSONObject payment = new JSONObject();
		payment.put("credit_card", creditCard);

		JSONObject body = new JSONObject();
		body.put("payment", payment);

		try {
		    EfiPay efi = new EfiPay(options);
		    JSONObject response = efi.call("definePayMethod", params, body);
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
