package com.andina.trading.controller;

import com.andina.trading.model.Accion;
import com.andina.trading.model.Empresa;
import com.andina.trading.model.UsuarioEmpresa;
import com.andina.trading.model.Usuarios;
import com.andina.trading.repository.AccionRepository;
import com.andina.trading.repository.EmpresaRepository;
import com.andina.trading.repository.UsuarioEmpresasRepository;
import com.andina.trading.repository.UsuariosRepository;
import com.andina.trading.util.AESUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Controlador para gestionar las operaciones de los usuarios en el sistema Andina Trading.
 * Incluye funcionalidad para listar, agregar, editar, eliminar, autenticar usuarios,
 * y gestionar la compra y venta de acciones.
 *
 * 
 * @version 1.0
 */
@Controller
@RequestMapping
public class UsuariosController {

    /** Repositorio de Usuarios para realizar operaciones de persistencia. */
    @Autowired
    private UsuariosRepository urep;

    /** Sesión HTTP para gestionar las sesiones de usuario. */
    @Autowired
    private HttpSession session;

    /** Repositorio de Empresa para operaciones de persistencia relacionadas con empresas. */
    @Autowired
    private EmpresaRepository empresaRepository;

    /** Repositorio de UsuarioEmpresas para operaciones de persistencia de usuario-empresa. */
    @Autowired
    private UsuarioEmpresasRepository usuarioEmpresasRepository;

    /** Repositorio de Accion para operaciones de persistencia relacionadas con acciones. */
    @Autowired
    private AccionRepository accionRepository;

    /** Almacena la acción seleccionada para las operaciones de compra/venta. */
    private Optional<Accion> accionSeleccionada;

    /**
     * Maneja la solicitud para listar todos los usuarios.
     *
     * @return ModelAndView que muestra la lista de usuarios.
     */
    @GetMapping("/listausuarios")
    public ModelAndView listar() {
        List<Usuarios> usuarios = urep.findAll();
        ModelAndView mav = new ModelAndView("listausuarios");
        mav.addObject("usuarios", usuarios);
        return mav;
    }

    /**
     * Maneja la solicitud para mostrar el formulario de nuevo usuario.
     *
     * @return ModelAndView que muestra el formulario para agregar un usuario.
     */
    @GetMapping("/newusuarios")
    public ModelAndView agregar() {
        ModelAndView mav = new ModelAndView("formusuarios");
        mav.addObject("usuario", new Usuarios());
        return mav;
    }

    /**
     * Guarda un nuevo usuario con la contraseña cifrada en la base de datos.
     *
     * @param usuario El usuario que se va a guardar.
     * @return Redirige a la página de inicio si se guarda con éxito, o muestra un mensaje de error si falla.
     */
    @PostMapping("/saveusuarios")
    public String save(Usuarios usuario) {
        try {
            // Cifra la contraseña antes de guardar en la base de datos
            String encryptedPassword = AESUtil.encrypt(usuario.getContrasena());
            usuario.setContrasena(encryptedPassword);

            // Guarda el usuario con la contraseña cifrada
            urep.save(usuario);
            return "redirect:/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/formusuarios?error"; // Redirige con mensaje de error si ocurre un problema
        }
    }

    /**
     * Muestra el formulario de edición para un usuario específico.
     *
     * @param id ID del usuario que se va a editar.
     * @return ModelAndView que muestra el formulario de edición con los datos del usuario.
     */
    @PostMapping("/editausuarios")
    public ModelAndView editarPorId(@RequestParam("id") int id) {
        Optional<Usuarios> usuario = urep.findById(id);
        ModelAndView mav = new ModelAndView("formusuarios");
        usuario.ifPresent(value -> mav.addObject("usuario", value));
        return mav;
    }

    /**
     * Elimina un usuario de la base de datos.
     *
     * @param id ID del usuario que se va a eliminar.
     * @return Redirige a la lista de usuarios después de eliminar.
     */
    @PostMapping("/eliminarusuarios")
    public String delete(@RequestParam("id") int id) {
        urep.deleteById(id);
        return "redirect:/listausuarios";
    }

    /**
     * Muestra la página de inicio después de iniciar sesión.
     *
     * @return ModelAndView que muestra la página de inicio.
     */
    @GetMapping("/inicio")
    public ModelAndView inicio() {
        return new ModelAndView("inicio");
    }

