package br.com.efi.pix.batch.cobv.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.json.JSONObject;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class pixCreateDueChargeBatch {
  public static void main(String[] args) {

    Credentials credentials = new Credentials();

    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("client_id", credentials.getClientId());
    options.put("client_secret", credentials.getClientSecret());
    options.put("certificate", credentials.getCertificate());
    options.put("sandbox", credentials.isSandbox());

    HashMap<String, String> params = new HashMap<String, String>();
    params.put("id", "1");

    List<Object> cobsv =  new ArrayList<Object>();

    Map<String, Object> cobsv1 = new HashMap<String, Object>();
    cobsv1.put("calendario", new JSONObject().put("dataDeVencimento", "2024-03-02").put("validadeAposVencimento", 30));
    cobsv1.put("txid", "ab476126se554ad593c7226beb5cb657");
    cobsv1.put("devedor", new JSONObject().put("cpf", "12345678909").put("nome", "Francisco da Silva"));
    cobsv1.put("valor", new JSONObject().put("original", "0.10"));
    cobsv1.put("chave", "Insira_aqui_sua_chave");
    cobsv1.put("solicitacaoPagador", "Informar matrícula");

    Map<String, Object> cobsv2 = new HashMap<String, Object>();
    cobsv2.put("calendario", new JSONObject().put("dataDeVencimento", "2024-03-02").put("validadeAposVencimento", 30));
    cobsv2.put("txid", "dg26612f0e554ad593c7226beb5cb650");
    cobsv2.put("devedor", new JSONObject().put("cpf", "12345678909").put("nome", "Francisco da Silva"));
    cobsv2.put("valor", new JSONObject().put("original", "0.10"));
    cobsv2.put("chave", "Insira_aqui_sua_chave");
    cobsv2.put("solicitacaoPagador", "Informar matrícula");

    cobsv.add(cobsv1);
    cobsv.add(cobsv2);

    Map<String, Object> body = new HashMap<String, Object>();
    //body.put("descricao", "Cobranças dos alunos do turno vespertino");
    body.put("cobsv", cobsv);

    try {
      EfiPay efi = new EfiPay(options);
      Map<String, Object> response = efi.call("pixCreateDueChargeBatch", params, body);
      System.out.println(response);

    } catch (EfiPayException e) {
      System.out.println(e.getError());
      System.out.println(e.getErrorDescription());
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

}
