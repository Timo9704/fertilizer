package de.aquaristik.kosmos.fertilizerService.fertilizer;

import javax.persistence.*;

@Entity
@Table(name = "fertilizer")
public class Fertilizer {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        private String name;

        private String hersteller;

        private double nitrat;

        private double phosphat;

        private double kalium;

        private double eisen;

        private double magnesium;


        public long getId() {
                return id;
        }

        public void setId(long id) {
                this.id = id;
        }

        public double getNitrat() {
                return nitrat;
        }

        public void setNitrat(double nitrat) {
                this.nitrat = nitrat;
        }

        public double getPhosphat() {
                return phosphat;
        }

        public void setPhosphat(double phosphat) {
                this.phosphat = phosphat;
        }

        public double getKalium() {
                return kalium;
        }

        public void setKalium(double kalium) {
                this.kalium = kalium;
        }

        public double getEisen() {
                return eisen;
        }

        public void setEisen(double eisen) {
                this.eisen = eisen;
        }

        public double getMagnesium() {
                return magnesium;
        }

        public void setMagnesium(double magnesium) {
                this.magnesium = magnesium;
        }

        public String getName() { return name; }

        public void setName(String name) { this.name = name; }

        public String getHersteller() { return hersteller; }

        public void setHersteller(String hersteller) { this.hersteller = hersteller; }

        public Fertilizer() {
        }

        public Fertilizer(Fertilizer fert){
               this.id = fert.id;
               this.name = fert.name;
               this.hersteller = fert.hersteller;
               this.nitrat = fert.nitrat;
               this.phosphat = fert.phosphat;
               this.kalium = fert.kalium;
               this.eisen = fert.eisen;
               this.magnesium = fert.magnesium;
        }
}
