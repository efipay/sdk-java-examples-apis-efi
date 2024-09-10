package br.com.efi.pix.cob.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;


import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class PixCreateCharge {
    public static void main(String[] args) {

	  Credentials credentials = new Credentials();

    HashMap<String, Object> options = new HashMap<String, Object>();
      options.put("client_id", credentials.getClientId());
      options.put("client_secret", credentials.getClientSecret());
      options.put("certificate", credentials.getCertificate());
      options.put("sandbox", credentials.isSandbox());

        HashMap<String, String> params = new HashMap<String, String>();
		    params.put("txid", "7978c0c97ea847e78e8849634473c1f1");

        Map<String, Object> body = new HashMap<String, Object>();

        Map<String, Object> calendario = new HashMap<String, Object>();
        calendario.put("expiracao", 3600);

        Map<String, Object> devedor = new HashMap<String, Object>();
        devedor.put("cpf", "12345678909");
        devedor.put("nome", "Francisco da Silva");

        Map<String, Object> valor = new HashMap<String, Object>();
        valor.put("original", "123.45");

        List<Map<String, Object>> infoAdicionais = new ArrayList<>();
        Map<String, Object> info1 = new HashMap<String, Object>();
        info1.put("nome", "Campo 1");
        info1.put("valor", "Informação Adicional1 do PSP-Recebedor");
        infoAdicionais.add(info1);

        Map<String, Object> info2 = new HashMap<String, Object>();
        info2.put("nome", "Campo 2");
        info2.put("valor", "Informação Adicional2 do PSP-Recebedor");
        infoAdicionais.add(info2);

        body.put("calendario", calendario);
        body.put("devedor", devedor);
        body.put("valor", valor);
        body.put("chave", "Insira_aqui_sua_chave");
        body.put("solicitacaoPagador", "Serviço realizado.");
        body.put("infoAdicionais", infoAdicionais);


        try {
          EfiPay efi = new EfiPay(options);
          Map<String, Object> response = efi.call("pixCreateCharge", params, body);
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
