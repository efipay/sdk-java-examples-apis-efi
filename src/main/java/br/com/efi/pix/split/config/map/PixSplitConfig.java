package br.com.efi.pix.split.config.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class PixSplitConfig {
    public static void main(String[] args) {
		Credentials credentials = new Credentials();

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());

		Map<String, Object> minhaParte = new HashMap<String, Object>();
        minhaParte.put("tipo", "porcentagem");
        minhaParte.put("valor", "50.00");

        Map<String, Object> favorecido_1 = new HashMap<String, Object>();
        favorecido_1.put("cpf", "12345678909");
        favorecido_1.put("conta", "1234567");

        Map<String, Object> favorecido_2 = new HashMap<String, Object>();
        favorecido_2.put("cpf", "94271564656");
        favorecido_2.put("conta", "7654321");

        List<Map<String, Object>> repasses = new ArrayList<>();
        Map<String, Object> repasse1 = new HashMap<String, Object>();
        repasse1.put("tipo", "porcentagem");
        repasse1.put("valor", "25.00");
        repasse1.put("favorecido", favorecido_1);
        repasses.add(repasse1);

        Map<String, Object> repasse2 = new HashMap<String, Object>();
        repasse2.put("tipo", "porcentagem");
        repasse2.put("valor", "25.00");
        repasse2.put("favorecido", favorecido_2);
        repasses.add(repasse2);

        Map<String, Object> split = new HashMap<String, Object>();
        split.put("divisaoTarifa", "assumir_total");
        split.put("minhaParte", minhaParte);
        split.put("repasses", repasses);

        Map<String, Object> lancamento = new HashMap<String, Object>();
        lancamento.put("imediato", true);

        Map<String, Object> body = new HashMap<String, Object>();
        body.put("descricao", "Batatinha frita 1, 2, 3");
        body.put("lancamento", lancamento);
        body.put("split", split);

		try {
			EfiPay efi = new EfiPay(options);
			
			Map<String, Object> response = efi.call("pixSplitConfig", new HashMap<String,String>(), body);
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
