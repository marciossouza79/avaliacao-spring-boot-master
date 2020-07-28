package br.com.tokiomarine.seguradora.avaliacao.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.repository.EstudanteRepository;

// TODO Efetue a implementação dos métodos da classe service
@Service
public class EstudanteServiceImpl implements EstudandeService {

	@Autowired
	EstudanteRepository repository;

	@Override
	public void cadastrarEstudante(@Valid Estudante estudante) {
		repository.save(estudante);
	}
	
	public Estudante cadastrarEstudanteRest(@Valid Estudante estudante) {
		return repository.save(estudante);
	}

	@Override
	public void atualizarEstudante(@Valid Estudante estudante) {
		Estudante newEstudante = find(estudante.getId());
		updateData(newEstudante,estudante);
		repository.save(estudante);
	}

	public Estudante atualizarEstudanteRest(@Valid Estudante estudante) {
		Estudante newEstudante = find(estudante.getId());
		updateData(newEstudante,estudante);
		return repository.save(estudante);
	}
	
	public Estudante find(Integer id) {
		Optional<Estudante> obj = repository.findById(id.intValue());
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Estudante.class.getName(), null));
	}

	@Override
	public List<Estudante> buscarEstudantes() {
		return repository.findAll();
	}

	@Override
	public Estudante buscarEstudante(long id) {
		Long newId = id;
		return find(newId.intValue());
	}

	public Estudante updateData(Estudante newEstudante, Estudante estudante) {
		newEstudante.setNome(estudante.getNome());
		newEstudante.setEmail(estudante.getEmail());
		newEstudante.setCurso(estudante.getCurso());
		newEstudante.setMatricula(estudante.getMatricula());
		return newEstudante;
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possivel excluir o estudante");
		}
	}
}
