package alquiler;

public class Categoria {
    private int idCategoria;
    private String nombreCategoria;
    private int pctg_temporadaAlta;
    private int pctg_temporadaBaja;
    private int costoAveriaLeve;
    private int costoAveriaModerada;
    private int costoAveriaTotal;
    private int tarifaDiaria;
    private Categoria Padre;
    
    public Categoria(String nombreCategoria, int pctg_temporadaAlta, int pctg_temporadaBaja,
                     int costoAveriaLeve, int costoAveriaModerada, int costoAveriaTotal, int tarifaDiaria) {
                        this.nombreCategoria = nombreCategoria;
                        this.pctg_temporadaAlta = pctg_temporadaAlta;
                        this.pctg_temporadaBaja = pctg_temporadaBaja;
                        this.costoAveriaLeve = costoAveriaLeve;
                        this.costoAveriaModerada = costoAveriaModerada;
                        this.costoAveriaTotal = costoAveriaTotal;
                        this.tarifaDiaria = tarifaDiaria;
                        this.Padre = null;
                     }
    
    public int getID() {
        return idCategoria;
    }

    public String getnombreCategoria() {
        return nombreCategoria;
    }

    public int getpctg_temporadaAlta() {
        return pctg_temporadaAlta;
    }

    public int getpctg_temporadaBaja() {
        return pctg_temporadaBaja;
    }

    public int getcostoAveriaLeve() {
        return costoAveriaLeve;
    }

    public int getcostoAveriaModerada() {
        return costoAveriaModerada;
    }

    public int getcostoAveriaTotal() {
        return costoAveriaTotal;
    }

    public int gettarifaDiaria() {
        return tarifaDiaria;
    }

    public Categoria getPadre() {
        return Padre;
    }

    public void setpctg_temporadaAlta(int fechaInicio, int fechaFin) {
        this.pctg_temporadaAlta = fechaInicio;
    }
    

    public void setpctg_temporadaBaja(int fechaInicio, int fechaFin) {
        this.pctg_temporadaBaja = fechaFin;
    }

    public void setcostoAveriaLeve(int costoALeve) {
        this.costoAveriaLeve = costoALeve;
    }

    public void setcostoAveriaModerada(int costoAModerada) {
        this.costoAveriaModerada = costoAModerada;
    }

    public void setcostoAveriaTotal(int costoATotal) {
        this.costoAveriaTotal = costoATotal;
    }

    public void settarifaDiaria(int costoTarifa) {
        this.tarifaDiaria = costoTarifa;
    }

    public void setPadre() {
    }
    

}
