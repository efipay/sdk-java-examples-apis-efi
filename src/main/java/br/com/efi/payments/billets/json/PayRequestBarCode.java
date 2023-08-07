package br.com.efi.payments.billets.json;

import java.util.HashMap;

import org.json.JSONObject;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class PayRequestBarCode {
    public static void main(String[] args) {

		Credentials credentials = new Credentials();

		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("codBarras", "Insira_aqui_o_codBarras");

		JSONObject body = new JSONObject();
		body.put("dataPagamento", "2022-06-14");
		body.put("valor", 1000);
		body.put("descricao", "Pagamento de boleto");

			try {
				EfiPay efi = new EfiPay(options);
				JSONObject response = efi.call("payRequestBarCode", params, body);
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
