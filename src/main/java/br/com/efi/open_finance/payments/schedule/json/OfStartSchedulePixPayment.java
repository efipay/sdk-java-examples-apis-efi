package br.com.efi.open_finance.payments.schedule.json;
import java.util.HashMap;

import org.json.JSONObject;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class OfStartSchedulePixPayment {
    public static void main(String[] args) {
		Credentials credentials = new Credentials();

		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());

		options.put("x-idempotency-key", "gxkc18gDqurGJRHIrtGD8NRcFWfignzdWKesdfh");

		JSONObject body = new JSONObject();
		body.put("pagador", new JSONObject().put("idParticipante", "ebbed125-5cd7-42e3-965d-2e7af8e3b7ae")
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
			.put("infoPagador", "Compra dia xx")
			.put("dataAgendamento", "2024-09-06"));
	
	
		try {
			EfiPay efi = new EfiPay(options);
			JSONObject response = efi.call("ofStartSchedulePixPayment", new HashMap<String,String>(), body);
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
