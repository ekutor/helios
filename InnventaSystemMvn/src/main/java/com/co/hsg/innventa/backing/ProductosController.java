package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.Parametros;
import com.co.hsg.innventa.beans.Productos;
import com.co.hsg.innventa.beans.enums.ProcessStates;
import com.co.hsg.innventa.session.NamedQuerys;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@Named(value = "productosController")
@ViewScoped
public class ProductosController extends AbstractController<Productos> {

    @Inject
    private MobilePageController mobilePageController;
    
    @Inject
    private ParametrosController params;
            
    private Productos findProduct; 
    
    private Collection<Parametros> info;
    
    private Collection<Productos> itemsForSale;
    
    private TreeNode productsTree;
    
    public ProductosController() {
        super(Productos.class);
    }

    public Productos getFindProduct() {
        return findProduct;
    }

    public void setFindProduct(Productos findProduct) {
        this.findProduct = findProduct;
    }
    
    public Collection<Productos> getItemsForSale() {
        if (itemsForSale == null || !keepList) {
            itemsForSale = this.chargeItems(NamedQuerys.PRODUCT_STATES, "state", ProcessStates.SALES_PRODUCT.getPermanentID());
            items = null;
        }
        return itemsForSale;
    }
    /**
     * Sets the "items" attribute with a collection of PedidosProducto entities
     * that are retrieved from Productos?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for PedidosProducto page
     */
    public String navigatePedidosProductoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("PedidosProducto_items", this.getSelected().getPedidosProductoList());
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/pedidosProducto/index";
    }
    
    public String transformTipoProducto(){
       return this.transformTipoProducto(this.selected.getTipoCodigo());
    }
    
     public String transformTipoProducto(String idTipoCodigo){
        String finded = "";
        if(info == null){
            info = params.chargeItems(NamedQuerys.PRODUCT_TYPES_PARAM);
        }
        for(Parametros p : info){
            if(p.getId().equals(idTipoCodigo)){
                finded = p.getClave1();
                break;
            }
        }
        return finded;
     }
 
    public TreeNode getProductsTree() {
        return productsTree;
    }
    
     public void setProductsTree(TreeNode root) {
        this.productsTree = root;
    }
    @PostConstruct
    public void init() {
        productsTree = new DefaultTreeNode(new Document("Files", "-", "Folder"), null);
 
    }
    
    public void addChildNode(){
       
        TreeNode documents = new DefaultTreeNode(new Document("Documents", "-", "Folder"), productsTree);
        TreeNode pictures = new DefaultTreeNode(new Document("Pictures", "-", "Folder"), productsTree);
        TreeNode movies = new DefaultTreeNode(new Document("Movies", "-", "Folder"), productsTree);
         
        TreeNode work = new DefaultTreeNode(new Document("Work", "-", "Folder"), documents);
        TreeNode primefaces = new DefaultTreeNode(new Document("PrimeFaces", "-", "Folder"), documents);
         
        //Documents
        TreeNode expenses = new DefaultTreeNode("document", new Document("Expenses.doc", "30 KB", "Word Document"), work);
        TreeNode resume = new DefaultTreeNode("document", new Document("Resume.doc", "10 KB", "Word Document"), work);
        TreeNode refdoc = new DefaultTreeNode("document", new Document("RefDoc.pages", "40 KB", "Pages Document"), primefaces);
         
        //Pictures
        TreeNode barca = new DefaultTreeNode("picture", new Document("barcelona.jpg", "30 KB", "JPEG Image"), pictures);
        TreeNode primelogo = new DefaultTreeNode("picture", new Document("logo.jpg", "45 KB", "JPEG Image"), pictures);
        TreeNode optimus = new DefaultTreeNode("picture", new Document("optimusprime.png", "96 KB", "PNG Image"), pictures);
         
        //Movies
        TreeNode pacino = new DefaultTreeNode(new Document("Al Pacino", "-", "Folder"), movies);
        TreeNode deniro = new DefaultTreeNode(new Document("Robert De Niro", "-", "Folder"), movies);
         
        TreeNode scarface = new DefaultTreeNode("mp3", new Document("Scarface", "15 GB", "Movie File"), pacino);
        TreeNode carlitosWay = new DefaultTreeNode("mp3", new Document("Carlitos' Way", "24 GB", "Movie File"), pacino);
         
        TreeNode goodfellas = new DefaultTreeNode("mp3", new Document("Goodfellas", "23 GB", "Movie File"), deniro);
        TreeNode untouchables = new DefaultTreeNode("mp3", new Document("Untouchables", "17 GB", "Movie File"), deniro);
    }

}
