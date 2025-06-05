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

    public static double efeitoDepth(double depth) {
        final double MAX_DEPTH = 700.0;
        if (depth > MAX_DEPTH) return 1.0;
        if (depth < 0) return 0.0;
        return 1.0 - (depth / MAX_DEPTH); 
    }


    public static double calcularIntensidade(double magnitude, double depth) {
        double depthEffect = efeitoDepth(depth);
        return magnitude * 0.8 - depthEffect * 0.2;
    }

    public static String definirNivel(double score) {
        if (score > 6.0) return "CRÍTICO";
        else if (score > 4.0) return "ALTO";
        return "MODERADO";
    }
}