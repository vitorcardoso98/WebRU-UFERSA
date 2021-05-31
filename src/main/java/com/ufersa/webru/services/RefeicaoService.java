package com.ufersa.webru.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufersa.webru.enums.TipoRefeicaoEnum;
import com.ufersa.webru.enums.TipoSubsidioEnum;
import com.ufersa.webru.model.Aluno;
import com.ufersa.webru.model.ParametroHorario;
import com.ufersa.webru.model.ParametroValorMonetario;
import com.ufersa.webru.model.Refeicao;
import com.ufersa.webru.model.RelatorioRefeicao;
import com.ufersa.webru.repositories.AlunoRepository;
import com.ufersa.webru.repositories.ParametroHorarioRepository;
import com.ufersa.webru.repositories.ParametroValorMonetarioRepository;
import com.ufersa.webru.repositories.RefeicaoRepository;

@Service
public class RefeicaoService {

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private RefeicaoRepository refeicaoRepository;

	@Autowired
	private ParametroHorarioRepository parametroHorarioRepository;

	@Autowired
	private ParametroValorMonetarioRepository parametroValorMonetarioRepository;

	public Refeicao cadastrarRefeicao(String matricula) {
		Aluno aluno = alunoRepository.findByMatricula(matricula);

		ParametroHorario parametroHorarioAlmoco = parametroHorarioRepository
				.findByIdentificador("HORARIO_PADRAO_ALMOCO");
		ParametroValorMonetario parametroValorAlmoco = parametroValorMonetarioRepository
				.findByIdentificador("VL_PADRAO_ALMOCO");
		ParametroValorMonetario parametroValorJantar = parametroValorMonetarioRepository
				.findByIdentificador("VL_PADRAO_JANTAR");
		ParametroValorMonetario parametroValorAlmocoAluno = parametroValorMonetarioRepository
				.findByIdentificador("VL_PADRAO_ALMOCO_ALUNO");
		ParametroValorMonetario parametroValorJantarAluno = parametroValorMonetarioRepository
				.findByIdentificador("VL_PADRAO_JANTAR_ALUNO");

		Refeicao refeicao = null;

		if (aluno.getStatus().equalsIgnoreCase("ativo")) {
			refeicao = new Refeicao();
			if (aluno.getTipo().equalsIgnoreCase("PROAE")) {
				refeicao.setTipoSubsidio(TipoSubsidioEnum.INTEGRAL);
				if (LocalTime.now().isBefore(parametroHorarioAlmoco.getHorario())) {
					refeicao.setTipoRefeicao(TipoRefeicaoEnum.ALMOCO);
					refeicao.setValor(parametroValorAlmoco.getValorMonetario());
				} else {
					refeicao.setTipoRefeicao(TipoRefeicaoEnum.JANTAR);
					refeicao.setValor(parametroValorJantar.getValorMonetario());
				}
			} else {
				refeicao.setTipoSubsidio(TipoSubsidioEnum.PARCIAL);
				if (LocalTime.now().isBefore(parametroHorarioAlmoco.getHorario())) {
					refeicao.setTipoRefeicao(TipoRefeicaoEnum.ALMOCO);
					refeicao.setValor(
							parametroValorAlmoco.getValorMonetario() - parametroValorAlmocoAluno.getValorMonetario());
				} else {
					refeicao.setTipoRefeicao(TipoRefeicaoEnum.JANTAR);
					refeicao.setValor(
							parametroValorJantar.getValorMonetario() - parametroValorJantarAluno.getValorMonetario());
				}
			}

			refeicao.setDataRefeicao(LocalDate.now());
			refeicao.setAluno(aluno);
		}else {
			System.out.println("Aluno Inativo");
		}

		return refeicao;
	}

	public Iterable<Refeicao> listarRefeicoes() {
		Iterable<Refeicao> refeicoes = refeicaoRepository.findAll();
		return refeicoes;
	}

	public List<Refeicao> getRelatorioRefeicaoMes(String mes) {
		List<Refeicao> refeicoes = refeicaoRepository.getRelatorio(Double.parseDouble(mes));
		return refeicoes;
	}

	public RelatorioRefeicao getValoresRelatorio(String mes) {

		RelatorioRefeicao relatorioRefeicao = new RelatorioRefeicao();

		List<Refeicao> refeicoes = refeicaoRepository.getRelatorio(Double.parseDouble(mes));

		for (Refeicao refeicao : refeicoes) {
			if (refeicao.getTipoRefeicao() == TipoRefeicaoEnum.ALMOCO) {
				if (refeicao.getTipoSubsidio() == TipoSubsidioEnum.INTEGRAL) {
					relatorioRefeicao.setValorTotalRefeicoesAlmocoIntegral(refeicao.getValor());
				} else {
					relatorioRefeicao.setValorTotalRefeicoesAlmocoParcial(refeicao.getValor());
				}
			} else {
				if ((refeicao.getTipoSubsidio() == TipoSubsidioEnum.INTEGRAL)) {
					relatorioRefeicao.setValorTotalRefeicoesJantarIntegral(refeicao.getValor());
				} else {
					relatorioRefeicao.setValorTotalRefeicoesJantarParcial(refeicao.getValor());
				}
			}
		}

		return relatorioRefeicao;
	}

}
