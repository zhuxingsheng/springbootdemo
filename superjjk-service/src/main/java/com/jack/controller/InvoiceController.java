package com.jack.controller;

import com.jjk.client.api.InvoiceApi;
import com.jjk.client.model.Invoices;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvoiceController implements InvoiceApi {
    @Override
    public Long createInvoice() {
        return null;
    }

    @Override
    public Invoices listInvoice() {
        return null;
    }
}
