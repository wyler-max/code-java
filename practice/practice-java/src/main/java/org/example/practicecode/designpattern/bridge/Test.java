package org.example.practicecode.designpattern.bridge;

import com.example.designpattern.bridge.AppStore;
import com.example.designpattern.bridge.Camera;
import com.example.designpattern.bridge.Software;
import com.example.designpattern.bridge.Vivo;

/**
 *
 */
public class Test {
    public static void main(String[] args) {

        System.out.println("Oppo 手机：");
        Software camera = new Camera();
        Oppo oppo = new Oppo();
        oppo.setSoftware(camera);
        oppo.run();

        System.out.println("Vivo 手机：");
        Software appStore = new AppStore();
        Vivo vivo = new Vivo();
        vivo.setSoftware(appStore);
        vivo.run();
    }
}
