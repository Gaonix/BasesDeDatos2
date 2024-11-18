package com.andina.trading.service;

import com.andina.trading.model.Empresa;
import com.andina.trading.repository.EmpresaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestión de operaciones relacionadas con las empresas en el sistema de transacciones bursátiles.
 * Proporciona métodos para la creación, consulta y obtención de empresas existentes por nombre.
 *
 * <p>Este servicio actúa como una capa intermedia entre el controlador y el repositorio,
 * facilitando la lógica de negocio relacionada con las empresas.</p>

 * @version 1.0
 */
@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    /**
     * Constructor para inyectar el repositorio de empresas.
     *
     * @param empresaRepository el repositorio que maneja la persistencia de empresas
     */
    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    /**
     * Obtiene una empresa por su nombre o la crea si no existe.
     * <p>Si la empresa con el nombre especificado ya existe en la base de datos,
     * se devuelve dicha empresa; de lo contrario, se crea una nueva empresa con los
     * datos proporcionados y se guarda en la base de datos.</p>
     *
     * @param nombreEmpresa el nombre de la empresa
     * @param pais el país de origen de la empresa
     * @param situacionEconomica la situación económica de la empresa
     * @return la empresa existente o la nueva empresa creada
     */
    public Empresa obtenerOcrearEmpresa(String nombreEmpresa, String pais, String situacionEconomica) {
        Optional<Empresa> empresaOptional = empresaRepository.findByNombreEmpresa(nombreEmpresa);
        return empresaOptional.orElseGet(() -> {
            Empresa nuevaEmpresa = new Empresa();
            nuevaEmpresa.setNombreEmpresa(nombreEmpresa);
            nuevaEmpresa.setPais(pais);
            nuevaEmpresa.setSituacionEconomica(situacionEconomica);
            return empresaRepository.save(nuevaEmpresa);
        });
    }

    /**
     * Obtiene la lista de todas las empresas almacenadas en el sistema.
     *
     * @return lista de entidades {@link Empresa}
     */
    public List<Empresa> obtenerEmpresas() {
        return empresaRepository.findAll();
    }
}
