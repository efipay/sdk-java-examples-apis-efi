package br.com.efi.open_finance.payments.recurrency.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.efi.Credentials;
import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

public class OfStartRecurrencyPixPayment {
    public static void main(String[] args) {
		Credentials credentials = new Credentials();

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());

		options.put("x-idempotency-key", "ae71713f-875b-4af3-9d85-0bcb43288847");

    	Map<String, Object> body = new HashMap<String, Object>();	
		
		// Pagador
		Map<String, Object> pagador = new HashMap<String, Object>();
        pagador.put("idParticipante", "9f4cd202-8f2b-11ec-b909-0242ac120002");
        pagador.put("cpf", "51687574219");
		pagador.put("cnpj", "90293071000112");
		body.put("pagador", pagador);

		// Favorecido
		Map<String, Object> favorecido = new HashMap<String, Object>();
        Map<String, Object> contaBanco = new HashMap<String, Object>();
        contaBanco.put("codigoBanco", "09089356");
        contaBanco.put("agencia", "0001");
        contaBanco.put("documento", "17558266300");
        contaBanco.put("nome", "Lucas Silva");
        contaBanco.put("conta", "654984");
        contaBanco.put("tipoConta", "CACC");
		favorecido.put("contaBanco", contaBanco);
        body.put("favorecido", favorecido);

		// Pagamento
		Map<String, Object> pagamento = new HashMap<String, Object>();
		pagamento.put("valor", "9.90");
		pagamento.put("codigoCidadeIBGE", "5300108");
		pagamento.put("infoPagador", "Churrasco");
		pagamento.put("idProprio", "6236574863254");

		// Recorrência
		Map<String, Object> recorrencia = new HashMap<String, Object>();
		recorrencia.put("tipo", "diaria");
		recorrencia.put("dataInicio", "2024-08-31");
		recorrencia.put("quantidade", 3);
		recorrencia.put("diaDaSemana", "SEGUNDA_FEIRA");
		recorrencia.put("diaDoMes", 15);

		// Datas (Array)
		List<String> datas = new ArrayList<String>();
		datas.add("2024-09-01");
		datas.add("2024-09-08");
		datas.add("2024-09-15");

		recorrencia.put("datas", datas);
		recorrencia.put("descricao", "Petshop");

		// Anexando recorrencia ao pagamento
		pagamento.put("recorrencia", recorrencia);

		// Anexando pagamento ao body
		body.put("pagamento", pagamento);

		try {
			EfiPay efi = new EfiPay(options);
			
			Map<String, Object> response = efi.call("ofStartRecurrencyPixPayment", new HashMap<String,String>(), body);
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
