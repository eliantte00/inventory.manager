package reftools.inventory.manager.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import reftools.inventory.manager.dto.AutopartRequest;
import reftools.inventory.manager.mapper.AutopartMapper;
import reftools.inventory.manager.service.AutopartService;

@Controller
public class AutopartViewController {

    private final AutopartService autopartService;

    public AutopartViewController(AutopartService autopartService) {
        this.autopartService = autopartService;
    }

    @GetMapping({"/", "/autoparts"})
    public String showAutoparts(Model model) {
        if (!model.containsAttribute("autopartForm")) {
            model.addAttribute("autopartForm", new AutopartRequest());
        }
        model.addAttribute("editing", false);
        model.addAttribute("autoparts", autopartService.getAllAutoparts());
        return "autoparts";
    }

    @PostMapping("/autoparts")
    public String createAutopart(@ModelAttribute("autopartForm") AutopartRequest request,
                                 RedirectAttributes redirectAttributes) {
        autopartService.createAutopart(AutopartMapper.fromRequest(request));
        redirectAttributes.addFlashAttribute("successMessage", "La refacción se registró correctamente.");
        return "redirect:/autoparts";
    }

    @GetMapping("/autoparts/{id}/editar")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("autopartForm", AutopartMapper.toRequest(autopartService.getAutopart(id)));
            model.addAttribute("autoparts", autopartService.getAllAutoparts());
            model.addAttribute("editing", true);
            return "autoparts";
        } catch (EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "No se encontró la refacción solicitada.");
            return "redirect:/autoparts";
        }
    }

    @PostMapping("/autoparts/{id}/editar")
    public String updateAutopart(@PathVariable Long id,
                                 @ModelAttribute("autopartForm") AutopartRequest request,
                                 RedirectAttributes redirectAttributes) {
        try {
            autopartService.updateAutopart(id, AutopartMapper.fromRequest(request));
            redirectAttributes.addFlashAttribute("successMessage", "La refacción se actualizó correctamente.");
        } catch (EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "No se encontró la refacción que intentas editar.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("autopartForm", request);
            return "redirect:/autoparts/" + id + "/editar";
        }
        return "redirect:/autoparts";
    }

    @RequestMapping(value = "/autoparts/{id}/eliminar", method = RequestMethod.POST)
    public String deleteAutopart(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            autopartService.deleteAutopart(id);
            redirectAttributes.addFlashAttribute("successMessage", "La refacción se eliminó correctamente.");
        } catch (EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "No se encontró la refacción que intentas eliminar.");
        }
        return "redirect:/autoparts";
    }
}
