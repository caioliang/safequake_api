package br.com.fiap.safequake_api.util;

public class GeoUtils {

    private static final double EARTH_RADIUS_KM = 6371.0;

    public static double calcularDistanciaKm(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        return EARTH_RADIUS_KM * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    }

    public static double calcularIntensidade(double magnitude, double depth) {
        return magnitude * 0.7 - depth * 0.3;
    }

    public static String definirNivel(double score) {
        if (score > 6.0) return "CRÍTICO";
        else if (score > 4.0) return "ALTO";
        return "MODERADO";
    }
}