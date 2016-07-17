package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.Parametros;
import com.co.hsg.innventa.beans.Productos;
import com.co.hsg.innventa.beans.enums.ProcessStates;
import com.co.hsg.innventa.session.NamedQuerys;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.DualListModel;
import org.primefaces.model.TreeNode;

@Named(value = "productosController")
@ViewScoped
public class ProductosController extends AbstractController<Productos> {

    @Inject
    private MobilePageController mobilePageController;
    
    @Inject
    private ParametrosController params;
    
    @Inject
    private Navigation nav;
            
    private Productos findProduct; 
    
    private Collection<Parametros> info;
    
    private Collection<Productos> itemsForSale;
    
    private TreeNode productsTree;
    
    private DualListModel<Productos> parentProducts;
    private Productos selectedchildProduct;
    
    private String qty;
    
    public ProductosController() {
        super(Productos.class);
        actualModule = Modules.PRODUCTS;
    }

    public DualListModel<Productos> getParentProducts() {
        if(parentProducts == null){
             parentProducts = new DualListModel<Productos>(new ArrayList<>(getItemsForSale()), new ArrayList<Productos>());
        }
        return parentProducts;
    }

    public void setParentProducts(DualListModel<Productos> parentProducts) {
        this.parentProducts = parentProducts;
    }
    
    public Productos getFindProduct() {
        return findProduct;
    }

    public void setFindProduct(Productos findProduct) {
        this.findProduct = findProduct;
    }
    
    public void setSelectedChild(Productos selectedProduct){
        if(selectedProduct != null){
            selectedchildProduct = selectedProduct;
        }
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
    
    public void addQty(ActionEvent e){
        
    }
    public Productos getSelectedChild(){
        return selectedchildProduct;
    }
    
    public Collection<Productos> getItemsForSale() {
        if (itemsForSale == null || !keepList) {
            itemsForSale = this.chargeItems(NamedQuerys.PRODUCT_TYPES, "type", ProcessStates.SALES_PRODUCT.getPermanentID());
            items = null;
        }
        return itemsForSale;
    }
    
    @Override
    public Collection<Productos> getItems() {
      if (nav.getModule().equals(Modules.PRODUCTS_SUPPLY)){
          this.chargeItems(NamedQuerys.PRODUCT_TYPES, "type", ProcessStates.ELEMENT.getPermanentID()); 
      }else{
          this.chargeItems(NamedQuerys.PRODUCT_TYPES, "type", ProcessStates.SALES_PRODUCT.getPermanentID());
      }
       return items;
    }
    public boolean isProductSale(){
        return  nav.getModule().equals(Modules.PRODUCTS_SALE);
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

    @Override
    public void save(ActionEvent event) {
        if(parentProducts != null && !parentProducts.getTarget().isEmpty()){
            selected.setProductosHijos(parentProducts.getTarget());
            for(Productos p :selected.getProductosHijos()){
               p.setProductoPadre(selected);
            }
        }
        super.save(event); 
    }
     
    
    public TreeNode getProductsTree() {
        initTree();
        return productsTree;
    }
    
     public void setProductsTree(TreeNode root) {
        this.productsTree = root;
    }

    public void initTree() {
        if(selected!= null){
            productsTree = new DefaultTreeNode(new Document("Producto", "-", "Folder"), null);
            TreeNode productsTreeName = new DefaultTreeNode(new Document(selected.getNombre(), "-", "Folder"), productsTree);
            TreeNode saleProducts = new DefaultTreeNode(new Document("Componentes", "-", "Folder"), productsTreeName);
            TreeNode supplies = new DefaultTreeNode(new Document("Insumos", "-", "Folder"), productsTreeName);
            for(Productos p: selected.getProductosHijos()){
                  TreeNode expenses = new DefaultTreeNode("document", new Document(p.getNombre(), "30 KB", "Word Document"), saleProducts);
            }
            productsTree.setExpanded(true);
            productsTreeName.setExpanded(true);
            saleProducts.setExpanded(true);
            supplies.setExpanded(true);
        }
    }

}
