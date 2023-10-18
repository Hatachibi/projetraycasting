package com.modules.controller;

import com.modules.controller.api.MondeApi;
import com.modules.entity.Monde;
import com.modules.vue.Rendu;

public class MondeController implements MondeApi {

    public static void genererMapDeBase() {
        Rendu.getInstance().drawWorld(Monde.baseMap);
    }



}
