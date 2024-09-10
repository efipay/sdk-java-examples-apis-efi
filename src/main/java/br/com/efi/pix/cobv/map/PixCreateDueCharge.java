package br.com.efi.pix.cobv.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class PixCreateDueCharge {
    public static void main(String[] args) {

	  Credentials credentials = new Credentials();

    HashMap<String, Object> options = new HashMap<String, Object>();
      options.put("client_id", credentials.getClientId());
      options.put("client_secret", credentials.getClientSecret());
      options.put("certificate", credentials.getCertificate());
      options.put("sandbox", credentials.isSandbox());

        HashMap<String, String> params = new HashMap<String, String>();
		    params.put("txid", "7978c0c97ea847e78e8849634473c1f1");

         Map<String, Object> abatimento = new HashMap<String, Object>();
         abatimento.put("modalidade", 1);
         abatimento.put("valorPerc", "5.00");

         Map<String, Object> multa = new HashMap<String, Object>();
         multa.put("modalidade", 2);
         multa.put("valorPerc", "15.00");

         Map<String, Object> juros = new HashMap<String, Object>();
         juros.put("modalidade", 2);
         juros.put("valorPerc", "2.00");

         Map<String, Object> desconto = new HashMap<String, Object>();
         desconto.put("modalidade", 3);
         desconto.put("valorPerc", "1.00");

         List<Object> infoAdicionais = new ArrayList<>();
         Map<String, Object> info1 = new HashMap<String, Object>();
         info1.put("nome", "Campo 1");
         info1.put("valor", "Informação Adicional1 do PSP-Recebedor");
         infoAdicionais.add(info1);
 
         Map<String, Object> body = new HashMap<String, Object>();
         body.put("calendario", new HashMap<String, Object>() {{
             put("dataDeVencimento", "2022-10-30");
             put("validadeAposVencimento", 30);
         }});
         body.put("devedor", new HashMap<String, Object>() {{
             put("cpf", "12345678909");
             put("nome", "Francisco da Silva");
             put("logradouro", "Alameda Souza, Numero 80, Bairro Braz");
             put("cidade", "Recife");
             put("uf", "PE");
             put("cep", "70011750");
         }});
         body.put("valor", new HashMap<String, Object>() {{
             put("original", "123.45");
             put("abatimento", abatimento);
             put("desconto", desconto);
             put("juros", juros);
             put("multa", multa);
         }});
         body.put("chave", "Insira_aqui_sua_chave");
         body.put("solicitacaoPagador", "Serviço realizado.");
         body.put("infoAdicionais", infoAdicionais);


        try {
          EfiPay efi= new EfiPay(options);
          Map<String, Object> response = efi.call("pixCreateDueCharge", params, body);
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
