package practica.logic;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlIDREF;

@XmlAccessorType(XmlAccessType.FIELD)
public class Partido {
    @XmlIDREF
    private Equipo equipoCasa;

    @XmlIDREF
    private Equipo equipoVisita;

    // QUITAR @XmlIDREF de estos campos - son primitivos int
    private int golesCasa;
    private int golesVisita;

    public Partido(Equipo equipoCasa, Equipo equipoVisita, int golesCasa, int golesVisita) {
        this.equipoCasa = equipoCasa;
        this.equipoVisita = equipoVisita;
        this.golesCasa = golesCasa;
        this.golesVisita = golesVisita;
    }

    public Partido() {
        this(null, null, 0, 0);
    }

    // Getters y setters (mantener los existentes)
    public Equipo getEquipoCasa() {
        return equipoCasa;
    }

    public void setEquipoCasa(Equipo equipoCasa) {
        this.equipoCasa = equipoCasa;
    }

    public Equipo getEquipoVisita() {
        return equipoVisita;
    }

    public void setEquipoVisita(Equipo equipoVisita) {
        this.equipoVisita = equipoVisita;
    }

    public int getGolesCasa() {
        return golesCasa;
    }

    public void setGolesCasa(int golesCasa) {
        this.golesCasa = golesCasa;
    }

    public int getGolesVisita() {
        return golesVisita;
    }

    public void setGolesVisita(int golesVisita) {
        this.golesVisita = golesVisita;
    }
}