package br.com.efi.open_finance.payment.json;

import java.util.HashMap;

import org.json.JSONObject;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class OfStartPixPayment {
    public static void main(String[] args) {
		Credentials credentials = new Credentials();

		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());

		options.put("x-idempotency-key", "gbkc18gDqurGJRHIrtGD8NRcFWfignzdWKesdfh");

		JSONObject body = new JSONObject();
		body.put("pagador", new JSONObject().put("idParticipante", "06c19499-3412-4125-84b7-d0fbc98b5019")
			.put("cpf", "45204392050").put("cnpj", "90293071000112"));;
		body.put("favorecido", new JSONObject().put("contaBanco", new JSONObject()
			.put("codigoBanco", "364")
			.put("agencia", "0001")
			.put("documento", "11122233344")
			.put("nome", "Luiz Silva")
			.put("conta", "654984")
			.put("tipoConta", "CACC")));
		body.put("valor", "9.90");
		body.put("codigoCidadeIBGE", "3540000");
		body.put("infoPagador", "Compra dia xx");
	
		try {
			EfiPay efi = new EfiPay(options);
			JSONObject response = efi.call("ofStartPixPayment", new HashMap<String,String>(), body);
			System.out.println(response);
		}catch (EfiPayException e){
			System.out.println(e.getError());
			System.out.println(e.getErrorDescription());
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
}
