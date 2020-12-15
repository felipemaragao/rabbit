package br.com.qualify.rabbit.repository;

import br.com.qualify.rabbit.entity.Processo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessoRepository extends JpaRepository<Processo, Long> {

}
