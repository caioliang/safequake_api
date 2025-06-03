package br.com.fiap.safequake_api.service;

import br.com.fiap.safequake_api.model.EarthquakeEvent;
import br.com.fiap.safequake_api.model.User;

    public class AlertService {
        public void emitirAlerta(User user, EarthquakeEvent evento, String nivel) {
        System.out.printf("🚨 ALERTA %s para %s! Terremoto detectado em %s (Mag %.2f)%n",
                nivel, user.getEmail(), evento.getPlace(), evento.getMagnitude());
}
}
