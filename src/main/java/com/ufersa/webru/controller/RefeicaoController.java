package com.ufersa.webru.controller;

import java.time.LocalTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ufersa.webru.enums.TipoRefeicaoEnum;
import com.ufersa.webru.enums.TipoSubsidioEnum;
import com.ufersa.webru.model.Aluno;
import com.ufersa.webru.model.ParametroHorario;
import com.ufersa.webru.model.Refeicao;
import com.ufersa.webru.repositories.AlunoRepository;
import com.ufersa.webru.repositories.ParametroHorarioRepository;
import com.ufersa.webru.repositories.RefeicaoRepository;

@Controller
public class RefeicaoController {

	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private RefeicaoRepository refeicaoRepository;
	
	@Autowired
	private ParametroHorarioRepository parametroHorarioRepository;
	
	@RequestMapping(value="/cadastrarRefeicao", method=RequestMethod.GET)
	public String formularioCadastrarRefeicao() {
		return "refeicao/cadastraRefeicao";
	}
	
	@RequestMapping(value="/cadastrarRefeicao", method=RequestMethod.POST)
	public String cadastrarRefeicao(HttpServletRequest request) {
		Aluno aluno = alunoRepository.findByMatricula(request.getParameter("matricula"));
		ParametroHorario parametroHorarioAlmoco = parametroHorarioRepository.findByIdentificador("VL_PADRAO_ALMOCO");
		Refeicao refeicao = new Refeicao();
		if(aluno.getTipo().equalsIgnoreCase("UFERSA")) {
			refeicao.setTipoSubsidio(TipoSubsidioEnum.INTEGRAL);
			refeicao.setAluno(aluno);
			if(LocalTime.now().isBefore(parametroHorarioAlmoco.getHorario())) {
				refeicao.setTipoRefeicao(TipoRefeicaoEnum.ALMOCO);
			}else {
				refeicao.setTipoRefeicao(TipoRefeicaoEnum.JANTAR);
			}
		}else {
			refeicao.setTipoSubsidio(TipoSubsidioEnum.PARCIAL);
		}
		refeicaoRepository.save(refeicao);
		return "redirect:/cadastrarRefeicao";
	}
	
}
