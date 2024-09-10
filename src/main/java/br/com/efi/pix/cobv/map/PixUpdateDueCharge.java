package br.com.efi.pix.cobv.map;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class PixUpdateDueCharge {
    public static void main(String[] args) throws IOException {

		Credentials credentials = new Credentials();

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("txid", "7979c0c97ed847e78e8849634473c1d1");

		 // Criando o mapa de devedor
		 HashMap<String, Object> devedor = new HashMap<String, Object>();
		 devedor.put("logradouro", "Alameda Souza, Numero 80, Bairro Braz");
		 devedor.put("cidade", "Recife");
		 devedor.put("uf", "PE");
		 devedor.put("cep", "70011750");
		 devedor.put("cpf", "12345678909");
		 devedor.put("nome", "Francisco da Silva");
 
		 // Criando o mapa de valor
		 HashMap<String, Object> valor = new HashMap<String, Object>();
		 valor.put("original", "13.45");
 
		 // Criando o corpo da requisição (body)
		 HashMap<String, Object> body = new HashMap<String, Object>();
		 body.put("sds", devedor);
		 body.put("valor", valor);
		 body.put("solicitacaoPagador", "Cobrança dos serviços prestados.");

			try {
				EfiPay efi = new EfiPay(options);
				Map<String, Object> response = efi.call("pixUpdateDueCharge", params, body);
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