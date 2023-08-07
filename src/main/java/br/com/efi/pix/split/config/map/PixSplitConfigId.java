package br.com.efi.pix.split.config.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.json.JSONObject;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class PixSplitConfigId {
    public static void main(String[] args) {
		Credentials credentials = new Credentials();

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", "6aeddee74dd1a890c0ace71f53f70002");

		JSONObject minhaParte = new JSONObject().put("tipo", "porcentagem").put("valor", "50.00");
      	JSONObject favorecido_1 = new JSONObject().put("cpf", "12345678909").put("conta", "1234567");
     	JSONObject favorecido_2 = new JSONObject().put("cpf", "94271564656").put("conta", "7654321");
		
		List<Object> repasses =  new ArrayList<Object>();
		repasses.add(new JSONObject().put("tipo", "porcentagem").put("valor", "25.00").put("favorecido", favorecido_1));
		repasses.add(new JSONObject().put("tipo", "porcentagem").put("valor", "25.00").put("favorecido", favorecido_2));

    	Map<String, Object> body = new HashMap<String, Object>();	
		body.put("descricao", "Batatinha frita 1, 2, 3");
		body.put("lancamento", new JSONObject().put("imediato", true));
		body.put("split", new JSONObject().put("divisaoTarifa", "assumir_total")
			.put("minhaParte", minhaParte)
			.put("repasses", repasses));

		try {
			EfiPay efi = new EfiPay(options);
			
			Map<String, Object> response = efi.call("pixSplitConfigId", params, body);
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
