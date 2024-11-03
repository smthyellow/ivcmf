package com.example.ivcmf.web;

import com.example.ivcmf.model.Bank;
import com.example.ivcmf.service.BankService;
import com.example.ivcmf.web.Util.APIConnector;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
public class TableController {

    private final BankService bankService;

    private final APIConnector apiConnector;

    public TableController(APIConnector apiConnector, BankService bankService) {
        this.apiConnector = apiConnector;
        this.bankService = bankService;
    }

    String attributeBanks = "banks";

    @GetMapping("/")
    public String testView(Model model) {

        String jsonString = "";

        try {

            URL url = new URL("https://api.nbrb.by/bic");
            jsonString = apiConnector.getJson(url);

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        byte[] bytes = jsonString.getBytes();
        String utf8EncodedString = new String(bytes, StandardCharsets.UTF_8);

        List<Bank> bankList = bankService.convertJSONtoList(utf8EncodedString);

        model.addAttribute(attributeBanks, bankList);

        return "index";
    }

}
