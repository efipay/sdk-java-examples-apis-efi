package br.com.efi.pix.split.config.json;
//falta esse
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class PixSplitConfig {
    public static void main(String[] args) {
		Credentials credentials = new Credentials();

		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());

		JSONObject minhaParte = new JSONObject().put("tipo", "porcentagem").put("valor", "50.00");
      	JSONObject favorecido_1 = new JSONObject().put("cpf", "12345678909").put("conta", "1234567");
     	JSONObject favorecido_2 = new JSONObject().put("cpf", "94271564656").put("conta", "7654321");

		JSONArray repasses = new JSONArray();
		repasses.put(new JSONObject().put("tipo", "porcentagem").put("valor", "25.00").put("favorecido", favorecido_1));
		repasses.put(new JSONObject().put("tipo", "porcentagem").put("valor", "25.00").put("favorecido", favorecido_2));

		JSONObject body = new JSONObject();
		body.put("descricao", "Batatinha frita 1, 2, 3");
		body.put("lancamento", new JSONObject().put("imediato", true));
		body.put("split", new JSONObject().put("divisaoTarifa", "assumir_total")
			.put("minhaParte", minhaParte)
			.put("repasses", repasses));
	
		try {
			EfiPay efi = new EfiPay(options);
			JSONObject response = efi.call("pixSplitConfig", new HashMap<String,String>(), body);
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
