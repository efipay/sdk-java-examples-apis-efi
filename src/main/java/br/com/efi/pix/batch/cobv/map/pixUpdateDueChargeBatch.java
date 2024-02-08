package br.com.efi.pix.batch.cobv.map;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class pixUpdateDueChargeBatch {

	public static void main(String[] args) throws IOException {

		Credentials credentials = new Credentials();

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", "1");

		List<Object> cobsv = new ArrayList<Object>();

		Map<String, Object> cobsv1 = new HashMap<String, Object>();
		cobsv1.put("calendario",new JSONObject().put("dataDeVencimento", "2024-05-02"));
		cobsv1.put("txid", "fb2761260e554ad593c9a26beb5cb650");
		cobsv1.put("valor", new JSONObject().put("original", "0.10"));

		Map<String, Object> cobsv2 = new HashMap<String, Object>();
		cobsv2.put("calendario", new JSONObject().put("dataDeVencimento", "2024-03-02"));
		cobsv2.put("txid", "7978c0c97ea847e68e8849634473c1f1");
		cobsv2.put("valor", new JSONObject().put("original", "0.10"));

		cobsv.add(cobsv1);
		cobsv.add(cobsv2);

		Map<String, Object> body = new HashMap<String, Object>();
		// body.put("descricao", "Cobranças dos alunos do turno vespertino");
		body.put("cobsv", cobsv);

		try {
			EfiPay efi = new EfiPay(options);
			Map<String, Object> response = efi.call("pixUpdateDueChargeBatch", params, body);
			System.out.println(response);
		} catch (EfiPayException e) {
			System.out.println(e.getError());
			System.out.println(e.getErrorDescription());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
