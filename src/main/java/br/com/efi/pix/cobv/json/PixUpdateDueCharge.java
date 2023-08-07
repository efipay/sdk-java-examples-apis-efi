package br.com.efi.pix.cobv.json;

import java.io.IOException;

import java.util.HashMap;

import org.json.JSONObject;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class PixUpdateDueCharge {
	public static void main(String[] args) throws IOException {

		Credentials credentials = new Credentials();

		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("txid", "7978c0c97ea847e78e8849634473c1f1");

		JSONObject body = new JSONObject();
		body.put("devedor",new JSONObject().put("logradouro", "Alameda Souza, Numero 80, Bairro Braz")
			.put("cidade", "Recife")
			.put("uf", "PE")
			.put("cep", "70011750")
			.put("cpf", "12345678909")
			.put("nome", "Francisco da Silva"));
		body.put("valor", new JSONObject().put("original", "123.45"));
		body.put("solicitacaoPagador", "Cobrança dos serviços prestados.");
		try {
			EfiPay efi = new EfiPay(options);
			JSONObject response = efi.call("pixUpdateDueCharge", params, body);
			System.out.println(response);
		} catch (EfiPayException e) {
			System.out.println(e.getError());
			System.out.println(e.getErrorDescription());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}