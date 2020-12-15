package br.com.qualify.rabbit.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
public class Processo implements Serializable {

    public static final String CACHE_NAME = "Processo";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    private String numeroProcesso;
    private String parteAutor;
    private String juizado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setNumeroProcesso(String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    public String getParteAutor() {
        return parteAutor;
    }

    public void setParteAutor(String parteAutor) {
        this.parteAutor = parteAutor;
    }

    public String getJuizado() {
        return juizado;
    }

    public void setJuizado(String juizado) {
        this.juizado = juizado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Processo processo = (Processo) o;
        return Objects.equals(id, processo.id) &&
                Objects.equals(numeroProcesso, processo.numeroProcesso) &&
                Objects.equals(parteAutor, processo.parteAutor) &&
                Objects.equals(juizado, processo.juizado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numeroProcesso, parteAutor, juizado);
    }
}
