package com.co.hsg.innventa.backing;

import com.co.hsg.innventa.backing.util.MobilePageController;
import com.co.hsg.innventa.beans.Parametros;
import com.co.hsg.innventa.beans.Productos;
import com.co.hsg.innventa.beans.ProductosComponentes;
import com.co.hsg.innventa.beans.enums.ProcessStates;
import com.co.hsg.innventa.session.NamedQuerys;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultTreeNode;
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
    
    private Productos componentSelected;
    
    private Collection<Parametros> info;
    
    private Collection<Productos> itemsForSale;
    
    private TreeNode productsTree;
    
    private List<Productos> targetProducts;
    
    public ProductosController() {
        super(Productos.class);
        actualModule = Modules.PRODUCTS;
    }

    public List<Productos> getTargetProducts() {
        if(targetProducts == null){
             targetProducts = new ArrayList<Productos>();
        }
        return targetProducts;
    }

    public void setTargetProducts(List<Productos> targetProducts) {
        this.targetProducts = targetProducts;
    }
    
    public Productos getFindProduct() {
        return findProduct;
    }

    public void setFindProduct(Productos findProduct) {
        this.findProduct = findProduct;this.findProduct.setAtributo("1");
    }

    public void onSelect(SelectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
       // context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().toString()));
    }

    public Productos getComponentSelected() {
        return componentSelected;
    }

    public void setComponentSelected(Productos componentSelected) {
        this.componentSelected = componentSelected;
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
        if(targetProducts != null && !targetProducts.isEmpty()){
            selected.setAtributo("1");
            targetProducts.add(selected);
            for(Productos p : targetProducts){
                ProductosComponentes pc = new ProductosComponentes();
                pc.setComponente(p);
                pc.setProductoPadre(selected);
                pc.setCantidad(Short.parseShort(p.getAtributo()));
                selected.getProductosParte().add(pc);
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
            for(ProductosComponentes p: selected.getProductosHijos()){
                  TreeNode expenses = new DefaultTreeNode("document", new Document(p.getComponente().getNombre(), "30 KB", "Word Document"), saleProducts);
            }
            productsTree.setExpanded(true);
            productsTreeName.setExpanded(true);
            saleProducts.setExpanded(true);
            supplies.setExpanded(true);
        }
    }

}
