package practica.data;

import jakarta.xml.bind.annotation.*;
import practica.logic.Equipo;
import practica.logic.Partido;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

public class Data {
    @XmlElementWrapper(name = "equipos")
    @XmlElement(name = "equipo")
    private List<Equipo> equipos;

    @XmlElementWrapper(name = "partidos")
    @XmlElement(name = "partido")
    private List<Partido> partidos;

    public Data() {

        equipos = new ArrayList<>();
        partidos = new ArrayList<>();

        Equipo equipo1 = new Equipo("SAP", "Deportivo Saprissa", 0, 0, 0, 0, 0);
        Equipo equipo2 = new Equipo("ALA", "L.D. Alajuelense", 0, 0, 0, 0, 0);
        Equipo equipo3 = new Equipo("SCA", "San Carlos", 0, 0, 0, 0, 0);
        Equipo equipo4 = new Equipo("SPO", "Sporting San Jose", 0, 0, 0, 0, 0);
        Equipo equipo5 = new Equipo("HER", "Herediano", 0, 0, 0, 0, 0);
        Equipo equipo6 = new Equipo("SAN", "Santa Ana", 0, 0, 0, 0, 0);
        Equipo equipo7 = new Equipo("GUA", "Guanacasteca", 0, 0, 0, 0, 0);
        Equipo equipo8 = new Equipo("SGU", "Santos de Guapiles", 0, 0, 0, 0, 0);
        Equipo equipo9 = new Equipo("PFC", "Puntarenas F.C.", 0, 0, 0, 0, 0);
        Equipo equipo10 = new Equipo("PER", "Perez Zeledon", 0, 0, 0, 0, 0);
        Equipo equipo11 = new Equipo("CRA", "Cartago", 0, 0, 0, 0, 0);
        Equipo equipo12 = new Equipo("LIB", "Liberia", 0, 0, 0, 0, 0);

        equipos.add(equipo1);
        equipos.add(equipo2);
        equipos.add(equipo3);
        equipos.add(equipo4);
        equipos.add(equipo5);
        equipos.add(equipo6);
        equipos.add(equipo7);
        equipos.add(equipo8);
        equipos.add(equipo9);
        equipos.add(equipo10);
        equipos.add(equipo11);
        equipos.add(equipo12);


    }

    public List<Equipo> getEquipos(){
        return equipos;
    }


    public List<Partido> getPartidos(){
        return partidos;
    }

}
