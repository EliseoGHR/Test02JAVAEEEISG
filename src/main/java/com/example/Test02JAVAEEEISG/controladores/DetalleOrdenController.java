package com.example.Test02JAVAEEEISG.controladores;

import com.example.Test02JAVAEEEISG.modelos.DetalleOrdenEISG;
import com.example.Test02JAVAEEEISG.servicios.interfaces.IDetalleOrdenService;
import com.example.Test02JAVAEEEISG.servicios.interfaces.IOrdenService;
import com.example.Test02JAVAEEEISG.servicios.interfaces.IProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/detalleOrdenes")
public class DetalleOrdenController {
    @Autowired
    private IDetalleOrdenService detalleOrdenService;

    @Autowired
    private IOrdenService ordenService;

    @Autowired
    private IProductoService productoService;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1) - 1; // si no está seteado se asigna 0
        int pageSize = size.orElse(5); // tamaño de la página, se asigna 5
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<DetalleOrdenEISG> detalleOrdenes = detalleOrdenService.buscarTodosPaginados(pageable);
        model.addAttribute("detalleOrdenes", detalleOrdenes);

        int totalPages = detalleOrdenes.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "detalleOrden/index";
    }

    @GetMapping("/create")
    public String create(DetalleOrdenEISG detalleOrdenEISG, Model model){
        model.addAttribute("productos", productoService.obtenerTodos());
        model.addAttribute("ordenes", ordenService.obtenerTodos());
        return "detalleOrden/create";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("detalleOrdenEISG") DetalleOrdenEISG detalleOrdenEISG, BindingResult result, Model model, RedirectAttributes attributes){
        if(result.hasErrors()){
            model.addAttribute(detalleOrdenEISG);
            model.addAttribute("productos", productoService.obtenerTodos());
            model.addAttribute("ordenes", ordenService.obtenerTodos());
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error.");
            return "detalleOrden/create";
        }

        detalleOrdenService.crearOEditar(detalleOrdenEISG);
        attributes.addFlashAttribute("msg", "Detalle Orden creada/modificada correctamente");
        return "redirect:/detalleOrdenes";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Long id, Model model){
        DetalleOrdenEISG detalleOrdenEISG = detalleOrdenService.buscarPorId(id).get();
        model.addAttribute("detalleOrden", detalleOrdenEISG);
        model.addAttribute("productos", productoService.obtenerTodos());
        model.addAttribute("ordenes", ordenService.obtenerTodos());
        return "detalleOrden/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model){
        DetalleOrdenEISG detalleOrdenEISG = detalleOrdenService.buscarPorId(id).get();
        model.addAttribute("detalleOrden",detalleOrdenEISG);
        model.addAttribute("productos", productoService.obtenerTodos());
        model.addAttribute("ordenes", ordenService.obtenerTodos());
        return "detalleOrden/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Long id, Model model){
        DetalleOrdenEISG detalleOrdenEISG = detalleOrdenService.buscarPorId(id).get();
        model.addAttribute("detalleOrden", detalleOrdenEISG);
        model.addAttribute("productos", productoService.obtenerTodos());
        model.addAttribute("ordenes", ordenService.obtenerTodos());
        return "detalleOrden/delete";
    }

    @PostMapping("/delete")
    public String delete(DetalleOrdenEISG detalleOrdenEISG, RedirectAttributes attributes){
        detalleOrdenService.eliminarPorId(detalleOrdenEISG.getId());
        attributes.addFlashAttribute("msg", "Detalle Orden eliminada correctamente");
        return "redirect:/detalleOrdenes";
    }
}
