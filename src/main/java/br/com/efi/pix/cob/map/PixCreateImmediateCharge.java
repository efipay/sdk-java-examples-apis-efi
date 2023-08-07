package br.com.efi.pix.cob.map;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class PixCreateImmediateCharge {
    public static void main(String[] args) {

	  Credentials credentials = new Credentials();

    HashMap<String, Object> options = new HashMap<String, Object>();
      options.put("client_id", credentials.getClientId());
      options.put("client_secret", credentials.getClientSecret());
      options.put("certificate", credentials.getCertificate());
      options.put("sandbox", credentials.isSandbox());

    
      Map<String, Object> body = new HashMap<String, Object>();
        body.put("calendario", new JSONObject().put("expiracao", 3600));
        body.put("devedor", new JSONObject().put("cpf", "12345678909").put("nome", "Francisco da Silva"));
        body.put("valor", new JSONObject().put("original", "123.45"));
        body.put("chave", "Insira_aqui_sua_chave");
        body.put("solicitacaoPagador", "Serviço realizado.");

        List<Object> infoAdicionais =  new ArrayList<Object>();
        infoAdicionais.add(new JSONObject().put("nome", "Campo 1").put("valor", "Informação Adicional1 do PSP-Recebedor"));
        infoAdicionais.add(new JSONObject().put("nome", "Campo 2").put("valor", "Informação Adicional2 do PSP-Recebedor"));
        body.put("infoAdicionais", infoAdicionais);

        try {
          EfiPay efi= new EfiPay(options);
          Map<String, Object> response = efi.call("pixCreateImmediateCharge", new HashMap<String,String>(), body);
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
