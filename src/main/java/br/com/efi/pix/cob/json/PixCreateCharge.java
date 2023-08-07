package br.com.efi.pix.cob.json;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class PixCreateCharge {
    public static void main(String[] args) {

	  Credentials credentials = new Credentials();

      JSONObject options = new JSONObject(); 
      options.put("client_id", credentials.getClientId());
      options.put("client_secret", credentials.getClientSecret());
      options.put("certificate", credentials.getCertificate());
      options.put("sandbox", credentials.isSandbox());

        HashMap<String, String> params = new HashMap<String, String>();
		    params.put("txid", "7978c0c97ea847e78e8849634473c1f1");

        JSONObject body = new JSONObject();
        body.put("calendario", new JSONObject().put("expiracao", 3600));
        body.put("devedor", new JSONObject().put("cpf", "12345678909").put("nome", "Francisco da Silva"));
        body.put("valor", new JSONObject().put("original", "123.45"));
        body.put("chave", "Insira_aqui_sua_chave");
        body.put("solicitacaoPagador", "Serviço realizado.");

        JSONArray infoAdicionais = new JSONArray();
        infoAdicionais.put(new JSONObject().put("nome", "Campo 1").put("valor", "Informação Adicional1 do PSP-Recebedor"));
        infoAdicionais.put(new JSONObject().put("nome", "Campo 2").put("valor", "Informação Adicional2 do PSP-Recebedor"));
        body.put("infoAdicionais", infoAdicionais);

        try {
          EfiPay efi = new EfiPay(options);
          JSONObject response = efi.call("pixCreateCharge", params, body);
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
