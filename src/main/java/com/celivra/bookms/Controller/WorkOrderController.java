package com.celivra.bookms.Controller;

import com.celivra.bookms.Entity.WorkOrder;
import com.celivra.bookms.Service.WorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WorkOrderController {
    @Autowired
    WorkOrderService workOrderService;

    @PostMapping("addWorkOrder")
    public String addWorkOrder(@RequestBody WorkOrder workOrder, RedirectAttributes reAModel) {


        reAModel.addFlashAttribute("activeSection", "order");
        return "redirect:/workOrders";
    }
}
