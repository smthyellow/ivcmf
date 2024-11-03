package com.example.ivcmf.service;

import com.example.ivcmf.model.Bank;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class BankService {

    public List<Bank> convertJSONtoList(String json) {

        String newJson = json.replace("�?", "И"); //устранение проблемы с буквой И

        GsonBuilder gson = new GsonBuilder();
        Type type = new TypeToken<ArrayList<Bank>>() {
        }.getType();
        List<Bank> banks = gson.create().<ArrayList<Bank>>fromJson(newJson, type);

        for (Bank bank : banks) {
            bank.setDtControl(convertDates(bank.getDtControl()));
            bank.setDTBegin(convertDates(bank.getDTBegin()));
            bank.setDtEnd(convertDates(bank.getDtEnd()));

        }
        return banks;
    }

    private String convertDates(String string) {

        if (string != null) {
            return String.format("%s.%s.%s",
                    string.substring(8, 10),
                    string.substring(5, 7),
                    string.substring(0, 4));
        } else {
            return null;
        }

    }

}
