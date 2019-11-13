/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.gson.Gson;
import dao.MesaDao;
import dao.PedidoDao;
import dao.ProdutoDao;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.Mesa;
import model.Ocupacao;
import model.Pedido;
import model.dto.PedidoDTO;

/**
 *
 * @author u
 */

@Path("/pedido")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.TEXT_PLAIN)
public class PedidoWS {
    
    
    
    @Path("/mesa/{qrcode}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public String aberturaMesa(@PathParam("qrcode") String qrcode){
        return new Gson().toJson(getMesaQrcode(qrcode));
        
    }

    private Mesa getMesaQrcode(String qrcode) {
        MesaDao dao = new MesaDao();
        //Mesa mesa = dao.pesquisarQRCODE
        return mesaTeste(qrcode);
    }
   

    @POST
    @Path("/finalizar")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Pedido cadastroPedido(String pedidoJSON){
        PedidoDTO pedidoDTO = new Gson().fromJson(pedidoJSON, PedidoDTO.class);
        Pedido pedido = pedidoDTO.getPedido();
        
        try{
            return new PedidoDao().salvar(pedido);
        }catch(Exception e){
            return null;
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
       public Mesa mesaTeste(String qrcode){
        Mesa m = new Mesa(Long.MIN_VALUE, 10L, true, "brunobixona", Ocupacao.DISPONIVEL, "mesa 10");
        if(qrcode.equals(m.getQrCode()))
            return m;
        else
            return null;
    }
    
  
   
    
    
    
    
    
}
