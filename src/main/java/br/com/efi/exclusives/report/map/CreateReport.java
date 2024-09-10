package br.com.efi.exclusives.report.map;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class CreateReport {
    public static void main(String[] args) throws IOException {
        Credentials credentials = new Credentials();

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());

		Map<String, Object> body = new HashMap<String, Object>();	
		body.put("dataMovimento", "2022-04-24");
		Map<String, Object> tipoRegistros = new HashMap<String, Object>();
        tipoRegistros.put("pixRecebido", true);
        tipoRegistros.put("pixDevolucaoEnviada", false);
        tipoRegistros.put("tarifaPixRecebido", true);
        tipoRegistros.put("pixEnviadoChave", true);
        tipoRegistros.put("pixEnviadoDadosBancarios", false);
        tipoRegistros.put("pixDevolucaoRecebida", true);

        body.put("tipoRegistros", tipoRegistros);
		
		try {
			EfiPay efi = new EfiPay(options);
			
			Map<String, Object> response = efi.call("createReport", new HashMap<String,String>(), body);
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