/*
 * Implementa��o do Bloqueto de Cobran�as do Banco do Brasil
 * - Conv�nio com 1000000
 * 
 * scoelho@stefanini.com
 */
package br.com.stefanini.treinamento.boleto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.stefanini.treinamento.exception.ManagerException;

public class BloquetoBBConvenioAcima1000000 extends BloquetoBBImpl implements
		BloquetoBB {

	@Override
	protected void validaDados() throws ManagerException {

		// TODO: COMPLETAR
		if (codigoBanco == null || codigoBanco.length() != 3) {
			throw new ManagerException(
					"C�digo do Banco n�o informado ou com tamanho diferente de 3 posi��es");
		}

		if (codigoMoeda == null || codigoMoeda.length() != 1) {
			throw new ManagerException(
					"C�digo de moeda n�o informado ou inv�lido");
		}

		if (dataVencimento == null) {
			throw new ManagerException("Data de vencimento n�o informada");
		}

		if (valor == null) {
			throw new ManagerException(
					"Valor do bloqueto bancário n�o informado");
		}

		if (numeroConvenioBanco == null || numeroConvenioBanco.length() != 7) {
			throw new ManagerException(
					"n�mero de conv�nio n�o informado ou o conv�nio informado � inv�lido. O conv�nio deve ter 7 posi��es");
		}

		if (complementoNumeroConvenioBancoSemDV == null
				&& complementoNumeroConvenioBancoSemDV.length() != 10) {
			throw new ManagerException(
					"Complemento do n�mero do conv�nio n�o informado."+"O complemento deve ter 10 posi��es");
		}


		if (tipoCarteira == null || tipoCarteira.length() != 2) {
			throw new ManagerException(
					"Tipo carteira n�o informado ou o valor � inv�lido");
		}

		if (dataBase == null) {
			throw new ManagerException("A database n�o foi informada.");
		}
	}

	public BloquetoBBConvenioAcima1000000(String codigoBanco,
			String codigoMoeda, Date dataVencimento, Date dataBase,
			BigDecimal valor, String numeroConvenioBanco,
			String complementoNumeroConvenioBancoSemDV,
			String numeroAgenciaRelacionamento,
			String contaCorrenteRelacionamentoSemDV, String tipoCarteira)
			throws ManagerException {

		this.codigoBanco = codigoBanco;
		this.codigoMoeda = codigoMoeda;
		this.dataVencimento = dataVencimento;
		this.valor = valor;
		this.numeroConvenioBanco = numeroConvenioBanco;
		this.complementoNumeroConvenioBancoSemDV = complementoNumeroConvenioBancoSemDV;
		this.numeroAgenciaRelacionamento = numeroAgenciaRelacionamento;
		this.contaCorrenteRelacionamentoSemDV = contaCorrenteRelacionamentoSemDV;
		this.tipoCarteira = tipoCarteira;
		this.dataBase = dataBase;

		validaDados();

	}

	// TODO: @sandro - refatorar os métodos getCodigoBarrasSemDigito() e
	// getCodigoBarras()
	@Override
	protected String getCodigoBarrasSemDigito() {

		init();

	    StringBuilder buffer = new StringBuilder();
		buffer.append(codigoBanco);
		buffer.append(codigoMoeda);
		buffer.append(fatorVencimento);
		buffer.append(getValorFormatado());
		buffer.append(String.format("%06d",0));
		buffer.append(numeroConvenioBanco);
		buffer.append(complementoNumeroConvenioBancoSemDV);
	    buffer.append(tipoCarteira);
		
	 return buffer.toString();
	}

	@Override
	public String getCodigoBarras() {

		init();

		StringBuilder buffer = new StringBuilder();
		buffer.append (codigoBanco); //Campo 01-03 (03)
		buffer.append (codigoMoeda); // Campo 04-04 (01) 
		buffer.append (digitoVerificadorCodigoBarras(getCodigoBarrasSemDigito())); //Campo 01-03 (03)
		
		buffer.append(fatorVencimento); //Campo 06-09 (04)
		buffer.append(getValorFormatado()); // Campo 10-19 (10)
		buffer.append(String.format("%06d", 0)); // Campo 20-25 (06)
		buffer.append(numeroConvenioBanco); //Campo 26-32 (07)
		
		buffer.append(complementoNumeroConvenioBancoSemDV); // Campo 24-30 (07)
		buffer.append(tipoCarteira); //Campo 43-44 (02)

		return buffer.toString();
	}

	@Override
	protected String getLDNumeroConvenio() {
   String convenio = String.format ("%07d", Long.valueOf(numeroConvenioBanco));
   return String.format ("%s.%s", convenio.substring(0, 1), convenio.substring (1,5));
   
	

	}

}
