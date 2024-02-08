package br.com.efi.pix.batch.cobv.json;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class pixCreateDueChargeBatch {
  public static void main(String[] args) {

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
		cobsv1.put("calendario", new JSONObject().put("dataDeVencimento", "2024-03-02").put("validadeAposVencimento", 30));
		cobsv1.put("txid", "ab276126se554ad593c7226beb5cb657");
    cobsv1.put("devedor", new JSONObject().put("cpf", "12345678909").put("nome", "Francisco da Silva"));
    cobsv1.put("valor", new JSONObject().put("original", "0.10"));
    cobsv1.put("chave", "Insira_aqui_sua_chave");
    cobsv1.put("solicitacaoPagador", "Informar matrícula");

		JSONObject cobsv2 = new JSONObject(); 
    cobsv2.put("calendario", new JSONObject().put("dataDeVencimento", "2024-03-02").put("validadeAposVencimento", 30));
		cobsv2.put("txid", "db27612f0e554ad593c7226beb5cb650");
    cobsv2.put("devedor", new JSONObject().put("cpf", "12345678909").put("nome", "Francisco da Silva"));
    cobsv2.put("valor", new JSONObject().put("original", "0.10"));
    cobsv2.put("chave", "Insira_aqui_sua_chave");
    cobsv2.put("solicitacaoPagador", "Informar matrícula");

		cobsv.put(cobsv1);
		cobsv.put(cobsv2);

    JSONObject body = new JSONObject();
    body.put("descricao", "Cobranças dos alunos do turno vespertino");
    body.put("cobsv", cobsv);

    try {
      EfiPay efi = new EfiPay(options);
      JSONObject response = efi.call("pixCreateDueChargeBatch", params, body);
      System.out.println(response);

    } catch (EfiPayException e) {
      System.out.println(e.getError());
      System.out.println(e.getErrorDescription());
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

}
