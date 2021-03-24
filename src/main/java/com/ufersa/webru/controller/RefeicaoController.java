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
		
		Refeicao refeicao = new Refeicao();
		
		if(aluno.getTipo().equalsIgnoreCase("UFERSA")) {
			refeicao.setTipoSubsidio(TipoSubsidioEnum.INTEGRAL);
			refeicao.setAluno(aluno);
			refeicao.setDataRefeicao(LocalDate.now());
			if(LocalTime.now().isBefore(parametroHorarioAlmoco.getHorario())) {
				refeicao.setTipoRefeicao(TipoRefeicaoEnum.ALMOCO);
				refeicao.setValor(parametroValorAlmoco.getValorMonetario());
			}else {
				refeicao.setTipoRefeicao(TipoRefeicaoEnum.JANTAR);
				refeicao.setValor(10.0);
			}
		}else {
			refeicao.setTipoSubsidio(TipoSubsidioEnum.PARCIAL);
			refeicao.setTipoRefeicao(TipoRefeicaoEnum.JANTAR);
			refeicao.setAluno(aluno);
			refeicao.setValor(7.50);
		}
		
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
