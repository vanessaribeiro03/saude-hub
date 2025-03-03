package com.projeto.saude_hub.exceptions.medicamentos;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CamposNulosMedicamentoException extends RuntimeException {
    private static final List<String> CAMPOS_OBRIGATORIOS = Arrays.asList(
            "nome", "dosagem", "periodo"
    );

    public static List<String> getCamposObrigatorios() {
        return CAMPOS_OBRIGATORIOS;
    }

    public CamposNulosMedicamentoException(Object objeto) {
        super("Campos obrigatórios não podem ser nulos: " + getCamposNulos(objeto));
    }

    private static String getCamposNulos(Object objeto) {
        List<String> camposNulos = Arrays.stream(objeto.getClass().getDeclaredFields())
                .filter(field -> CAMPOS_OBRIGATORIOS.contains(field.getName()))
                .peek(field -> field.setAccessible(true))
                .filter(field -> {
                    try {
                        return field.get(objeto) == null;
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("Erro ao verificar campos nulos", e);
                    }
                })
                .map(Field::getName)
                .collect(Collectors.toList());

        return String.join(", ", camposNulos);
    }
}

