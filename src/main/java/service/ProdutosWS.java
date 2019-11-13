/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import dao.ProdutoDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.Produto;
import com.google.gson.Gson;
import dao.BordaDao;
import dao.CategoriaDao;
import dao.MesaDao;
import dao.SaboresDao;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import model.Borda;
import model.Categoria;
import model.Sabores;

/**
 *
 * @author u
 */

@Path("/buscar")
public class ProdutosWS {
        
    @Path("/produtos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String showProdutos(){
        return new Gson().toJson(getProdutosBanco());
    }
    
    
    @Path("/bordas")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String showBordas(){
        return new Gson().toJson(getBordasBanco());
    }
  
    
    @Path("/categorias")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String showCategorias(){
        return new Gson().toJson(getCategoriasBanco());
    }
    
    
    @Path("/sabores")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String showSabores(){
        return new Gson().toJson(getSaboresBanco());
    }
    
    private List<Produto> getProdutosBanco(){
      try{
          return new ProdutoDao().listarParaPedidos();
      }catch(Exception x){
          return new ArrayList<>();
      }
    }
    
    private List<Borda> getBordasBanco(){
        try{
           return new BordaDao().listar();
        }catch(Exception e){
            return new ArrayList<>();
        }
    }
    
    private List<Categoria> getCategoriasBanco() {
        try{
            return new CategoriaDao().listar();
        }catch(Exception e){
            return new ArrayList<>();
        }
    }
 
    
    private List<Sabores> getSaboresBanco() {
        try{
            return new SaboresDao().listar();
        }catch(Exception e){
            return new ArrayList<>();
        }
    }
      
 
}



