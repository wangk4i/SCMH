package com.hyd.gwinterfaceserver.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GWUrl {
    public static String mentalhealthbusinesssynergy;

    @Value("${WebServer.MentalhealthbusinesssynergyURL}")
    public  void setMentalHealthInterfaceUrl(String mentalhealthbusinesssynergy) { GWUrl.mentalhealthbusinesssynergy = mentalhealthbusinesssynergy; }
}
