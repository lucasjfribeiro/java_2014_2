/*
 * Implementa��o do Bloqueto de Cobran�as do Banco do Brasil
 * - Conv�nio com 6 posi��es
 * 
 * scoelho@stefanini.com
 */
package br.com.stefanini.treinamento.boleto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.stefanini.treinamento.exception.ManagerException;

public class BloquetoBBConvenio6 extends BloquetoBBImpl implements BloquetoBB {

	@Override
	protected void validaDados() throws ManagerException {

		//TODO: FAZER VALIDA��ES
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

		if (numeroConvenioBanco == null || numeroConvenioBanco.length() != 6) {
			throw new ManagerException(
					"n�mero de conv�nio n�o informado ou o conv�nio informado � inv�lido. O conv�nio deve ter 6 posi��es");
		}

		if (complementoNumeroConvenioBancoSemDV == null
				&& complementoNumeroConvenioBancoSemDV.length() != 5) {
			throw new ManagerException(
					"Complemento do n�mero do conv�nio n�o informado. O complemento deve ter 5 posi��es");
		}

		if (numeroAgenciaRelacionamento == null
				|| numeroAgenciaRelacionamento.length() != 4) {
			throw new ManagerException(
					"n�mero da ag�ncia de Relacionamento n�o informado. O n�mero da ag�ncia deve ter 4 posi��es");
		}

		if (contaCorrenteRelacionamentoSemDV == null
				|| contaCorrenteRelacionamentoSemDV.length() != 8) {
			throw new ManagerException(
					"Conta corrente de relacionamento n�o informada. O n�mero da conta deve ter 8 posi��es");
		}

		if (tipoCarteira == null || tipoCarteira.length() != 2) {
			throw new ManagerException(
					"Tipo carteira n�o informado ou o valor � inv�lido");
		}

		if (dataBase == null) {
			throw new ManagerException("A database n�o foi informada.");
		}

	}


	public BloquetoBBConvenio6(String codigoBanco, String codigoMoeda,
			Date dataVencimento, Date dataBase, BigDecimal valor,
			String numeroConvenioBanco,
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

	@Override
	protected String getLDNumeroConvenio() {

		init();

		StringBuilder buffer = new StringBuilder();
		buffer.append(codigoBanco);
		buffer.append(codigoMoeda);
		buffer.append(fatorVencimento);
		buffer.append(getValorFormatado());
		buffer.append(numeroConvenioBanco);
		buffer.append(complementoNumeroConvenioBancoSemDV);
		buffer.append(numeroAgenciaRelacionamento);
		buffer.append(contaCorrenteRelacionamentoSemDV);
		buffer.append(tipoCarteira);	
				
		return "";

	}

	// TODO: @sandro - refatorar os m�todos getCodigoBarrasSemDigito() e
	// getCodigoBarras()

	@Override
	protected String getCodigoBarrasSemDigito() {

		init();

		StringBuilder buffer = new StringBuilder();
		
		init();

		StringBuilder buffer = new StringBuilder();
		
		buffer.append (codigoBanco); //Campo 01-03 (03)
		buffer.append (codigoMoeda); // Campo 04-04 (01) 
		buffer.append (digitoVerificadorCodigoBarras(getCodigoBarrasSemDigito())); //Campo 01-03 (03)
		
		buffer.append(fatorVencimento); //Campo 06-09 (04)
		buffer.append(getValorFormatado()); // Campo 10-19 (10)
		buffer.append(numeroConvenioBanco); //Campo 20-23 (04)
		
		buffer.append(complementoNumeroConvenioBancoSemDV)); // Campo
		buffer.append(tipoCarteira); //Campo 43-44 (02)
		
		
		//TODO: COMPLETAR

		return buffer.toString();
	}

	@Override
	public String getCodigoBarras() {

		init();

		StringBuilder buffer = new StringBuilder();

		//TODO: COMPLETAR

		return buffer.toString();
	}

}
