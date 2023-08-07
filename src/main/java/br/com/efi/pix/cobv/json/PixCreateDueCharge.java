package br.com.efi.pix.cobv.json;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class PixCreateDueCharge {
    public static void main(String[] args) {

	  Credentials credentials = new Credentials(); 

      JSONObject options = new JSONObject();
      options.put("client_id", credentials.getClientId());
      options.put("client_secret", credentials.getClientSecret());
      options.put("certificate", credentials.getCertificate());
      options.put("sandbox", credentials.isSandbox());

      HashMap<String, String> params = new HashMap<String, String>();
      params.put("txid", "7978c0c97ea847e78e8849634473c1d1");

      JSONObject abatimento = new JSONObject().put("modalidade", 1).put("valorPerc", "0.01");
      JSONObject multa = new JSONObject().put("modalidade", 2).put("valorPerc", "0.01");      
      JSONObject juros = new JSONObject().put("modalidade", 2).put("valorPerc", "0.01");
      JSONObject desconto = new JSONObject().put("modalidade", 3).put("valorPerc", "0.01");

      JSONArray infoAdicionais = new JSONArray();
      infoAdicionais.put(new JSONObject().put("nome", "Campo 1").put("valor", "Informação Adicional1 do PSP-Recebedor"));

      JSONObject body = new JSONObject();
      body.put("calendario", new JSONObject().put("dataDeVencimento", "2023-01-02").put("validadeAposVencimento", 30));
      body.put("devedor", new JSONObject().put("cpf", "12345678909")
          .put("nome", "Francisco da Silva")
          .put("logradouro", "Alameda Souza, Numero 80, Bairro Braz")
          .put("cidade", "Recife")
          .put("uf", "PE")
          .put("cep", "70011750"));
      body.put("valor", new JSONObject().put("original", "0.10")
          .put("abatimento", abatimento).put("desconto", desconto)
          .put("multa", multa)
          .put("juros", juros)
          .put("desconto", desconto));
      body.put("chave", "Insira_aqui_sua_chave");
      body.put("solicitacaoPagador", "Serviço realizado.");
      body.put("infoAdicionais", infoAdicionais);

        try {
          EfiPay efi= new EfiPay(options);
          JSONObject response = efi.call("pixCreateDueCharge", params, body);
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
