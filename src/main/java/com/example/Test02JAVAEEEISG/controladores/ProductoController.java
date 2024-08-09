package com.example.Test02JAVAEEEISG.controladores;

import org.springframework.stereotype.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Test02JAVAEEEISG.modelos.ProductoEISG;
import com.example.Test02JAVAEEEISG.servicios.interfaces.IProductoService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private IProductoService productoService;
    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1) - 1; // si no está seteado se asigna 0
        int pageSize = size.orElse(5); // tamaño de la página, se asigna 5
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<ProductoEISG> productoEISGS = productoService.buscarTodosPaginados(pageable);
        model.addAttribute("productos", productoEISGS);

        int totalPages = productoEISGS.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "producto/index";
    }

    @GetMapping("/create")
    public String create(ProductoEISG productoEISG){
        return "producto/create";
    }

    @PostMapping("/save")
    public String save(ProductoEISG productoEISG, BindingResult result, Model model, RedirectAttributes attributes){
        if(result.hasErrors()){
            model.addAttribute(productoEISG);
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error.");
            return "producto/create";
        }

        productoService.crearOEditar(productoEISG);
        attributes.addFlashAttribute("msg", "Producto creado/modificado correctamente");
        return "redirect:/productos";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model){
        ProductoEISG productoEISG = productoService.buscarPorId(id).get();
        model.addAttribute("producto", productoEISG);
        return "producto/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        ProductoEISG productoEISG = productoService.buscarPorId(id).get();
        model.addAttribute("producto", productoEISG);
        return "producto/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model){
        ProductoEISG productoEISG = productoService.buscarPorId(id).get();
        model.addAttribute("producto", productoEISG);
        return "producto/delete";
    }

    @PostMapping("/delete")
    public String delete(ProductoEISG productoEISG, RedirectAttributes attributes){
        productoService.eliminarPorId(productoEISG.getId());
        attributes.addFlashAttribute("msg", "Producto eliminado correctamente");
        return "redirect:/productos";
    }
}