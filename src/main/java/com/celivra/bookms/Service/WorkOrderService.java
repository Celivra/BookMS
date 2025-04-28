package com.celivra.bookms.Service;

import com.celivra.bookms.Entity.WorkOrder;
import com.celivra.bookms.Mapper.WorkOrderMapper;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkOrderService {
    @Autowired
    WorkOrderMapper workOrderMapper;

    public boolean addWorkOrder(WorkOrder workOrder) {
        return workOrderMapper.addWorkOrder(workOrder);
    }

    public boolean updateWorkOrder(WorkOrder workOrder) {
        return workOrderMapper.updateWorkOrder(workOrder);
    }
}
