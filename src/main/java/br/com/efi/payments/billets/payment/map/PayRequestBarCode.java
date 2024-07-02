package br.com.efi.payments.billets.payment.map;

import java.util.HashMap;
import java.util.Map;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class PayRequestBarCode {
    public static void main(String[] args) {

		Credentials credentials = new Credentials();

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("codBarras", "Insira_aqui_o_codBarras");

		Map<String, Object> body = new HashMap<String, Object>();
		body.put("dataPagamento", "2022-06-14");
		body.put("valor", 1000);
		body.put("descricao", "Pagamento de boleto");

			try {
				EfiPay efi = new EfiPay(options);
				
				Map<String, Object> response = efi.call("payRequestBarCode", params, body);
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
