package com.pucminas.easyfarma.domain.enums;

public enum TipoPessoa {

    PACIENTE(1, "permitido participar dos procedimentos"),
    FUNCIONARIO(2, "permitido realizar acoes no sistema");

    private int cod;
    private String descricao;

    TipoPessoa(int cod, String descricao) {

    }

    public static TipoPessoa toTipoPessoa(int cod) {

        for (TipoPessoa tipoPessoa : TipoPessoa.values()) {

            if (tipoPessoa.getCod() == cod) {
                return tipoPessoa;
            }
        }
        return null;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }
}
