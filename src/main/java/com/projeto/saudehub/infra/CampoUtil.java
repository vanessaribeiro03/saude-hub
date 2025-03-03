package com.projeto.saudehub.infra;

import java.lang.reflect.Field;
import java.util.List;

public class CampoUtil {

    public static boolean hasCamposNulosGenerico(Object dto, List<String> camposObrigatorios) {
        for (Field field : dto.getClass().getDeclaredFields()) {
            if (!camposObrigatorios.contains(field.getName())) {
                continue;
            }

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
