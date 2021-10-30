package co.edu.usa.fincaapp.Ciclo3.reportes;

import co.edu.usa.fincaapp.Ciclo3.entidades.Client;

/**
 *
 * @author USUARIO
 */
public class ContadorClientes {
    private Long total;
    private Client client;

    public ContadorClientes(Long total, Client client) {
        this.total = total;
        this.client = client;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    
    
    
    
    
}
