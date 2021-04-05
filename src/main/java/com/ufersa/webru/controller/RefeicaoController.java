package com.ufersa.webru.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ufersa.webru.enums.TipoRefeicaoEnum;
import com.ufersa.webru.enums.TipoSubsidioEnum;
import com.ufersa.webru.model.Aluno;
import com.ufersa.webru.model.ParametroHorario;
import com.ufersa.webru.model.ParametroValorMonetario;
import com.ufersa.webru.model.Refeicao;
import com.ufersa.webru.repositories.AlunoRepository;
import com.ufersa.webru.repositories.ParametroHorarioRepository;
import com.ufersa.webru.repositories.ParametroValorMonetarioRepository;
import com.ufersa.webru.repositories.RefeicaoRepository;

@Controller
public class RefeicaoController {

	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private RefeicaoRepository refeicaoRepository;
	
	@Autowired
	private ParametroHorarioRepository parametroHorarioRepository;
	
	@Autowired
	private ParametroValorMonetarioRepository parametroValorMonetarioRepository;
	
	@RequestMapping(value="/cadastrarRefeicao", method=RequestMethod.GET)
	public String formularioCadastrarRefeicao() {
		return "refeicao/cadastraRefeicao";
	}
	
	@RequestMapping(value="/cadastrarRefeicao", method=RequestMethod.POST)
	public String cadastrarRefeicao(HttpServletRequest request) {
		Aluno aluno = alunoRepository.findByMatricula(request.getParameter("matricula"));
		
		ParametroHorario parametroHorarioAlmoco = parametroHorarioRepository.findByIdentificador("HORARIO_PADRAO_ALMOCO");
		ParametroValorMonetario parametroValorAlmoco = parametroValorMonetarioRepository.findByIdentificador("VL_PADRAO_ALMOCO");
		ParametroValorMonetario parametroValorJantar = parametroValorMonetarioRepository.findByIdentificador("VL_PADRAO_JANTAR");
		ParametroValorMonetario parametroValorAlmocoAluno = parametroValorMonetarioRepository.findByIdentificador("VL_PADRAO_ALMOCO_ALUNO");
		ParametroValorMonetario parametroValorJantarAluno = parametroValorMonetarioRepository.findByIdentificador("VL_PADRAO_JANTAR_ALUNO");
		
		Refeicao refeicao = new Refeicao();
		
		if(aluno.getTipo().equalsIgnoreCase("UFERSA")) {
			refeicao.setTipoSubsidio(TipoSubsidioEnum.INTEGRAL);
			if(LocalTime.now().isBefore(parametroHorarioAlmoco.getHorario())) {
				refeicao.setTipoRefeicao(TipoRefeicaoEnum.ALMOCO);
				refeicao.setValor(parametroValorAlmoco.getValorMonetario());
			}else {
				refeicao.setTipoRefeicao(TipoRefeicaoEnum.JANTAR);
				refeicao.setValor(parametroValorJantar.getValorMonetario());
			}
		}else {
			refeicao.setTipoSubsidio(TipoSubsidioEnum.PARCIAL);
			if(LocalTime.now().isBefore(parametroHorarioAlmoco.getHorario())) {
				refeicao.setTipoRefeicao(TipoRefeicaoEnum.ALMOCO);
				refeicao.setValor(parametroValorAlmoco.getValorMonetario() - parametroValorAlmocoAluno.getValorMonetario());
			}else {
				refeicao.setTipoRefeicao(TipoRefeicaoEnum.JANTAR);
				refeicao.setValor(parametroValorJantar.getValorMonetario() - parametroValorJantarAluno.getValorMonetario());
			}
		}
		
		refeicao.setDataRefeicao(LocalDate.now());
		refeicao.setAluno(aluno);
		
		refeicaoRepository.save(refeicao);
		return "redirect:/cadastrarRefeicao";
	}
	
	@RequestMapping(value="/listarRefeicoes", method=RequestMethod.GET)
	public ModelAndView listarRefeicoesCadastradas() {
		ModelAndView modeloRefeicao = new ModelAndView("refeicao/listaRefeicoes");
		Iterable<Refeicao> refeicoes = refeicaoRepository.findAll();
		modeloRefeicao.addObject("refeicoes", refeicoes);
		return modeloRefeicao;
	}
	
}
