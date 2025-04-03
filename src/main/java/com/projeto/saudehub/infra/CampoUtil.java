package com.projeto.saudehub.infra;

import java.lang.reflect.Field;
import java.util.List;

public class CampoUtil {

    public static boolean hasCamposNulosGenerico(Object dto, List<String> camposObrigatorios) {
        // aqui eu pego todos os campos da classe do objeto
        for (Field field : dto.getClass().getDeclaredFields()) {
            // verifico se o campo tá na lista de obrigatórios
            if (!camposObrigatorios.contains(field.getName())) {
                continue;
            }

            // libera o acesso ao campo privado e verifico se ele é nulo
            field.setAccessible(true);
            try {
                if (field.get(dto) == null) {
                    return true;
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Erro ao verificar campos nulos.", e);
            }
        }
        return false;
    }
}
