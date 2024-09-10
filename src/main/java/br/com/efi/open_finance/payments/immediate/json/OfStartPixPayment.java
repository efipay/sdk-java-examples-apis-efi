package br.com.efi.open_finance.payments.immediate.json;
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
		body.put("pagador", new JSONObject().put("idParticipante", "9f4cd202-8f2b-11ec-b909-0242ac120002")
			.put("cpf", "51687574219").put("cnpj", "90293071000112"));
		body.put("favorecido", new JSONObject().put("contaBanco", new JSONObject()
			.put("codigoBanco", "09089356")
			.put("agencia", "0001")
			.put("documento", "17558266300")
			.put("nome", "Lucas Silva")
			.put("conta", "654984")
			.put("tipoConta", "CACC")));
		body.put("pagamento", new JSONObject().put("valor", "9.90")
			.put("codigoCidadeIBGE", "5300108")
			.put("infoPagador", "Compra dia xx"));
	
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
