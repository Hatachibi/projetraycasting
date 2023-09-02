package com.modules.controller;

import com.modules.controller.api.PersonnageApi;
import com.modules.service.PersonnageService;
import com.modules.service.interfaces.IPersonnageService;
import com.modules.vue.Rendu;

public class PersonnageController implements PersonnageApi {

    private static final PersonnageApi personnageController = new PersonnageController();

    public static PersonnageApi getPersonnageController() {
        return personnageController;
    }
    @Override
    public void deplacement() {
        PersonnageService.getPersonnageService().deplacement();
    }

    @Override
    public void subitDegats(int dommage) {

    }

    public void drawPlayer() {
        PersonnageService.getPersonnageService().drawPlayer();
    }
}
