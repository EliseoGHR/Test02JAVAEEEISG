package com.example.Test02JAVAEEEISG.controladores;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Test02JAVAEEEISG.modelos.OrdenEISG;
import com.example.Test02JAVAEEEISG.servicios.interfaces.IOrdenService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ordenes")
public class OrdenController {
    @Autowired
    private IOrdenService ordenService;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1) - 1; // si no está seteado se asigna 0
        int pageSize = size.orElse(5); // tamaño de la página, se asigna 5
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<OrdenEISG> ordenes = ordenService.buscarTodosPaginados(pageable);
        model.addAttribute("ordenes", ordenes);

        int totalPages = ordenes.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "orden/index";
    }

    @GetMapping("/create")
    public String create(OrdenEISG ordenEISG){
        return "orden/create";
    }

    @PostMapping("/save")
    public String save(@Valid OrdenEISG ordenEISG, BindingResult result, Model model, RedirectAttributes attributes){
        if(result.hasErrors()){
            model.addAttribute(ordenEISG);
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error.");
            return "orden/create";
        }

        ordenService.crearOEditar(ordenEISG);
        attributes.addFlashAttribute("msg", "Orden creada/modificada correctamente");
        return "redirect:/ordenes";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Long id, Model model){
        OrdenEISG ordenEISG = ordenService.buscarPorId(id).get();
        model.addAttribute("orden", ordenEISG);
        return "orden/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model){
        OrdenEISG ordenEISG = ordenService.buscarPorId(id).get();
        model.addAttribute("orden", ordenEISG);
        return "orden/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Long id, Model model){
        OrdenEISG ordenEISG = ordenService.buscarPorId(id).get();
        model.addAttribute("orden", ordenEISG);
        return "orden/delete";
    }

    @PostMapping("/delete")
    public String delete(OrdenEISG ordenEISG, RedirectAttributes attributes){
        ordenService.eliminarPorId(ordenEISG.getId());
        attributes.addFlashAttribute("msg", "Orden eliminada correctamente");
        return "redirect:/ordenes";
    }
}
