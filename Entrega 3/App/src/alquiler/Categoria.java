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

    public void setpctg_temporadaAlta(int pctg_temporadaAlta) {
        this.pctg_temporadaAlta = pctg_temporadaAlta;
    }

    public void setpctg_temporadaBaja(int pctg_temporadaBaja) {
        this.pctg_temporadaBaja = pctg_temporadaBaja;
    }

    public void setcostoAveriaLeve(int costoAveriaLeve) {
        this.costoAveriaLeve = costoAveriaLeve;
    }

    public void setcostoAveriaModerada(int costoAveriaModerada) {
        this.costoAveriaModerada = costoAveriaModerada;
    }

    public void setcostoAveriaTotal(int costoAveriaTotal) {
        this.costoAveriaTotal = costoAveriaTotal;
    }

    public void settarifaDiaria(int tarifaDiaria) {
        this.tarifaDiaria = tarifaDiaria;
    }

    public void setPadre(Categoria categoriaPadre) {
        this.Padre = categoriaPadre;
    }

}
