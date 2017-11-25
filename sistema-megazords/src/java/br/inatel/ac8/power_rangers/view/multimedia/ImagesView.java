package br.inatel.ac8.power_rangers.view.multimedia;
 
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
 
@ManagedBean
public class ImagesView {
     
    private List<String> images;
     
    @PostConstruct
    public void init() {
        images = new ArrayList<String>();
        for (int i = 1; i <= 5; i++) {
            images.add("power_rangers" + i + ".png");
        }
    }
 
    public List<String> getImages() {
        return images;
    }
}