package inventario;

public class Categoria {
    private int idCategoria;
    private String nombreCategoria;
    private double pctg_temporadaAlta;
    private double pctg_temporadaBaja;
    private int costoAveriaLeve;
    private int costoAveriaModerada;
    private int costoAveriaTotal;
    private int tarifaDiaria;
    private int id_Padre;
    
    public Categoria(String nombreCategoria, double pctg_temporadaAlta, double pctg_temporadaBaja,
                     int costoAveriaLeve, int costoAveriaModerada, int costoAveriaTotal, int tarifaDiaria) {
                        this.nombreCategoria = nombreCategoria;
                        this.pctg_temporadaAlta = pctg_temporadaAlta;
                        this.pctg_temporadaBaja = pctg_temporadaBaja;
                        this.costoAveriaLeve = costoAveriaLeve;
                        this.costoAveriaModerada = costoAveriaModerada;
                        this.costoAveriaTotal = costoAveriaTotal;
                        this.tarifaDiaria = tarifaDiaria;
                     }
    
    public int getID() {
        return this.idCategoria;
    }

    public String getnombreCategoria() {
        return this.nombreCategoria;
    }

    public double getpctg_temporadaAlta() {
        return this.pctg_temporadaAlta;
    }

    public double getpctg_temporadaBaja() {
        return this.pctg_temporadaBaja;
    }

    public int getcostoAveriaLeve() {
        return this.costoAveriaLeve;
    }

    public int getcostoAveriaModerada() {
        return this.costoAveriaModerada;
    }

    public int getcostoAveriaTotal() {
        return this.costoAveriaTotal;
    }

    public int gettarifaDiaria() {
        return this.tarifaDiaria;
    }

    public int getPadre() {
        return this.id_Padre;
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

    public void setid_Padre(int id_Padre) {
        this.id_Padre=id_Padre;
    }
    

}
