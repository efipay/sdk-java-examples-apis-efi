package br.com.efi.pix.batch.cobv.json;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class pixUpdateDueChargeBatch {
	public static void main(String[] args) throws IOException {

		Credentials credentials = new Credentials();

		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", "1");

		JSONArray cobsv = new JSONArray();

		JSONObject cobsv1 = new JSONObject();
		cobsv1.put("calendario", new JSONObject().put("dataDeVencimento", "2024-04-02"));
		cobsv1.put("txid", "fb2761260e554ad593c9a26beb5cb650");
		cobsv1.put("valor", new JSONObject().put("original", "10.10"));

		JSONObject cobsv2 = new JSONObject();
		cobsv2.put("calendario", new JSONObject().put("dataDeVencimento", "2024-04-02"));
		cobsv2.put("txid", "7978c0c97ea847e68e8849634473c1f1");
		cobsv2.put("valor", new JSONObject().put("original", "10.10"));
		cobsv.put(cobsv1);
		cobsv.put(cobsv2);

		JSONObject body = new JSONObject();
		//body.put("descricao", "Cobranças dos alunos do turno vespertino");
		body.put("cobsv", cobsv);
		try {
			EfiPay efi = new EfiPay(options);
			JSONObject response = efi.call("pixUpdateDueChargeBatch", params, body);
			System.out.println(response);
		} catch (EfiPayException e) {
			System.out.println(e.getError());
			System.out.println(e.getErrorDescription());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