/*















    
    
       private List<Borda> lista_bordas() {
       
           List<Borda> bordas = new ArrayList<>();
           bordas.add(new Borda(Long.MIN_VALUE, "Catupiry", 3.00));
           bordas.add(new Borda(Long.MIN_VALUE, "Cheddar", 3.00));
           bordas.add(new Borda(Long.MIN_VALUE, "bacon", 5.00));
           bordas.add(new Borda(Long.MIN_VALUE, "Chocolate", 5.00));
           bordas.add(new Borda(Long.MIN_VALUE, "Nutella", 8.00));
           bordas.add(new Borda(Long.MIN_VALUE, "Sem Borda", 0.00));
                    
           return bordas;
       }  




   /*    List<Produto> lista = new ArrayList<>();
        lista.add(new Produto(Long.MIN_VALUE, "Coca cola 2L", "Refrigerante - Coca cola", 7.00, myListCategoria().get(0) , 0, false, false, true));
        lista.add(new Produto(Long.MIN_VALUE, "Sprit 2L", "Refrigerante Sprit", 7.00, myListCategoria().get(0) , 0, false, false, true));
        lista.add(new Produto(Long.MIN_VALUE, "Laranjinha 2L", "Refrigerante Sprit", 7.00, myListCategoria().get(0), 0, false, false, true));
        lista.add(new Produto(Long.MIN_VALUE, "Pepsi", "Refrigerante Sprit", 7.00, myListCategoria().get(0), 0, false, false, true));
        lista.add(new Produto(Long.MIN_VALUE, "Puresa", "Refrigerante Sprit", 7.00, myListCategoria().get(0), 0, false, false, true));
        lista.add(new Produto(Long.MIN_VALUE, "Pepsisi", "Refrigerante Sprit", 7.00, myListCategoria().get(0), 0, false, false, true));
        lista.add(new Produto(Long.MIN_VALUE, "Puresinha feio", "Refrigerante Sprit", 7.00, myListCategoria().get(1), 0, false, false, true));
        lista.add(new Produto(Long.MIN_VALUE, "Água sem gás 500ml", "agua sem gas", 2.50, myListCategoria().get(1), 0, false, false ,true ));
        lista.add(new Produto(Long.MIN_VALUE, "Água COM gás 500ml", "agua sem gas", 2.50,myListCategoria().get(1), 0, false, false ,true));
        lista.add(new Produto(Long.MIN_VALUE, "Pizza Broto", "pizza pequena 8 fatias", 50.00, myListCategoria().get(3), 1, true, true,true ));
        lista.add(new Produto(Long.MIN_VALUE, "Pizza P", "pizza pequena 8 fatias", 50.00, myListCategoria().get(3), 1, true, true,true ));
        lista.add(new Produto(Long.MIN_VALUE, "Pizza PM", "pizza pequena 8 fatias", 50.00, myListCategoria().get(3), 1, true, true,true ));
        lista.add(new Produto(Long.MIN_VALUE, "Pizza M", "pizza media 10 fatias", 55.00, myListCategoria().get(3), 2, true, true,true));
        lista.add(new Produto(Long.MIN_VALUE, "Pizza G", "pizza grande 12 fatias", 60.00, myListCategoria().get(3), 3, true, true,true));
        lista.add(new Produto(Long.MIN_VALUE, "Pizza GG", "pizza gigante 15 fatias", 70.00, myListCategoria().get(3), 4, true, true,true));
        lista.add(new Produto(Long.MIN_VALUE, "Lasanha Pequena", "lasanha media", 20.00, myListCategoria().get(2), 2, false, true,true));
        lista.add(new Produto(Long.MIN_VALUE, "Lasanha Média", "lasanha media", 20.00, myListCategoria().get(2), 2, false, true,true));
        lista.add(new Produto(Long.MIN_VALUE, "Lasanha Double", "lasanha media", 20.00, myListCategoria().get(2), 2, false, true,true));
        lista.add(new Produto(Long.MIN_VALUE, "Lasanha Grande", "lasanha grande", 40.00, myListCategoria().get(2), 4, false, true,true));
        lista.add(new Produto(Long.MIN_VALUE, "Xis de coração", "xis coração", 17.00, myListCategoria().get(4), 0, false, true,true));
        lista.add(new Produto(Long.MIN_VALUE, "Xis do banha", "xis do banha", 15.00, myListCategoria().get(4), 0, false, true,true));
        
     lista.add(new Produto(2, "Refrigerante - sprite 350ml", "sprit lata", 3.00,true, new Categoria(1, "Bebidas", false)));
        lista.add(new Produto(3, "Água sem gás 500ml", "agua sem gas", 2.50,true, new Categoria(1, "Bebidas", false)));
        lista.add(new Produto(4, "Água sem gás 500ml", "agua sem gas", 2.50,true, new Categoria(2, "Drinks", false)));
        lista.add(new Produto(5, "Pizza Calabresa", "pizza de calabresa, cabela e azeitonas", 50.00, true, new Categoria(5, "Pizzas", true)));
        lista.add(new Produto(6, "Pizza Calabresa e peperoni", "pizza de calabresa, cabela e azeitonas", 55.00, true, new Categoria(5, "Pizzas", true)));
        lista.add(new Produto(7, "Pizza Bacon", "pizza de bacon, cabela e queijo", 55.00, true, new Categoria(5, "Pizzas", true)));
        lista.add(new Produto(8, "Pizza Bacon", "pizza de bacon, cabela e queijo", 55.00, true, new Categoria(6, "Pizzas Doces", true)));
        lista.add(new Produto(9, "Pizza Bacon", "pizza de bacon, cabela e queijo", 55.00, true, new Categoria(7, "Pizzas Sem Lactose", true)));
 
  










    private List<Categoria> myListCategoria() {
        List<Categoria> lista_categoria = new ArrayList<>();
        lista_categoria.add(new Categoria(Long.MIN_VALUE,"Bebidas", false));
        lista_categoria.add(new Categoria(Long.MIN_VALUE,"Drinks", false));
        lista_categoria.add(new Categoria(Long.MIN_VALUE,"Lasanhas", true));
        lista_categoria.add(new Categoria(Long.MIN_VALUE,"Pizzas", true));
        lista_categoria.add(new Categoria(Long.MIN_VALUE,"Pizzas Doces", true));
        lista_categoria.add(new Categoria(Long.MIN_VALUE,"Xis", true));
        lista_categoria.add(new Categoria(Long.MIN_VALUE,"Xis Gourmet", true));
        lista_categoria.add(new Categoria(Long.MIN_VALUE,"Xurumela", true));
       
        return lista_categoria;
    }
  














   private List<Sabores> lista_sabores() {
        List<Sabores> sabores = new ArrayList<>();
        sabores.add(new Sabores(Long.MIN_VALUE,"Calabresa","Calabresa azeitona cebola", myListCategoria().get(3)));
        sabores.add(new Sabores(Long.MIN_VALUE,"Calabresa com cebola","Calabresa azeitona cebola",myListCategoria().get(3)));
        sabores.add(new Sabores(Long.MIN_VALUE,"Calabresa sem cebola","Calabresa azeitona cebola",myListCategoria().get(3)));
        sabores.add(new Sabores(Long.MIN_VALUE,"Bacon","Calabresa azeitona cebola",myListCategoria().get(3)));
        sabores.add(new Sabores(Long.MIN_VALUE,"Alho e Oleo","Calabresa azeitona cebola",myListCategoria().get(3)));
        sabores.add(new Sabores(Long.MIN_VALUE,"Presunto e queijo","Calabresa azeitona cebola",myListCategoria().get(3)));
        sabores.add(new Sabores(Long.MIN_VALUE,"Frango e milho","Calabresa azeitona cebola",myListCategoria().get(3)));
        sabores.add(new Sabores(Long.MIN_VALUE,"Brocolis","Calabresa azeitona cebola",myListCategoria().get(3)));
        sabores.add(new Sabores(Long.MIN_VALUE,"Peperoni","Calabresa azeitona cebola",myListCategoria().get(3)));
        sabores.add(new Sabores(Long.MIN_VALUE,"Strogonoff","Calabresa azeitona cebola",myListCategoria().get(3)));
        sabores.add(new Sabores(Long.MIN_VALUE,"Catarinense","Calabresa azeitona cebola",myListCategoria().get(3)));
        sabores.add(new Sabores(Long.MIN_VALUE,"Da casa","Calabresa azeitona cebola",myListCategoria().get(3)));
        sabores.add(new Sabores(Long.MIN_VALUE,"Kalzone","Calabresa azeitona cebola",myListCategoria().get(3)));
        sabores.add(new Sabores(Long.MIN_VALUE,"Chocolate Preto","Calabresa azeitona cebola",myListCategoria().get(3)));
        sabores.add(new Sabores(Long.MIN_VALUE,"Chocolate Branco","Calabresa azeitona cebola",myListCategoria().get(3)));
        sabores.add(new Sabores(Long.MIN_VALUE,"Chocolate Afro","Calabresa azeitona cebola",myListCategoria().get(3)));
        sabores.add(new Sabores(Long.MIN_VALUE,"Cacau","Calabresa azeitona cebola",myListCategoria().get(3)));
        sabores.add(new Sabores(Long.MIN_VALUE,"Romeo e Julieta","Calabresa azeitona cebola",myListCategoria().get(3)));
        sabores.add(new Sabores(Long.MIN_VALUE,"Surprise","Calabresa azeitona cebola",myListCategoria().get(3)));
        sabores.add(new Sabores(Long.MIN_VALUE,"Alecrim e ervas proibidas","Calabresa azeitona cebola!", myListCategoria().get(3)));
        sabores.add(new Sabores(Long.MIN_VALUE,"Calabresa 21","Calabresa azeitona cebola",myListCategoria().get(3)));
        sabores.add(new Sabores(Long.MIN_VALUE,"Rota 66","Calabresa azeitona cebola",myListCategoria().get(3)));
        sabores.add(new Sabores(Long.MIN_VALUE,"Calabresa apimentada","Calabresa azeitona cebola",myListCategoria().get(3)));
        sabores.add(new Sabores(Long.MIN_VALUE,"Red Hot Chilli Peppers","Calabresa azeitona cebola",myListCategoria().get(3)));
        sabores.add(new Sabores(Long.MIN_VALUE,"Calabresa sabor do funk ostentaçao","Calabresa azeitona cebola",myListCategoria().get(3)));
        return sabores;
    }



*/