    /**
     * Muestra la página de inicio de sesión.
     *
     * @return ModelAndView que muestra la página de inicio de sesión.
     */
    @GetMapping("/index")
    public ModelAndView iniciosesion() {
        return new ModelAndView("index");
    }

    /**
     * Cierra la sesión actual y redirige a la página de inicio de sesión.
     *
     * @return Redirige a la página de inicio de sesión.
     */
    @GetMapping("/logout")
    public String logout() {
        // Elimina el email de la sesión
        session.removeAttribute("userEmail");
        session.invalidate();
        return "redirect:/index";
    }

    /**
     * Autentica al usuario en función de su correo electrónico y contraseña cifrada.
     *
     * @param email Correo electrónico del usuario.
     * @param contrasena Contraseña ingresada por el usuario.
     * @return Redirige a la página de inicio si las credenciales son correctas, o muestra un mensaje de error en caso contrario.
     */
    @PostMapping("/login")
    public ModelAndView doLogin(@RequestParam("email") String email, @RequestParam("contrasena") String contrasena) {
        try {
            String encryptedPassword = AESUtil.encrypt(contrasena);
            Optional<Usuarios> usuario = urep.findByEmailAndContrasena(email, encryptedPassword);
            if (usuario.isPresent()) {
                // Guarda el email en la sesión
                session.setAttribute("userEmail", email);
                return new ModelAndView("redirect:/inicio");
            } else {
                ModelAndView mav = new ModelAndView("index");
                mav.addObject("error", "Correo electrónico o contraseña incorrectos");
                return mav;
            }
        } catch (Exception e) {
            ModelAndView mav = new ModelAndView("index");
            mav.addObject("error", "Ocurrió un error de autenticación");
            return mav;
        }
    }

    /**
     * Muestra el perfil del usuario autenticado, incluyendo sus detalles y empresas relacionadas.
     *
     * @param model el modelo de Spring utilizado para pasar datos a la vista
     * @return ModelAndView que muestra la vista del perfil del usuario
     */
    @GetMapping("/perfil")
    public ModelAndView perfil(Model model) {
        String email = (String) session.getAttribute("userEmail");

        if (email != null) {
            Usuarios usuario = urep.findByEmail(email);

            if (usuario != null) {
                model.addAttribute("userEmail", email);
                model.addAttribute("userNombre", usuario.getNombre());
                model.addAttribute("userRole", usuario.getRol());
                model.addAttribute("userBalance", usuario.getBalance());

                List<UsuarioEmpresa> listaEmpresasUsuario = usuarioEmpresasRepository.findByUsuario(usuario);
                model.addAttribute("empresasUsuario", listaEmpresasUsuario);
            } else {
                model.addAttribute("error", "Usuario no encontrado");
            }
        } else {
            model.addAttribute("error", "No hay una sesión activa");
        }

        return new ModelAndView("perfil");
    }

    /**
     * Maneja la compra de acciones, actualizando el balance del usuario e insertando los datos en la tabla usuario_empresas.
     *
     * @param empresa El nombre de la empresa seleccionada.
     * @param cantidad La cantidad de acciones compradas.
     * @return Redirige a la vista de acciones con un mensaje de éxito o error.
     */
    @PostMapping("/comprar")
    public ModelAndView comprarAcciones(@RequestParam("empresa") String empresa, @RequestParam("cantidad") int cantidad) {
        String email = (String) session.getAttribute("userEmail");
        Usuarios usuario = urep.findByEmail(email);

        if (usuario != null && cantidad > 0) {
            accionSeleccionada = accionRepository.findTopByNombreEmpresaOrderByFechaActualizacionDesc(empresa);
            if (accionSeleccionada.isPresent()) {
                BigDecimal precioAccion = accionSeleccionada.get().getPrecioActual();
                BigDecimal totalCompra = precioAccion.multiply(BigDecimal.valueOf(cantidad));

                if (usuario.getBalance().compareTo(totalCompra) >= 0) {
                    usuario.setBalance(usuario.getBalance().subtract(totalCompra));

                    UsuarioEmpresa nuevaCompra = new UsuarioEmpresa();
                    nuevaCompra.setUsuario(usuario);
                    nuevaCompra.setEmpresa(accionSeleccionada.get().getEmpresa());
                    nuevaCompra.setCantidad(cantidad);
                    nuevaCompra.setNombreEmpresa(accionSeleccionada.get().getNombreEmpresa());
                    nuevaCompra.setNombreUsuario(usuario.getNombre());
                    usuarioEmpresasRepository.save(nuevaCompra);

                    urep.save(usuario);

                    return new ModelAndView("redirect:/acciones").addObject("mensaje", "Compra realizada con éxito");
                } else {
                    return new ModelAndView("redirect:/acciones").addObject("error", "Saldo insuficiente para la compra");
                }
            } else {
                return new ModelAndView("redirect:/acciones").addObject("error", "Empresa no válida");
            }
        } else {
            return new ModelAndView("redirect:/acciones").addObject("error", "Usuario no válido o cantidad incorrecta");
        }
    }

