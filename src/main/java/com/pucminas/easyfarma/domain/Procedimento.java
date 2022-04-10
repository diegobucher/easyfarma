package com.pucminas.easyfarma.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Procedimento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descricao;
    private int medico;
    private int enfermeiro;
    private int paciente;
    private Date data;
    private int preco;

    public Procedimento() {
    }

    public Procedimento(Integer id, String descricao, Pessoa medico,
                        Pessoa enfermeiro, Pessoa paciente, Date data, int preco) {
        this.id = id;
        this.descricao = descricao;
        this.medico = medico.getId();
        this.enfermeiro = enfermeiro.getId();
        this.paciente = paciente.getId();
        this.data = data;
        this.preco = preco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getMedico() {
        return medico;
    }

    public void setMedico(Pessoa medico) {
        this.medico = medico.getId();
    }

    public int getEnfermeiro() {
        return enfermeiro;
    }

    public void setEnfermeiro(Pessoa enfermeiro) {
        this.enfermeiro = enfermeiro.getId();
    }

    public int getPaciente() {
        return paciente;
    }

    public void setPaciente(Pessoa paciente) {
        this.paciente = paciente.getId();
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Procedimento other = (Procedimento) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
