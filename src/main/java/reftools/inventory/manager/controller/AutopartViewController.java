package reftools.inventory.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
}
