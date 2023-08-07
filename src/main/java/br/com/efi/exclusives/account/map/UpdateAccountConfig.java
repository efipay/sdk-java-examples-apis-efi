package br.com.efi.exclusives.account.map;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class UpdateAccountConfig {
    public static void main(String[] args) throws IOException {
        Credentials credentials = new Credentials();

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());

		Map<String, Object> body = new HashMap<String, Object>();	
		body.put("pix", new JSONObject()
			.put("receberSemChave", true)
			.put("chaves", new JSONObject()
			.put("Insira_aqui_sua_chave", new JSONObject()
			.put("recebimento", new JSONObject()
			.put("txidObrigatorio", true)
			.put("qrCodeEstatico", new JSONObject()
			.put("recusarTodos", false))))));

			try {
				EfiPay efi = new EfiPay(options);
				Map<String, Object> response = efi.call("updateAccountConfig", new HashMap<String,String>(), body);
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