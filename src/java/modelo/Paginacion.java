/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;

/**
 *
 * @author juana
 */
public class Paginacion {
    List<productos> listapro;
    int numpag;
    int pag;

    public Paginacion(List<productos> listapro, int numpag, int pag) {
        this.listapro = listapro;
        this.numpag = numpag;
        this.pag=pag;
    }

    public int getPag() {
        return pag;
    }

    public void setPag(int pag) {
        this.pag = pag;
    }

    public List<productos> getListapro() {
        return listapro;
    }

    public void setListapro(List<productos> listapro) {
        this.listapro = listapro;
    }

    public int getNumpag() {
        return numpag;
    }

    public void setNumpag(int numpag) {
        this.numpag = numpag;
    }
    
}
