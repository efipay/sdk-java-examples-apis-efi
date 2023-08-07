package br.com.efi.pix.location.map;

import java.util.HashMap;
import java.util.Map;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class PixLocationList {
    public static void main(String[] args) {
        Credentials credentials = new Credentials();

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());

    	HashMap<String, String> params = new HashMap<String, String>();
		params.put("inicio", "2021-04-01T16:01:35Z");
		params.put("fim", "2021-04-21T16:01:35Z");
		params.put("txIdPresente", "true");
		params.put("tipoCob", "cob");
		params.put("paginacao.paginaAtual", "0");
		params.put("paginacao.itensPorPagina", "10");

		try {
			EfiPay efi= new EfiPay(options);
			Map<String, Object> response = efi.call("pixLocationList", params, new HashMap<String, Object>());
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
