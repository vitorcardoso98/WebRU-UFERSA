package com.ufersa.webru.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		
		if(aluno.getTipo().equalsIgnoreCase("PROAE")) {
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
	
	@RequestMapping(value="/relatorioRefeicoes", method=RequestMethod.GET)
	public ModelAndView relatorioRefeicoes(Model model) {
		Double valorTotalAlmocoIntegral = 0.0;
		Double valorTotalAlmocoParcial = 0.0;
		Double valorTotalJantarIntegral = 0.0;
		Double valorTotalJantarParcial = 0.0;
		Iterable<Refeicao> refeicoes = refeicaoRepository.findAll();
		for(Refeicao refeicao: refeicoes) {
			if(refeicao.getTipoRefeicao() == TipoRefeicaoEnum.ALMOCO) {
				if(refeicao.getTipoSubsidio() == TipoSubsidioEnum.INTEGRAL) {
					valorTotalAlmocoIntegral = valorTotalAlmocoIntegral + refeicao.getValor();
				}else {
					valorTotalAlmocoParcial = valorTotalAlmocoParcial + refeicao.getValor();
				}
			}else {
				if((refeicao.getTipoSubsidio() == TipoSubsidioEnum.INTEGRAL)) {
					valorTotalJantarIntegral = valorTotalJantarIntegral + refeicao.getValor();
				}else {
					valorTotalJantarParcial = valorTotalJantarParcial + refeicao.getValor();
				}
			}
		}
		
		ModelAndView modeloRefeicao = new ModelAndView("refeicao/relatorioRefeicoes");
		model.addAttribute("valorTotalAlmocoIntegral", valorTotalAlmocoIntegral);
		model.addAttribute("valorTotalAlmocoParcial", valorTotalAlmocoParcial);
		model.addAttribute("valorTotalJantarIntegral", valorTotalJantarIntegral);
		model.addAttribute("valorTotalJantarParcial", valorTotalJantarParcial);
		modeloRefeicao.addObject(model);
		
		valorTotalAlmocoIntegral = 0.0;
		valorTotalAlmocoParcial = 0.0;
		valorTotalJantarIntegral = 0.0;
		valorTotalJantarParcial = 0.0;
		
		return modeloRefeicao;
	}
	
}
