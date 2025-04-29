package com.celivra.bookms.Controller;

import com.celivra.bookms.Entity.User;
import com.celivra.bookms.Entity.WorkOrder;
import com.celivra.bookms.Service.WorkOrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WorkOrderController {
    @Autowired
    WorkOrderService workOrderService;

    @PostMapping("/addWorkOrder")
    public String addWorkOrder(WorkOrder workOrder, HttpServletRequest request, RedirectAttributes reAModel) {

        User user = (User) request.getSession().getAttribute("user");
        workOrder.setUserId(user.getId());
        workOrderService.addWorkOrder(workOrder);
        reAModel.addFlashAttribute("activeSection", "order");
        return "redirect:/";
    }
}