    /**
     * Maneja la venta de acciones, actualizando el balance del usuario y eliminando la acción de la lista si la cantidad es igual.
     *
     * @param empresa El nombre de la empresa seleccionada.
     * @param cantidad La cantidad de acciones vendidas.
     * @return Redirige a la vista de acciones con un mensaje de éxito o error.
     */
    @PostMapping("/vender")
    public ModelAndView venderAcciones(@RequestParam("empresa") String empresa, @RequestParam("cantidad") int cantidad) {
        String email = (String) session.getAttribute("userEmail");
        Usuarios usuario = urep.findByEmail(email);

        if (usuario != null && cantidad > 0) {
            accionSeleccionada = accionRepository.findTopByNombreEmpresaOrderByFechaActualizacionDesc(empresa);

            if (accionSeleccionada.isPresent()) {
                BigDecimal precioAccion = accionSeleccionada.get().getPrecioActual();
                BigDecimal totalVenta = precioAccion.multiply(BigDecimal.valueOf(cantidad));

                List<UsuarioEmpresa> accionesUsuario = usuarioEmpresasRepository.findByUsuario(usuario);
                Optional<UsuarioEmpresa> accionUsuarioOpt = accionesUsuario.stream()
                        .filter(a -> a.getEmpresa().getNombreEmpresa().equals(empresa))
                        .findFirst();

                if (accionUsuarioOpt.isPresent()) {
                    UsuarioEmpresa accionUsuario = accionUsuarioOpt.get();

                    if (accionUsuario.getCantidad() >= cantidad) {
                        usuario.setBalance(usuario.getBalance().add(totalVenta));

                        if (accionUsuario.getCantidad() == cantidad) {
                            usuarioEmpresasRepository.delete(accionUsuario);
                        } else {
                            accionUsuario.setCantidad(accionUsuario.getCantidad() - cantidad);
                            usuarioEmpresasRepository.save(accionUsuario);
                        }

                        urep.save(usuario);

                        return new ModelAndView("redirect:/perfil").addObject("mensaje", "Venta realizada con éxito");
                    } else {
                        return new ModelAndView("redirect:/perfil").addObject("error", "Cantidad de acciones insuficiente para la venta");
                    }
                } else {
                    return new ModelAndView("redirect:/perfil").addObject("error", "No posee acciones de la empresa seleccionada");
                }
            } else {
                return new ModelAndView("redirect:/perfil").addObject("error", "No se encontró el precio actual de la acción");
            }
        } else {
            return new ModelAndView("redirect:/perfil").addObject("error", "Usuario no válido o cantidad incorrecta");
        }
    }

    /**
     * Muestra las acciones que posee el usuario autenticado.
     *
     * @return ModelAndView que muestra la vista con las acciones del usuario.
     */
    @GetMapping("/accionesUsuario")
    public ModelAndView mostrarAccionesUsuario() {
        String email = (String) session.getAttribute("userEmail");
        Usuarios usuario = urep.findByEmail(email);

        if (usuario != null) {
            List<UsuarioEmpresa> accionesUsuario = usuarioEmpresasRepository.findByUsuario(usuario);
            ModelAndView mav = new ModelAndView("accionesUsuario");
            mav.addObject("accionesUsuario", accionesUsuario);
            return mav;
        } else {
            return new ModelAndView("redirect:/index").addObject("error", "Usuario no válido");
        }
    }
}
